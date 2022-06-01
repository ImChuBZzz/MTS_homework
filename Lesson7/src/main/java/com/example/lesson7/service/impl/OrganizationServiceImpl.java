package com.example.lesson7.service.impl;

import com.example.lesson7.data.Organization;
import com.example.lesson7.data.exceptions.RequiredFieldMissedException;
import com.example.lesson7.repo.OrganizationRepository;
import com.example.lesson7.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        validation(organization);
        organization.setId(UUID.randomUUID().toString());
        organizationRepository.save(organization);
        return organization;
    }

    private void validation(Organization org) {
        if(org.getName().isBlank()) throw new RequiredFieldMissedException("Field 'name' is empty");
        if(org.getINN().isBlank()) throw new RequiredFieldMissedException("Field 'INN' is empty");
        if(org.getCreationDate() == null) throw new RequiredFieldMissedException("Field 'creation date' is empty");
        if(org.getUpdateDate() == null) throw new RequiredFieldMissedException("Field 'update date' is empty");
    }
}
