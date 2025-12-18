package controller;

import java.sql.Date;
import java.time.LocalDate;

import dao.UserDAO;
import model.User;

public class UserController {
	
	 private UserDAO userDAO;

	    public UserController() {
	        userDAO = new UserDAO();
	    }

	    public String addEmployee(String name, String email, String password, 
	                              String confirmPassword, String gender, Date dob, String role) {

	        if(name.isEmpty()) return "Name cannot be empty";

	        if(!email.endsWith("@govlash.com"))
	            return "Employee email must end with @govlash.com";

	        if(password.length() < 6)
	            return "Password must be at least 6 characters";

	        if(!password.equals(confirmPassword))
	            return "Passwords do not match";

	        if(!(gender.equals("Male") || gender.equals("Female")))
	            return "Gender must be Male or Female";

	        if(!(role.equals("Admin") || role.equals("Laundry Staff") || role.equals("Receptionist")))
	            return "Invalid role";

	        int age = LocalDate.now().getYear() - dob.toLocalDate().getYear();
	        if(age < 17) return "Employee must be at least 17 years old";

	        User emp = new User(name, email, password, gender, dob, role);

	        boolean success = userDAO.insertUser(emp);
	        return success ? "SUCCESS" : "Failed to create employee";
	    }

}
