package in.tharun.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tharun.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Integer>
{
         public UserEntity findByEmail(String email);
         public UserEntity findByEmailAndPass(String email,String pass);
         
}
