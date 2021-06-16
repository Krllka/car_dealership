package back.Services.Impl;

import back.DAO.Impl.EquipDAO;
import back.DAO.Impl.ProviderDAO;
import back.Entities.Provider;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProviderService extends AbstractService<Provider, ProviderDAO>
    implements ServiceInterface<Provider> {
    ProviderService(ProviderDAO dao ){
        super(dao);
    }
}
