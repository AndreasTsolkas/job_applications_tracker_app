package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.ApplicationStatusHistoryDTO;
import com.example.jobtracker.entity.ApplicationStatusHistory;
import com.example.jobtracker.mapper.ApplicationStatusHistoryMapper;
import com.example.jobtracker.repository.ApplicationStatusHistoryRepository;

import java.util.List;

@Service
public class ApplicationStatusHistoryService {

    private final ApplicationStatusHistoryRepository applicationStatusHistoryRepository;

    public ApplicationStatusHistoryService(
            ApplicationStatusHistoryRepository applicationStatusHistoryRepository) {

        this.applicationStatusHistoryRepository = applicationStatusHistoryRepository;
    }


    public List<ApplicationStatusHistoryDTO> findAll() {

        return applicationStatusHistoryRepository.findAll()
                .stream()
                .map(ApplicationStatusHistoryMapper::toDTO)
                .toList();
    }


    public ApplicationStatusHistoryDTO findById(Long id) {

        ApplicationStatusHistory history =
                applicationStatusHistoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application status history not found"));

        return ApplicationStatusHistoryMapper.toDTO(history);
    }


    public List<ApplicationStatusHistoryDTO> findByApplicationId(Long applicationId) {

        return applicationStatusHistoryRepository.findByApplicationId(applicationId)
                .stream()
                .map(ApplicationStatusHistoryMapper::toDTO)
                .toList();
    }


    public ApplicationStatusHistoryDTO save(ApplicationStatusHistoryDTO dto) {

        ApplicationStatusHistory history =
                ApplicationStatusHistoryMapper.toEntity(dto);

        ApplicationStatusHistory savedHistory =
                applicationStatusHistoryRepository.save(history);

        return ApplicationStatusHistoryMapper.toDTO(savedHistory);
    }


    public ApplicationStatusHistoryDTO update(Long id, ApplicationStatusHistoryDTO dto) {

        ApplicationStatusHistory history =
                applicationStatusHistoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application status history not found"));

        ApplicationStatusHistoryMapper.updateEntity(history, dto);

        ApplicationStatusHistory updatedHistory =
                applicationStatusHistoryRepository.save(history);

        return ApplicationStatusHistoryMapper.toDTO(updatedHistory);
    }


    public void delete(Long id) {

        applicationStatusHistoryRepository.deleteById(id);
    }
}