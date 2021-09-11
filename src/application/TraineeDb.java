package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TraineeDb {
	public static List<Trainee> getTrainee(){
		List<Trainee> list = new ArrayList<Trainee>();
		try {
			String sql = "SELECT * FROM Trainee WHERE 1";
			Connection con = SqlInteraction.getConnection();
			PreparedStatement preparedstatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset =  preparedstatement.executeQuery();
			while(resultset.next()) {
				Trainee trainee = new Trainee();
				trainee.setCin(resultset.getString(1));
				trainee.setNom(resultset.getString(2));
				trainee.setVille(resultset.getString(3));
				trainee.setEcole(resultset.getString(4));
				trainee.setSpecialite(resultset.getString(5));
				trainee.setNiveau(resultset.getString(6));
				trainee.setDatedepot(resultset.getString(7));
				trainee.setDatefin(resultset.getString(8));
				list.add(trainee);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
