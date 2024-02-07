package com.hcd.genaispringai.controller;

import com.hcd.genaispringai.response.JobDescription;
import com.hcd.genaispringai.response.JobReasons;
import com.hcd.genaispringai.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

    private final OpenAiService openAiService;

    public OpenAiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @GetMapping("/job-description")
    public ResponseEntity<String> jobDescription(@RequestParam("job") String job,
                                                         @RequestParam("location") String location) {
        return ResponseEntity.ok(openAiService.jobDescription(job, location));
    }

    @GetMapping("/job-description/formatted")
    public ResponseEntity<JobDescription> formattedJobDescription(@RequestParam("job") String job,
                                                                  @RequestParam("location") String location) {
        return ResponseEntity.ok(openAiService.formattedJobDescription(job, location));
    }

    @GetMapping("/job-reasons")
    public ResponseEntity<String> jobReasons(@RequestParam(value = "count", required = false, defaultValue = "3") int count,
                                             @RequestParam("job") String job,
                                             @RequestParam("location") String location) {
        return ResponseEntity.ok(openAiService.jobReasons(count, job, location));
    }

    @GetMapping("/job-reasons/formatted")
    public ResponseEntity<JobReasons> formattedJobReasons(@RequestParam(value = "count", required = false, defaultValue = "3") int count,
                                                          @RequestParam("job") String job,
                                                          @RequestParam("location") String location) {
        return ResponseEntity.ok(openAiService.formattedJobReasons(count, job, location));
    }
}
