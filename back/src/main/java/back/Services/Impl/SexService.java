package back.Services.Impl;

import back.DAO.Impl.PostsDAO;
import back.DAO.Impl.SexDAO;
import back.Entities.Sex;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SexService extends AbstractService<Sex, SexDAO>{
    SexService(SexDAO dao ){
        super(dao);
    }
}
