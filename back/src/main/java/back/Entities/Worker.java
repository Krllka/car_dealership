package back.Entities;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker extends AbstractEntity{
    @Column(name = "Name")
    String name;
    @ManyToOne
    @JoinColumn(name = "posts_ID", foreignKey = @ForeignKey(name = "fk_workers_posts1"))
    private Posts post;
    @ManyToOne
    @JoinColumn(name = "car_dealership_ID", foreignKey = @ForeignKey(name = "fk_workers_car_dealership1"))
    private Dealership dealer;
    @ManyToOne
    @JoinColumn(name = "Sex_ID", foreignKey = @ForeignKey(name = "fk_workers_Sex1"))
    private Sex sex;
    @Column(name = "passport")
    String passport;

    public Worker(){}
    public Worker(String str){super(str);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public void setPost(String post) {
        this.post = new Posts(post);
    }
    public Dealership getDealer() {
        return dealer;
    }

    public void setDealer(Dealership dealer) {
        this.dealer = dealer;
    }
    public void setDealer(String dealer) {
        this.dealer = new Dealership(dealer);
    }


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setSex(String sex) {
        this.sex = new Sex(sex);
    }


    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
