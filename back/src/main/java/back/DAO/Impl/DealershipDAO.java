package back.DAO.Impl;

import back.Entities.Clients;
import back.Entities.Dealership;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DealershipDAO extends AbstractDAO<Dealership>{
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Dealership> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Dealership").list();
    }
    @Override
    public Dealership getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Dealership.class ,id);
    }
}
