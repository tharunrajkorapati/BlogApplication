package in.tharun.controller;

import org.hibernate.annotations.CollectionTypeRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.tharun.binding.LoginForm;
import in.tharun.binding.SignUpForm;
import in.tharun.service.User;

@Controller
public class UserContoller
{
	@Autowired
	private User user;
	
	@GetMapping("/signup")
	public String singUp(Model model)
	{
		model.addAttribute("user",new SignUpForm());
		return "signup";
	}
	public void init(Model model)
	{
		model.addAttribute("user",new SignUpForm());
	}
	@PostMapping("/signup")
	public String sing(SignUpForm form,Model model)
	{
		init(model);
		if(form.getPass().equals(form.getRpass()))
		{
			String saveUser = user.saveUser(form);
			if(saveUser.contains("enter unic email id"))
			{
				model.addAttribute("email","enter unic email id" );
			}
			if(saveUser.contains("registration success"))
			{
				model.addAttribute("success", "registration success");
			}
		}
		else
		{
			model.addAttribute("e","password must be same");
		}
		
		return "signup";
	}
	@GetMapping("/login")
	public String login(Model model)
	{
		LoginForm from =new LoginForm();
		model.addAttribute("login", from);
		return "login";
	}
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("login") LoginForm form,Model model)
	{
		boolean login = user.login(form);
		if(login)
		{
			return "redirect:/dashboard";
		}
		else
		{
			model.addAttribute("error", "Invalid credentials");
		}
		return "login";
	}
}
