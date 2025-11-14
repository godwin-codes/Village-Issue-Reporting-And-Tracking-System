# Village Issue Reporting and Tracking System

A simple Java web application (Jakarta Servlets) for reporting, viewing, and tracking village issues.

## Features
- User registration and login
- Report issues with title, description, and category
- View user's reported issues
- Admin dashboard to view all issues and update status

## Project Structure
- JSP pages at project root and `WebContent/includes`
- Java source files in `src/com/village/...`
- JDBC connection helper in `src/com/village/util/DBConnection.java`
- DAO classes: `UserDAO`, `IssueDAO`
- Servlets: `LoginServlet`, `RegisterServlet`, `ReportIssueServlet`, `UpdateStatusServlet`, `LogoutServlet`

## Prerequisites
- Java 11+ (or matching version for your servlet container)
- Apache Tomcat 10+ (or another Jakarta EE compatible container)
- MySQL server
- MySQL Connector/J in `WEB-INF/lib` (project already contains `mysql-connector-j-9.4.0.jar`)

## Quick start
- Deploy the WAR to Tomcat or run the project from your IDE (import as a Java web project).
- Set DB environment variables (example below), start MySQL and Tomcat, then open the app at: `http://localhost:8080/VillageIssueSystem`

## Database setup
Create the database and tables. Example SQL:

```sql
CREATE DATABASE IF NOT EXISTS village_issue_system;
USE village_issue_system;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) DEFAULT 'user'
);

CREATE TABLE issues (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  category VARCHAR(100),
  status VARCHAR(50) DEFAULT 'Pending',
  reported_by INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (reported_by) REFERENCES users(id) ON DELETE SET NULL
);
```


### Create an admin user
Run this after creating a user (replace `<user-id>`):
```sql
UPDATE users SET role='admin' WHERE id=<user-id>;
```
Or insert directly with `role='admin'`.

## Configuration
DB configuration is read from environment variables; `config.properties` is used as a fallback (do NOT commit it).

Environment variables (recommended):
- `DB_HOST` (default: `localhost`)
- `DB_PORT` (default: `3306`)
- `DB_NAME` (default: `village_issue_system`)
- `DB_USER` (default: `root`)
- `DB_PASSWORD` (default: empty)

Example (PowerShell, temporary for the session):
```powershell
$env:DB_HOST = "localhost"
$env:DB_PORT = "3306"
$env:DB_NAME = "village_issue_system"
$env:DB_USER = "root"
$env:DB_PASSWORD = "your_db_password"
```

Example (PowerShell, set permanently for current user):
```powershell
setx DB_HOST "localhost"
setx DB_PORT "3306"
setx DB_NAME "village_issue_system"
setx DB_USER "root"
setx DB_PASSWORD "your_db_password"
```

Or create `config.properties` in the project root (do NOT commit):
```
db.host=localhost
db.port=3306
db.name=village_issue_system
db.user=root
db.password=your_db_password
```

Make sure the MySQL connector is available at `WEB-INF/lib/mysql-connector-j-<version>.jar` (this project contains `mysql-connector-j-9.4.0.jar`).

## Important security notes
- Passwords: `UserDAO` currently stores passwords as plain text. For production, store salted bcrypt hashes instead. Recommended library: `jBCrypt` (add jar to `WEB-INF/lib` or use Maven).
- Secrets: Do not commit `config.properties` or `.env` files. `.gitignore` excludes these by default.

Quick bcrypt checklist:
1. Add `jBCrypt` jar to `WEB-INF/lib` (or dependency manager).
2. In `RegisterServlet`, hash the password before calling `UserDAO.registerUser`.
3. In `UserDAO.loginUser`, verify the password using `BCrypt.checkpw(plain, hashed)`.

## Run locally
1. Copy the project WAR to Tomcat's `webapps` folder or deploy from your IDE.
  - Example Tomcat path: `C:\path\to\tomcat\webapps\VillageIssueSystem.war`
2. Ensure MySQL is running and environment variables are set.
3. Start Tomcat and open `http://localhost:8080/VillageIssueSystem` (or your configured context path).

## Git
A `.gitignore` is included to prevent committing build artifacts, compiled classes, and secret files.
Repo: https://github.com/godwin-codes/Village-Issue-Reporting-And-Tracking-System

## License & contributing
- Consider adding a `LICENSE` (e.g., MIT) and `CONTRIBUTING.md` if you want community contributions.

## Next steps I can help with
- Add `schema.sql` to the repo
- Implement password hashing (bcrypt) in registration and login
- Add CI or deployment instructions


