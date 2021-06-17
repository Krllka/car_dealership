package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Order;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class OrderDAO extends AbstractDAO<Order>
        implements DAOinterface<Order> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Order> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Order").list();
    }
    @Override
    public void add(Order film) {
        Session session = sessionFactory.getCurrentSession();
        film.setDate(new Date(System.currentTimeMillis()));
        film.setPrice(film.getCar().getPrice());
        session.save(film);
    }


    @Override
    public Order getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Order.class ,id);
    }
}
