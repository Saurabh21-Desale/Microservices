package com.example.company_service.controller;

import com.example.company_service.model.Company;
import com.example.company_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id){
        return companyService.getCompany(id);
    }

    @PostMapping
    public String createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return "Company Created Successfully!!!";
    }

    @PutMapping("/{id}")
    public String updateCompany(@PathVariable Long id, @RequestBody Company updateCompany){
        companyService.updateCompany(id, updateCompany);
        return "Company Updated Successfully!!!";
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return "Company Deleted Successfully!!!";
    }
}
