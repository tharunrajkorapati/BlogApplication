package in.tharun.serImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tharun.binding.AddPost;
import in.tharun.binding.CommentForm;
import in.tharun.binding.Filter;
import in.tharun.entity.CommentEntity;
import in.tharun.entity.PostEntity;
import in.tharun.entity.UserEntity;
import in.tharun.repo.CommtRepo;
import in.tharun.repo.PostRepo;
import in.tharun.repo.UserRepo;
import in.tharun.service.Post;
import jakarta.servlet.http.HttpSession;

@Service
public class PostImp implements Post {

	@Autowired
	private UserRepo urepo;
	@Autowired
	private PostRepo prepo;
	@Autowired
	private CommtRepo crepo;
	
	@Autowired
	private HttpSession session;
	
	
	
	@Override
	public List<PostEntity> allPosts() {
		List<PostEntity> findAll = prepo.findAll();
		return findAll;
	}
	
	@Override
	public PostEntity more(Integer id) {
		PostEntity entity = prepo.findById(id).get();
		return entity;
	}
	
	
	@Override
	public void cmt(CommentForm form) {
		CommentEntity e=new CommentEntity();
		e.setComment(form.getCmt());
		e.setName(form.getName());
		e.setEmail(form.getEmail());
		PostEntity postEntity = prepo.findById(form.getPostId()).get();
		e.setPost(postEntity);
		crepo.save(e);
		
	}
	
	
	@Override
	public List<CommentEntity> allCmts(Integer id) {
		PostEntity postEntity = prepo.findById(id).get();
		List<CommentEntity> findByPostid = crepo.findByPost(postEntity);
		return findByPostid;
	}
	
	public List<PostEntity> posts(Integer id) {
		// TODO Auto-generated method stub
		//List<PostEntity> findAllByUser_Id = prepo.findAllByUser_Id(id);
		UserEntity userEntity = urepo.findById(id).get();
		Integer userid = userEntity.getUserid();
		List<PostEntity> findByUserId = prepo.findByUserid(userEntity);
		return findByUserId;
	}
	
//	public List<PostEntity> posts(Integer id) 
//	{
//		List<PostEntity> findByUserId = prepo.findAllByUser_id(id);
//		return findByUserId;
//	}
	
	@Override
	public boolean addPost(AddPost post) {
		PostEntity e=new PostEntity();
		if(post.getId()==null)
		{
		e.setTitle(post.getTitile());
		e.setDescription(post.getDiscreption());
		e.setContent(post.getContent());
		Integer id=(Integer) session.getAttribute("userid");
		UserEntity entity = urepo.findById(id).get();
		e.setUserid(entity);
		prepo.save(e);
		}
		else
		{
			Optional<PostEntity> findById = prepo.findById(post.getId());
			if(findById.isPresent())
			{
				PostEntity postEntity = findById.get();
				postEntity.setTitle(post.getTitile());
				postEntity.setDescription(post.getDiscreption());
				postEntity.setContent(post.getContent());
				prepo.save(postEntity);
			}
		}
		return true;
	}
	@Override
	public List<CommentEntity> allUserCmts(Integer id) {
		UserEntity userEntity = urepo.findById(id).get();
		return crepo.findCommentsByUserid(userEntity);
		
	}
	@Override
	public void delete(Integer id) {
		crepo.deleteById(id);
		
	}
	@Override
	public void deletePost(Integer id) {
		prepo.deleteById(id);
		
	}
	@Override
	public List<PostEntity> filter(Filter f) {
		List<PostEntity> findAll = prepo.findAll();
		if(!"".equals(f.getName()))
		 findAll = findAll.stream().filter(e -> e.getTitle().contains(f.getName())).collect(Collectors.toList());
		
		return findAll;
	}

}
