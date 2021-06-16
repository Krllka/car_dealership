package back.Controllers.Impl;


import back.DAO.Impl.DeliveryDAO;
import back.Entities.Delivery;
import back.Services.Impl.DeliveryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("delivery")
public class DeliveryController extends AbstracrtController<Delivery, DeliveryService> {
    public DeliveryController(DeliveryService Service) {
        super(Service);
    }
}
