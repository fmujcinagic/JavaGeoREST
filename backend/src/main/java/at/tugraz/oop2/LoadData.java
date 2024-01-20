package at.tugraz.oop2;
import org.locationtech.jts.geom.GeometryCollection;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class LoadData {
    private Map<String, String> nodesMap;
    private Map<String, String> latMap;
    private Map<String, String> lonMap;
    private Map<String,List<String>> wayMap;
    private Map<String,List<String>> wayMapUnmodified;
    private Map<String, Object> polygonsMap;
    private Map<String, Object> wayTagsMap;
    private Map<String, Object> relationTagsMap;
    private long nodeCount;
    private long wayCount;

    private final String OSMFile;

    private Set<String> uniqueNodes;
    private Set<String> uniqueWays;
    private List<CustomRelation> relations;

    private LinkedHashMap<String, Object> nodesTagsMap;

    Map<String, GeometryCollection> relationGeometryMap;

    public LoadData(String OSMFile) {
        initMaps();
        this.OSMFile = OSMFile;
    }

    public void initData() {
        try (InputStream is = new FileInputStream(OSMFile)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamReader.START_ELEMENT && "node".equals(reader.getLocalName())) {
                    loadNodes(reader);
                }
                else if (event == XMLStreamReader.START_ELEMENT && "way".equals(reader.getLocalName())) {
                    initWays(reader);
                }
                else if (event == XMLStreamReader.START_ELEMENT && "relation".equals(reader.getLocalName()))
                {
                    loadRelation(reader);
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal issues, i.e. cannot reach the backend.", e);
        }
        nodesMap.keySet().removeAll(uniqueNodes);
        wayMap.keySet().removeAll(uniqueWays);
        setNodeCount(nodesMap.size());
        setWayCount(wayMap.size());
    }

    private void loadNodes(XMLStreamReader reader) {
        String id = reader.getAttributeValue(null, "id");
        String lat = reader.getAttributeValue(null, "lat");
        String lon = reader.getAttributeValue(null, "lon");
        latMap.put(id, lat);
        lonMap.put(id, lon);
        nodesMap.put(id, lat+lon);
        Map<String, String> localNodesMap = new LinkedHashMap<>();
        try{
            while (reader.hasNext()) {
                int innerEvent = reader.next();
                if (innerEvent == XMLStreamReader.START_ELEMENT && "tag".equals(reader.getLocalName())) {
                    String k = reader.getAttributeValue(null, "k");
                    String v = reader.getAttributeValue(null, "v");
                    localNodesMap.put(k, v);
                }
                if (innerEvent == XMLStreamReader.END_ELEMENT && "node".equals(reader.getLocalName())) {
                    break;
                }
            }
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal issues, i.e. cannot reach the backend.", e);
        }
        if(!localNodesMap.isEmpty()){
            nodesTagsMap.put(id, localNodesMap);
        }
    }
    private void loadRelation(XMLStreamReader reader) {
        String id = reader.getAttributeValue(null, "id");
        List<RelationMember> members = new ArrayList<>();
        Map<String, String> tags = new LinkedHashMap<>();
        List<String> wayForSpecificRelation = new ArrayList<>();
        try{
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamReader.START_ELEMENT) {
                    if ("member".equals(reader.getLocalName())) {
                        String type = reader.getAttributeValue(null, "type");
                        String ref = reader.getAttributeValue(null, "ref");
                        String role = reader.getAttributeValue(null, "role");
                        wayForSpecificRelation.add(ref);
                        members.add(new RelationMember(type, ref, role));
                    } else if ("tag".equals(reader.getLocalName())) {
                        String k = reader.getAttributeValue(null, "k");
                        String v = reader.getAttributeValue(null, "v");
                        tags.put(k, v);
                    }


                } else if (event == XMLStreamReader.END_ELEMENT && "relation".equals(reader.getLocalName())) {
                    if(!tags.isEmpty())
                    {
                        relationTagsMap.put(id, tags);
                    }
                    GeometryCollection geometryCollection;
                    try {
                        geometryCollection = GeometryCollectionBuild.buildGeom(members, tags, this, id);
                        relationGeometryMap.put(id, geometryCollection);
                    } catch (Exception e)
                    {
                        throw new CustomNotFoundException("The geometry cannot be built.");
                    }

                    uniqueWays.addAll(wayForSpecificRelation);
                    CustomRelation relation = new CustomRelation(id, members, tags);
                    relations.add(relation);
                    break;
                }
            }
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal issues, i.e. cannot reach the backend.", e);
        }
    }


    void initWays(XMLStreamReader reader) {
        String wayId = reader.getAttributeValue(null, "id");
        String firstRef = null, lastRef = null;
        long countValidNodes = 0;
        List<String> nodesForSpecificWay = new ArrayList<>();
        Map<String, Object> currentTags = new LinkedHashMap<>();
        try {
            while (reader.hasNext()) {
                int innerEvent = reader.next();
                if (innerEvent == XMLStreamReader.START_ELEMENT && "nd".equals(reader.getLocalName())) {
                    String ref = reader.getAttributeValue(null, "ref");
                    if (firstRef == null) { // this registers the first node (checking if polygon)
                        firstRef = ref;
                    }
                    lastRef = ref; // this updates the last node, so it can be later compared with first one
                    if (latMap.get(ref) != null && lonMap.get(ref) != null) {
                        nodesForSpecificWay.add(ref);
                        countValidNodes += 1;
                    }
                } else if (innerEvent == XMLStreamReader.START_ELEMENT && "tag".equals(reader.getLocalName())) {
                    String key = reader.getAttributeValue(null, "k");
                    String value = reader.getAttributeValue(null, "v");
                    currentTags.put(key, value);
                } else {
                    // This means that our iterator saw closing tag, i.e. </way>
                    if (innerEvent == XMLStreamReader.END_ELEMENT && "way".equals(reader.getLocalName())) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal issues, i.e. cannot reach the backend.", e);
        }
        boolean isClosed = Objects.equals(firstRef,lastRef);
        if(isClosed && countValidNodes > 2){ // this adds the way to polygon, because it is closed and has > 2 nodes
            polygonsMap.put(wayId,nodesForSpecificWay);
        }
        if(!currentTags.isEmpty()){
            wayTagsMap.put(wayId, currentTags);
        }
        uniqueNodes.addAll(nodesForSpecificWay);
        wayMap.put(wayId, nodesForSpecificWay);
        wayMapUnmodified.put(wayId,nodesForSpecificWay);
    }
    void initMaps(){
        nodesMap = new LinkedHashMap<>();
        latMap = new LinkedHashMap<>();
        lonMap = new LinkedHashMap<>();
        wayMap = new LinkedHashMap<>();
        wayMapUnmodified = new LinkedHashMap<>();
        polygonsMap = new LinkedHashMap<>();
        wayTagsMap = new LinkedHashMap<>();
        uniqueNodes = new HashSet<>();
        uniqueWays = new HashSet<>();
        relations = new ArrayList<>();
        nodesTagsMap = new LinkedHashMap<>();
        relationTagsMap = new LinkedHashMap<>();
        relationGeometryMap = new LinkedHashMap<>();
    }
    public Map<String, String> getNodesMap() {
        return nodesMap;
    }

    public Map<String, Object> getRelationTagsMap() {
        return relationTagsMap;
    }


    public Map<String, String> getLatMap() {
        return latMap;
    }

    public Map<String, List<String>> getWayMapUnmodified() {
        return wayMapUnmodified;
    }

    public Map<String, String> getLonMap() {
        return lonMap;
    }
    public Map<String, List<String>> getWayMap() {
        return wayMap;
    }
    public Map<String, Object> getPolygonsMap() {
        return polygonsMap;
    }
    public Map<String, Object> getWayTagsMap() {
        return wayTagsMap;
    }
    public long getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(long nodeCount) {
        this.nodeCount = nodeCount;
    }

    public long getWayCount() {
        return wayCount;
    }

    public void setWayCount(long wayCount) {
        this.wayCount = wayCount;
    }
    public List<CustomRelation> getRelations() {
        return relations;
    }
    public Map<String, Object> getNodesTagsMapMap(){
        return nodesTagsMap;
    }

    public Map<String, GeometryCollection> getRelationGeometryMap() {
        return relationGeometryMap;
    }
}
