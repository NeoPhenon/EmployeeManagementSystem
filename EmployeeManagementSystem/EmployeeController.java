package EmployeeManagementSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeController {
	private final EmployeeDao employeeService;
	
	private static final String path = "C:\\Users\\Acer\\Desktop\\EmployeeData.txt";
	
	private static final Scanner sc = new Scanner(System.in); 
	
	EmployeeController(EmployeeDao employeeService ){
		this.employeeService =  employeeService;
	}
	EmployeeController(){
		this.employeeService = null;
		}
	public void addEmployeeData() {
	try(PrintWriter writer = new PrintWriter(new FileWriter(path , true ))){
		System.out.println(" Please Enter Employee Id ");
		int id = sc.nextInt();
		
		System.out.println(" Please Enter Employee name ");
		String name = sc.next();
		
		
		System.out.println(" Please Enter Employee salary ");
		double salary = sc.nextDouble();
		
		System.out.println(" Please Enter Employee age ");
		int age = sc.nextInt();
		
		Employee employee = new Employee(id , name , salary , age );
		writer.println(employee.getId()+","+employee.getName()+","+employee.getSalary()+","+employee.getAge());
		employeeService.addEmployee(employee);
		System.out.println(" Employee datas added up successfully ");
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
	  }
	}
	public void getEmployeeById() {
		System.out.println(" Please Enter Employee id to track down ");
		int id = sc.nextInt();
		Optional<Employee> data = employeeService.getEmployeeById(id);
		if( !data.isPresent()) {
			System.out.println(" Employee id is not affordable to be found ");
		}
		else {
			System.out.println(data.get());
		}
	}
	public void showAllemployeesByDatas() {
		employeeService.showAllEmployeeDatas();
	}
	public void updateEmployeeDatas() {
		System.out.println(" Please Enter Your Current Employee id ");
		int idToUpdate = sc.nextInt();
		Optional<Employee> employees = employeeService.getEmployeeById(idToUpdate);
		
		if( employees.isPresent() ) {
	try(PrintWriter writer = new PrintWriter(new FileWriter(path , false ))){
			System.out.println(" Please Enter Employee id to update ");
			int id = sc.nextInt();
			System.out.println(" Please Enter Employee name to Update  ");
			String name = sc.next();
			System.out.println(" Please Enter Employee salary to update ");
			double salary = sc.nextDouble();
			
			System.out.println(" Please Enter Employee age to update ");
			int age = sc.nextInt();
			
			Employee updatedEmployee = new Employee(id , name , salary , age );
			int result = employeeService.updateEmployeeById(idToUpdate, updatedEmployee);
			if( result == 1 ) {
				for( Employee employeeData : employeeService.getAllEmployees() ) {
	                writer.println(employeeData.getId() + "," + employeeData.getName() + "," +
	                		employeeData.getSalary() + "," + employeeData.getAge());
				}
				System.out.println("Employee Updated Successfully");
				writer.close();
			}
			else {
				  System.out.println("Failed to update employee.");
			}
		}
			catch( IOException e ) {
				 e.printStackTrace();
			}
		}
		else {
			  System.out.println("Employee not found.");
			}
		
	}

	public void deleteEmployee() {
		System.out.println(" Please Enter Your Current Employee id ");
		int id = sc.nextInt();
		Optional<Employee> dataSet = employeeService.getEmployeeById(id);
	   if( dataSet.isPresent()) {
	try(PrintWriter writer = new PrintWriter(new FileWriter(path , false ))){
		employeeService.deleteEmployeeById(id);
		System.out.println("Employee Deleted Successfully");
		writer.close();
	 }
		
	catch (IOException e) {
            e.printStackTrace();
        }
	}
		else {
			 System.out.println("Employee not found.");
		}
	}

}
