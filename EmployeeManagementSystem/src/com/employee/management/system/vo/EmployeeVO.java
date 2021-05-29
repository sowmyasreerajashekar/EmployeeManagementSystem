package com.employee.management.system.vo;

/**
 * EmployeeVO value object to store the employee details
 * @author Sowmyasree
 *
 */
public class EmployeeVO implements Comparable<EmployeeVO>{
	
	private int empID;
	private String empFirstName;
	private String empLastName;
	private int empAge;
	private int empSalary;
	private String empDesignation;
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	
	// overriding the compareTo method of Comparable class
    @Override 
    public int compareTo(EmployeeVO compareEmp)
    {
        
		int compareSalary
            = ((EmployeeVO)compareEmp).getEmpSalary();
  
        //  For Employee details in Ascending order
        return this.getEmpSalary() - compareSalary;
    }
  
    @Override 
    public String toString()
    {
        return "[ Employee ID = " + getEmpID() + ", Employee First Name="
            + getEmpFirstName() + ", Employee Last Name = " + getEmpLastName() +
            ", Employee Age = "+ getEmpAge() + ", Employee Salary = " + getEmpSalary() +
            ", Employee Designation = "+ getEmpDesignation()+"]";
    }
}
