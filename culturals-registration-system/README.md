# College Culturals Registration System

A comprehensive web application for managing college cultural event registrations built with Spring Boot following MVC architecture.

## Features

### Participant Features
- User registration and login with OTP
- View available cultural events
- Register for events with special requirements
- Email confirmation after registration
- View personal event registrations
- Department-wise leaderboard

### Admin Features
- Admin login
- Create, edit, and delete events
- View all event registrations
- Auto-hide events when slots are full
- Manage department points
- View participant feedback

## Technology Stack

- **Backend**: Java 17, Spring Boot 3.2.5
- **Frontend**: Thymeleaf, HTML5, CSS3, Tailwind CSS
- **Database**: MySQL
- **Build Tool**: Maven
- **Email**: Spring Mail (Gmail SMTP)

## Project Structure

```
culturals-registration-system/
├── src/
│   ├── main/
│   │   ├── java/com/culturals/registration/
│   │   │   ├── controller/          # MVC Controllers
│   │   │   ├── domain/              # Entity classes
│   │   │   ├── repository/          # JPA Repositories
│   │   │   └── service/             # Business logic
│   │   └── resources/
│   │       ├── templates/           # Thymeleaf HTML templates
│   │       ├── static/              # CSS, JS, images
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Prerequisites

Before running the application, ensure you have:

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/

2. **MySQL Server**
   - Download from: https://dev.mysql.com/downloads/mysql/

3. **Maven** (usually included with IDEs)
   - Download from: https://maven.apache.org/download.cgi

4. **Visual Studio Code** with extensions:
   - Extension Pack for Java
   - Spring Boot Extension Pack
   - Live Server (optional)

## Setup Instructions for VSCode

### Step 1: Install VSCode Extensions

1. Open VSCode
2. Go to Extensions (Ctrl+Shift+X)
3. Search and install:
   - "Extension Pack for Java" by Microsoft
   - "Spring Boot Extension Pack" by VMware
   - "Maven for Java" by Microsoft

### Step 2: Create MySQL Database

1. Open MySQL Command Line or MySQL Workbench
2. Create a new database:

```sql
CREATE DATABASE culturals_db;
```

3. (Optional) Create a MySQL user:

```sql
CREATE USER 'culturals_user'@'localhost' IDENTIFIED BY 'culturals_password';
GRANT ALL PRIVILEGES ON culturals_db.* TO 'culturals_user'@'localhost';
FLUSH PRIVILEGES;
```

### Step 3: Configure Application Properties

1. Open `src/main/resources/application.properties`
2. Update the database credentials:

```properties
# Update these values
spring.datasource.url=jdbc:mysql://localhost:3306/culturals_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

3. Configure email settings (for Gmail):
   - Go to your Google Account
   - Enable 2-Step Verification
   - Generate an App Password: https://myaccount.google.com/apppasswords
   - Update in application.properties:

```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### Step 4: Open Project in VSCode

1. Open VSCode
2. File > Open Folder
3. Select the `culturals-registration-system` folder
4. VSCode will automatically detect it as a Maven project

### Step 5: Build the Project

**Option 1: Using VSCode Terminal**
```bash
./mvnw clean install
```

**Option 2: Using Command Palette**
1. Press Ctrl+Shift+P
2. Type "Maven: Execute commands"
3. Select "clean install"

### Step 6: Run the Application

**Option 1: Using VSCode**
1. Open `CulturalsRegistrationApplication.java`
2. Right-click in the editor
3. Select "Run Java"

**Option 2: Using Terminal**
```bash
./mvnw spring-boot:run
```

**Option 3: Using Spring Boot Dashboard**
1. Click on Spring Boot Dashboard in VSCode sidebar
2. Click the play button next to your application

### Step 7: Access the Application

Open your browser and go to:
```
http://localhost:8080
```

## Initial Setup

### Create Admin Account

Since there's no admin registration page, you need to manually insert an admin into the database:

```sql
USE culturals_db;

INSERT INTO admins (username, email, password) 
VALUES ('admin', 'admin@college.edu', 'admin123');
```

### Create Sample Event (Optional)

```sql
INSERT INTO events (event_name, description, duration, event_time, venue, 
                    max_participants, registration_deadline, is_visible, registered_count)
VALUES ('Dance Competition', 'Inter-department dance competition', 120, 
        '2024-03-15 14:00:00', 'Main Auditorium', 50, 
        '2024-03-10 23:59:59', true, 0);
```

## Usage Flow

### For Participants:
1. Go to http://localhost:8080
2. Click "Register Now"
3. Fill in registration details
4. Login with credentials
5. Browse available events
6. Register for events
7. Receive email confirmation

### For Admins:
1. Go to http://localhost:8080
2. Click "Admin Login"
3. Enter admin credentials
4. Create and manage events
5. View registrations
6. Update department points

## API Endpoints

### Participant Endpoints
- `GET /participant/register` - Show registration form
- `POST /participant/register` - Register new participant
- `GET /participant/login` - Show login form
- `POST /participant/login` - Login participant
- `GET /participant/dashboard` - Participant dashboard
- `GET /participant/logout` - Logout

### Event Endpoints
- `GET /events/available` - View available events
- `GET /events/{id}` - View event details
- `GET /events/{id}/register` - Register for event
- `GET /events/my-registrations` - View my registrations

### Admin Endpoints
- `GET /admin/login` - Admin login
- `GET /admin/dashboard` - Admin dashboard
- `GET /events/admin/create` - Create event form
- `POST /events/admin/create` - Create event
- `GET /events/admin/list` - List all events
- `GET /events/admin/{id}/edit` - Edit event
- `POST /events/admin/{id}/delete` - Delete event

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `application.properties`:
```properties
server.port=8081
```

### Database Connection Issues
- Verify MySQL is running
- Check database name, username, and password
- Ensure MySQL port (3306) is not blocked

### Email Not Sending
- Verify Gmail credentials
- Ensure "App Password" is used, not regular password
- Check if "Less secure app access" is enabled (if not using App Password)

### Build Errors
```bash
# Clean and rebuild
./mvnw clean install -U

# Skip tests if needed
./mvnw clean install -DskipTests
```

## Database Schema

The application auto-creates tables on first run (using `spring.jpa.hibernate.ddl-auto=update`):

- `participants` - Participant details
- `admins` - Admin accounts
- `events` - Event information
- `event_registrations` - Event registrations
- `winners` - Event winners and points

## Testing

Run tests using:
```bash
./mvnw test
```

## Future Enhancements

- Photo upload for events
- Real-time WhatsApp OTP
- Payment integration
- QR code for event entry
- Participant reviews and ratings
- Certificate generation
- Mobile app

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is created for educational purposes.

## Support

For issues or questions, please create an issue in the repository.

---

**Author**: G HARISH (311123205019)
**Institution**: LICET
**Course**: DevOps Lab
