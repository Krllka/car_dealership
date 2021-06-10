package back.Entities;

import javax.persistence.*;

@Entity
@Table(name = "cars_has_equipment")
public class Car_Spec extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "cars_ID", foreignKey = @ForeignKey(name = "fk_cars_has_equipment_cars1"))
    private Car car;
    @ManyToOne
    @JoinColumn(name = "specifications_ID", foreignKey = @ForeignKey(name = "fk_cars_has_specifications_specifications1"))
    private Specific spec;
    @Column(name = "value")
    String value;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCar(String car) {
        this.car = new Car(car);
    }

    public Specific getSpec() {
        return spec;
    }

    public void setSpec(Specific spec) {
        this.spec = spec;
    }
    public void setSpec(String spec) {
        this.spec = new Specific(spec);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
