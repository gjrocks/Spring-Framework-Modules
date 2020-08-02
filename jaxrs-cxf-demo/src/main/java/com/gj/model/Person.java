package com.gj.model;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Person")
public class Person {

	@FormParam("id")
	int id;
	@FormParam("age")
	int age;
	@FormParam("fname")
	String fname;
	@FormParam("lname")
	String lname;
	Date dob;
	@FormParam("sal")
	double sal;
	@FormParam("deptCode")
	int deptCode;
	
	
	public Person(){
		super();
	}
	
	
	
	public Person(int id, int age, String fname, String lname, Date dob,
			double sal) {
		super();
		this.id = id;
		this.age = age;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.sal = sal;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}



	public int getDeptCode() {
		return deptCode;
	}



	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + deptCode;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (deptCode != other.deptCode)
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (id != other.id)
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (Double.doubleToLongBits(sal) != Double.doubleToLongBits(other.sal))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Person [id=" + id + ", age=" + age + ", fname=" + fname
				+ ", lname=" + lname + ", dob=" + dob + ", sal=" + sal
				+ ", deptCode=" + deptCode + "]";
	}



	

	
	
}
