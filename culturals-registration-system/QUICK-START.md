# QUICK START GUIDE

## Prerequisites Checklist
- [ ] Java 17 installed
- [ ] MySQL installed and running
- [ ] VSCode with Java extensions installed

## 5-Minute Setup

### 1. Database Setup (2 minutes)
```bash
# Login to MySQL
mysql -u root -p

# Create database
CREATE DATABASE culturals_db;
exit;
```

### 2. Configure Application (1 minute)
Edit `src/main/resources/application.properties`:
- Line 9: Update MySQL password
- Line 23-24: Update email credentials (optional for now)

### 3. Run Application (2 minutes)
```bash
# In project directory
./mvnw spring-boot:run
```

Wait for: `Started CulturalsRegistrationApplication`

### 4. Access Application
Open browser: http://localhost:8080

## Default Credentials

**Admin:**
- Email: admin@college.edu
- Password: admin123

**Test Participant:**
- Email: john@college.edu
- Password: password123

## First Steps

1. **As Participant:**
   - Register new account at: http://localhost:8080/participant/register
   - Login and view events
   - Register for an event

2. **As Admin:**
   - Login at: http://localhost:8080/admin/login
   - Create new events
   - View registrations

## Troubleshooting

**Can't connect to database?**
- Check MySQL is running
- Verify password in application.properties

**Port 8080 in use?**
- Change port in application.properties to 8081

**Build fails?**
```bash
./mvnw clean install -DskipTests
```

For detailed instructions, see SETUP-GUIDE.md
