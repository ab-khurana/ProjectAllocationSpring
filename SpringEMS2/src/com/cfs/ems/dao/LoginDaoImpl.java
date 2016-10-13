package com.cfs.ems.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cfs.ems.model.Login;
import com.cfs.ems.model.ProjectAllocation;
import com.cfs.ems.model.Status;

@Repository
public class LoginDaoImpl implements LoginDao {

	public Status loginToDB(Login lpo) throws Exception {

		Status status = new Status();
		System.out.println("inside dao");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "root");
		//		Statement stmt = con.createStatement();
		PreparedStatement ps=con.prepareStatement("select * from login_table where employee_username=? and employee_password=?");
		ps.setString(1, lpo.getUsername());
		ps.setString(2, lpo.getPassword());

		ResultSet rs = ps.executeQuery();

		if(rs.next()){
			if(rs.getString(4).equals("y")){
				status.setStatus(1);

				String empId = rs.getString(1);
				status.setEmpId(empId);
				PreparedStatement ps2 = con.prepareStatement("select employee_details.First_Name, employee_details.Last_Name from employee_details inner join login_table on login_table.employee_id = employee_details.Employee_ID where login_table.employee_id = ?;");
				ps2.setString(1, empId);
				ResultSet rs2 =  ps2.executeQuery();
				rs2.next();
				status.setEmpName(rs2.getString(1));
				status.setEmpLastName(rs2.getString(2));

			}
			if(rs.getString(4).equals("n")){
				//				status.setStatus(2);
				String empId = rs.getString(1);
				status.setEmpId(empId);
				PreparedStatement ps2 = con.prepareStatement("select employee_details.First_Name, employee_details.Last_Name,employee_details.IsManager from employee_details inner join login_table on login_table.employee_id = employee_details.Employee_ID where login_table.employee_id = ?;");
				ps2.setString(1, empId);
				ResultSet rs2 =  ps2.executeQuery();
				if(rs2.next()){
					if(rs2.getString(3).equals("y")){
						status.setStatus(2);
						status.setEmpName(rs2.getString(1));
						status.setEmpLastName(rs2.getString(2));
					}
					else {
						status.setStatus(3);
						status.setEmpName(rs2.getString(1));
						status.setEmpLastName(rs2.getString(2));
					}


				}
			}
		}
		else
			status.setStatus(0);
		con.close();


		return status;
	}

	public ProjectAllocation updateProjectAll(ProjectAllocation e)	throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root");    
		Statement stmt=con1.createStatement();  
		System.out.println("inside dao");
		System.out.println(e.getProjectAllocationId());

		ResultSet rs=stmt.executeQuery("select * FROM project_allocations WHERE project_all_id ="+e.getProjectAllocationId()+"");


		ProjectAllocation e1= new ProjectAllocation();

		while (rs.next()) {

			e1.setProjectAllocationId(rs.getString("project_all_id"));
			e1.setEmployeeId(rs.getString("employee_id"));
			e1.setDesignation(rs.getString("designation"));
			e1.setStartDate(rs.getDate("start_date"));
			e1.setEndDate(rs.getDate("end_date"));
			e1.setProjectId(rs.getString("project_id"));

		}

		con1.close();
		return e1;

	}

	public Boolean updateAllocationDao(ProjectAllocation e1) throws Exception{


		boolean st=false;
		String projectId=null;

		Class.forName("com.mysql.jdbc.Driver");

		Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root"); 

		String sqlselect1="select project_id from project_details where project_name=?";
		PreparedStatement stmt1 = con2.prepareStatement(sqlselect1);
		stmt1.setString(1, e1.getProjectName());

		ResultSet rse = stmt1.executeQuery();
		while(rse.next()){
			projectId = rse.getString("project_id");
		}

		PreparedStatement ps= con2.prepareStatement("update project_allocations set project_id=?, designation=?,start_date=?,end_date=? where  project_all_id=?");  


		ps.setString(1,projectId);  
		ps.setString(2, e1.getDesignation());
		ps.setDate(3, (Date) e1.getStartDate());
		ps.setDate(4, (Date) e1.getEndDate());
		ps.setString(5, e1.getProjectAllocationId());



		int r=ps.executeUpdate();

		if(r>0){
			st=true;
		}
		else {
			st=false;
		}
		con2.close();
		return st;	

	}

	public Boolean delete(Integer id) throws Exception {
		Boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "root");

		String sql = "update project_allocations set status='inactive' WHERE project_all_id ="+id+" ";

		Statement stmt=con.createStatement();
		int result=stmt.executeUpdate(sql);
		System.out.println("No. of records successfully deleted: "+result);

		if(result>0) {
			status = true;
		} else {
			status = false;
		}		
		return status;
	}

	public List<ProjectAllocation> fetchAllToDb() throws SQLException {

		Connection con = null;
		ResultSet rs = null;
		List<ProjectAllocation> listOfProjects = new LinkedList<ProjectAllocation>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ems", "root", "root");

			String sql = "SELECT project_allocations.project_all_id, project_details.project_id, project_details.project_name, employee_details.employee_id, employee_details.first_name, employee_details.last_name, project_allocations.designation, project_allocations.start_date, project_allocations.end_date, project_allocations.status FROM ems.project_allocations, ems.project_details, ems.employee_details WHERE ems.project_allocations.project_id = ems.project_details.project_id AND ems.project_allocations.employee_id = ems.employee_details.employee_id AND ems.project_allocations.status = 'active';";

			PreparedStatement ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql);

			while (rs.next()) {

				ProjectAllocation pa = new ProjectAllocation();
				pa.setProjectAllocationId(rs.getString(1));
				pa.setProjectId(rs.getString(2));
				pa.setProjectName(rs.getString(3));
				pa.setEmployeeId(rs.getString(4));
				pa.setDesignation(rs.getString(7));
				pa.setStartDate(rs.getDate(8));
				pa.setEndDate(rs.getDate(9));
				pa.setStatus(rs.getString(10));
				listOfProjects.add(pa);

			}


		}

		catch (Exception e) {

			System.out.println("in dao   " + e);
		}

		finally {

			con.close();
		}
		return listOfProjects;

	}
	@Override
	public List fetchProjectNameDao() throws Exception {

		List list=new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root");
			Statement stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery( "SELECT project_name FROM project_details ");

			while(rs2.next()){
				list.add(rs2.getString("project_name"));

			}

		}catch(Exception e){
			e.printStackTrace();

		}

		return list;
	}

	@Override
	public List fetchEmployeeNameDao() throws Exception {
		List list=new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root");
			Statement stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery( "SELECT first_name FROM employee_details ");

			while(rs2.next()){
				list.add(rs2.getString("first_name"));

			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectAllocation> searchEmployeeProjectName(String projectName) throws Exception {
		List<ProjectAllocation> list=new LinkedList<>();
		System.out.println("before try");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root");
			System.out.println("inside dao" + projectName);
			String sql = "SELECT project_allocations.project_all_id, project_details.project_id, project_details.project_name, employee_details.employee_id, employee_details.first_name, employee_details.last_name, project_allocations.designation, project_allocations.start_date, project_allocations.end_date, project_allocations.status FROM ems.project_allocations, ems.project_details, ems.employee_details WHERE ems.project_allocations.project_id = ems.project_details.project_id AND ems.project_allocations.employee_id = ems.employee_details.employee_id AND ems.project_allocations.status = 'active' AND ems.project_details.project_name =" + "'" +projectName+ "';";
//			System.out.println("empId is " + empId);
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);

			while(rs.next()){
				ProjectAllocation pa = new ProjectAllocation();
				pa.setProjectAllocationId(rs.getString(1));
				pa.setProjectId(rs.getString(2));
				pa.setProjectName(rs.getString(3));
				pa.setEmployeeId(rs.getString(4));
				pa.setDesignation(rs.getString(7));
				pa.setStartDate(rs.getDate(8));
				pa.setEndDate(rs.getDate(9));
				pa.setStatus(rs.getString(10));
				list.add(pa);

			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProjectAllocation> searchEmployeeProject(String empId) throws Exception {
		List<ProjectAllocation> list=new LinkedList<>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root");
			String sql = "SELECT project_allocations.project_all_id, project_details.project_id, project_details.project_name, employee_details.employee_id, employee_details.first_name, employee_details.last_name, project_allocations.designation, project_allocations.start_date, project_allocations.end_date, project_allocations.status FROM ems.project_allocations, ems.project_details, ems.employee_details WHERE ems.project_allocations.project_id = ems.project_details.project_id AND ems.project_allocations.employee_id = ems.employee_details.employee_id AND ems.project_allocations.status = 'active' AND ems.project_allocations.employee_id =" +empId+ ";";
			System.out.println("empId is " + empId);
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);

			while(rs.next()){
				ProjectAllocation pa = new ProjectAllocation();
				pa.setProjectAllocationId(rs.getString(1));
				pa.setProjectId(rs.getString(2));
				pa.setProjectName(rs.getString(3));
				pa.setEmployeeId(rs.getString(4));
				pa.setDesignation(rs.getString(7));
				pa.setStartDate(rs.getDate(8));
				pa.setEndDate(rs.getDate(9));
				pa.setStatus(rs.getString(10));
				list.add(pa);

			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int allocationEmployeeDoa(ProjectAllocation projectAllocation)
			throws Exception {

		int status = 0;
		int project_all_Id=0;
		String projectId = null;
		String employeeId = null;
		Date startDate=null;
		Date endDate=null;

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","root");

		String sqlselect1="select project_id from project_details where project_name=?";
		PreparedStatement stmt1 = conn.prepareStatement(sqlselect1);
		stmt1.setString(1, projectAllocation.getProjectName());

		ResultSet rse = stmt1.executeQuery();
		while(rse.next()){
			projectId=rse.getString("project_id");
		}

		String sqlselect2="select project_all_id from project_allocations order by project_all_id DESC LIMIT 1";
		PreparedStatement stmt2 = conn.prepareStatement(sqlselect2);

		ResultSet rse1 = stmt2.executeQuery();
		while(rse1.next()){
			project_all_Id = rse1.getInt("project_all_id");

		}

		String sqlselect="select employee_id from employee_details where first_name=?";
		java.sql.PreparedStatement stmt = conn.prepareStatement(sqlselect);
		stmt.setString(1, projectAllocation.getEmployeeName());

		ResultSet rs2 = stmt.executeQuery();
		while(rs2.next()){
			employeeId=rs2.getString("employee_id");
		}

		PreparedStatement ps7=conn.prepareStatement("select start_date,end_date from project_details where project_id=?");
        ps7.setString(1,projectId);
        ResultSet rs6 = ps7.executeQuery();
        
        while(rs6.next()){
        	
                        startDate=rs6.getDate("start_date");
                        endDate=rs6.getDate("end_date");
                        System.out.println("dao start date is " + startDate);
                        
        }
        if(!projectAllocation.getStartDate().after(startDate) && !projectAllocation.getEndDate().before(endDate)){
        	System.out.println("pro start date is " +projectAllocation.getStartDate() ) ;
        	status=6;
        	return status;

        }



		PreparedStatement ps1=conn.prepareStatement("select * from project_allocations where employee_id=? and project_id=?");
		ps1.setString(1,employeeId );
		ps1.setString(2,projectId);

		ResultSet rs = ps1.executeQuery();

		if(rs.next()){
			status=3;
			return status;
		}
		else{
			String sql = "insert into project_allocations (project_all_id,project_id, employee_id, designation, start_date, end_date, status) values (?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			project_all_Id=project_all_Id+1;
			String project_all_Id1=Integer.toString(project_all_Id);
			ps.setString(1, (project_all_Id1));
			ps.setString(2, projectId);
			ps.setString(3, employeeId);
			ps.setString(4, projectAllocation.getDesignation());
			ps.setDate(5,(Date) projectAllocation.getStartDate());
			ps.setDate(6,  (Date) projectAllocation.getEndDate());
			ps.setString(7, "active");

			int result = ps.executeUpdate();

			if(result>0) {
				status = 1;
				return status;
			} else {
				status = 2;
				return status;
			}		
		}



	}

}