package EmployeeManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class EmployeeDatabaseConfiguration {
	Scanner sc = new Scanner(System.in);
	
	EmployeeDatabaseConfiguration() throws ClassNotFoundException, SQLException{

		Connection dbConnection = new CreateConnectionHelper().createConnection();
		//createTable(dbConnection);
		
		while( true ) {
			
		int choice = menuProcess();
		switch(choice) {
			
		case 1:
			insertEmployeeData(dbConnection);
			break;
		case 2 : 
			deleteEmployeeData(dbConnection);
			break;
		case 3 : 
			getAllEmployeeData(dbConnection);
			break;
		case 4 :
			updateEmployeeData(dbConnection);
			default :
				break;
		}
		
		}
	}
	private void updateEmployeeData(Connection dbConnection) throws SQLException {
		String table_datas = "UPDATE employees SET empName = ?, empSalary = ? WHERE empId = ?";
		System.out.println(" Enter id ");
		int empid = Integer.parseInt(sc.nextLine());
		
		PreparedStatement pre = dbConnection.prepareStatement(table_datas);
		pre.setInt(1, empid);
		
		ResultSet defaultSet = pre.executeQuery();

		if(defaultSet == null ) {
			System.out.println(" No datas trackable ");
			System.exit(0);
		}
		
		System.out.println(".... Data before Update ....");
		
		while( defaultSet.next() ) {
			System.out.println("Id : " + defaultSet.getInt(1));
			System.out.println("Name : " + defaultSet.getString(2));
			System.out.println("Salary : " + defaultSet.getDouble(3));
		}
		
		PreparedStatement pre2 = dbConnection.prepareStatement(table_datas);
		boolean result = pre2.execute();
		System.out.println("Enter employee id ");
		int empId = Integer.parseInt(sc.nextLine());
		
		System.out.println(" Enter employee name ");
		String empName = sc.nextLine();
		
		System.out.println(" Enter employee Salary ");
		double empSalary = Double.parseDouble(sc.nextLine());
		
		pre2.setInt(1, empId);
		pre2.setString(2, empName);
		pre2.setDouble(3, empSalary);
		
		System.out.println(result == false ? "---- Employee Data(id: "+ empId +") is updated ----" : "ResultSet returned");
	}
	private void getAllEmployeeData(Connection dbConnection) throws SQLException{
		String table_datas =  "SELECT empId, empName, empSalary FROM employees";
		PreparedStatement pre = dbConnection.prepareStatement(table_datas);
		ResultSet defaultSet = pre.executeQuery();
		if( defaultSet == null ) {
			System.out.println(" No datas trackable ");
			System.exit(0);
		}
			while( defaultSet.next() ) {		
				System.out.println("Id : " + defaultSet.getInt(1));
				System.out.println("Name : " + defaultSet.getString(2));
				System.out.println("Salary : " + defaultSet.getDouble(3));
		}
		
	}
	private void deleteEmployeeData(Connection dbConnection) throws SQLException {
		String  table_datas = "DELETE FROM employees WHERE empId = ?";
		PreparedStatement pre = dbConnection.prepareStatement(table_datas);
		
		System.out.println(" Enter employee Id ");
		int empId = Integer.parseInt(sc.nextLine());
		
		pre.setInt(1, empId);
		boolean result = pre.execute();
	  
		System.out.println(result == false ? "---- Employee Id("+ empId +") is Deleted ----" : "ResultSet returned");
	}
	private void insertEmployeeData(Connection dbConnection) throws SQLException {
		String table_datas =   "INSERT INTO employees (empId, empName, empSalary) VALUES (?, ?, ?)";
		PreparedStatement pre = dbConnection.prepareStatement(table_datas);
		
		System.out.println("Enter employee id ");
		int empId = Integer.parseInt(sc.nextLine());
		
		System.out.println(" Enter employee name ");
		String empName = sc.nextLine();
		
		System.out.println(" Enter employee Salary ");
		double empSalary = Double.parseDouble(sc.nextLine());
		
		pre.setInt(1, empId);
		pre.setString(2, empName);
		pre.setDouble(3, empSalary);
		
		int result = pre.executeUpdate();
		
		if( result != -1 && result > 0 ) {
			System.out.println(" Employee Inserted successfully ");
		}
		else {
			System.out.println(" Something hallaciously wrong , can not be inserted ");
			return ;
		}
	}
	private int menuProcess() {
		System.out.println("================");
		System.out.println("Please Select by Number...");
		System.out.println("1. Insert Employee Data");
		System.out.println("2. Delete Employee Data");
		System.out.println("3. Show All Employee Data");
		System.out.println("4. Update Employee Data");
		System.out.println("5. Exit Program");
		int inputUserProcess = Integer.parseInt(sc.nextLine());
		return inputUserProcess;
	}
	private void createTable(Connection dbConnection) throws SQLException {
		String table_datas =  "CREATE TABLE employees (empId INT, empName VARCHAR(50), empSalary INT)";
		PreparedStatement pre = dbConnection.prepareStatement(table_datas);
		
		int result = pre.executeUpdate();
		
		if( result != -1 ) {
			System.out.println(" Employee Table created successfully ");
		}
		else {
			System.out.println(" table can not be created ");
		}
	}
	public static void main(String[] args ) throws ClassNotFoundException, SQLException {
		new EmployeeDatabaseConfiguration();
	}
}