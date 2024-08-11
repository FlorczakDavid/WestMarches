package com.david.florczak.westmarches.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CampaignCreate(@NotBlank @Size(max=200) String campaignName, 
		@Size(max=2000) String campaignDesc, 
		@Size(max=42) String campaignImage, 
		@NotNull Boolean campaignIsPublic) {

}
