package com.pritish.joblisting.repository;

import com.pritish.joblisting.entity.JobPostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends MongoRepository<JobPostEntity,String> {
}
