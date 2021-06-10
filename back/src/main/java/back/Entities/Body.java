package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "body")
public class Body extends AbstractEntity{
    @Column(name = "Name")
    String name;

    public  Body(){}
    public  Body(String str){ super(str);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
