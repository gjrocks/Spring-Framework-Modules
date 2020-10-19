package com.gj.es.model;

public class WorkGit {

	String commitInfo;
	String branch;
	String gitRepoName;
	String exDate;
	String workIDs;
	
	
	public String getWorkIDs() {
		return workIDs;
	}
	public void setWorkIDs(String workIDs) {
		this.workIDs = workIDs;
	}
	public WorkGit() {
		super();
		
	}
	public String getCommitInfo() {
		return commitInfo;
	}
	public void setCommitInfo(String commitInfo) {
		this.commitInfo = commitInfo;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getGitRepoName() {
		return gitRepoName;
	}
	public void setGitRepoName(String gitRepoName) {
		this.gitRepoName = gitRepoName;
	}
	public String getExDate() {
		return exDate;
	}
	public void setExDate(String exDate) {
		this.exDate = exDate;
	}
	@Override
	public String toString() {
		return "WorkGit [commitInfo=" + commitInfo + ", branch=" + branch + ", gitRepoName=" + gitRepoName + ", exDate="
				+ exDate + ", workIDs=" + workIDs + "]";
	}
	
	
	
	
}
