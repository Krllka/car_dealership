package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Posts extends AbstractEntity{
    @Column(name = "Name")
    String name;

    public Posts(){}
    public Posts(String str){super(str);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
