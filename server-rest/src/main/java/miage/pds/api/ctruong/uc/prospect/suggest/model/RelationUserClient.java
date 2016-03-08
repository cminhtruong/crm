package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by Truong on 2/6/2016.
 */
@Entity("relation_user_client")
public class RelationUserClient {

    @Id
    private ObjectId id;

    @Property
    private ObjectId clientId;

    @Property
    private String userId;

    public RelationUserClient() {
    }

    public RelationUserClient(ObjectId clientId, String userId) {
        this.clientId = clientId;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RelationUserClient{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
