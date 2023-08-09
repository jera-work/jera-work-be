package com.lawencon.candidate.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_skill")
public class CandidateSkill extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}
