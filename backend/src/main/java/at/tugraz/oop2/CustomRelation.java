package at.tugraz.oop2;
import java.util.*;

public class CustomRelation {
    private String id;
    private List<RelationMember> members;
    private Map<String, String> tags;

    public CustomRelation(String id, List<RelationMember> members, Map<String, String> tags) {
        this.id = id;
        this.members = members;
        this.tags = tags;
    }
    public String getId() {
        return id;
    }
    public List<RelationMember> getMembers() {
        return members;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}

