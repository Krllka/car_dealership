package back.Services.Impl;

import back.DAO.Impl.WorkerDAO;
import back.Entities.Worker;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WorkerService extends AbstractService<Worker, WorkerDAO>
        implements ServiceInterface<Worker> {
    WorkerService(WorkerDAO dao ){
        super(dao);
    }

}
