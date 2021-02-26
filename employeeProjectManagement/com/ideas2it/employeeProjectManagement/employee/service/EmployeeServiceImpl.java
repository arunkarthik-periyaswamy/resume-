/**
 * Employee service which handles the business logics for employee management
 */
package com.ideas2it.employeeProjectManagement.employee.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ideas2it.employeeProjectManagement.address.model.Address;
import com.ideas2it.employeeProjectManagement.employee.dao.EmployeeDAO;
import com.ideas2it.employeeProjectManagement.employee.dao.EmployeeDAOImpl;
import com.ideas2it.employeeProjectManagement.employee.model.Employee;
import com.ideas2it.employeeProjectManagement.project.model.Project;
import com.ideas2it.employeeProjectManagement.project.service.ProjectServiceImpl;
import com.ideas2it.employeeProjectManagement.project.service.ProjectService;

/**
 * EmployeeService is an Service class which performs logics and store values 
 * and send it to view class
 * 
 * @author  A.K
 * @since   22-01-2021
 * @version 1.0
 */
public class EmployeeServiceImpl implements EmployeeService {

	ArrayList<Employee> employeeList = new ArrayList<Employee>();
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	ProjectService projectService = new ProjectServiceImpl();


	/**
	 * To view all Employee details list
	 * 
	 * @return employeeList  the Employee details list
	 */
	public List<Map<String, Object>> viewEmployeeList() {
		List<Map<String,Object>> employeeDetailsList =
				new ArrayList<Map<String,Object>>();		
		List<Employee> employeeList = employeeDAO.viewEmployeeList();	
		for (Employee employee : employeeList) {	
			Map<String, Object> employeeDetails= new LinkedHashMap<String, Object>();
			employeeDetails.put("id", employee.getEmployeeId());
			employeeDetails.put("firstName", employee.getFirstName());
			employeeDetails.put("secondName", employee.getSecondName());
			employeeDetails.put("designation", employee.getDesignation());
			employeeDetails.put("salary", employee.getSalary());
			employeeDetails.put("emailId", employee.getEmailId());
			employeeDetails.put("age", employee.getDateOfBirth());
			employeeDetails.put("phoneNumber", employee.getPhoneNumber());
			employeeDetailsList.add(employeeDetails);
			List<Address> addressDetailsList = employee.getAddresses();
			for (Address address : addressDetailsList) {
				Map<String, Object> employeeAddress = new LinkedHashMap<String, Object>();
				employeeAddress.put("employeeId", address.getEmployeeId());
				employeeAddress.put("streetAddress", address.getStreetAddress());
				employeeAddress.put("state", address.getState());
				employeeAddress.put("city", address.getCity());
				employeeAddress.put("postalCode", address.getPostalCode());
				employeeDetailsList.add(employeeAddress);
			}
		} 
		return employeeDetailsList;		
	}

	/**
	 * Add the employee details and address details
	 * and getting the employee Id for the employee
	 * 
	 * @param firstName 	         employee first name
	 * @param secondName	         employee second name
	 * @param designation 	         designation of employee
	 * @param salary	             of employee
	 * @param emailId	             email of an employee 
	 * @param age	                 age of an employee
	 * @param phoneNumber 	         phone number of an employee
	 * @param  streetAddress         permanent street address of an employee
	 * @param  state                 Permanent state
	 * @param  city                  permanent city
	 * @param  postalCode            permanent postalCode
	 * @param  currentStreetAddress	 current street address
	 * @param  currentState          current state
	 * @param  currentCity           current city
	 * @param  currentPostalCode     current postal code
	 * @throws SQLException          suppress the SQLException
	 */
	public int createEmployeeDetails(String firstName, String secondName,
			String designation, String 	salary, String emailId, 
			String dateOfBirth, String phoneNumber, String streetAddress,
			String state, String city, String postalCode, String currentStreetAddress,
			String currentState, String currentCity, String currentPostalCode) {		
		Employee employee= new Employee(firstName, secondName,
				designation, salary, emailId, dateOfBirth, phoneNumber);
		Address permanentAddress = new Address(streetAddress, state,
				city, postalCode);
		Address currentAddress = new Address(currentStreetAddress, currentState,
				currentCity, currentPostalCode);
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(permanentAddress);
		addressList.add(currentAddress);
		employee.setAddresses(addressList);
		int employeeId = employeeDAO.createEmployeeDetails(employee);
		/*addressService.createAddressDetails(employeeId, streetAddress, state,
				city, postalCode, currentStreetAddress, 
				currentState, currentCity, currentPostalCode);*/
		return employeeId;
	}

	/**
	 * To delete employee details by id.
	 * 
	 * @param  employeeId    to delete the details by employeeId  
	 * @return               boolean
	 */
	public boolean employeeDelete(int employeeId) {
		return employeeDAO.deleteEmployee(employeeId);
	}


	/**
	 * To update employee Details and the boolean is returned
	 *
	 * @param employeeId             employeeId to update details
	 * @param firstName 	         employee first name
	 * @param secondName	         employee second name
	 * @param designation 	         designation of employee
	 * @param salary	             of employee
	 * @param emailId	             email of an employee 
	 * @param age	                 age of an employee
	 * @param phoneNumber 	         phone number of an employee
	 * @param  streetAddress         permanent street address of an employee
	 * @param  state                 Permanent state
	 * @param  city                  permanent city
	 * @param  postalCode            permanent postalCode
	 * @param  currentStreetAddress	 current street address
	 * @param  currentState          current state
	 * @param  currentCity           current city
	 * @param  currentPostalCode     current postal code
	 */
	public boolean isUpdateEmployeeDetails(int employeeId, String firstName, String secondName,
			String designation, String salary,	String emailId, String dateOfBirth, String phoneNumber, 
			String streetAddress, String state, String city,
			String postalCode,	String currentStreetAddress, String currentState,
			String currentCity, String currentPostalCode) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setFirstName(firstName);
		employee.setSecondName(secondName);
		employee.setDesignation(designation);
		employee.setSalary(salary);
		employee.setEmailId(emailId);
		employee.setDateOfBirth(dateOfBirth);
		employee.setPhoneNumber(phoneNumber);

		Address permanentAddress = new Address();
		Address currentAddress = new Address();
		permanentAddress.setStreetAddress(streetAddress);
		permanentAddress.setState(state);
		permanentAddress.setCity(city);
		permanentAddress.setPostalCode(postalCode);
		currentAddress.setStreetAddress(currentStreetAddress);
		currentAddress.setState(currentState);
		currentAddress.setCity(currentCity);
		currentAddress.setPostalCode(currentPostalCode);
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(permanentAddress);
		addressList.add(currentAddress);

		employee.setAddresses(addressList);
		boolean isEmployeeUpdate = employeeDAO.isUpdateEmployeeDetails(employee);	
		return (true == isEmployeeUpdate) ? true : false;
	}

	/**
	 * To view employee Details
	 * 
	 * @param employeeId  to get the employee details of this id  
	 */
	public List<Map<String,Object>> viewEmployeeDetails(int employeeId) {		
		ArrayList<Map<String,Object>> employeeDetails =
				new ArrayList<Map<String,Object>>();
		Map<String,Object> employeeData = new LinkedHashMap<String,Object>();
		Employee employee = employeeDAO.viewEmployeeDetails(employeeId);
		employeeData.put("id", employee.getEmployeeId());
		employeeData.put("firstName", employee.getFirstName());
		employeeData.put("secondName", employee.getSecondName());
		employeeData.put("designation", employee.getDesignation());
		employeeData.put("salary", employee.getSalary());
		employeeData.put("emailId", employee.getEmailId());
		employeeData.put("age", employee.getDateOfBirth());
		employeeData.put("phoneNumber", employee.getPhoneNumber());
		employeeDetails.add(employeeData);
		List<Address> addressDetails = employee.getAddresses();
		for ( Address address : addressDetails) {
			Map<String,Object> employeeaddress = new LinkedHashMap<String,Object>();
			employeeaddress.put("streetAddress", address.getStreetAddress());
			employeeaddress.put("state", address.getState());
			employeeaddress.put("city", address.getCity());
			employeeaddress.put("postalCode", address.getPostalCode());
			employeeDetails.add(employeeaddress);
		}	
		return employeeDetails;		
	}

	/**
	 * To assign project to employee
	 * 
	 * @param employeeId        employeeId 
	 * @param employeeProjects  projects for employeeId
	 * @return                  boolean
	 */
	public boolean projectAssign(int employeeId, List<Integer> employeeProjects) {
		Employee employee = employeeDAO.viewEmployeeDetails(employeeId);
		List<Project> employeeProjectList  = projectService.employeeProjectDetails(employeeProjects);
		employee.setProjects(employeeProjectList);		
		return employeeDAO.projectAssign(employee);
	}

	/**
	 * To get the projectIds List
	 * @return              projectId lists
	 */
	public List<Set<Integer>> availableProjects() {
		return projectService.availableProjects();
	}
}