package miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by tlacouque on 16/12/2015.
 */

@Entity("purchasingcentral")
public class PurchasingCentral {

    @Id
    int id;

    @Property
    String name;

    @Property
    String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
