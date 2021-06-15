package back.Services.Impl;

import back.DAO.Impl.EquipDAO;
import back.Entities.Equip;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EquipService extends AbstractService<Equip, EquipDAO>{
    EquipService(EquipDAO dao ){
        super(dao);
    }
}
