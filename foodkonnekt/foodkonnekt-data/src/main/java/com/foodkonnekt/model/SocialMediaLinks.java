package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Social_Media_Links")
public class SocialMediaLinks {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "facebook_link")
	private String faceBookLink;
	
	@Column(name = "yelp_link")
	private String yelpLink;
	
	@Column(name = "instagram_link")
	private String instagramLink;
	
	@OneToOne 
    @JoinColumn(name="merchant_id") 
	private Merchant merchant;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFaceBookLink() {
		return faceBookLink;
	}

	public void setFaceBookLink(String faceBookLink) {
		this.faceBookLink = faceBookLink;
	}

	public String getYelpLink() {
		return yelpLink;
	}

	public void setYelpLink(String yelpLink) {
		this.yelpLink = yelpLink;
	}

	public String getInstagramLink() {
		return instagramLink;
	}

	public void setInstagramLink(String instagramLink) {
		this.instagramLink = instagramLink;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}
