package in.tharun.service;

import in.tharun.binding.LoginForm;
import in.tharun.binding.SignUpForm;

public interface User 
{
     public String saveUser(SignUpForm form);
     public boolean login(LoginForm form);
}
