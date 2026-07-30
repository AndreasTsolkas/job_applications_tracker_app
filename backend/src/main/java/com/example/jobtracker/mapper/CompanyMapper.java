package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.CompanyDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.Sector;

public class CompanyMapper {

    private CompanyMapper() {
    }

    public static CompanyDTO toDTO(Company company) {

        if (company == null) {
            return null;
        }

        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .website(company.getWebsite())
                .location(company.getLocation())
                .sectorId(
                        company.getSector() != null
                                ? company.getSector().getId()
                                : null
                )
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }


    public static Company toEntity(CompanyDTO dto) {

        if (dto == null) {
            return null;
        }

        Company company = Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .website(dto.getWebsite())
                .location(dto.getLocation())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();

        if (dto.getSectorId() != null) {
            Sector sector = new Sector();
            sector.setId(dto.getSectorId());
            company.setSector(sector);
        }

        return company;
    }
}