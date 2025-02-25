package com.example.job_service.service;

import com.example.job_service.dto.JobDTO;
import com.example.job_service.model.Job;

import java.util.List;

public interface JobService {

    List<JobDTO> getAllJobs();

    void createJob(Job job);

    JobDTO getJob(Long id);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job updatedJob);

}
