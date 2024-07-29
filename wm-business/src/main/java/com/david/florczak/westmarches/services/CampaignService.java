package com.david.florczak.westmarches.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.florczak.westmarches.dtos.CampaignTable;
import com.david.florczak.westmarches.repositories.CampaignJPARepository;

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
	
}
