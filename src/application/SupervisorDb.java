package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupervisorDb {
	public static List<Supervisor> getSupervisor(){
		List<Supervisor> list = new ArrayList<Supervisor>();
		try {
			String sql = "SELECT * FROM supervisors WHERE 1";
			Connection con = SqlInteraction.getConnection();
			PreparedStatement preparedstatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset =  preparedstatement.executeQuery();
			while(resultset.next()) {
				Supervisor supervisor = new Supervisor();
				supervisor.setEncadrant(resultset.getString(1));
				supervisor.setService(resultset.getString(2));
				supervisor.setAccord(resultset.getString(3));
				list.add(supervisor);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
