package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	private static final String INSERT_EMPLOYEE ="insert into employeetest(name,surname,salary) values (?,?,?)";
	private static final String ALL_EMPLOYEES = "select * from employeetest";
	private static final String DELETE_EMPLOYEE = "delete from employeetest where id=?";
	
	private DataSource getDataSource() {
		ConnectionManager manager = new ConnectionManager();
		DataSource dataSource = manager.getDataSource();
		return dataSource;
	}
	
	
	@Override
	public void insertEmployee(String name, String surname, int salary){
		DataSource dataSource = getDataSource();
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setInt(3, salary);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> getEmployees() {
		DataSource dataSource = getDataSource();
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(ALL_EMPLOYEES);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				int salary = resultSet.getInt("salary");
				
				Employee employee = new Employee(id, name, surname, salary);
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public void removeEmployee(int id) {
		DataSource dataSource = getDataSource();
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
