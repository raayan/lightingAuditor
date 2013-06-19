import java.sql.Date;


public class Light {
	private int ID;
	private String name;
	private String loc;
	private Date dateAdded;
	public Light(int id, String name, String location, Date date){
		ID = id;
		this.name = name;
		this.loc = location;
		this.dateAdded = date;
	}
	public int getID(){
		return ID;
	}
	public String getName(){
		return name;
	}
	public String getLocation(){
		return loc;
	}
	public Date getDate(){
		return dateAdded;
	}
	public String toString(){
		return this.ID + ":" + this.name;
	}
}
