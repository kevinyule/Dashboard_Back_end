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

    public Concept createConcept(Concept conceptRequest){

        Concept concept = new Concept();
        concept.setDescription(conceptRequest.getDescription());

        return conceptRepository.save(concept);
    }
}
