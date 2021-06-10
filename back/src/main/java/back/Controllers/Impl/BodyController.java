package back.Controllers.Impl;

import back.Entities.Body;
import back.Services.Impl.BodyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("body")
public class BodyController extends AbstracrtController<Body, BodyService>{
    public BodyController(BodyService Service) {
        super(Service);
    }
}
