package in.tharun.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.tharun.entity.CommentEntity;
import in.tharun.entity.PostEntity;
import in.tharun.entity.UserEntity;

public interface CommtRepo extends JpaRepository<CommentEntity, Integer> {
      public List<CommentEntity> findByPost(PostEntity e);
      
      @Query("SELECT c FROM CommentEntity c JOIN c.post p WHERE p.userid = :userid")
      public List<CommentEntity> findCommentsByUserid(UserEntity userid);
}
