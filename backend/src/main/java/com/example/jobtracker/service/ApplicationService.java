package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.ApplicationStatusHistory;
import com.example.jobtracker.mapper.ApplicationMapper;
import com.example.jobtracker.repository.ApplicationRepository;
import com.example.jobtracker.repository.ApplicationStatusHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationStatusHistoryRepository applicationStatusHistoryRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            ApplicationStatusHistoryRepository applicationStatusHistoryRepository) {

        this.applicationRepository = applicationRepository;
        this.applicationStatusHistoryRepository = applicationStatusHistoryRepository;
    }


    public List<ApplicationDTO> findAll() {

        return applicationRepository.findAll()
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public ApplicationDTO findById(Long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return ApplicationMapper.toDTO(application);
    }


    public List<ApplicationDTO> findByUserId(Long userId) {

        return applicationRepository.findByUserId(userId)
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public List<ApplicationDTO> findByStatusId(Long statusId) {

        return applicationRepository.findByStatusId(statusId)
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public List<ApplicationDTO> findByJobPostingId(Long jobPostingId) {

        return applicationRepository.findByJobPostingId(jobPostingId)
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public ApplicationDTO save(ApplicationDTO dto) {

        Application application = ApplicationMapper.toEntity(dto);

        Application savedApplication = applicationRepository.save(application);

        recordStatusChange(savedApplication);

        return ApplicationMapper.toDTO(savedApplication);
    }


    public ApplicationDTO update(Long id, ApplicationDTO dto) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Long previousStatusId = application.getStatus() != null
                ? application.getStatus().getId()
                : null;

        ApplicationMapper.updateEntity(application, dto);

        Application updatedApplication = applicationRepository.save(application);

        Long newStatusId = updatedApplication.getStatus() != null
                ? updatedApplication.getStatus().getId()
                : null;

        if (!Objects.equals(previousStatusId, newStatusId)) {
            recordStatusChange(updatedApplication);
        }

        return ApplicationMapper.toDTO(updatedApplication);
    }


    public void delete(Long id) {

        applicationRepository.deleteById(id);
    }


    private void recordStatusChange(Application application) {

        if (application.getStatus() == null) {
            return;
        }

        ApplicationStatusHistory history = ApplicationStatusHistory.builder()
                .application(application)
                .status(application.getStatus())
                .changedAt(LocalDateTime.now())
                .build();

        applicationStatusHistoryRepository.save(history);
    }
}
