package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Equip;
import back.Entities.Provider;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProviderDAO extends AbstractDAO<Provider>
    implements DAOinterface<Provider> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Provider> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Provider").list();
    }
    @Override
    public Provider getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Provider.class ,id);
    }
}
