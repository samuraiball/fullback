package com.heno.fullback.model.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.io.Serializable;

/**
 * Member Entity
 * have
 *
 * @author Yuya Hirooka
 */

@Entity
@Table(name = "member")
public class Member implements Serializable {

	public Member() {
	}

	public Member(String memberId, String memberName,
				  String mailAddress, String password) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.password = password;
		this.mailAddress = mailAddress;
	}

	@Id
	@Column(name = "id")
	private String memberId;

	@Column(name = "member_name")
	private String memberName;

	@Column(name = "mail_address")
	private String mailAddress;

	@JsonIgnore
	private String password;

	public String getMemberId() {
		return memberId;
	}

	public String getPassword() {
		return password;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMailAddress() {
		return mailAddress;
	}


}
