package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Sex")
public class Sex extends AbstractEntity{
    @Column(name = "Name")
    String name;

    public Sex(){}
    public Sex(String str){super(str);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
