
package com.employee.management.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.employee.management.system.controller.EmployeeManagementController;
import com.employee.management.system.vo.EmployeeVO;
/**
 * Employee Management App
 * Execution class
 * @author Sowmyasree
 * 
 */
public class EmployeeManagementSystem {
	
	/**
	 * Employee Management main method
	 * User options are as below
	 * 1 - Read the Employee details from HTML and insert it Employee System
	 * 2 - Display all the Employee Records
	 * 3 - Delete an Employee record
	 * 4 - System exit
	 * @param args
	 */
	public static void main(String[] args) {
		
		EmployeeManagementController empController = new EmployeeManagementController();
		try {
			System.out.println("Welcome to Employee Management System");
			System.out.println("Enter 1 to Read the Employee details from HTML and insert it into database");
			System.out.println("Enter 2 to Display all the Employee Records");
			System.out.println("Enter 3 to Delete an Employee record");
			System.out.println("Enter 4 to exit");
			
			//read data from console
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String inputLine = reader.readLine();
		    
		    //User options based on input
		    switch(inputLine) {
		    case "1" :
			    System.out.println("Enter the HTML file path.");
			    String htmlPath = reader.readLine();
			    empController.processEmployeeDataFromHTML(htmlPath);
		    	break;
		    case "2" :	
		    	empController.displayAllEmployeeRecords();		    	
		    	break;
		    case "3" :
		    	System.out.println("Enter Employee ID to delete the Employee Record");
		    	String empID = reader.readLine();
			    empController.deleteEmployeeRecord(empID);
		    	break;
		    case "4" :
		    	System.exit(0);
		    	break;
		    default:
		    	System.out.println("Invalid option Entered");
		    }
		System.out.println("-------------------------------------------------------");
		} catch (Exception e) {
			System.out.println("Exception occured in main method "+e.getMessage());
		}
	}
}
