package com.gj.es.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = "alm4",createIndex = false)
public class Work {

	@Id
	Long id;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date work_creation_date;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date work_inception_date;
	
	String sprint;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date sprint_start_date;
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date sprint_end_date;
	
	String workDesc;
	String workType;
	String priority;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date devl_deploy_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date qual_deploy_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date cert_deploy_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Field(format = DateFormat.none, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date prod_deploy_date;
	String workSource;
	String assignedTo;
	String gitRepoName;
	String commitId;
	String WorkID;
	
	public String getWorkID() {
		return WorkID;
	}
	public void setWorkID(String workID) {
		WorkID = workID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getWork_creation_date() {
		return work_creation_date;
	}
	public void setWork_creation_date(Date work_creation_date) {
		this.work_creation_date = work_creation_date;
	}
	public Date getWork_inception_date() {
		return work_inception_date;
	}
	public void setWork_inception_date(Date work_inception_date) {
		this.work_inception_date = work_inception_date;
	}
	public String getSprint() {
		return sprint;
	}
	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	public Date getSprint_start_date() {
		return sprint_start_date;
	}
	public void setSprint_start_date(Date sprint_start_date) {
		this.sprint_start_date = sprint_start_date;
	}
	public Date getSprint_end_date() {
		return sprint_end_date;
	}
	public void setSprint_end_date(Date sprint_end_date) {
		this.sprint_end_date = sprint_end_date;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getDevl_deploy_date() {
		return devl_deploy_date;
	}
	public void setDevl_deploy_date(Date devl_deploy_date) {
		this.devl_deploy_date = devl_deploy_date;
	}
	public Date getQual_deploy_date() {
		return qual_deploy_date;
	}
	public void setQual_deploy_date(Date qual_deploy_date) {
		this.qual_deploy_date = qual_deploy_date;
	}
	public Date getCert_deploy_date() {
		return cert_deploy_date;
	}
	public void setCert_deploy_date(Date cert_deploy_date) {
		this.cert_deploy_date = cert_deploy_date;
	}
	public Date getProd_deploy_date() {
		return prod_deploy_date;
	}
	public void setProd_deploy_date(Date prod_deploy_date) {
		this.prod_deploy_date = prod_deploy_date;
	}
	public String getWorkSource() {
		return workSource;
	}
	public void setWorkSource(String workSource) {
		this.workSource = workSource;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getGitRepoName() {
		return gitRepoName;
	}
	public void setGitRepoName(String gitRepoName) {
		this.gitRepoName = gitRepoName;
	}
	public String getCommitId() {
		return commitId;
	}
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}
	@Override
	public String toString() {
		return "Work [id=" + id + ", work_creation_date=" + work_creation_date + ", work_inception_date="
				+ work_inception_date + ", sprint=" + sprint + ", sprint_start_date=" + sprint_start_date
				+ ", sprint_end_date=" + sprint_end_date + ", workDesc=" + workDesc + ", workType=" + workType
				+ ", priority=" + priority + ", devl_deploy_date=" + devl_deploy_date + ", qual_deploy_date="
				+ qual_deploy_date + ", cert_deploy_date=" + cert_deploy_date + ", prod_deploy_date=" + prod_deploy_date
				+ ", workSource=" + workSource + ", assignedTo=" + assignedTo + ", gitRepoName=" + gitRepoName
				+ ", commitId=" + commitId + ", WorkID=" + WorkID + "]";
	}
	
	
	
	
	
}
