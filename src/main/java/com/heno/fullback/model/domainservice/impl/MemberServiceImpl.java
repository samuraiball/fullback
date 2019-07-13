package com.heno.fullback.model.domainservice.impl;

import com.heno.fullback.common.exception.DataAlreadyExistsException;
import com.heno.fullback.common.exception.DataNotFoundException;
import com.heno.fullback.model.valueobject.Member;
import com.heno.fullback.repository.MemberDao;
import com.heno.fullback.model.domainservice.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MemberService.
 * Deal with Member
 * FIXME:データ検証のリファクタリング
 */
@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	private PasswordEncoder passwordEncoder;

	public MemberServiceImpl(
			MemberDao memberDao,
			PasswordEncoder passwordEncoder
	) {
		this.memberDao = memberDao;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Get All members
	 *
	 * @return Get all members (includes deleted members)
	 */
	@Override
	public List<Member> getAllMembers() {
		return memberDao.selectAll();
	}

	/**
	 * Get a member.
	 *
	 * @return Get the member who defined by member id.
	 */
	@Override
	public Member getMember(String memberId) {
		return memberDao.selectById(memberId);
	}

	/**
	 * Update the member.
	 *
	 * @return Updated member who defined by member id.
	 */
	@Override
	public Member updateMember(Member member) {

		Member targetMember = memberDao.selectById(member.getMemberId());

		if (StringUtils.isEmpty(member.getPassword())) {
			member.setPassword(targetMember.getPassword());
		} else {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
		}

		if (targetMember.isDeleted()) {
			throw new DataNotFoundException();
		}

		memberDao.update(member);
		return member;
	}

	/**
	 * Create a member.
	 *
	 * @return Created member who defined by member id.
	 */
	@Override
	public Member create(Member member) {
		if (memberDao.selectByMailAddress(member.getMailAddress()) != null) {
			throw new DataAlreadyExistsException();
		}
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberDao.insert(member);
		return member;
	}


	/**
	 * Delete the member.
	 *
	 * @return Deleted member who defined by member id.
	 */
	@Override
	public void deleteMember(String memberId) {
		Member member = memberDao.selectById(memberId);

		if (member == null || member.isDeleted()) {
			throw new DataNotFoundException();
		}

		member.toggleDeleteFlag();
		memberDao.update(member);
	}
}
