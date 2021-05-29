package com.employee.management.system.controller;

import java.util.ArrayList;
import java.util.List;
import com.employee.management.service.EmployeeService;
import com.employee.management.helper.EmployeeHelper;
import com.employee.management.system.vo.EmployeeVO;
/**
 * EmployeeController class to route the control back and forth to console and Service Layer
 * respectively
 * @author Sowmyasree
 *
 */
public class EmployeeManagementController {
	
	EmployeeService empService  = new EmployeeService();
	
	/**
	 * Method to read Employee Data from HTML and save it onto MySQL
	 * @return
	 */
	public void processEmployeeDataFromHTML(String htmlPath) {
		try {
			List<EmployeeVO> empList = new ArrayList<EmployeeVO>();
	    	EmployeeHelper helper = new EmployeeHelper();
	    	empList = helper.parseHTMLFile(htmlPath);
	    	empService.insertEmployeeDetails(empList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to retrieve all the Employee Records from MySQL DB
	 */
	public void displayAllEmployeeRecords() {
		empService.displayEmployeeDetails();
	}
	
	/**
	 * Method to Delete Employee Record based on Employee ID
	 * @param empID
	 */
	public void deleteEmployeeRecord(String empID) {
		empService.deleteEmployeeDetails(empID);
	}

}
