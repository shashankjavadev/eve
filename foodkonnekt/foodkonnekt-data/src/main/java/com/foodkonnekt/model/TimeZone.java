package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "time_zone")
public class TimeZone {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "time_zone_name")
	private String timeZoneName;
	
	@Column(name = "time_zone_Code")
	private String timeZoneCode;
	
	@Column(name = "hour_difference")
	private Integer hourDifference;
	
	@Column(name = "minut_difference")
	private Integer minutDifference;
	
	public Integer getMinutDifference() {
		return minutDifference;
	}

	public void setMinutDifference(Integer minutDifference) {
		this.minutDifference = minutDifference;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public String getTimeZoneCode() {
		return timeZoneCode;
	}

	public void setTimeZoneCode(String timeZoneCode) {
		this.timeZoneCode = timeZoneCode;
	}

	public Integer getHourDifference() {
		return hourDifference;
	}

	public void setHourDifference(Integer hourDifference) {
		this.hourDifference = hourDifference;
	}
	
	

}
