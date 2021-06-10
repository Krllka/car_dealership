package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Car;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAO extends AbstractDAO<Car>
            implements DAOinterface<Car> {
@Autowired
public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
        }
@Override
public List<Car> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Car").list();
        }
@Override
public Car getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Car.class ,id);
        }
}
