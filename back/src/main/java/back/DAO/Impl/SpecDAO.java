package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Car;
import back.Entities.Specific;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecDAO extends AbstractDAO<Specific>
        implements DAOinterface<Specific> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Specific> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Specific").list();
    }
    @Override
    public Specific getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Specific.class ,id);
    }
}
