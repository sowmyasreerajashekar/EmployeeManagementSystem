package com.employee.management.service;

import java.util.Iterator;
import java.util.List;

import com.employee.management.dao.EmployeeDAO;
import com.employee.management.system.vo.EmployeeVO;

/**
 * Employee Service class to handle business logic
 * @author Sowmyasree
 *
 */
public class EmployeeService {
	
	EmployeeDAO empDAO = new EmployeeDAO();	
	
	/**
	 * Method to insert Employee records to MySQL DB
	 * @param empList
	 */
	public void insertEmployeeDetails(List<EmployeeVO> empList) {
		
		//business logic to check if there is a valid employee salary
		Iterator<EmployeeVO> itr = empList.iterator();
		while(itr.hasNext()) {
			EmployeeVO empVO = itr.next();
			if(empVO.getEmpSalary() < 0) {
				System.out.println("Employee Salary cannot be less than 0. Please check the file again");
				return;
			}
		}
		empDAO.insertEmployeeDetails(empList);
	}
	
	/**
	 * Method to retrieve Employee records from MySQL DB and display onto console
	 * @param empList
	 */
	public void displayEmployeeDetails() {
		empDAO.readAllEmployeeDetails();
	}
	
	/**
	 * Method to delete Employee Record based on Employee ID
	 * @param empList
	 */
	public void deleteEmployeeDetails(String empId) {
		empDAO.deleteEmployee(empId);
	}
}
