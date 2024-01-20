package at.tugraz.oop2;


public class RelationMember {
    private String type;
    private String ref;
    private String role;

    public RelationMember(String type, String ref, String role) {
        this.type = type;
        this.ref = ref;
        this.role = role;
    }
    @Override
    public String toString() {
        return "RelationMember{" +
                "type='" + type + '\'' +
                ", ref='" + ref + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public String getRef() {
        return ref;
    }

    public String getRole() {
        return role;
    }
}
