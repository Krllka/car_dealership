package back.Services;

import back.Entities.AbstractEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public interface ServiceInterface<E extends AbstractEntity>{
    List<E> allAccs();
    void add(E film);
    void delete(E film);
    void edit(E film);
    E getById(String id);
}
