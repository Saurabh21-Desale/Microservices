package com.example.company_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMessage implements Serializable{

    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;
}
