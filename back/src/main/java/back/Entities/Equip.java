package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equip extends AbstractEntity{
    @Column(name = "Name")
    String name;

    public Equip(){}
    public Equip(String s){super(s);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
