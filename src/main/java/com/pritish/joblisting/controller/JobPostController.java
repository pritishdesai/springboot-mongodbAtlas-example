package com.pritish.joblisting.controller;

import com.pritish.joblisting.dto.JobPostDto;
import com.pritish.joblisting.service.JobPostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JobPostController {

    private final JobPostService jobPostService;

    @Operation(summary = "Fetches all the job posts")
    @GetMapping("/posts")
    public ResponseEntity<List<JobPostDto>> getAllJobPosts(){
        return ResponseEntity.ok(jobPostService.getAllJobPosts());
    }

    @Operation(summary = "Add a new Job Post")
    @PostMapping("/add-post")
    public ResponseEntity<JobPostDto> addJobPost(@RequestBody JobPostDto jobPostDto){
        return new ResponseEntity<>(jobPostService.addJobPost(jobPostDto),
                HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Search Job Posts")
    @GetMapping("/posts/{text}")
    public ResponseEntity<List<JobPostDto>> searchJobPosts(@PathVariable String text){
        return ResponseEntity.ok(jobPostService.searchJobsByText(text));
    }
}
