package ar.edu.itba.webapp.form;

import javax.validation.constraints.Size;

import ar.edu.itba.webapp.form.constraint.UserCanJoinProject;

@UserCanJoinProject(markedField="member")
public class AddMemberForm {

	@Size(min=1, max=100)
	private String member;

	private String projectCode;
	
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getMember() {
		return member;
	}
	
	public String getProjectCode() {
		return projectCode;
	}

	public void setMember(String member) {
		this.member = member;
	}
}
