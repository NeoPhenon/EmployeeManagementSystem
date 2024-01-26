package EmployeeManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeService implements EmployeeDao{
	
	private static final List<Employee> dao = new ArrayList<>();
	private static final Map<String,Employee> uniqueMap = new HashMap<>(); // name -> Obj(ultimate employee)
	
	@Override
	public int addEmployee(Employee employee) {
	 if( !uniqueMap.containsKey(employee.getName())) {
		 uniqueMap.put(employee.getName(), employee);
		 dao.add(employee);
		 return 1;
	 }
	 else {
		 return -1;
	 	}
	}
	
	@Override
	public void showAllEmployeeDatas() {
		System.out.println(" Employees Datas ");
		boolean found = true;
		for( Employee emp : dao ) {
			 if( dao.isEmpty() ) {
				 System.out.println(" Your employee list is ultimately empty ");
				 found = false;
				 break;
			 }
		}
		if( found ) {
			System.out.println(dao.toString());
		}
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		// euphortimistically convert list to optional and make up process
		return dao.stream().filter(employee -> employee.getId() == id ).findFirst();
	}
	@Override
	public int updateEmployeeById( int id , Employee EmployeeToUpdate ) {
		Optional<Employee> dataSet = getEmployeeById(id);
		// set apart employee beneficially to update 
		return dataSet.map( employee -> {
			int indexOfEmployeeToUpdate = dao.indexOf(employee);
			if( indexOfEmployeeToUpdate != -1 && uniqueMap.containsKey(dataSet.get().getName())) {
		Employee employeeResult = new Employee(EmployeeToUpdate.getId() , EmployeeToUpdate.getName() , 
						EmployeeToUpdate.getSalary() , EmployeeToUpdate.getAge() );
				uniqueMap.put(dataSet.get().getName(), EmployeeToUpdate);
				dao.set(indexOfEmployeeToUpdate, employeeResult);
				return 1;
			}
			return 0;
		}).orElse(0);
	}

	@Override
	public int deleteEmployeeById(int id) {
		Optional<Employee> dataSet = getEmployeeById(id);
		
		dao.remove(dataSet.get());
		uniqueMap.remove(dataSet.get().getName());
		return 0;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return dao;
	}
}
