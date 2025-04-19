## 🌿 GreenTrack – Eco Activity Web App

Welcome to **GreenTrack**, your eco-companion for tracking sustainability efforts! This web application allows individuals to track their eco-friendly activities, participate in events, earn rewards, and climb the leaderboard — all while contributing to a greener planet. It enables users to seamlessly register, log in, and manage their event participation.

Authenticated users can:
- View a personalized homepage
- Explore upcoming public events
- Enroll or drop events
- Update their profile information

The application includes a **reward point system** that ranks users on a leaderboard, encouraging engagement and participation. It uses **Thymeleaf** for front-end rendering, **Spring MVC** for request handling, and integrates with a **service layer** for user and event logic, ensuring modular and scalable design. **Session management** is also implemented to handle secure logins and user tracking.

---

## ✨ Features

### User Side
- Sign Up / Login with secure authentication
- Enroll in green events and earn reward points
- View leaderboard to see how your eco-impact ranks
- Track personal activities like recycling, carpooling, etc.
- Profile management (update/delete account)
- Contact GreenTrack team via a dedicated form

### Admin Side
- Admin Login portal
- Manage Events: Create, Read, Update, Delete
- User insights for monitoring participation and growth

### Security
- Role-based access control using Spring Security
- Access restrictions for authenticated users and admins

---

## 🚀 Technologies Used
- **Frontend**: HTML, CSS, Bootstrap, Thymeleaf
- **Backend**: Java, Spring Boot (MVC, Security, Data JPA)
- **Database**: MySQL
- **Build Tool**: Maven
- **Version Control**: Git

---

## 📂 Project Structure
```text
J2EE_Project_GreenTrack/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/luv2code/springboot/thymeleafdemo/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── security/
│   │   │       ├── config/
│   │   │       ├── repository/
│   │   │       └── services/
│   │   └── resources/
│   │       ├── templates/fragments         # Thymeleaf HTML files
│   │       ├── static/css/                 # CSS styles
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## 📆 Database

The MySQL schema includes the following tables:

The `event_m` database is designed to manage users, admins, events, and eco-friendly personal activities. It allows users to register, enroll in events, earn reward points, and track sustainable actions, supporting community engagement and environmental awareness.

### 1. `admins`
Stores admin login credentials.  
**Fields:**
- `id` (Primary Key, Auto Increment): Unique ID for each admin.  
- `username` (Unique): Admin's login username.  
- `password`: Admin's password.  

### 2. `users`
Stores user profile and reward details.  
**Fields:**
- `id` (Primary Key, Auto Increment): Unique ID for each user.  
- `name`: Full name of the user.  
- `username` (Unique): Chosen username.  
- `password`: Encrypted password for login.  
- `email`: User’s email address.  
- `address`: Physical address of the user.  
- `total_reward_points`: Accumulated reward points based on event participation.  

### 3. `events`
Stores details about available events.  
**Fields:**
- `id` (Primary Key, Auto Increment): Unique event ID.  
- `event_name` (Unique): Name of the event.  
- `description`: Brief event description.  
- `event_date`: Scheduled date for the event.  
- `location`: Venue of the event.  
- `reward`: Reward points assigned for participation.  

### 4. `enrolled_events`
Links users to the events they’ve enrolled in.  
**Fields:**
- `id` (Primary Key, Auto Increment): Unique enrollment ID.  
- `event_id`: Foreign key referencing `events(id)`.  
- `user_id`: Foreign key referencing `users(id)`.  

### 5. `personal_activities`
Stores eco-friendly actions that users can perform individually.  
**Fields:**
- `id` (Primary Key, Auto Increment): Unique activity ID.  
- `title`: Name of the activity.  
- `description`: How to perform the activity.  
- `impact`: Environmental benefit of the activity.  

> **Note**: The full SQL script is available in the repo as `Database.sql`

---

## 🔎 Routes and Functionality

### General Routes (Main Controller)
- `GET /` — Loads the home page of the application.
- `GET /activitiesPage` — Displays the list of personal eco-friendly activities.
- `GET /rank` — Shows the leaderboard sorted by reward points.
- `GET /event` — Loads the event list for users.
- `GET /contact` — Displays the contact page.

### ActivityController
- `GET /activities` — Fetches and displays all personal activities in the system.

### AdminController
- `GET /admin/login` — Displays the admin login form.
- `POST /admin/login` — Processes the admin login request.
- `GET /admin/dashboard` — Loads the admin dashboard with all events listed.
- `GET /admin/events/add` — Shows the form to add a new event.
- `POST /admin/events/add` — Handles the submission of a new event.
- `GET /admin/events/update/{id}` — Displays the form to update an existing event.
- `POST /admin/events/update/{id}` — Processes the event update form submission.
- `GET /admin/events/delete/{id}` — Deletes a specific event by ID.
- `GET /admin/logout` — Logs out the admin by invalidating the session.

### EventController
- `GET /events` — Displays the list of all public events.
- `POST /enroll` — Handles user enrollment for a specific event.
- `POST /admin/events/new` — Allows the admin to create a new event (alternative POST route).
- `GET /events/{id}` — Displays event details for a given ID.
- `POST /admin/events/{id}/update` — Updates an existing event by ID.
- `POST /admin/events/{id}/delete` — Deletes an event by ID.

### UserController Routes
- `GET /user/signup` – Displays the user sign-up form.
- `POST /user/signup` – Handles user registration and shows a login prompt upon success.
- `GET /user/login` – Displays the user login form.
- `POST /user/login` – Authenticates the user and redirects to the home page if credentials are valid.
- `GET /user/home/{id}` – Displays the home page of the logged-in user.
- `GET /user/{id}` – Displays detailed profile information of a specific user.
- `GET /user/events/{id}` – Displays available and enrolled events for a user.
- `GET /user/update/{id}` – Shows the form for updating the user's information.
- `POST /user/update/{id}` – Handles the update of user information.
- `POST /user/enroll/{userId}/{eventId}` – Enrolls a user in a selected event.
- `POST /user/drop/{userId}/{eventId}` – Allows a user to drop (unenroll from) an event.
- `GET /user/leaderboard` – Displays the leaderboard of users sorted by reward points.
- `GET /user/logout` – Logs out the user and redirects to the login page.

---

## 🌐 REST API Layer

The REST API layer in the GreenTrack project is designed to handle HTTP requests related to user interactions, event management, and eco-friendly activities, while ensuring smooth communication between the frontend and backend. We have achieved it by creating `EventRestController`, `UserRestController` and `AdminRestController`. The API uses Spring MVC to handle the requests and Spring Boot to manage the application’s configuration and dependencies.

---

## ✨ Getting Started

### Clone the Repository
```bash
git clone https://github.com/pp849/J2EE_Project_GreenTrack.git
```

### Prerequisites
- Java 17+
- MySQL Server
- Maven
- IDE (IntelliJ / Eclipse recommended)

### Set Up
1. Create Database:
   - Import the SQL script from `Database.sql`
   - Update `application.properties` with your DB credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/greentrack
     spring.datasource.username=yourUsername
     spring.datasource.password=yourPassword
     ```

2. Run the App:
```bash
mvn clean install
mvn spring-boot:run
```
Access: [http://localhost:8080](http://localhost:8080)

