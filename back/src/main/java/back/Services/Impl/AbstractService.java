package back.Services.Impl;

import back.DAO.Impl.AbstractDAO;
import back.Entities.AbstractEntity;
import back.Services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;
import java.util.List;

@MappedSuperclass
public abstract class AbstractService<E extends AbstractEntity, R extends AbstractDAO<E>>
        implements ServiceInterface<E> {

    protected R repo;

    @Autowired
    public AbstractService(R repo) {
        this.repo = repo;
    }

    @Transactional
    public List<E> allAccs() {
        return repo.allAccs();
    }


    @Transactional
    public void add(E  ent) {
        repo.add(ent);
    }


    @Transactional
    public void delete(E  ent) {
        repo.delete(ent);
    }


    @Transactional
    public void edit(E ent) {
        repo.edit(ent);
    }

    @Transactional
    public E getById(String id){
        return repo.getById(id);
    }

}
