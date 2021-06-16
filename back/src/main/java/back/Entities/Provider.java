package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "provider")
public class Provider extends AbstractEntity{
    @Column(name = "Name")
    String name;

    public Provider(){}
    public Provider(String s){super(s);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
