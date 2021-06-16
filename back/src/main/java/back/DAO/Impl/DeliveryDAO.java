package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DeliveryDAO extends AbstractDAO<Delivery>
    implements DAOinterface<Delivery> {
     @Autowired
     public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
         super.setSessionFactory(sessionFactory);
     }

     @Override
     public void add(Delivery car) {
         Session session = sessionFactory.getCurrentSession();
         car.setDate(new Date(System.currentTimeMillis()));
         session.save(car);
         if( car.getCars() != null )
             for (Car item : car.getCars()) {
                 Delivry_cars save = new Delivry_cars(item, car);
                 save.setPrice(item.getDel_price());
                 save.setCount(item.getCount());
                 session.save(save);
             }

     }
     @Override
     public void delete(Delivery item) {
         Session session = sessionFactory.getCurrentSession();
         session.delete(item);
     }

     @Override
     public List<Delivery> allAccs() {
         Session session = super.sessionFactory.getCurrentSession();
         List<Delivery> list = session.createQuery("From Delivery").list();
         String currID;
         for (Delivery item: list) {
             currID = item.getId();
             item.setCars(new ArrayList<Car>());
             Query<Delivry_cars> query  = session.createQuery("from Delivry_cars o where o.deliv.id = :currID");
             query.setParameter("currID", currID);
             List<Delivry_cars> arr = query.list();
             for (Delivry_cars spec: arr) {
                 Car save = spec.getCar();
                 save.setDel_price(spec.getPrice());
                 save.setCount(spec.getCount());
                 item.addCars(save);
             }

         }
         return list;
     }
     @Override
     public Delivery getById(String id) {
         Session session = sessionFactory.getCurrentSession();
         Delivery prod = session.get(Delivery.class ,id);

         if(prod == null) return null;
         prod.setCars(new ArrayList<Car>());

         Query<Delivry_cars> query  = session.createQuery("from Delivry_cars o where o.car.id = :currID");
         query.setParameter("currID", id);
         List<Delivry_cars> arr = query.list();
         for (Delivry_cars spec: arr) {
             Car save = spec.getCar();
             save.setDel_price(spec.getPrice());
             save.setCount(spec.getCount());
             prod.addCars(save);
         }

         return prod;
     }

     @Override
     public void edit(Delivery delivery) {
         Session session = sessionFactory.getCurrentSession();
         Query qu = session.createSQLQuery("DELETE  FROM delivery_has_cars WHERE delivery_ID = :id" );
         qu.setParameter("id", Integer.valueOf(delivery.getId()));
         qu.executeUpdate();
         

         if( delivery.getCars() != null )
             for (Car item : delivery.getCars()) {
                 Delivry_cars car_spec =  new Delivry_cars(item, delivery);
                 car_spec.setCount(item.getCount());
                 car_spec.setPrice(item.getDel_price());
                 session.save(car_spec);
             }
         session.merge(delivery);
     }
}
