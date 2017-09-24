package com.foodkonnekt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;


@Entity
@Table(name="personalinfo")
@XmlRootElement(name="PersonalInfo")
@Audited
public class PersonalInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	
	@Basic
	@Column(length=45)
	private String name;
	
	
	@Basic
	@Column(length=20)
	private String phoneNumber;
	

	@Basic
    private Date createdTime;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PersonalInfo)) {
			return false;
		}
		PersonalInfo other = (PersonalInfo) obj;
		if (createdTime == null) {
			if (other.createdTime != null) {
				return false;
			}
		} else if (!createdTime.equals(other.createdTime)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phoneNumber == null) {
			if (other.phoneNumber != null) {
				return false;
			}
		} else if (!phoneNumber.equals(other.phoneNumber)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "PersonalInfo [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", createdTime=" + createdTime + "]";
	}
	
	


	
}
