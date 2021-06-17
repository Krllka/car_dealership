package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Sex;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SexDAO extends AbstractDAO<Sex>
    implements DAOinterface<Sex> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Sex> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Sex").list();
    }
    @Override
    public Sex getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Sex.class ,id);
    }
}
