package com.heno.fullback.model.domainservice.impl;

import com.heno.fullback.model.valueobject.Member;
import com.heno.fullback.model.repository.MemberDao;
import com.heno.fullback.security.MemberUserDetail;
import org.seasar.doma.jdbc.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberUserDetailService implements UserDetailsService {

	@Autowired
	MemberDao memberDao;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
		Member member = null;
		try {
			member = memberDao.selectByMailAddress(mailAddress);
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("the mail address is not registered");
		}
		return new MemberUserDetail(member);
	}
}
