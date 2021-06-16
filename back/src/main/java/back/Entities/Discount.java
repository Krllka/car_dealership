package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount extends AbstractEntity{
    @Column(name = "value")
    private int value;

    public Discount(){}
    public Discount(String str){super(str);}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
