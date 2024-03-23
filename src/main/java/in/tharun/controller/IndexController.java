package in.tharun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.tharun.binding.CommentForm;
import in.tharun.binding.Filter;
import in.tharun.entity.CommentEntity;
import in.tharun.entity.PostEntity;
import in.tharun.service.Post;

@Controller
public class IndexController {
	@Autowired
	private Post pservice;
	@GetMapping("/")
    public String index(Model model)
    {
		List<PostEntity> allPosts = pservice.allPosts();
		model.addAttribute("all", allPosts);
		model.addAttribute("filter", new Filter());
   	    return "index";
    }
	@GetMapping("/more")
	public String more(@RequestParam Integer id,Model model)
	{
		PostEntity more = pservice.more(id);
		List<CommentEntity> allCmts = pservice.allCmts(id);
		CommentForm form =new CommentForm();
		form.setPostId(id);
		model.addAttribute("more", more);
		model.addAttribute("form", form);
		model.addAttribute("new", new CommentForm());
		model.addAttribute("cmts", allCmts);
		
		return "more";
	}
	public void init(Model model,CommentForm form)
	{
		Integer id = form.getPostId();
		PostEntity more = pservice.more(id);
		model.addAttribute("more", more);
		//model.addAttribute("form", new CommentForm());
		
		List<CommentEntity> allCmts = pservice.allCmts(id);
		CommentForm cmt =new CommentForm();
		cmt.setPostId(id);
		model.addAttribute("more", more);
		model.addAttribute("form", cmt);
		model.addAttribute("cmts", allCmts);
	}
	@PostMapping("/cmt")
	public String cmt(@ModelAttribute("new") CommentForm form,Model model)
	{
		
	    pservice.cmt(form);	
	    init(model,form);
		return "more";
	}


	@PostMapping("/filter")
	public String filter(@ModelAttribute("filter") Filter f,Model model)
	{
		List<PostEntity> filter = pservice.filter(f);
		
		model.addAttribute("all", filter);
		return "index";
	}
}
