package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.CVDTO;
import com.example.jobtracker.entity.CV;
import com.example.jobtracker.mapper.CVMapper;
import com.example.jobtracker.repository.CVRepository;

import java.util.List;

@Service
public class CVService {

    private final CVRepository cvRepository;

    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }


    public List<CVDTO> findAll() {

        return cvRepository.findAll()
                .stream()
                .map(CVMapper::toDTO)
                .toList();
    }


    public CVDTO findById(Long id) {

        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));

        return CVMapper.toDTO(cv);
    }


    public List<CVDTO> findByUserId(Long userId) {

        return cvRepository.findByUserId(userId)
                .stream()
                .map(CVMapper::toDTO)
                .toList();
    }


    public CVDTO save(CVDTO dto) {

        CV cv = CVMapper.toEntity(dto);

        CV savedCV = cvRepository.save(cv);

        deactivateOtherActiveCVs(savedCV);

        return CVMapper.toDTO(savedCV);
    }


    public CVDTO update(Long id, CVDTO dto) {

        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));

        CVMapper.updateEntity(cv, dto);

        CV updatedCV = cvRepository.save(cv);

        deactivateOtherActiveCVs(updatedCV);

        return CVMapper.toDTO(updatedCV);
    }


    public void delete(Long id) {

        cvRepository.deleteById(id);
    }


    private void deactivateOtherActiveCVs(CV cv) {

        if (!Boolean.TRUE.equals(cv.getIsActive()) || cv.getUser() == null) {
            return;
        }

        List<CV> userCVs = cvRepository.findByUserId(cv.getUser().getId());

        for (CV other : userCVs) {

            if (other.getId().equals(cv.getId())) {
                continue;
            }

            if (Boolean.TRUE.equals(other.getIsActive())) {
                other.setIsActive(false);
                cvRepository.save(other);
            }
        }
    }
}
