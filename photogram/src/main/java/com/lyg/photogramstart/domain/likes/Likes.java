package com.lyg.photogramstart.domain.likes;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.lyg.photogramstart.domain.image.Image;
import com.lyg.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
	uniqueConstraints = {
			@UniqueConstraint(
				name="likes_uk",
				columnNames = {"imageId", "userId"}
			)
	}
)
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "imageId")
	@ManyToOne
	private Image image;
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	@CreationTimestamp 
	private Timestamp createDate;
	
}
