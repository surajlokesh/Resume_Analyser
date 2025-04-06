import React, { useState, ChangeEvent, FormEvent } from "react";
import axios from "axios";
import "./ResumeUpload.css";

const ResumeUpload: React.FC = () => {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [jobDescription, setJobDescription] = useState<string>("");
  const [feedback, setFeedback] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  // Handle file input changes
  const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files.length > 0) {
      setSelectedFile(event.target.files[0]);
      setError("");
    }
  };

  // Handle job description text area changes
  const handleJobDescriptionChange = (
    event: ChangeEvent<HTMLTextAreaElement>
  ) => {
    setJobDescription(event.target.value);
  };

  // Handle form submission
  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!selectedFile) {
      setError("Please select a resume file.");
      return;
    }

    setError("");
    setLoading(true);

    const formData = new FormData();
    formData.append("file", selectedFile);
    // Append the job description (optional)
    formData.append("jobDescription", jobDescription);

    try {
      const response = await axios.post<string>(
        "http://localhost:8080/api/resume/analyze",
        formData,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );
      setFeedback(response.data);
    } catch (err) {
      console.error(err);
      setError("Error analyzing resume.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="upload-container">
      <h2>Resume Analyzer</h2>
      <form className="upload-form" onSubmit={handleSubmit}>
        <label>
          Resume File:
          <input
            type="file"
            accept=".pdf,.doc,.docx,.txt"
            onChange={handleFileChange}
          />
        </label>
        <br />
        <br />
        <label>
          Job Description (Optional):
          <textarea
            value={jobDescription}
            onChange={handleJobDescriptionChange}
            placeholder="Paste the job description here..."
            rows={5}
            style={{ width: "100%", marginTop: "0.5rem" }}
          />
        </label>
        <br />
        <br />
        <button className="upload-button" type="submit">
          Analyze Resume
        </button>
      </form>
      {loading && <p className="loading-text">Analyzing your resume...</p>}
      {error && <p className="error-text">{error}</p>}
      {feedback && (
        <div className="feedback-container">
          <h3>Analysis Feedback</h3>
          <pre className="feedback-pre">
            {typeof feedback === "object"
              ? JSON.stringify(feedback, null, 2)
              : feedback}
          </pre>
        </div>
      )}
    </div>
  );
};

export default ResumeUpload;
