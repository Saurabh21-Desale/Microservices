package com.example.job_service.mapper;

import com.example.job_service.dto.JobDTO;
import com.example.job_service.external.Company;
import com.example.job_service.external.Review;
import com.example.job_service.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company){

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        //jobDTO.setReview(review);

        return jobDTO;
    }
}
