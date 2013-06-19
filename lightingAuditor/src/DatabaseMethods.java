import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseMethods {
	private static final String fileName = "E:\\lightingAuditor.accdb";
	private static final String connectioinURL = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+fileName;

	public static boolean CreateLight(String name, String location) {
		try {
			String connURL = connectioinURL;
			Connection conn = DriverManager.getConnection(connURL, "","");
			Statement s = conn.createStatement();
			
			s.execute("INSERT INTO Lights (LightName, LightLocation, DateAdded) VALUES ('"+ name +"', '" + location +"', Date())");
						
			
			s.close();
			conn.close();
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateLocation(int id, String location) {
		try {
			String connURL = connectioinURL;
			Connection conn = DriverManager.getConnection(connURL, "","");
			Statement s = conn.createStatement();
			String crewMember = "N/A";
			s.execute("UPDATE Lights SET LightLocation='"+location+"' WHERE LightID=" + id);
			s.execute("INSERT INTO Moves (LightID, DateMoved, NewLocation, CrewMember) VALUES ("+ id +", Date(), '" + location + "', '" + crewMember + "')");
			
			s.close();
			conn.close();
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static String getLocation(int id) {
		try {
			String connURL = connectioinURL;
			Connection conn = DriverManager.getConnection(connURL, "","");
			Statement s = conn.createStatement();
			
			s.execute("SELECT LightLocation FROM Lights WHERE LightID=" + id);
						
			 ResultSet rs = s.getResultSet();

			 	
			 while ((rs!=null) && (rs.next())) {
			 		return rs.getString(1);
			 }
			s.close();
			conn.close();
			
			return "";
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return "N/A";
		}
	}
	public static boolean hasPassword(String username) {
		try {
			String connURL = connectioinURL;
			Connection conn = DriverManager.getConnection(connURL, "","");
			Statement s = conn.createStatement();
			
			s.execute("SELECT Password FROM CrewMember WHERE Name='" + username + "'");
						
			 ResultSet rs = s.getResultSet();

			 	
			 while ((rs!=null) && (rs.next())) {
			 		return true;
			 }
			s.close();
			conn.close();
			
			return false;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static String getPassword(String username) {
		try {
			String connURL = connectioinURL;
			Connection conn = DriverManager.getConnection(connURL, "","");
			Statement s = conn.createStatement();
			
			s.execute("SELECT Password FROM CrewMember WHERE Name='" + username  + "'");
						
			 ResultSet rs = s.getResultSet();

			 	
			 while ((rs!=null) && (rs.next())) {
			 		return rs.getString(1);
			 }
			s.close();
			conn.close();
			
			return "";
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	public static boolean returnToStorage(int id) {
		try {
			String connURL = connectioinURL;
			Connection conn = DriverManager.getConnection(connURL, "","");
			Statement s = conn.createStatement();
			
			s.execute("UPDATE Lights SET LightLocation='storage' WHERE LightID=" + id);
				
			 
			s.close();
			conn.close();
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	 /**
	  * Returns all the Buy Orders
	  * @param MCItemID
	  * @return
	  */
	 public static ArrayList<Light> getAllLights() {
	 	try {
	 	 String connURL = connectioinURL;
	 	 Connection conn = DriverManager.getConnection(connURL, "","");
	 	 Statement s = conn.createStatement();
	 	  	 	  
	 	 s.execute("SELECT LightID, LightName, LightLocation, DateAdded FROM Lights ORDER BY LightID");
	 	  
	 	 ResultSet rs = s.getResultSet();
	 	  
	 	 ArrayList<Light> lights = new ArrayList<Light>();
	 	 
	 	 while ((rs!=null) && (rs.next())) {
	 		Light created = new Light(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
	 		lights.add(created);
	 	 }
	 	 
	 	 s.close();
	 	 conn.close();
	 	 return lights;

	 	} catch (Exception ex) {
	 	 ex.printStackTrace();
	 	 return null;
	 	}
	 }


}
