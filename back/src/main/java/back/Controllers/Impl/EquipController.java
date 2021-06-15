package back.Controllers.Impl;

import back.Entities.Equip;
import back.Services.Impl.EquipService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("equip")
public class EquipController extends AbstracrtController<Equip, EquipService>{
    public EquipController(EquipService Service) {
        super(Service);
    }
}
