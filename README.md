
# Book Social Network

## Overview
The **Book Social Network** is a Java-based web application designed to foster connections among book enthusiasts. Users can register, log in, and engage in book-related social activities while managing their profiles securely.

## Features
- **Authentication:** User registration, login, and account verification.
- **Profile Management:** Secure handling of user data and profiles.
- **Book Service:** Connect and share insights about books.
- **Email Services:** Verification email and password recovery.

## Project Structure
The project follows a standard Java Spring Boot structure(Modern Driven Architecture). Key components include:

### Main Application
- **`BookSocialNetworkApplication.java`**: The entry point of the application.

### Authentication Module
- **Controllers**
  - `AuthController.java`: Handles authentication-related requests.
- **DTOs**
  - `LoginRequest.java`: For user login data.
  - `LoginResponse.java`: For login responses.
  - `RegisterRequest.java`: For user registration data.
  - `VerifyRequest.java`: For account verification.
- **Services**
  - `ResentVerificationEmailService.java`: Resends verification emails.
  - `UserActivationService.java`: Manages user account activation.
  - `UserLoginService.java`: Handles user login logic.
  - `UserRegisterService.java`: Manages user registration logic.

### book Module
- **Controllers**
  - `BookController.java`: Handles authentication-related requests.
- **DTOs**
  - `BookMapper.java`: For map the book with its dto (BookResponse and BorrowedBookResponse)
  - `BookRequest.java`: For retured Book data
  - `BookResponse.java`: For accespt book data.
  - `BorrowedBookResponse.java`: For retured BorrowedBook data.
- **Services**
  - `FindAllBooksByOwnerService.java`
  - `FindAllDisplayableBookService.java`
  - `FindBookByIdService.java`
  - `GetAllBorrowedBookService.java`
  - `SaveBook.java`

## Prerequisites
- **Java 21**
- **Maven**
- **MySQL Database**

## Setup Instructions
1. Clone the repository:
   ```
   git clone git@github.com:mostafa-kaoud15/book-social-network.git
   ```
2. Navigate to the project directory:
   ```
   cd book-social-network
   ```
3. Configure the database in `application.properties`:
   ```
   spring.datasource.url=<database-url>
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   ```
4. Build and run the application:
   ```
   mvn spring-boot:run
   ```

## API Endpoints
### Authentication
- **POST /auth/register**: Register a new user.
- **POST /auth/login**: Login with credentials.
- **POST /auth/verify**: Verify account.
- **POST /auth/resend-verification**: Resend verification email.
### Book
- **GET /book**: Return all sharable books
- **GET /book/{id}**: Get specific book with its id.
- **POST /book/save**: Saveing an book.
- **GET /book/owner**: Get all the books owned by a specific owner.
- **GET /book/borrowed**: Get all borrowed books by user
- **POST /book/borrow/{id}**: Borrowed book by user

## Technologies Used
- **Java 21**
- **Spring Boot**
- **MySQL**
- **Maven**
