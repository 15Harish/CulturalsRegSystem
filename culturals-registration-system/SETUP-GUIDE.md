# Step-by-Step Guide to Run College Culturals Registration System in VSCode

## Part 1: Install Prerequisites

### 1.1 Install Java JDK 17

1. Go to https://www.oracle.com/java/technologies/downloads/
2. Download JDK 17 for your operating system
3. Install and note the installation path
4. Verify installation:
   ```bash
   java -version
   ```
   Should show: java version "17.x.x"

### 1.2 Install MySQL

1. Go to https://dev.mysql.com/downloads/mysql/
2. Download MySQL Community Server
3. During installation:
   - Set root password (remember this!)
   - Choose default settings
4. Verify installation:
   ```bash
   mysql --version
   ```

### 1.3 Install Visual Studio Code

1. Go to https://code.visualstudio.com/
2. Download and install VSCode
3. Open VSCode

### 1.4 Install VSCode Extensions

Open VSCode and press `Ctrl+Shift+X`, then search and install:

1. **Extension Pack for Java** (by Microsoft)
   - Includes Java Language Support, Debugging, Testing, Maven

2. **Spring Boot Extension Pack** (by VMware)
   - Includes Spring Boot Tools, Spring Initializr

3. **MySQL** (by Jun Han) - Optional but helpful
   - For database management within VSCode

## Part 2: Setup Database

### 2.1 Start MySQL Server

**Windows:**
- Open Services (Win+R, type `services.msc`)
- Find "MySQL" and click "Start"

**Mac/Linux:**
```bash
sudo systemctl start mysql
```

### 2.2 Create Database

**Option 1: Using MySQL Command Line**
```bash
mysql -u root -p
# Enter your MySQL password
```

Then run:
```sql
CREATE DATABASE culturals_db;
USE culturals_db;
exit;
```

**Option 2: Using MySQL Workbench**
1. Open MySQL Workbench
2. Connect to local instance
3. Click "Create New Schema"
4. Name it: `culturals_db`
5. Click "Apply"

### 2.3 Run Initialization Script

1. In MySQL Workbench or command line:
```bash
mysql -u root -p culturals_db < database-init.sql
```

Or copy and paste the contents of `database-init.sql` into MySQL Workbench and execute.

## Part 3: Configure the Project

### 3.1 Open Project in VSCode

1. Open VSCode
2. Click "File" > "Open Folder"
3. Navigate to `culturals-registration-system` folder
4. Click "Select Folder"

### 3.2 Update application.properties

1. In VSCode, open: `src/main/resources/application.properties`

2. Update database password:
```properties
spring.datasource.password=YOUR_MYSQL_ROOT_PASSWORD
```

3. Configure email (for Gmail):
   
   **Get Gmail App Password:**
   - Go to: https://myaccount.google.com/security
   - Enable 2-Step Verification
   - Go to: https://myaccount.google.com/apppasswords
   - Select "Mail" and "Other (Custom name)"
   - Type: "Culturals App"
   - Copy the generated 16-character password

   **Update in application.properties:**
   ```properties
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-16-char-app-password
   ```

### 3.3 Wait for Dependencies to Download

- VSCode will automatically download Maven dependencies
- Check bottom-right corner for "Java" tasks
- Wait until you see "✓ Ready" or "Build Success"

## Part 4: Build and Run

### 4.1 Build the Project

**Option 1: Using VSCode Terminal**
1. Open Terminal: `View` > `Terminal` or `Ctrl+\``
2. Run:
```bash
./mvnw clean install
```

**Option 2: Using Maven Extension**
1. Click Maven icon in left sidebar
2. Expand your project
3. Right-click "Lifecycle" > "clean"
4. Right-click "Lifecycle" > "install"

### 4.2 Run the Application

**Option 1: Direct Run**
1. Open: `src/main/java/com/culturals/registration/CulturalsRegistrationApplication.java`
2. Right-click in the editor
3. Select "Run Java"

**Option 2: Using Spring Boot Dashboard**
1. Look for "Spring Boot Dashboard" in left sidebar
2. Find your application
3. Click the green play ▶ button

**Option 3: Using Terminal**
```bash
./mvnw spring-boot:run
```

### 4.3 Verify Application Started

Look for this in the console:
```
Started CulturalsRegistrationApplication in X.XXX seconds
```

## Part 5: Access and Test

### 5.1 Open Application

Open your browser and go to:
```
http://localhost:8080
```

You should see the College Culturals homepage!

### 5.2 Test Participant Registration

1. Click "Register Now"
2. Fill in the form:
   - Name: Test User
   - Department: CSE
   - Roll Number: 21CS999
   - Email: test@example.com
   - Phone: 9876543210
   - Gender: Male
   - Password: test123

3. Click "Register"
4. Login with the same credentials

### 5.3 Test Admin Login

1. Go to: http://localhost:8080/admin/login
2. Login with:
   - Email: admin@college.edu
   - Password: admin123

## Part 6: Common Issues and Solutions

### Issue 1: Port 8080 Already in Use

**Solution:**
Edit `application.properties`:
```properties
server.port=8081
```
Then access: http://localhost:8081

### Issue 2: Can't Connect to Database

**Solution:**
1. Check MySQL is running
2. Verify database name: `culturals_db`
3. Verify username: `root`
4. Verify password in `application.properties`
5. Try connecting manually:
   ```bash
   mysql -u root -p culturals_db
   ```

### Issue 3: Dependencies Not Downloading

**Solution:**
1. Check internet connection
2. Delete `.m2` folder (Maven cache)
3. In terminal:
   ```bash
   ./mvnw clean install -U
   ```

### Issue 4: Email Not Sending

**Solutions:**
1. Verify you're using Gmail App Password (not regular password)
2. Check spam folder
3. Temporarily disable email to test other features:
   - Comment out email code in `EmailService.java`

### Issue 5: Java Not Found

**Solution:**
1. Verify Java installation: `java -version`
2. Set JAVA_HOME environment variable:
   
   **Windows:**
   ```
   setx JAVA_HOME "C:\Program Files\Java\jdk-17"
   ```
   
   **Mac/Linux:**
   Add to `~/.bashrc` or `~/.zshrc`:
   ```
   export JAVA_HOME=/path/to/jdk-17
   export PATH=$JAVA_HOME/bin:$PATH
   ```

### Issue 6: Maven Wrapper Not Found

**Solution:**
```bash
# Windows
mvnw.cmd clean install

# Mac/Linux
chmod +x mvnw
./mvnw clean install
```

## Part 7: Development Workflow

### Making Changes

1. **Backend Changes (Java files):**
   - Edit controller/service/repository
   - Save file (Ctrl+S)
   - Stop application (Ctrl+C in terminal)
   - Run again

2. **Frontend Changes (HTML):**
   - Edit HTML files
   - Save
   - Refresh browser (F5)

3. **Database Changes:**
   - Either run SQL directly in MySQL
   - Or update entity classes and restart (Spring will update schema)

### Debugging

1. **Add Breakpoints:**
   - Click left of line numbers in Java files
   - Red dot appears

2. **Run in Debug Mode:**
   - Right-click `CulturalsRegistrationApplication.java`
   - Select "Debug Java"

3. **Step Through Code:**
   - Use Debug toolbar: Step Over (F10), Step Into (F11)

## Part 8: Project Structure Explained

```
culturals-registration-system/
│
├── src/main/java/com/culturals/registration/
│   ├── controller/          # Handles HTTP requests
│   │   ├── HomeController.java
│   │   ├── ParticipantController.java
│   │   ├── AdminController.java
│   │   └── EventController.java
│   │
│   ├── domain/             # Database entities
│   │   ├── Participant.java
│   │   ├── Admin.java
│   │   ├── Event.java
│   │   ├── EventRegistration.java
│   │   └── Winner.java
│   │
│   ├── repository/         # Database operations
│   │   ├── ParticipantRepository.java
│   │   ├── AdminRepository.java
│   │   ├── EventRepository.java
│   │   ├── EventRegistrationRepository.java
│   │   └── WinnerRepository.java
│   │
│   ├── service/            # Business logic
│   │   ├── ParticipantService.java
│   │   ├── AdminService.java
│   │   ├── EventService.java
│   │   ├── EventRegistrationService.java
│   │   ├── WinnerService.java
│   │   └── EmailService.java
│   │
│   └── CulturalsRegistrationApplication.java  # Main class
│
├── src/main/resources/
│   ├── templates/          # HTML pages
│   │   ├── index.html
│   │   ├── participant-register.html
│   │   ├── participant-login.html
│   │   ├── participant-dashboard.html
│   │   ├── admin-login.html
│   │   └── admin-dashboard.html
│   │
│   ├── static/            # CSS, JS, images
│   └── application.properties  # Configuration
│
├── pom.xml                # Maven dependencies
├── database-init.sql      # Initial database data
└── README.md             # Documentation
```

## Part 9: Next Steps

### Add More Features

1. **Add more HTML pages** in `src/main/resources/templates/`
2. **Add controllers** for new functionality
3. **Update services** for business logic
4. **Add repositories** for database queries

### Deploy to Production

1. Change `application.properties`:
   ```properties
   spring.jpa.hibernate.ddl-auto=validate
   ```
2. Build JAR:
   ```bash
   ./mvnw clean package
   ```
3. Run JAR:
   ```bash
   java -jar target/registration-0.0.1-SNAPSHOT.jar
   ```

## Part 10: Keyboard Shortcuts

**VSCode:**
- `Ctrl+Shift+P` - Command Palette
- `Ctrl+\`` - Toggle Terminal
- `Ctrl+B` - Toggle Sidebar
- `F5` - Start Debugging
- `Shift+F5` - Stop Debugging
- `Ctrl+Shift+F` - Find in Files
- `Ctrl+P` - Quick Open File

**Java Specific:**
- `F2` - Rename
- `F12` - Go to Definition
- `Shift+F12` - Find References
- `Ctrl+.` - Quick Fix

## Support

If you encounter issues:
1. Check console for error messages
2. Verify MySQL is running
3. Check application.properties configuration
4. Review README.md troubleshooting section
5. Check Spring Boot logs in terminal

Good luck with your project! 🚀
