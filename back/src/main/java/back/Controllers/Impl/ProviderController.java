package back.Controllers.Impl;

import back.Entities.Provider;
import back.Services.Impl.EquipService;
import back.Services.Impl.ProviderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("provider")
public class ProviderController extends AbstracrtController<Provider, ProviderService>{
    public ProviderController(ProviderService Service) {
        super(Service);
    }
}
