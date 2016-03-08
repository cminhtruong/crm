package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

/**
 * Created by Truong on 2/6/2016.
 */
@Entity("sales")
public class Sales {

    @Id
    private ObjectId id;

    @Property
    private ObjectId prospectId;

    @Property
    private Date date;

    @Property
    private long value;

    public Sales() {

    }

    public Sales(ObjectId prospectId, Date date, long value) {
        this.prospectId = prospectId;
        this.date = date;
        this.value = value;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProspectId() {
        return prospectId;
    }

    public void setProspectId(ObjectId prospectId) {
        this.prospectId = prospectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", prospectId='" + prospectId + '\'' +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
