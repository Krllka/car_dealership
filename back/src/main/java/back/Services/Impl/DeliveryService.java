package back.Services.Impl;

import back.DAO.Impl.DeliveryDAO;
import back.Entities.Delivery;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeliveryService extends AbstractService<Delivery, DeliveryDAO>
        implements ServiceInterface<Delivery> {
    DeliveryService(DeliveryDAO dao ){
        super(dao);
    }
}
