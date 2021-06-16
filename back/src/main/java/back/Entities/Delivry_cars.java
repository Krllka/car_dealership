package back.Entities;

import javax.persistence.*;

@Entity
@Table(name = "delivery_has_cars")
public class Delivry_cars extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "cars_ID", foreignKey = @ForeignKey(name = "fk_delivery_has_cars_cars1"))
    private Car car;
    @ManyToOne
    @JoinColumn(name = "delivery_ID", foreignKey = @ForeignKey(name = "fk_delivery_has_cars_delivery1"))
    private Delivery deliv;
    @Column(name = "count")
    private int count;
    @Column(name = "price")
    private double price;

    public Delivry_cars(){}
    public Delivry_cars(Car car , Delivery deliv){
        this.car = car;
        this.deliv = deliv;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public void setCar(String  car) {
        this.car = new Car(car);
    }

    public Delivery getDeliv() {
        return deliv;
    }

    public void setDeliv(Delivery deliv) {
        this.deliv = deliv;
    }
    public void setDeliv(String deliv) {
        this.deliv = new Delivery(deliv);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
