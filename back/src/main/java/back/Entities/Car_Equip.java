package back.Entities;

import javax.persistence.*;

@Table(name = "cars_has_equipment")
@Entity
public class Car_Equip extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "cars_ID", foreignKey = @ForeignKey(name = "fk_cars_has_equipment_cars1"))
    private Car car;
    @ManyToOne
    @JoinColumn(name = "equipment_ID", foreignKey = @ForeignKey(name = "fk_cars_has_equipment_equipment1"))
    private Equip equip;

    public Car_Equip(){}
    public Car_Equip(Equip spec, Car car){
        this.car = car;
        this.equip = spec;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public void setCar(String car) {
        this.car = new Car(car);
    }
    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }
    public void setEquip(String equip) {
        this.equip = new Equip(equip);
    }
}
