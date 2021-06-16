package back.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery")
public class Delivery extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "provider_ID", foreignKey = @ForeignKey(name = "fk_delivery_provider1"))
    private Provider prov;
    @Column(name = "date")
    private Date date;

    transient private List<Car> cars;

    public Delivery(){}
    public Delivery(String prov){
        super(prov);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    public void addCars(Car car) {
        if(this.cars == null)
            this.cars = new ArrayList<Car>();
        this.cars.add(car);
    }

    public Provider getProv() {
        return prov;
    }

    public void setProv(Provider prov) {
        this.prov = prov;
    }
    public void setProv(String prov) {
        this.prov = new Provider(prov);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
