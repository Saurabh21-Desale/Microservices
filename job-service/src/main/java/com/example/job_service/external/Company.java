package com.example.job_service.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private Long id;
    private String name;
    private String description;
    private List<Review> review;
}
