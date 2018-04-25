//class for instantiating the response for the UUID request

//https://stackoverflow.com/a/8345074
import java.text.SimpleDateFormat;
import java.util.Date;

public class UUID_Response {
    private int Source_id;
    private String UUID;
    private int Entity_version;

    private Sender.EntityType Entity_type;
    private Sender.SourceType Source;

    public UUID_Response(UUID_Request thisUUID_Request)
    {
        this.Source_id = thisUUID_Request.getSource_id();
        this.Entity_type = thisUUID_Request.getEntity_type();
        this.Source = thisUUID_Request.getSource();
        this.Entity_version=1;

        //dummy value
        this.UUID=getNewUUID("");
    }
    public UUID_Response(int source_id, Sender.EntityType Entity_type, Sender.SourceType thisSourceType) {
        this.Source_id = source_id;
        this.Entity_type = Entity_type;
        this.Source = thisSourceType;
        this.Entity_version=1;
        //dummy value
        this.UUID=getNewUUID("");
    }

    //    GETTERS & SETTERS
    public int getSource_id() {
        return Source_id;
    }
    public void setSource_id(int source_id) {
        Source_id = source_id;
    }

    public String getUUID() {
        return UUID;
    }
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getEntity_version() {
        return Entity_version;
    }
    public void setEntity_version(int entity_version) {
        Entity_version = entity_version;
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

    public String getNewUUID(String test) {
        //dummy message
        return "<123UUID321>";

    }

    //https://stackoverflow.com/a/8345074
    public static String getCurrentDateTimeStamp()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }

    @Override
    public String toString() {
        String s = "\n\n[UUID_RESPONSE tostring()]: \n";
        s+= "{\n 'Source_id' : '"+this.getSource_id()+ "' ,\n ";
        s+= " 'UUID' : '"+this.getUUID()+ " ,\n ";
        s+= " 'Entity_version' : '"+this.getEntity_version()+ " ,\n ";
        s+= " 'Entity_type' : '"+this.getEntity_type()+ " , \n ";
        s+= " 'Source' : '"+this.getSource()+ " \n } \n DaTiSt: " + getCurrentDateTimeStamp();

        return s;
    }

}
