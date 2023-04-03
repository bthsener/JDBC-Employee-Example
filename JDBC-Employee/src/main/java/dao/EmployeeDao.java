package dao;

import java.util.List;

import model.Employee;

public interface EmployeeDao {

	public void insertEmployee(String name, String username, int salary);
	
	public List<Employee> getEmployees();
	
	public void removeEmployee(int id);
}
