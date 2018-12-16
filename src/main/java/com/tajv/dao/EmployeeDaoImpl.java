package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		String hql = "from Employee where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Employee> employeesList = query.list();

		if (employeesList != null && !employeesList.isEmpty()) {
			return employeesList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public Employee getEmployeeByNickname(String nickname) {
		String hql = "from Employee where nickname=" + nickname;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Employee> employeesList = query.list();

		if (employeesList != null && !employeesList.isEmpty()) {
			return employeesList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
	}

	@Override
	@Transactional
	public void deleteEmployee(Employee employee) {
		sessionFactory.getCurrentSession().delete(employee);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		String hql = "from Employee";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Employee> EmployeeList = query.list();

		if (EmployeeList != null && !EmployeeList.isEmpty()) {
			return EmployeeList;
		}
		return null;
	}

}
