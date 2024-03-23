package in.tharun.serImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tharun.binding.LoginForm;
import in.tharun.binding.SignUpForm;
import in.tharun.entity.UserEntity;
import in.tharun.repo.UserRepo;
import in.tharun.service.User;
import jakarta.servlet.http.HttpSession;

@Service
public class UserSerImp implements User {
  
	@Autowired
	private UserRepo repo;
	@Autowired
	private HttpSession session;
	public String saveUser(SignUpForm form)
	{
		UserEntity email = repo.findByEmail(form.getEmail());
		if(email != null)
		{
			return " enter unic email id";	
		}
		else
		{
		    UserEntity e=new UserEntity();
		    e.setFname(form.getFname());
		    e.setLname(form.getLname());
		    e.setEmail(form.getEmail());
		    e.setPass(form.getPass());
		    repo.save(e);
			return "registration success";
		}
	}
	@Override
	public boolean login(LoginForm form) {
		UserEntity entity = repo.findByEmailAndPass(form.getEmail(),form.getPass());
		if(entity !=null)
		{
			session.setAttribute("userid", entity.getUserid());
			return true;
		}
		return false;
	}

}
