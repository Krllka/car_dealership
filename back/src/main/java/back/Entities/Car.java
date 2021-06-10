package back.Entities;

import javax.persistence.*;


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

    public Car(){}
    public Car(String s){super(s);}
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
