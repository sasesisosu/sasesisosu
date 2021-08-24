package com.lyg.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyg.photogramstart.repository.SubscribeRepository;
import com.lyg.photogramstart.web.dto.subscribe.SubscribeDto;

@Service
public class SubscribeService {

	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private EntityManager em;
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> subscribeList(int principalId, int pageUserId){

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, ");
		sb.append("if((? = u.id), 1, 0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?");

		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
	}
	
	
	@Transactional
	public void subscribe(int fromUserId, int toUserId) {
		subscribeRepository.mSubscribe(fromUserId, toUserId);
	}
	
	@Transactional
	public void unSubscribe(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}






















