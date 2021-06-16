package back.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Clients extends AbstractEntity{
    @Column(name = "Name")
    String name;
    @Column(name = "passport")
    String passport;

    public  Clients(){}
    public  Clients(String str){ super(str);}

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
