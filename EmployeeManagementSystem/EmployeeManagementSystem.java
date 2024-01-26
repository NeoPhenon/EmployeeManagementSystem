package EmployeeManagementSystem;

import java.util.Scanner;

public class EmployeeManagementSystem {
	private static final Scanner sc = new Scanner(System.in);
	private final EmployeeController employeeController = new EmployeeController();
	EmployeeManagementSystem(){
			MenuProcess();
			
	}

	private void MenuProcess() {
		
		while( true ) {
	try {
		int choice = showMenu();
		switch( choice ) {
		case 0:
			if( choice == 0 ) {
			 System.out.println(" Thank You So Much For Using Our Employee Management System!! ");
				System.exit(0);
				sc.close();
			}
			break;
		case 1:
			employeeController.addEmployeeData();
			break;
		case 2 :
			employeeController.updateEmployeeDatas();
			break;
		case 3:
			employeeController.deleteEmployee();
			break;
		case 4 :
			employeeController.showAllemployeesByDatas();
			break;
		case 5: 
			employeeController.getEmployeeById();
			break;
			default :
			break;
		}
			}
		catch( NumberFormatException nfe ) {
			System.err.println(" Make Sure You Indespensibly put out only Number ");
			System.out.println();
			}
	    }
		
	}
	private int showMenu() {
		
		System.out.println("---- Welcome To Momentious Employee Management System ------- ");
		System.out.println("0.Exit");
		System.out.println("1.AddEmployee Datas ");
		System.out.println("2. Update Employee By Employee Id ");
		System.out.println("3. Delete Employee By Employee Id ");
		System.out.println("4. Display All Employee Datas ");
		System.out.println("5. Display Employee By Employee Id ");
		System.out.println(" Please just Enter Your sychronous choice ");
		System.out.println();
		int choice = Integer.parseInt(sc.nextLine());
		return choice;
	}
	public static void main(String[] args ) {
		new EmployeeManagementSystem();
	}
}
