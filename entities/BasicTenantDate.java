package entities;

public class BasicTenantDate {
	
	private int userid;
	private String First_Last;
	
	public BasicTenantDate(int urid, String First, String Last){
		userid = urid;
		First_Last = First + " " + Last;
	}
	
	public String getFullName(){
		return First_Last;
	}
	
	public int getUserID(){
		return userid;
	}
	
	public String toString(){
		return First_Last;
	}
}
