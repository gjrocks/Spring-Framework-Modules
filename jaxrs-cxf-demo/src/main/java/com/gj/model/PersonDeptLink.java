package com.gj.model;



import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="PersonDeptLink")
public class PersonDeptLink {

	Integer deptNo;
	private Integer personId;

	
	public PersonDeptLink(){
		super();
		deptNo=0;
		personId=0;
	}
	public PersonDeptLink(Integer deptNo,Integer personId) {
		super();
		this.deptNo=deptNo;
		this.personId = personId;
	}
	
	public Integer getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	
	
}
