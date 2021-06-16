package back.Controllers.Impl;

import back.Entities.Order;
import back.Services.Impl.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("order")
public class OrderControleller extends AbstracrtController<Order,OrderService>{
    public OrderControleller(OrderService Service) {
        super(Service);
    }
}
