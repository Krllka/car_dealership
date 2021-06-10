package back.Services.Impl;

import back.DAO.Impl.BodyDAO;
import back.Entities.Body;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class BodyService extends AbstractService<Body, BodyDAO>
        implements ServiceInterface<Body> {
    BodyService(BodyDAO dao ){
        super(dao);
    }
}
