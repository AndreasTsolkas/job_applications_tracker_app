package com.example.demo.service;

import com.example.demo.entity.Sector;
import com.example.demo.repository.SectorRepository;
import org.springframework.stereotype.Service;

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