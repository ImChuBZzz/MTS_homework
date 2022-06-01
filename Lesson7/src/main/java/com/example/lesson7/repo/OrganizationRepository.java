package com.example.lesson7.repo;

import com.example.lesson7.data.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
