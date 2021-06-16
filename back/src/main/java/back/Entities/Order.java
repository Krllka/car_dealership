package back.Entities;

import javax.persistence.*;

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
}
