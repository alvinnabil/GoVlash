# GoVlash_LaundryManagementSystem
GoVlash Laundry is a Java-based desktop application designed to manage laundry business operations.
The system implements MVC (Model–View–Controller) architecture and uses Java Swing for the user interface with MySQL as the database.


# Features : 
- Multi-role authentication:
  - Admin
  - Customer
  - Receptionist
  - Laundry Staff
- Secure login and registration
- Role-based dashboards
- Laundry order management
- Order status tracking
- Database integration (MySQL)
- Clean MVC architecture

# Technologies Used
- Java SE 11 / 17
- Java Swing (GUI)
- MySQL
- Eclipse IDE
- JDBC
- Git & GitHub

# Prerequisites
Before running the application, make sure you have:
- Java JDK 11 or later
- MySQL Server
- Eclipse IDE (recommended)
- Git installed

# How To Use
- Clone The Repository
  - Open Terminal or Git Bash and run : git clone https://github.com/your-username/your-repository-name.git
  - Then move into the project directory : cd your-repository-name

- Open Project in the Eclipse
  - Open Eclipse IDE
  - Go to : File -> Import -> Existing Project Into Workspace
  - Select the cloned project folder
  - Click Finish
 
- Add Library in Build Path that include External JAR's 
  - Library that should be added : jafafx-sdk, mysql-connector-java

- Database Setup
  - Create a new MySQL database : CREATE DATABASE govlash_laundry;
  - Import the SQL file, Or manually create tables :
      - users
      - notifications
      - transactions
      - services
  - Update database credentials in : src/database/DBConnection.java

- Run the Application
  - navigate to : src/main/Main.java
  - Right-click on Main.java
  - Run As -> Java Application
  - The Login window will appear



# Notes
- This application uses Swing, not JavaFX.
- VM arguments are not required.
- Database connection is executed only during login to avoid UI freezing.
- For jafafx-sdk and mysql-connector-java you can find and download it on google or the other trusted platform such as github etc.

# Author
**Alvin Nabil Thoriq**
Software Engineering Student At Binus University | Full Stack Developer

* GitHub: [alvinnabil](https://github.com/alvinnabil)
* Portfolio: [vinndev.vercel.app](https://vinndev.vercel.app)
* LinkedIn : [alvin-nabil-8972792a7](www.linkedin.com/in/alvin-nabil-8972792a7)

# License

This project is available and open for learning and personal use








