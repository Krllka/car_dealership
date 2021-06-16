package back.Services.Impl;

import back.DAO.Impl.ClientsDAO;
import back.Entities.Clients;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientsService extends AbstractService<Clients, ClientsDAO>
        implements ServiceInterface<Clients> {
    ClientsService(ClientsDAO dao ){
        super(dao);
    }
}
