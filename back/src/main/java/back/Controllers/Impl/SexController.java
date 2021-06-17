package back.Controllers.Impl;

import back.Entities.Sex;
import back.Services.Impl.ProviderService;
import back.Services.Impl.SexService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("sex")
public class SexController extends AbstracrtController<Sex, SexService>{
    public SexController(SexService Service) {
        super(Service);
    }
}
