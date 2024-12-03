# QuizeApp
# QuizApp

A Spring Boot-based application for creating and managing quizzes.

## Features
- Add questions to the quiz.
- Users can take quizzes and answer questions.
- Tracks user performance, including correct answers and overall score.

---

## Prerequisites

Ensure you have the following installed:
- Java 17 or later
- Maven 3.6 or later
- MySQL (or any database of your choice)

---

## Getting Started

### 1. Clone the Repository

How to Use the QuizApp
Step 1: Adding Questions
To add questions to the quiz, send a POST request to the /quiz/add-question endpoint with a JSON body containing the question text, options, and the correct answer.

Step 2: Taking the Quiz
Users can take the quiz by sending a POST request to the /quiz/take/{userId} endpoint, replacing {userId} with the actual user ID. The response will contain a random question for the user to answer.

Step 3: Submitting Answers
After receiving a question, the user can submit their answer by sending a POST request to the /quiz/submit/{userId} endpoint with the selected option in the request body.

Step 4: Checking User Performance
To check user performance, send a GET request to the /quiz/dashboard/{userId} endpoint. This will return the total questions attempted, correct answers, and overall score percentage.

Step 5: Ending the Quiz
To end the quiz session, send a POST request to the /quiz/end/{userId} endpoint to get the final performance metrics.

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>



Testing with Postman
You can test the above API endpoints using Postman:

Set the HTTP method to POST or GET as needed.
Enter the URL (e.g., http://localhost:8080/quiz/add-question).
Set the request body to JSON format where applicable.
Send the request and observe the response.
Updating the Project
To update your project with the latest changes from GitHub:

Pull Latest Changes
If updates are available on the GitHub repository, sync them with:

bash
Copy code
git pull origin main
Push Local Changes
To push your local changes to GitHub:

bash
License
This project is licensed under the MIT License. See the LICENSE file for details.

Acknowledgments
Spring Boot for the application framework.
MySQL for database management.
Postman for API testing.



