package com.infosat.appRecruitment.security.service;

import com.infosat.appRecruitment.exceptions.OffreNotFoundException;
import com.infosat.appRecruitment.model.Offre;
import com.infosat.appRecruitment.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OffreService {
    private final OffreRepository offreRepository;

    @Autowired
    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public Offre addOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    public List<Offre> findAllOffres() {
        return offreRepository.findAll();
    }

    public Offre findOffreById(Long id) {
        return offreRepository.findOffreById(id)
                .orElseThrow(() -> new OffreNotFoundException("Offre by id " + id + " was not found"));
    }

    public Offre updateOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    public void deleteOffre(Long id) {
        offreRepository.deleteOffreById(id);
    }
}
