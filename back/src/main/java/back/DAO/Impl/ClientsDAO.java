package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Body;
import back.Entities.Clients;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientsDAO extends AbstractDAO<Clients>
        implements DAOinterface<Clients> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Clients> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Clients").list();
    }
    @Override
    public Clients getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Clients.class ,id);
    }
}
