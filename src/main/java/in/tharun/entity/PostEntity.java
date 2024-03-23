package in.tharun.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
@Entity
@Table(name ="blog_post")
public class PostEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer postid;
     private String title;
     private String description;
     @Lob
     @Column(columnDefinition = "TEXT")
     private String content;
     @CreationTimestamp
     private LocalDate createOn;
     @UpdateTimestamp
     private LocalDate updateOn;
     
     @ManyToOne
     @JoinColumn(name ="userid")
     private UserEntity userid;
     
     @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
     private List<CommentEntity> comments;
}
