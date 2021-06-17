package back.Services.Impl;

import back.DAO.Impl.DiscountDAO;
import back.Entities.Discount;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DiscountService extends AbstractService<Discount, DiscountDAO> {
    DiscountService(DiscountDAO dao ){
        super(dao);
    }
}
