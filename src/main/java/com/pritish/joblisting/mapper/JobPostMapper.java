package com.pritish.joblisting.mapper;

import com.pritish.joblisting.dto.JobPostDto;
import com.pritish.joblisting.entity.JobPostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobPostMapper {

    JobPostMapper JOB_POST_MAPPER = Mappers.getMapper(JobPostMapper.class);

    JobPostDto convertJobPostEntityToJobPostDto(JobPostEntity jobPostEntity);

    JobPostEntity convertJobPostDtoToJobPostEntity(JobPostDto jobPostDto);
}
