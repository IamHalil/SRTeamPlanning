//class for instantiating the request for the UUID

public class UUID_Request {
    private int Source_id;
    private Sender.EntityType Entity_type;
    private Sender.SourceType Source;

    public UUID_Request(int source_id, Sender.EntityType Entity_type, Sender.SourceType thisSource) {
        this.Source_id = source_id;
        this.Entity_type = Entity_type;
        this.Source = thisSource;
    }

    //    GETTERS & SETTERS
    public int getSource_id() {
        return Source_id;
    }
    public void setSource_id(int source_id) {
        Source_id = source_id;
    }

    public Sender.EntityType getEntity_type() {
        return Entity_type;
    }
    public void setEntity_type(Sender.EntityType entity_type) {
        Entity_type = entity_type;
    }

    public Sender.SourceType getSource() {
        return Source;
    }
    public void setSource(Sender.SourceType source) {
        Source = source;
    }

//    NEXT?
}
