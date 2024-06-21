package com.pritish.joblisting.service;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pritish.joblisting.dto.JobPostDto;
import com.pritish.joblisting.entity.JobPostEntity;
import com.pritish.joblisting.mapper.JobPostMapper;
import com.pritish.joblisting.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final MongoClient client;
    private final MongoConverter mongoConverter;

    @Value("${spring.data.mongodb.database}")
    String mongoDatabaseName;

    public List<JobPostDto> getAllJobPosts(){
        List<JobPostDto> jobPosts = new ArrayList<>();
        List<JobPostEntity> jobPostEntityList = jobPostRepository.findAll();
        log.info("JobPostService::getAllJobPosts => Fetched all job posts : {}",jobPostEntityList);
        for(JobPostEntity jobPost : jobPostEntityList){
            JobPostDto jobPostDto = JobPostMapper.JOB_POST_MAPPER
                    .convertJobPostEntityToJobPostDto(jobPost);
            jobPosts.add(jobPostDto);
        }

        return jobPosts;

    }

    public JobPostDto addJobPost(JobPostDto jobPostDto) {
        return JobPostMapper.JOB_POST_MAPPER.convertJobPostEntityToJobPostDto(
                jobPostRepository.save(
                        JobPostMapper.JOB_POST_MAPPER.convertJobPostDtoToJobPostEntity(jobPostDto)));
    }

    public List<JobPostDto> searchJobsByText(String text) {

        final List<JobPostDto> posts = new ArrayList<>();

        MongoDatabase mongoDatabase = client.getDatabase(mongoDatabaseName);
        MongoCollection<Document> jobPostCollection = mongoDatabase.getCollection("JobPost");
        AggregateIterable<Document> searchResult = jobPostCollection.aggregate(Arrays.asList(
                new Document("$search",
                        new Document("index", "default")
                                .append("text",
                                        new Document("query", text)
                                                .append("path",
                                                        Arrays.asList("technologies", "desc", "profile")))),
                new Document("$sort", new Document("experience", 1L)), new Document("$limit", 5L)));
        log.info("Search Results : {}",searchResult);
        searchResult.forEach(document ->
                posts.add(mongoConverter.read(JobPostDto.class,document))
                );
        log.info("Job Posts For Search Criteria [ {} ] : {}",text,posts);
        return posts;
    }
}
