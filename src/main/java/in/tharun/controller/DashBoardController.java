package in.tharun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.tharun.binding.AddPost;
import in.tharun.entity.CommentEntity;
import in.tharun.entity.PostEntity;
import in.tharun.service.Post;
import in.tharun.service.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashBoardController
{
	@Autowired
	private User user;
	@Autowired
	private Post service;
	@Autowired
	private HttpSession session;
	@GetMapping("/dashboard")
    public String dash(Model model)
    {
		Integer id=(Integer) session.getAttribute("userid");
		List<PostEntity> posts = service.posts(id);
		
		model.addAttribute("posts", posts);
    	return "dashboard";
    }
	
	@GetMapping("/deletep")
	public String deletePost(@RequestParam Integer id)
	{
		service.deletePost(id);
		
		return "redirect:/dashboard"; 
	}
	@GetMapping("/edit")
   	public String edit(AddPost post,@RequestParam Integer id,Model model)
	{
	    PostEntity postEntity = service.more(id);
	    post.setId(postEntity.getPostid());
	    post.setTitile(postEntity.getTitle());
	    post.setDiscreption(postEntity.getDescription());
	    post.setContent(postEntity.getContent());
	    
	    model.addAttribute("post", post);
		return "addpost";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/addpost")
	public String add(Model model)
	{
		AddPost post=new AddPost();
		model.addAttribute("post",post);
		return "addpost";
	    
	}
	@PostMapping("/addpost")
	public String addPost(@ModelAttribute("post") AddPost post ,Model model)
	{
	     boolean addPost = service.addPost(post); 
	     model.addAttribute("msg", "post added");
		return "addpost";
	}
	@GetMapping("/comments")
	public String comments(Model model)
	{
		Integer id=(Integer) session.getAttribute("userid");
		List<CommentEntity> allUserCmts = service.allUserCmts(id);
		model.addAttribute("cmts", allUserCmts);
		return "comments";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id,Model model)
	{
		service.delete(id);
		
		return "redirect:/comments";
	}
}
