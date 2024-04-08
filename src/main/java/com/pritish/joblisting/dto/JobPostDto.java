package com.pritish.joblisting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostDto {

    private String id;
    private String desc;
    private String experience;
    private String profile;
    private String[] technologies;
}
