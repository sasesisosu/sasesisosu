package com.lyg.photogramstart.domain.user;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.lyg.photogramstart.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블 생성
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20, unique=true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String website;
	private String bio;
	
	@Column(nullable = false)
	private String email;
	
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	@OneToMany(mappedBy = "user")
	private List<Image> images;

	@CreationTimestamp 
	private Timestamp createDate;
	
//	private LocalDateTime createDate;
//	
//	@PrePersist // DB에 insert되기 직전에 실행
//	public void createDate() {
//		this.createDate = LocalDateTime.now();
//	}
	
}
