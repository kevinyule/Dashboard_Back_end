package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.interfaces.IConceptService;
import com.dashboard_gk.dashboard_gk.model.Concept;
import com.dashboard_gk.dashboard_gk.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptService implements IConceptService {

    @Autowired
    ConceptRepository conceptRepository;

    public void createConcept(Concept conceptRequest){

        Concept concept = new Concept();
        concept.setUserId(conceptRequest.getUserId());
        concept.setCategoryId(conceptRequest.getCategoryId());
        concept.setTypeId(conceptRequest.getTypeId());
        concept.setDescription(conceptRequest.getDescription());
        conceptRepository.save(concept);
    }
}
