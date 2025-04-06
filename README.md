# Resume Analyzer

A web application that analyzes resumes using GPT and provides detailed feedback and recommendations. The project leverages a Java Spring Boot backend and a React + TypeScript frontend to offer an intuitive interface for users to upload resumes, optionally include a job description, and receive actionable insights.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Deployment](#deployment)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview

The Resume Analyzer uses OpenAI's GPT to analyze resumes and provide structured feedback. Users can upload their resume file (PDF, DOC, DOCX, TXT) and optionally paste a job description to receive a job match score and improvement recommendations.

## Features

- **Resume Upload:** Supports multiple file formats.
- **Job Description Analysis:** Optionally paste a job description for a tailored job match score.
- **GPT-based Feedback:** Provides detailed analysis focusing on:
  - Key skills and expertise
  - Clarity and organization
  - Areas for improvement
  - Recommendations for enhancement
- **JSON-formatted Output:** Feedback is returned as JSON, making it easy to parse.
- **Secure API Key Management:** API key is securely managed via environment variables loaded from a `.env` file.

## Tech Stack

- **Backend:** Java, Spring Boot, OkHttp, Apache PDFBox, org.json
- **Frontend:** React, TypeScript, Axios
- **Build Tool:** Maven
- **Environment Management:** `.env` file (loaded using dotenv-java)

## Getting Started

### Prerequisites

- Java 17 or higher (Java 24 as configured)
- Maven 3.6+
- Node.js and npm (for frontend)
- An OpenAI API key

### Backend Setup

1. **Clone the Repository:**

   ```bash
   git clone <your-repository-url>
   cd resumeanalyser

2. **Configure Environment Variables:**

   ```bash
   OPENAI_API_KEY=sk-your_openai_api_key_here

3. **Build and Run the Backend:**

   ```bash
   mvn clean install
   mvn spring-boot:run

### Frontend Setup

1. **Navigate to the Frontend Folder:**

   ```bash
   cd ../resume-analyzer-frontend

2. **Install Dependencies:**

   ```bash
   npm install

3. **Run the Frontend:**

   ```bash
   npm start

## Security

- **API Key Management:** The OpenAI API key is stored securely using environment variables loaded from a .env file.
- **.gitignore:** Sensitive files like .env are excluded via the .gitignore file to prevent accidental commits.

## Contributing

Contributions are welcome! Please fork the repository, create a feature branch, and submit pull requests. For major changes, open an issue first to discuss your ideas.

## License

This project is licensed under the MIT License.

## Contact

For questions, issues, or feature requests, please open an issue or contact me at surajlokesh12@gmail.com.

   
