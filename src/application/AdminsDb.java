package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminsDb extends SqlInteraction{

	
	public static List<Admins> getAdmins (){
		List<Admins> list = new ArrayList<Admins>();
		try {
			String sql = "SELECT * FROM users WHERE 1";
			Connection con = SqlInteraction.getConnection();
			PreparedStatement preparedstatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset =  preparedstatement.executeQuery();
			while(resultset.next()) {
				Admins adm = new Admins();
				adm.setUsername(resultset.getString(2));
				adm.setPassword(resultset.getString(3));
				list.add(adm);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
