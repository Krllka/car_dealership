package back.Services.Impl;

import back.DAO.Impl.DealershipDAO;
import back.DAO.Impl.DeliveryDAO;
import back.Entities.Dealership;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DealershipService extends AbstractService<Dealership,DealershipDAO>
        implements ServiceInterface<Dealership> {
    DealershipService(DealershipDAO dao ){
        super(dao);
    }
}
