package back.Controllers.Impl;

import back.Entities.Dealership;
import back.Services.Impl.DealershipService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("saloon")
public class DealershipController extends AbstracrtController<Dealership, DealershipService>{
    public DealershipController(DealershipService Service) {
        super(Service);
    }
}
