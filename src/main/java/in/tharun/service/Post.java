package in.tharun.service;

import java.util.List;

import in.tharun.binding.AddPost;
import in.tharun.binding.CommentForm;
import in.tharun.binding.Filter;
import in.tharun.entity.CommentEntity;
import in.tharun.entity.PostEntity;

public interface Post 
{
     public List<PostEntity> posts(Integer id);
     
     public List<PostEntity> allPosts();
     public boolean addPost(AddPost post);
     public PostEntity more(Integer id);
     public void cmt(CommentForm form);
     public List<CommentEntity> allCmts(Integer id);
     public List<CommentEntity> allUserCmts(Integer id);
     public void delete(Integer id);
     public void deletePost(Integer id);
     public List<PostEntity> filter(Filter f);
}
