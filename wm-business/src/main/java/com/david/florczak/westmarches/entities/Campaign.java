package com.david.florczak.westmarches.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_campaigns")
public class Campaign extends AbstractEntity {
	
	@Column(name = "campaign_name")
	private String campaignName;	
	@Column(name = "campaign_desc")
	private String campaignDesc;	
	@Column(name = "campaign_is_public")
	private Boolean campaignIsPublic;	
	@Column(name = "campaign_img")
	private String campaignImage;
	
	public Campaign() {
		// for the ORM
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(campaignName);
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
		Campaign other = (Campaign) obj;
		return Objects.equals(campaignName, other.campaignName);
	}

	@Override
	public String toString() {
		return "Campaign [campaignName=" + campaignName + ", campaignDesc=" + campaignDesc
				+ ", campaignIsPublic=" + campaignIsPublic + ", campaignImage=" + campaignImage + "]";
	}
	
	
}
