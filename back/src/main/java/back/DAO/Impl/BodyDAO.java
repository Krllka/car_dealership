package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Body;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BodyDAO extends AbstractDAO<Body>
            implements DAOinterface<Body> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Body> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Body").list();
    }
    @Override
    public Body getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Body.class ,id);
    }
}
