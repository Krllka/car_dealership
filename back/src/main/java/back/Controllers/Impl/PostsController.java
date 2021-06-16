package back.Controllers.Impl;

import back.Entities.Posts;
import back.Services.Impl.EquipService;
import back.Services.Impl.PostsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("posts")
public class PostsController extends AbstracrtController<Posts, PostsService>{
    public PostsController(PostsService Service) {
        super(Service);
    }
}
