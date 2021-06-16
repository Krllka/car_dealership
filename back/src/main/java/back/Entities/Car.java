package back.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cars")
public class Car extends AbstractEntity {
    @Column(name = "Name")
    String name;
    @Column(name = "price")
    double price;
    @Column(name = "description")
    String description;
    @ManyToOne
    @JoinColumn(name = "Body_ID", foreignKey = @ForeignKey(name = "fk_cars_Body"))
    private Body body;

    transient private List<Specific> specs;
    transient private List<Equip> equip;
    transient private double del_price;
    transient private int count;

    public double getDel_price() {
        return del_price;
    }

    public void setDel_price(double del_price) {
        this.del_price = del_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Car(){}
    public Car(String s){super(s);}

    public List<Equip> getEquip() {
        return equip;
    }

    public void setEquip(List<Equip> equip) {
        this.equip = equip;
    }
    public void addEquip(Equip equip) {
        if(this.equip == null)
            this.equip = new ArrayList<Equip>();
        this.equip.add(equip);
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public List<Specific> getSpecs() {
        return specs;
    }
    public void addSpecs(Specific spec) {
        if(this.specs == null)
            this.specs = new ArrayList<Specific>();
        this.specs.add(spec);
    }

    public void setSpecs(List<Specific> specs) {
        this.specs = specs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = new Body(body);
    }
}
