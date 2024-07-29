package com.david.florczak.westmarches.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_campaigns")
public class Campaign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "campaign_name")
	private String campaignName;	
	@Column(name = "campaign_desc")
	private String campaignDesc;	
	@Column(name = "campaign_is_public")
	private Boolean campaignIsPublic;	
	@Column(name = "campaign_img")
	private String campaignImage;
	
	public Campaign() {} // Required by ORM

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignDesc() {
		return campaignDesc;
	}

	public void setCampaignDesc(String campaignDesc) {
		this.campaignDesc = campaignDesc;
	}

	public Boolean getcampaignIsPublic() {
		return campaignIsPublic;
	}

	public void setcampaignIsPublic(Boolean campaignIsPublic) {
		this.campaignIsPublic = campaignIsPublic;
	}

	public String getCampaignImage() {
		return campaignImage;
	}

	public void setCampaignImage(String campaignImage) {
		this.campaignImage = campaignImage;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", campaignName=" + campaignName + ", campaignDesc=" + campaignDesc
				+ ", campaignIsPublic=" + campaignIsPublic + ", campaignImage=" + campaignImage + "]";
	}; 
	
	
}
