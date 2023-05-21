package project;

public class Hacker {
	private String hackerName, access1, access2, access3;
	
	public Hacker(String hackerName, String access1, String access2, String access3) {
		setHackerName(hackerName);
		setAccess1(access1);
		setAccess2(access2);
		setAccess3(access3);
	}

	//Setters
	private void setHackerName(String a) {
		// TODO Auto-generated method stub
		hackerName = a;
	}
	private void setAccess1(String a) {
		// TODO Auto-generated method stub
		access1 = a;
	}
	private void setAccess2(String a) {
		// TODO Auto-generated method stub
		access2 = a;
	}
	private void setAccess3(String a) {
		// TODO Auto-generated method stub
		access3 = a;
	}

	public String getHackerName() {
		return hackerName;
	}
	public String getAccess1() {
		return access1;
	}
	public String getAccess2() {
		return access2;
	}
	public String getAccess3() {
		return access3;
	}
	
	
	
	public String toString() {
		String a = "Hacker: " + getHackerName();
		a += "\nWho had access to : ";
		if(getAccess1()!="null") {
			a += getAccess1();
		}
		if(getAccess2()!="null") {
			a += ", " + getAccess2();
		}
		if(getAccess3()!="null") {
			a += ", " + getAccess3();
		}
		
		return a;
	}
	
	public String getAccess() {
		return getAccess1() + " " + getAccess2() + " " + getAccess3();
	}
	
}
