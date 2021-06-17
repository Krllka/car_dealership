package back.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
public class Order extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "cars_ID", foreignKey = @ForeignKey(name = "fk_orders_cars1"))
    private Car car;
    @ManyToOne
    @JoinColumn(name = "clients_ID", foreignKey = @ForeignKey(name = "fk_orders_clients1"))
    private Clients client;
    @ManyToOne
    @JoinColumn(name = "Discount_ID", foreignKey = @ForeignKey(name = "fk_orders_Discount1"))
    private Discount disc;
    @ManyToOne
    @JoinColumn(name = "workers_ID", foreignKey = @ForeignKey(name = "fk_orders_workers1"))
    private Worker worker;
    @Column(name = "date")
    private Date date;
    @Column(name = "price")
    private double price;


    public Order(){}
    public Order(String str){super(str);}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
    public void setClient(String client) {
        this.client = new Clients(client);
    }

    public Discount getDisc() {
        return disc;
    }

    public void setDisc(Discount disc) {
        this.disc = disc;
    }
    public void setDisc(String disc) {
        this.disc = new Discount(disc);
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public void setWorker(String worker) {
        this.worker = new Worker(worker);
    }
}
