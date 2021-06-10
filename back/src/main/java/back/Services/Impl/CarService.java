package back.Services.Impl;

import back.DAO.Impl.CarDAO;
import back.Entities.Car;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarService extends AbstractService<Car, CarDAO>
        implements ServiceInterface<Car> {
    CarService(CarDAO dao ){
        super(dao);
    }
}
