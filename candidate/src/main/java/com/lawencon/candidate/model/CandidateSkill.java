package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class CandidateSkill extends BaseEntity{
	private Skill skill;
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
