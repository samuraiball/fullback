package com.heno.fullback.model.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heno.fullback.model.common.Role;
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
				  String mailAddress, String password,
				  Role role, boolean deleteFlag) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.password = password;
		this.mailAddress = mailAddress;
		this.role = role;
		this.deleteFlag = deleteFlag;
	}

	@Id
	@Column(name = "id")
	private String memberId;

	@Column(name = "member_name")
	private String memberName;

	@Column(name = "mail_address")
	private String mailAddress;

	@Column(name = "role")
	private Role role;

	@Column(name = "delete_flag")
	private boolean deleteFlag;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	public String getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public Role getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public boolean isDeleted() {
		return deleteFlag;
	}

	public void toggleDeleteFlag(){
		this.deleteFlag = !this.deleteFlag;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
