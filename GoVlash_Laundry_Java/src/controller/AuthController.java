package controller;

import java.sql.Date;
import java.time.LocalDate;

import dao.UserDAO;
import model.User;

public class AuthController {
	
	private UserDAO userDAO;

    public AuthController() {
        userDAO = new UserDAO();
    }

    // LOGIN
    public User login(String email, String password) {

        if(email.isEmpty() || password.isEmpty()) {
            return null;
        }

        User user = userDAO.getUserByEmail(email);
        if(user == null) return null;

        if(!user.getPassword().equals(password)) return null;

        return user;
    }

    // CUSTOMER REGISTRATION
    public String registerCustomer(String name, String email, String password,
                                   String confirmPassword, String gender, Date dob) {

        if(name.isEmpty()) return "Name cannot be empty!";
        if(email.isEmpty()) return "Email cannot be empty!";

        if(!email.endsWith("@email.com"))
            return "Customer email must end with @email.com";

        if(password.length() < 6)
            return "Password must be at least 6 characters";

        if(!password.equals(confirmPassword))
            return "Passwords do not match";

        if(!(gender.equals("Male") || gender.equals("Female")))
            return "Gender must be Male or Female";

        int age = LocalDate.now().getYear() - dob.toLocalDate().getYear();
        if(age < 12) return "Customer must be at least 12 years old";

        User newUser = new User(name, email, password, gender, dob, "Customer");

        boolean success = userDAO.insertUser(newUser);
        return success ? "SUCCESS" : "Failed to register";
    }

}
