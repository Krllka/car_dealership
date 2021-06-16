package back.Services.Impl;

import back.DAO.Impl.EquipDAO;
import back.DAO.Impl.PostsDAO;
import back.Entities.Posts;
import back.Services.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostsService extends AbstractService<Posts, PostsDAO>
    implements ServiceInterface<Posts> {
    PostsService(PostsDAO dao ){
        super(dao);
    }
}
