package back.Services.Impl;

import back.DAO.Impl.CarDAO;
import back.DAO.Impl.SpecDAO;
import back.Entities.Specific;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class SpecSerivce extends AbstractService<Specific, SpecDAO>
        implements ServiceInterface<Specific> {
    SpecSerivce(SpecDAO dao ){
        super(dao);
    }
}
