package com.david.florczak.westmarches.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.florczak.westmarches.dtos.CampaignTable;
import com.david.florczak.westmarches.services.CampaignService;

@RestController
@RequestMapping("/campaigns")
@CrossOrigin("*")
public class CampaignController {
	private final CampaignService service;
	
	public CampaignController(CampaignService campaignService) {
		this.service = campaignService;
	}
	
	@GetMapping("/list")
	List<CampaignTable> getCampaignTable() {
		return service.getCampaignTable();
	}
}
