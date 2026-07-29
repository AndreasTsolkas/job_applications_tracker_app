package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.Sector;
import com.example.jobtracker.repository.SectorRepository;

import java.util.List;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }


    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }


    public Sector findById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found"));
    }


    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }


    public void delete(Long id) {
        sectorRepository.deleteById(id);
    }
}