package com.example.company_service.service;

import com.example.company_service.dto.ReviewMessage;
import com.example.company_service.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompany(Long id);

    void createCompany(Company company);

    void updateCompany(Long id, Company updateCompany);

    void deleteCompany(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);

}
