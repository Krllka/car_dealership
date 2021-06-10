package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specifications")
public class Specific extends AbstractEntity{
    @Column(name = "Name")
    String name;

    public Specific(){}
    public Specific(String s){super(s);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
