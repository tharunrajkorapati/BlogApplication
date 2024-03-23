package in.tharun.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="blog_user_dtls")
public class UserEntity
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer userid;
   private String fname;
   private String lname;
   private String email;
   private String pass;
   
}
