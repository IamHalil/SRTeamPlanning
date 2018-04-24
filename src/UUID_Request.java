public class UUID_Request {
    private int Source_id;

    public enum EntityType {visitor, admin, responsible};
    public enum MessageSource {Front_End, Planning, Monitoring, Kassa, CRM, Facturatie};

    private EntityType Entity_type;
    private MessageSource Source;

    public UUID_Request(int source_id, EntityType Entity_type, MessageSource thisMessageSource) {
        this.Source_id = source_id;
        this.Entity_type = Entity_type;
        this.Source = thisMessageSource;
    }

    //    GETTERS & SETTERS
    public int getSource_id() {
        return Source_id;
    }
    public void setSource_id(int source_id) {
        Source_id = source_id;
    }

    public EntityType getEntity_type() {
        return Entity_type;
    }
    public void setEntity_type(EntityType entity_type) {
        Entity_type = entity_type;
    }

    public MessageSource getSource() {
        return Source;
    }
    public void setSource(MessageSource source) {
        Source = source;
    }

//    NEXT?
}
