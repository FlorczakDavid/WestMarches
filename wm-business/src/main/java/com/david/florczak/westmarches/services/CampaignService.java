package com.david.florczak.westmarches.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.florczak.westmarches.dtos.CampaignCreate;
import com.david.florczak.westmarches.dtos.CampaignTable;
import com.david.florczak.westmarches.entities.Campaign;
import com.david.florczak.westmarches.repositories.CampaignJPARepository;

import jakarta.validation.Valid;

@Service
public class CampaignService {
	
	private final CampaignJPARepository repository;
	
	public CampaignService(CampaignJPARepository campaignJPARepository) {
		this.repository = campaignJPARepository;
	}

	public List<CampaignTable> getCampaignTable() {
		// TODO Auto-generated method stub
		return repository.findAllProjectedByOrderByCampaignName();
	}

	public void create(@Valid CampaignCreate inputs) {
		Campaign campaign = new Campaign();
		campaign.setCampaignName(inputs.campaignName());
		campaign.setCampaignDesc(inputs.campaignDesc());
		campaign.setCampaignImage(inputs.campaignImage());
		campaign.setcampaignIsPublic(inputs.campaignIsPublic());
		repository.save(campaign);
	}
	
}
