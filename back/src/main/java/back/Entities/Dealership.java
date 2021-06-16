package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_dealership")
public class Dealership extends AbstractEntity{
    @Column(name = "Name")
    String name;
    @Column(name = "Address")
    String address;


    public  Dealership(){}
    public  Dealership(String str){ super(str);}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
