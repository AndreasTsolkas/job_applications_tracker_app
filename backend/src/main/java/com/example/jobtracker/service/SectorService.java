package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.SectorDTO;
import com.example.jobtracker.entity.Sector;
import com.example.jobtracker.mapper.SectorMapper;
import com.example.jobtracker.repository.SectorRepository;

import java.util.List;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }


    public List<SectorDTO> findAll() {

        return sectorRepository.findAll()
                .stream()
                .map(SectorMapper::toDTO)
                .toList();
    }


    public SectorDTO findById(Long id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found"));

        return SectorMapper.toDTO(sector);
    }


    public SectorDTO save(SectorDTO dto) {

        Sector sector = SectorMapper.toEntity(dto);

        Sector savedSector = sectorRepository.save(sector);

        return SectorMapper.toDTO(savedSector);
    }


    public void delete(Long id) {

        sectorRepository.deleteById(id);
    }
}