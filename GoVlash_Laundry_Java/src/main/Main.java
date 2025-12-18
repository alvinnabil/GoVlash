package main;

import database.DBConnection;
import view.LoginView;

import javax.swing.SwingUtilities;

public class Main {
	
	
	public static void main(String[] args) {
		
		
		
	// Run the App
		new LoginView().setVisible(true);
		
	
	// Database Connection
		DBConnection.getConnection();
		
		
	}
	
	

}
