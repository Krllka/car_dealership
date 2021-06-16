package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.Equip;
import back.Entities.Worker;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDAO extends AbstractDAO<Worker>
        implements DAOinterface<Worker> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Worker> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Worker").list();
    }
    @Override
    public Worker getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Worker.class ,id);
    }
}
