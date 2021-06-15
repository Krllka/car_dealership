package back.DAO.Impl;

import back.DAO.DAOinterface;
import back.Entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDAO extends AbstractDAO<Car>
            implements DAOinterface<Car> {
        @Autowired
        public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
                super.setSessionFactory(sessionFactory);
        }

        @Override
        public void add(Car car) {
                Session session = sessionFactory.getCurrentSession();
                session.save(car);
                if( car.getSpecs() != null )
                      for (Specific item : car.getSpecs()) {
                         Car_Spec save = new Car_Spec(item, car);
                         save.setValue(item.getValue());
                              System.out.println("-----");
                         System.out.println(item.getValue());
                              System.out.println("-----");
                         session.save(save);
                      }
                if( car.getEquip() != null )
                     for (Equip item : car.getEquip()) {
                         System.out.println("-----");
                         session.save(new Car_Equip(item, car));
                     }

        }
        @Override
        public void delete(Car item) {
                Session session = sessionFactory.getCurrentSession();
                session.delete(item);
        }

        @Override
        public List<Car> allAccs() {
                Session session = super.sessionFactory.getCurrentSession();
                List<Car> list = session.createQuery("From Car").list();
                String currID;
                for (Car item: list) {
                        currID = item.getId();
                        item.setSpecs(new ArrayList<Specific>());
                        Query<Car_Spec> query  = session.createQuery("from Car_Spec o where o.car.id = :currID");
                        query.setParameter("currID", currID);
                        List<Car_Spec> arr = query.list();
                        for (Car_Spec spec: arr) {
                                Specific save = spec.getSpec();
                                save.setValue(spec.getValue());
                                item.addSpecs(save);
                        }

                }
                return list;
        }
        @Override
        public Car getById(String id) {
                Session session = sessionFactory.getCurrentSession();
                Car prod = session.get(Car.class ,id);

                if(prod == null) return null;
                prod.setSpecs(new ArrayList<Specific>());

                String currID = prod.getId();
                prod.setSpecs(new ArrayList<Specific>());

               Query<Car_Spec> query  = session.createQuery("from Car_Spec o where o.car.id = :currID");
               query.setParameter("currID", currID);
               List<Car_Spec> arr = query.list();
               for (Car_Spec spec: arr) {
                       Specific save = spec.getSpec();
                       save.setValue(spec.getValue());
                       prod.addSpecs(save);
               }
//
                Query<Car_Equip> query2  = session.createQuery("from Car_Equip o where o.car.id = :currID");
                query2.setParameter("currID", currID);
                List<Car_Equip> arr2 = query2.list();
                for (Car_Equip spec: arr2) {
                    prod.addEquip(spec.getEquip());
                }
                return prod;
        }

        @Override
        public void edit(Car film) {
                Session session = sessionFactory.getCurrentSession();
                Query qu = session.createSQLQuery("DELETE  FROM cars_has_specifications WHERE cars_ID = :id" );
                qu.setParameter("id", Integer.valueOf(film.getId()));
                qu.executeUpdate();
                qu = session.createSQLQuery("DELETE  FROM cars_has_equipment WHERE cars_ID = :id" );
                qu.setParameter("id", Integer.valueOf(film.getId()));
                qu.executeUpdate();

                if( film.getSpecs() != null )
                  for (Specific item : film.getSpecs()) {
                        Car_Spec car_spec =  new Car_Spec(item, film);
                        car_spec.setValue(item.getValue());
                        session.save(car_spec);
                  }
                if( film.getEquip() != null )
                  for (Equip item : film.getEquip()) {
                      Car_Equip car_spec =  new Car_Equip(item, film);
                      session.save(car_spec);
                  }


                session.merge(film);
        }
}
