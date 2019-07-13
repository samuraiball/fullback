package com.heno.fullback.model.entitiy;

import com.heno.fullback.model.valueobject.Member;
import org.seasar.doma.Entity;

import java.io.Serializable;
import java.util.List;

@Entity
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	private Member productOwner;
	private Member scrumMaster;

	private double velocity;

	public Member getProductOwner() {
		return productOwner;
	}

	public Member getScrumMaster() {
		return scrumMaster;
	}

	public List<Member> getDeveloppers() {
		return developpers;
	}

	public double getVelocity() {
		return velocity;
	}

	private List<Member> developpers;

}
