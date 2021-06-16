package back.Controllers.Impl;

import back.Entities.Clients;
import back.Services.Impl.ClientsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("clients")
public class ClientsController extends AbstracrtController<Clients, ClientsService>{
    public ClientsController(ClientsService Service) {
        super(Service);
    }
}
