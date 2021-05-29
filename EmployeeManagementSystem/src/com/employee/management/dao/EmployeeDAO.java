package com.employee.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.employee.management.helper.EmployeeHelper;
import com.employee.management.system.vo.EmployeeVO;
/**
 * Class to handle DB transactions
 * @author Sowmyasree
 *
 */
public class EmployeeDAO {
	Connection connection = null;
	EmployeeHelper helper = new EmployeeHelper();
	private static final String SQL_DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * Method to establish DB connection
	 * @return
	 */
	private Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName(SQL_DRIVER);
				Properties prop = helper.loadProperties();
				connection = DriverManager.getConnection(prop.getProperty("dbURL"),
						prop.getProperty("dbUserName") , prop.getProperty("dbPassword"));
			}
		} catch (Exception e) {
			System.out.println("Exception caught : " + e.getMessage());
		}
		return connection;
	}

	/**
	 * Method to insert Employee Details from HTML file to DB
	 * @param empList
	 */
	public void insertEmployeeDetails(List<EmployeeVO> empList) {

		try {
			final String INSERT_EMPLOYEE = "INSERT INTO emp_details "
					+ "(emp_id, emp_first_name, emp_last_name, emp_age, emp_salary, emp_designation ) "
					+ "VALUES (?,?,?,?,?,?)";
			//Create Prepared statement
			PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_EMPLOYEE);
			//Pass the data to prepared Statement object
			Iterator<EmployeeVO> it = empList.iterator();
			int count = 0;
			while (it.hasNext()) {
				EmployeeVO empVO = it.next();
				preparedStatement.setInt(1, empVO.getEmpID());
				preparedStatement.setString(2, empVO.getEmpFirstName());
				preparedStatement.setString(3, empVO.getEmpLastName());
				preparedStatement.setString(4, String.valueOf(empVO.getEmpAge()));
				preparedStatement.setString(5, String.valueOf(empVO.getEmpSalary()));
				preparedStatement.setString(6, empVO.getEmpDesignation());
				int row = preparedStatement.executeUpdate();
				if (row>0) {
					count++;
				}
			}
			if (count>0) {
				System.out.println("Employee Details are saved in the system successfully");
			}
			else {
				System.out.println("Employee Management System failed to save the details ");
			}

		} catch (Exception e) {
			System.out.println("DB Exception" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error occured while closing DB connection");
			}
		}
	}

	/**
	 * Method to select all employee records and display on console
	 */
	public void readAllEmployeeDetails() {
		try {
			EmployeeVO empVO;
			List<EmployeeVO> empVOList = new ArrayList<EmployeeVO>();
			final String READ_EMPLOYEES = "SELECT * FROM EMP_DETAILS ORDER BY EMP_SALARY";
			//create Statement
			Statement stmt = getConnection().createStatement();
			//Execute query
			ResultSet rs = stmt.executeQuery(READ_EMPLOYEES);
			//Iterate over result set
			while (rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpID(rs.getInt("emp_id"));
				empVO.setEmpFirstName(rs.getString("emp_first_name"));
				empVO.setEmpLastName(rs.getString("emp_last_name"));
				empVO.setEmpAge(Integer.valueOf(rs.getString("emp_age")));
				empVO.setEmpSalary(Integer.valueOf(rs.getString("emp_salary")));
				empVO.setEmpDesignation(rs.getString("emp_designation"));
				empVOList.add(empVO);
			}
			//Sort the Employee List based on Employee Salary
			Collections.sort(empVOList);
			Iterator<EmployeeVO> it = empVOList.iterator();
			while (it.hasNext()) {
				EmployeeVO empVODisp = it.next();
				System.out.println("Employee ID               :" +empVODisp.getEmpID());
				System.out.println("Employee First Name       :" +empVODisp.getEmpFirstName());
				System.out.println("Employee Last Name        :" +empVODisp.getEmpLastName());
				System.out.println("Employee Age              :" +empVODisp.getEmpAge());
				System.out.println("Employee Salary           :" +empVODisp.getEmpSalary());
				System.out.println("Employee Designation      :" +empVODisp.getEmpDesignation());
				
				System.out.println("----------------------------------------------------------");
			}

		} catch (Exception e) {
			System.out.println("Exception occured in Employee DAO" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error occured while closing DB connection");
			}
		}
	}
	
	/**
	 * Method to delete an Employee record given an Employee ID
	 * @param empID
	 */
	public void deleteEmployee(String empID) {
		try {
			final String DELETE_EMPLOYEE = "DELETE FROM EMP_DETAILS WHERE EMP_ID="+empID;
			Statement stmt = getConnection().createStatement();
			int count = stmt.executeUpdate(DELETE_EMPLOYEE);
			//check if DB record has been found and deleted 
			if (count>0) {
				System.out.println("Employee "+empID+" details are deleted successfully");
			}
			else {
				System.out.println("Employee record for ID "+empID+" is not found");
			}
		} catch (Exception e) {
			System.out.println("Exception occured in Employee DAO" + e.getMessage());
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error occured while closing DB connection");
			}
		}
	}

	
}
