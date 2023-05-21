package project;

public class Account {
	
	private String username, password, realname, address, phoneNum, device, auth1, auth2, auth3, auth4, auth5, successList="", failList="";
	private int success, fails;
	
	public Account(String username, String password, String realname, String address,
			String phoneNum, String device, String auth1, String auth2, String auth3,
			String auth4, String auth5) {
		setUserName(username);
		setPassword(password);
		setRealName(realname);
		setAddress(address);
		setPhoneNum(phoneNum);
		setDevice(device);
		setAuth1(auth1);
		setAuth2(auth2);
		setAuth3(auth3);
		setAuth4(auth4);
		setAuth5(auth5);
		
	}
	public void addToSuccess(String a) {
		successList+= a+ " ";
	}
	public void addToFail(String a) {
		failList+= a+ " ";
	}
	private String getSuccessList() {
		return successList;
	}
	private String getFailList() {
		return failList;
	}
	public void incrementSuccess(){
		success++;
	}
	private int getSuccess() {
		return success;
	}
	public void incrementFails() {
		fails++;
	}
	private int getFails() {
		return fails;
	}
	

	//Setters
	private void setUserName(String a) {
		// TODO Auto-generated method stub
		username = a;
	}
	private void setPassword(String a) {
		// TODO Auto-generated method stub
		password = a;
	}
	private void setRealName(String a) {
		// TODO Auto-generated method stub
		realname = a;
	}
	private void setAddress(String a) {
		// TODO Auto-generated method stub
		address = a;
	}
	private void setPhoneNum(String a) {
		// TODO Auto-generated method stub
		phoneNum = a;
	}
	private void setDevice(String a) {
		// TODO Auto-generated method stub
		device = a;
	}
	private void setAuth1(String a) {
		// TODO Auto-generated method stub
		auth1 = a;
	}
	private void setAuth2(String a) {
		// TODO Auto-generated method stub
		auth2 = a;
	}
	private void setAuth3(String a) {
		// TODO Auto-generated method stub
		auth3 = a;
	}
	private void setAuth4(String a) {
		// TODO Auto-generated method stub
		auth4 = a;
	}
	private void setAuth5(String a) {
		// TODO Auto-generated method stub
		auth5 = a;
	}

	public String getUserName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getRealName() {
		return realname;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public String getDevice() {
		return device;
	}
	public String getAuth1() {
		return auth1;
	}
	public String getAuth2() {
		return auth2;
	}
	public String getAuth3() {
		return auth3;
	}
	public String getAuth4() {
		return auth4;
	}
	public String getAuth5() {
		return auth5;
	}
	
	public String toString() {
		String a = "Username: " + getUserName();
		a += "\nLocation: " + getAddress();
		a += "\nDevice: " + getDevice();
		return a;
	}
	public String toStringToGUI() {
		String a = "<html><br>Username: " + getUserName();
		a += "<br> Location: " + getAddress();
		a += "<br>Device: " + getDevice() + "</html>";
		return a;
	}
	
	public String getAuthentications() {
		return getAuth1() + " " + getAuth2() + " " + getAuth3() + " " + getAuth4() + " " + getAuth5();
	}
	
	public String SuccessToFail() {
		return getUserName() + ":\nSuccessfully defended against " + getSuccess() + " Hacker(s)"
				+ "\nSuccessfully defended against "+getSuccessList()
				+ "\nUnsuccessfully defended against "+getFails()+" Hacker(s)"
						+ "\nUnsuccessfully defended against "+getFailList()
						+ "\nSuccess Rate: " +(double)getSuccess()/(getSuccess()+getFails())*100 +"%";
	}


}
