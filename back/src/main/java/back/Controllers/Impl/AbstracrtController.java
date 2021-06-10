package back.Controllers.Impl;

import back.Controllers.ControllerInterface;
import back.Entities.AbstractEntity;
import back.Services.ServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstracrtController <E extends AbstractEntity, S extends ServiceInterface<E>>
        implements ControllerInterface<E> {
    protected S Service;

    @Autowired
    public AbstracrtController(S Service) {
        this.Service = Service;
    }
    @GetMapping
    public List<E> allAccs() {
        return Service.allAccs();
    }
    @GetMapping("/{id}")
    public E read(@PathVariable("id") String id) {
        return Service.getById(id);
    }
    @PostMapping
    public E create(@RequestBody E inputAcc ) {
        try{
        Service.add(inputAcc);
        return inputAcc;

          }catch (Exception ex){

               return null;
          }
    }
    @PutMapping("/{id}")
    public E edit(@PathVariable("id") String id,   E editAcc) {
        E save = null;
        try{
            save = Service.getById(id);
            BeanUtils.copyProperties(editAcc, save, "id");
            Service.edit(save);
        }catch (IllegalArgumentException ex){

        }

        return save;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        try{
            Service.delete(Service.getById(id));
        }catch(IllegalArgumentException ex){

        }
    }
}
