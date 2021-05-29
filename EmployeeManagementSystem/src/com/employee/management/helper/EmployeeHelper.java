package com.employee.management.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.employee.management.system.vo.EmployeeVO;

/**
 * EmployeeHelper helper class to serve EmployeeController class
 * @author Sowmyasree
 *
 */
public class EmployeeHelper {
	
	/**
	 * Method to parse incoming HTML file and convert it to Value Object
	 * @param htmlPath
	 * @return
	 */
	
	public List<EmployeeVO> parseHTMLFile(String htmlPath){
		List<EmployeeVO> empList = new ArrayList<>();
		EmployeeVO empVO;
		try {
	    	  File input = new File(htmlPath);
	    	  
	    	  //Parse the HTML file using JSOUP API and store the values in EmployeeVO object
	    	  Document doc = Jsoup.parse(input, "UTF-8");
	    	   for (Element table : doc.select("table")) {
	    	       for (Element row : table.select("tr")) {
	    	           Elements tds = row.select("td");
	    	           if (tds.size() > 0) {
	    	        	   empVO = new EmployeeVO();
	    	        	   empVO.setEmpID(Integer.valueOf(tds.get(0).text()));
	    	        	   empVO.setEmpFirstName(tds.get(1).text());
	    	        	   empVO.setEmpLastName(tds.get(2).text());
	    	        	   empVO.setEmpAge(Integer.valueOf(tds.get(3).text()));
	    	        	   empVO.setEmpSalary(Integer.valueOf(tds.get(4).text()));
	    	        	   empVO.setEmpDesignation(tds.get(5).text());
	    	        	   empList.add(empVO);
	    	           }
	    	       } 
	    	   }  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empList;
	}
	
	public Properties loadProperties(){
		Properties properties = new Properties();
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file is not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception occured while reading property file "+ e.getMessage());
		}
		return properties;
	}

}
