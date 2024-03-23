package in.tharun.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.tharun.entity.PostEntity;
import in.tharun.entity.UserEntity;
import in.tharun.service.Post;

public interface PostRepo extends JpaRepository<PostEntity,Integer>
{
      public List<PostEntity> findByUserid(UserEntity userid);
//      @Query("SELECT p FROM PostEntity p WHERE p.userId = :userId")
//      List<PostEntity> findByUserId(@Param("userId") Integer userId);
      
      
}
