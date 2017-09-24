package com.foodkonnekt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "openhours")
@XmlRootElement(name = "OpenHours")
@Audited
public class OpenHours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public String getConvenienceFee() {
		return convenienceFee;
	}

	public void setConvenienceFee(String convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

	public Integer getIsTaxable() {
		return isTaxable;
	}

	public void setIsTaxable(Integer isTaxable) {
		this.isTaxable = isTaxable;
	}

	@Basic
    private Date startTime;

    @Basic
    private Date endTime;

    @Basic
    private Integer day;
    
    @Transient
    private String convenienceFee;
    
    @Transient
    private Integer allowFutureOrder;
    
    @Transient
    private Integer allowMultipleKoupon;
    
    
    
    public Integer getAllowMultipleKoupon() {
		return allowMultipleKoupon;
	}

	public void setAllowMultipleKoupon(Integer allowMultipleKoupon) {
		this.allowMultipleKoupon = allowMultipleKoupon;
	}

	@Transient
    private Integer activeCustomerFeedback;
    
    public Integer getActiveCustomerFeedback() {
		return activeCustomerFeedback;
	}

	public void setActiveCustomerFeedback(Integer activeCustomerFeedback) {
		this.activeCustomerFeedback = activeCustomerFeedback;
	}

	@Transient
    private Integer futureDaysAhead;
    
    @Transient
    private SocialMediaLinks socialMediaLinks;
    
    
    public SocialMediaLinks getSocialMediaLinks() {
		return socialMediaLinks;
	}

	public void setSocialMediaLinks(SocialMediaLinks socialMediaLinks) {
		this.socialMediaLinks = socialMediaLinks;
	}

	public Integer getFutureDaysAhead() {
		return futureDaysAhead;
	}

	public void setFutureDaysAhead(Integer futureDaysAhead) {
		this.futureDaysAhead = futureDaysAhead;
	}

	public Integer getAllowFutureOrder() {
		return allowFutureOrder;
	}

	public void setAllowFutureOrder(Integer allowFutureOrder) {
		this.allowFutureOrder = allowFutureOrder;
	}

	@Transient
    private String pickUpTiime;

    public String getPickUpTiime() {
		return pickUpTiime;
	}

	public void setPickUpTiime(String pickUpTiime) {
		this.pickUpTiime = pickUpTiime;
	}

	@Transient
    private Integer isTaxable;
    
    @Transient
    private Integer locationId;

    public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	@Transient
    private String sTimeToSave;

    @Transient
    private String eTimeToSave;
    
    @Transient
    private String allowPaymentModes;

    public String getAllowPaymentModes() {
		return allowPaymentModes;
	}

	public void setAllowPaymentModes(String allowPaymentModes) {
		this.allowPaymentModes = allowPaymentModes;
	}

	@Transient
    private String selectedDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
        if (!(obj instanceof OpenHours)) {
            return false;
        }
        OpenHours other = (OpenHours) obj;
        if (day == null) {
            if (other.day != null) {
                return false;
            }
        } else if (!day.equals(other.day)) {
            return false;
        }
        if (endTime == null) {
            if (other.endTime != null) {
                return false;
            }
        } else if (!endTime.equals(other.endTime)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (startTime == null) {
            if (other.startTime != null) {
                return false;
            }
        } else if (!startTime.equals(other.startTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OpenHours [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", day=" + day + "]";
    }

    public String getsTimeToSave() {
        return sTimeToSave;
    }

    public void setsTimeToSave(String sTimeToSave) {
        this.sTimeToSave = sTimeToSave;
    }

    public String geteTimeToSave() {
        return eTimeToSave;
    }

    public void seteTimeToSave(String eTimeToSave) {
        this.eTimeToSave = eTimeToSave;
    }

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

	

}
