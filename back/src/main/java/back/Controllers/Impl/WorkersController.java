package back.Controllers.Impl;

import back.Entities.Worker;
import back.Services.Impl.WorkerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("worker")
public class WorkersController extends AbstracrtController<Worker, WorkerService>{
    public WorkersController(WorkerService Service) {
        super(Service);
    }
}
