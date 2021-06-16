package back.Services.Impl;

import back.DAO.Impl.OrderDAO;
import back.Entities.Order;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService extends AbstractService<Order, OrderDAO>
        implements ServiceInterface<Order> {
    OrderService(OrderDAO dao ){
        super(dao);
    }
}
