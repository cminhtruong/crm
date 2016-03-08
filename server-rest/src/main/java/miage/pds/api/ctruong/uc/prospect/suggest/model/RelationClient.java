package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by Truong on 2/6/2016.
 */
@Entity("relation")
public class RelationClient {

    @Id
    private ObjectId id;

    @Property
    private ObjectId clientId;

    @Property
    private ObjectId prospectId;

    public RelationClient() {
    }

    public RelationClient(ObjectId clientId, ObjectId prospectId) {
        this.clientId = clientId;
        this.prospectId = prospectId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    public ObjectId getProspectId() {
        return prospectId;
    }

    public void setProspectId(ObjectId prospectId) {
        this.prospectId = prospectId;
    }

    @Override
    public String toString() {
        return "RelationClient{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", prospectId=" + prospectId +
                '}';
    }
}
