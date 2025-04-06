package com.resumeanalyser.resumeanalyser.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONObject;

@Service
public class GPTService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Autowired
    private HttpServletRequest request;

    private final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    

    public String getResumeFeedback(String resumeText) throws IOException {

        String jobDesc = request.getParameter("jobDescription");
        String promptInstructions = """
    You are an expert resume analyzer. Analyze the following resume and provide detailed feedback.
    Focus on:
    1. Key skills and expertise
    2. Clarity and organization
    3. Areas for improvement
    4. Match with the job description (if provided)
    
    Format your response in these points and it should look like this:
    - summary: Brief overview of the resume
    - skills: List of key skills identified
    - clarityFeedback: Feedback on resume clarity and organization
    - improvementAreas: Specific areas that need improvement
    - jobMatchScore: Score out of 100 for job match (if job description provided) or give "no job description provided"
    - recommendations: Specific recommendations for improvement
    """;

    String prompt = promptInstructions + "\n\nResume:\n" + resumeText;
    if (jobDesc != null && !jobDesc.isEmpty()) {
        prompt += "\n\nJob Description:\n" + jobDesc;
    }

OkHttpClient client = new OkHttpClient();

JSONObject message = new JSONObject();
message.put("role", "user");
message.put("content", prompt);

JSONObject body = new JSONObject();
body.put("model", "gpt-3.5-turbo");
body.put("messages", List.of(message));
body.put("temperature", 0.7);

Request request = new Request.Builder()
        .url(OPENAI_URL)
        .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

try (Response response = client.newCall(request).execute()) {
    if (response.isSuccessful()) {
        JSONObject json = new JSONObject(response.body().string());
        return json.getJSONArray("choices")
                   .getJSONObject(0)
                   .getJSONObject("message")
                   .getString("content");
    } else {
        throw new IOException("OpenAI API Error: " + response.message());
    }
}
    }
}
