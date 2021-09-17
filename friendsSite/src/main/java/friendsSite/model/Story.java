package friendsSite.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String imageUrl;
	
	private String storyAccount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	
	private int storyLikeCount;
	
	@OneToMany(mappedBy = "story")
	@JsonIgnoreProperties({"story"})
	@OrderBy("id desc")
	private List<StoryComment> storyComment;
	
	@OneToMany(mappedBy = "story")
	@JsonIgnoreProperties({"story"})
	private List<StoryLike> storyLike;
	
	private Timestamp createDate;

	
}
