package back.Controllers.Impl;

import back.Entities.Specific;
import back.Services.Impl.CarService;
import back.Services.Impl.SpecSerivce;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("spec")
public class SpecController extends AbstracrtController<Specific, SpecSerivce>{
    public SpecController(SpecSerivce Service) {
        super(Service);
    }
}
