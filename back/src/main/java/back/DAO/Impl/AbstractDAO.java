package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.MappedSuperclass;
import java.util.List;
@MappedSuperclass
public class AbstractDAO<E extends AbstractEntity>  implements DAOinterface<E> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory.getObject();
    }

    @Override
    public List<E> allAccs() {
        Session session = sessionFactory.getCurrentSession();
        List<E>  list = session.createQuery("from "+ AbstractEntity.class).list();
        return list;
    }

    @Override
    public void add(E film) {
        Session session = sessionFactory.getCurrentSession();
        session.save(film);
    }

    @Override
    public void delete(E film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);
    }

    @Override
    public void edit(E film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
    }
    @Override
    public E getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        E get = (E) session.get(AbstractEntity.class, id);
        return get;
    }

    public List<E> readByLogin(@PathVariable("name") String login) {
        Session session = sessionFactory.getCurrentSession();
        List<E>  list = session.createQuery("from "+ AbstractEntity.class).list();
        return list;
    }
}
