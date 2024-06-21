package com.pritish.joblisting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "JobPost")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostEntity {

    private String id;
    private String desc;
    private String experience;
    private String profile;
    private String[] technologies;
}
