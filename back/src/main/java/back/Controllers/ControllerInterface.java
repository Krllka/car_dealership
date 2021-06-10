package back.Controllers;

import back.Entities.AbstractEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControllerInterface <E extends AbstractEntity>{
    @GetMapping
    public List<E> allAccs();
    @GetMapping("/{id}")
    public E read(@PathVariable("id") String id);
    @PostMapping
    public E create(@RequestBody E inputAcc);
    @PutMapping("/{id}")
    public E edit( @PathVariable("id") String id, @RequestBody E editAcc);
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id);
}
