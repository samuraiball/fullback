package com.heno.fullback.security;

import com.heno.fullback.model.common.Role;
import com.heno.fullback.model.entitiy.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MemberUserDetail implements UserDetails {

	Member member;

	public MemberUserDetail(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return this.member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections
				.singleton(new SimpleGrantedAuthority( member.getRole().toString()));
	}

	public String getMemberId(){
		return member.getMemberId();
	}

	public boolean isAdmin(){
		return Role.ROLE_ADMIN.equals(this.member.getRole());
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getMailAddress();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
