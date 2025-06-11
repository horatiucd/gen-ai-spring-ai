package com.hcd.genaispringai.service;

import com.hcd.genaispringai.response.JobDescription;
import com.hcd.genaispringai.response.JobReasons;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final ChatClient client;

    public OpenAiService(ChatClient.Builder chatClientBuilder) {
        client = chatClientBuilder.defaultSystem("You are a helpful HR assistant.")
                .build();
    }

    public String jobDescription(String job, String location) {
        final String promptText = """
                Write a one paragraph job description for a {job} based in {location}.
                """;

        final PromptTemplate promptTemplate = new PromptTemplate(promptText);
        promptTemplate.add("job", job);
        promptTemplate.add("location", location);

        return client.prompt(promptTemplate.create())
                .call()
                .content();
    }

    public JobDescription formattedJobDescription(String job, String location) {
        final String promptText = """
                Write a one paragraph job description for a {job} based in {location}.
                """;

        return client.prompt()
                .user(userSpec -> userSpec.text(promptText)
                        .param("job", job)
                        .param("location", location))
                .call()
                .entity(JobDescription.class);
    }

    public String jobReasons(int count, String domain, String location) {
        final String promptText = """
                Write {count} reasons why people in {location} should consider a {job} job.
                These reasons need to be short, so they fit on a poster.
                For instance, "{job} jobs are rewarding."
                """;

        final PromptTemplate promptTemplate = new PromptTemplate(promptText);
        promptTemplate.add("count", count);
        promptTemplate.add("job", domain);
        promptTemplate.add("location", location);

        return client.prompt(promptTemplate.create())
                .call()
                .content();
    }

    public JobReasons formattedJobReasons(int count, String job, String location) {
        final String promptText = """
                Write {count} reasons why people in {location} should consider a {job} job.
                These reasons need to be short, so they fit on a poster.
                For instance, "{job} jobs are rewarding."
                """;

        return client.prompt()
                .user(userSpec -> userSpec.text(promptText)
                        .param("count", count)
                        .param("job", job)
                        .param("location", location))
                .call()
                .entity(JobReasons.class);
    }
}
