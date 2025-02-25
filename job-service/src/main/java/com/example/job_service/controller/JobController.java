package com.example.job_service.controller;

import com.example.job_service.dto.JobDTO;
import com.example.job_service.model.Job;
import com.example.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
//@RequiredArgsConstructor
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobDTO> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDTO getJob(@PathVariable Long id){
        return jobService.getJob(id);
    }

    @PostMapping
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job Created Successfully!!!";
    }

    @PutMapping("/{id}")
    public String updateJob(@PathVariable Long id, @RequestBody Job updateJob){
        jobService.updateJob(id, updateJob);
        return "Job Updated Successfully!!!";
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return "Job Deleted Successfully!!!";
    }

}
