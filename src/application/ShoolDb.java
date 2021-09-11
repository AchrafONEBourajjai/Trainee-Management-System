package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShoolDb extends SqlInteraction {
	
	public static List<School> getShools() {
		List<School> list = new ArrayList<School>();
		try {
			String sql = "SELECT * FROM schools WHERE 1";
			Connection con = SqlInteraction.getConnection();
			PreparedStatement preparedstatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset =  preparedstatement.executeQuery();
			while(resultset.next()) {
				School school = new School();
				school.setNom(resultset.getString(1));
				school.setVille(resultset.getString(2));
				list.add(school);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
 }
