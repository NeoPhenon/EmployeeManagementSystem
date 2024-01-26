package EmployeeManagementSystem;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
	
	public int addEmployee(Employee employee);
	
	public abstract void showAllEmployeeDatas();
	public abstract Optional<Employee> getEmployeeById(int id ); // to dexterously stave off nullPointer exception and
							// make program more efficacious
	public abstract int updateEmployeeById( int id , Employee employeeToUpdate);
	
	public abstract int deleteEmployeeById( int id );
	public List<Employee>getAllEmployees();
	
}
