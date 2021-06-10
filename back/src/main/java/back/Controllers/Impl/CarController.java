package back.Controllers.Impl;

import back.Entities.Car;
import back.Services.Impl.CarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("car")
public class CarController extends AbstracrtController<Car, CarService>{
    public CarController(CarService Service) {
        super(Service);
    }
}
