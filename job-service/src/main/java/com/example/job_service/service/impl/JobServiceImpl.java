package com.example.job_service.service.impl;

import com.example.job_service.clients.CompanyClient;
import com.example.job_service.clients.ReviewClient;
import com.example.job_service.dto.JobDTO;
import com.example.job_service.external.Company;
import com.example.job_service.external.Review;
import com.example.job_service.mapper.JobMapper;
import com.example.job_service.model.Job;
import com.example.job_service.repository.JobRepository;
import com.example.job_service.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    //@Retry(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    //@CircuitBreaker(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO convertToDto(Job job){

        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getReviews(job.getReviewId());

        company.setReview(reviews);

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company);

        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJob(Long id) {

        Job job = jobRepository.findById(id).orElseThrow();
        return convertToDto(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;

        }
        return false;
    }
}
