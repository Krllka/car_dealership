package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Body;
import back.Entities.Equip;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipDAO extends AbstractDAO<Equip>
    implements DAOinterface<Equip> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Equip> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Equip").list();
    }
    @Override
    public Equip getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Equip.class ,id);
    }
}
