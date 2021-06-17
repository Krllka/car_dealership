package back.Controllers.Impl;

import back.Entities.Discount;
import back.Services.Impl.DiscountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("discount")
public class DiscountController extends AbstracrtController<Discount, DiscountService> {
    public DiscountController(DiscountService Service) {
        super(Service);
    }
}
