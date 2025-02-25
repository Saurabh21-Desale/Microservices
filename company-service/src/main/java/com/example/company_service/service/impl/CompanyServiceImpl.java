package com.example.company_service.service.impl;

import com.example.company_service.clients.ReviewClient;
import com.example.company_service.dto.ReviewMessage;
import com.example.company_service.model.Company;
import com.example.company_service.repository.CompanyRepository;
import com.example.company_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Long id, Company updateCompany) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            companyRepository.save(company);
        }
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow();
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);


    }
}
