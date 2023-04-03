package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

@WebServlet("/employeeController")
public class employeeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("employeeId"));
		EmployeeDaoImpl eImpl = new EmployeeDaoImpl();
		eImpl.removeEmployee(id);
		
		List<Employee> employees = new ArrayList<Employee>();
		System.out.println("gel emp geldi");
		employees = eImpl.getEmployees();
		
		req.setAttribute("allEmployees", employees);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("JdbcEmployee.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post'a girdi");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		int salary = Integer.parseInt(req.getParameter("salary"));
		
		EmployeeDaoImpl eImpl = new EmployeeDaoImpl();
		
		System.out.println("insert'e geldi");
		eImpl.insertEmployee(name, surname, salary);
		
		List<Employee> employees = new ArrayList<Employee>();
		System.out.println("gel emp geldi");
		employees = eImpl.getEmployees();
		
		req.setAttribute("allEmployees", employees);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("JdbcEmployee.jsp");
		dispatcher.forward(req, resp);
		
	}
}
