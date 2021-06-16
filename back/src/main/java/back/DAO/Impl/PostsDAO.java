package back.DAO.Impl;

import back.Entities.Clients;
import back.Entities.Posts;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostsDAO extends AbstractDAO<Posts> {
    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Posts> allAccs() {
        Session session = super.sessionFactory.getCurrentSession();
        return session.createQuery("from Posts").list();
    }
    @Override
    public Posts getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Posts.class ,id);
    }
}
