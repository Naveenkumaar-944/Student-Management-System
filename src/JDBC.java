import java.sql.*;
import java.util.*;

public class JDBC {
	 
	public String url = "jdbc:mysql://localhost:3306/school";
	public String username = "root";
	public String password = "123456";
	
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	Scanner sc = new Scanner(System.in);
	
	public void create() throws Exception{
		String Query = "insert into student(student_name, student_department, student_gender, student_age) values(?,?,?,?)";
		
		con = DriverManager.getConnection(url, username, password);
		ps = con.prepareStatement(Query);
		
		System.out.print("Enter Student Name: ");
		String name = sc.nextLine();
		ps.setString(1, name);
		
		System.out.print("Enter Student Department: ");
		String dept = sc.nextLine();
		ps.setString(2, dept);
		
		System.out.print("Enter Student Gender(Male / Female): ");
		String gender = sc.nextLine();
		ps.setString(3, gender);
		
		System.out.print("Enter Student Age(Under 30): ");
		String age = sc.nextLine();
		ps.setString(4, age);
		
		ps.executeUpdate();
        
		System.out.println(" Created Successfully!");
		
		 con.close();
	}

	public void update() throws Exception{
		System.out.print("Enter the Student ID");
		String id = sc.nextLine();
		
		con = DriverManager.getConnection(url, username, password);
		
		String Query = "Select * from Student where student_id = ?;";
		
		ps = con.prepareStatement(Query);
		ps.setString(1,id);
		
		rs = ps.executeQuery();
		
		if(rs.next()) {
			String update_query = "update student set student_name = ?, student_department = ?, student_gender = ?, student_age = ? where id = ?";
			
			ps = con.prepareStatement(update_query);
			
			System.out.print("Enter Student Name: ");
			String name = sc.nextLine();
			ps.setString(1, name);
			
			System.out.print("Enter Student Department: ");
			String dept = sc.nextLine();
			ps.setString(2, dept);
			
			System.out.print("Enter Student Gender(Male / Female): ");
			String gender = sc.nextLine();
			ps.setString(3, gender);
			
			System.out.print("Enter Student Age(Under 30): ");
			String age = sc.nextLine();
			ps.setString(4, age);
			
			int rows = ps.executeUpdate();
			if(rows > 0) {
				System.out.println(id + " updated Successfully!");
			}
			else {
				System.out.println("Data not updated");
			}
		}
		else {
			System.out.println(id + " is Invalid!");
		}
		
        con.close();
	}
	
	public void delete() throws Exception{
		System.out.println("Enter the Student ID");
		String id = sc.nextLine();
		
		con = DriverManager.getConnection(url, username, password);
		
		String Query = "select * from student where student_id = ?";
		
		ps = con.prepareStatement(Query);
		ps.setString(1, id);
		
		rs = ps.executeQuery();
		
		if(rs.next()) {
			String delete_Query = "delete from student where student_id = ?";
			
			ps = con.prepareStatement(delete_Query);
			ps.setString(1, id);
			
			int rows = ps.executeUpdate();
			if(rows > 0) {
				System.out.println(id + " Data Deleted Successfully!");
			}
			else {
				System.out.println("Deletion Failed!");
			}
		}
		else {
			System.out.println(id + " is Invalid!");
		}
		
        con.close();
	}
	
	public void view() throws Exception {
		System.out.println("====================================================");
		System.out.println("📋 STUDENT DATA VIEW MENU");
		System.out.println("====================================================\n");

		System.out.println("1️  View All Student Records\n"
				+ "2️  View Specific Student Record\n"
				+ "3️  Return to Main Menu");

		System.out.println("\n----------------------------------------------------");
		System.out.print("Please Enter Your Choice (1 - 3): ");
		String choice = sc.nextLine();
		switch(choice) {
		case "1":
			String Viewall_Query = "select * from student;";
			
			con = DriverManager.getConnection(url, username, password);
			st = con.createStatement();
			ps = con.prepareStatement(Viewall_Query);
			rs = ps.executeQuery();
			
			  System.out.println("-------------------------------------------------------------");
			    System.out.printf("%-5s %-12s %-15s %-10s %-5s\n",
			            "ID","STUDENT_ID","NAME","DEPT","AGE");
			    System.out.println("-------------------------------------------------------------");

			    while(rs.next()) {

			        System.out.printf("%-5d %-12s %-15s %-10s %-5S\n",
			                rs.getInt("id"),
			                rs.getString("student_id"),
			                rs.getString("student_name"),
			                rs.getString("student_department"),
			                rs.getString("student_age")
			        );
			    }

			    System.out.println("-------------------------------------------------------------");
			    break;
			    
		case "2":
			System.out.print("Enter the Student_ID: ");
			String id = sc.nextLine();
			
			con = DriverManager.getConnection(url, username, password);
			
			String View_Query = "select * from student where student_id = ?;";
			
			ps = con.prepareStatement(View_Query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			  System.out.println("-------------------------------------------------------------");
			    System.out.printf("%-5s %-12s %-15s %-10s %-5s\n",
			            "ID","STUDENT_ID","NAME","DEPT","AGE");
			    System.out.println("-------------------------------------------------------------");

			    while(rs.next()) {

			        System.out.printf("%-5d %-12s %-15s %-10s %-5S\n",
			                rs.getInt("id"),
			                rs.getString("student_id"),
			                rs.getString("student_name"),
			                rs.getString("student_department"),
			                rs.getString("student_age")
			        );
			    }

			    System.out.println("-------------------------------------------------------------");
			    break;

        default:
            System.out.println("Invalid Choice");

    con.close();
		}
	}
	public static void main(String[]arg) throws Exception{
		JDBC obj = new JDBC();
		boolean running = true;
		
		while(running) {
			System.out.println("====================================================");
			System.out.println("🎓 STUDENT MANAGEMENT SYSTEM");
			System.out.println("====================================================\n");

			System.out.println("Choose an Operation:\n");

			System.out.println("1️  Add New Student Record\n"
					+ "2️  View Student Information\n"
					+ "3️  Update Student Details\n"
					+ "4️  Delete Student Record\n"
					+ "5️  Exit Application");

			System.out.println("\n----------------------------------------------------");
			System.out.print("Please Enter Your Choice (1 - 5): ");
			String choice = obj.sc.nextLine();
			
			switch(choice) {
				case "1":
					obj.create();
					break;
				case "2":
					obj.view();
					break;
				case "3":
					obj.update();
					break;
				case "4":
					obj.delete();
					break;
		        case "5":
		            System.out.println("Program Closed");
		            running = false;
		            break;

		        default:
		            System.out.println("Invalid choice");
			}
		}
	}
}