package com.tajv.dao;

import java.util.List;

import com.tajv.model.Employee;

public interface EmployeeDao {

	public Employee getEmployeeById(int id);

	public Employee getEmployeeByNickname(String nickname);

	public List<Employee> getAllEmployees();

	public void saveEmployee(Employee employe);

	public void deleteEmployee(Employee employe);

	public void updateEmployee(Employee employe);

}
