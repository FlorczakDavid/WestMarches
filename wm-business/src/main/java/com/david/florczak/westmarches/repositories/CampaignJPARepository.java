package com.david.florczak.westmarches.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.dtos.CampaignTable;
import com.david.florczak.westmarches.entities.Campaign;

@Repository
public interface CampaignJPARepository extends JpaRepository<Campaign, Long> {

	List<CampaignTable> findAllProjectedByOrderByCampaignName();

}
