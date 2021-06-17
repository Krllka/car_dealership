package back.DAO.Impl;

import back.Entities.Discount;
import back.Entities.Provider;
import back.Services.Impl.DiscountService;
import back.DAO.DAOinterface;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DiscountDAO extends AbstractDAO<Discount>
        implements DAOinterface<Discount>{
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Discount> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Discount").list();
    }
    @Override
    public Discount getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Discount.class ,id);
    }
}
