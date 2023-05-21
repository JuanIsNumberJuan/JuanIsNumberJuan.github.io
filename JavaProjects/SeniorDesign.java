package project;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class SeniorDesign{
	
	private static JLabel usernameLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passText;
	private static JButton button, button2;
	private static JLabel success;
	private static JLabel success2;
	
	private static boolean close = false;
	
	private static ArrayList<Account> data = new ArrayList<Account>();
	private static ArrayList<Hacker> data2 = new ArrayList<Hacker>();		

	public static void main(String[] args) {
		System.out.println("\n\n***** MySQL JDBC Testing *****");
        Connection conn = null;
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");

        String userName = "root";
        String password = "Mimikyu815$";
        String url = "jdbc:mysql://localhost:3306/SeniorDesign";
        String url2 = "jdbc:mysql://localhost:3306/SeniorDesign?autoReconnect=true&useSSL=false";
        //conn = DriverManager.getConnection(url,userName,password);
        conn = DriverManager.getConnection(url2, userName, password);

        System.out.println ("\nDatabase Connection Established...");
        Statement stmt=conn.createStatement();
		
        
        ResultSet Query = stmt.executeQuery("select * from Account;");
        
        while(Query.next()) {
        	Account a = new Account(Query.getString(1), Query.getString(2), Query.getString(3),
        			Query.getString(4), Query.getString(5), Query.getString(6), Query.getString(7),
        			Query.getString(8), Query.getString(9), Query.getString(10), Query.getString(11));
        	
        	data.add(a);
        }

        ResultSet Query2 = stmt.executeQuery("select * from Hacker;");
        while(Query2.next()) {
        	Hacker a = new Hacker(Query2.getString(1), Query2.getString(2), Query2.getString(3), Query2.getString(4));
        	data2.add(a);
        }
        for(int x = 0; x<data.size(); x++) {
        	for(int y = 0; y<data2.size(); y++){
        		if(data.get(x).getAuthentications().contains("Password") && !data2.get(y).getAccess().contains("Password")) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("Facial Recognition") && (!data2.get(y).getAccess().contains("Facial Recognition") && !data2.get(y).getAccess().contains("Biometrics"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("Fingerprint scan") && (!data2.get(y).getAccess().contains("Fingerprint Scan") && !data2.get(y).getAccess().contains("Biometrics"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("Location") && (!data2.get(y).getAccess().contains("Location") && !data2.get(y).getAccess().contains("Stolen Device"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("Text-Message") && (!data2.get(y).getAccess().contains("SMS") && !data2.get(y).getAccess().contains("Stolen Device"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("App") && (!data2.get(y).getAccess().contains("App") && !data2.get(y).getAccess().contains("Stolen Device"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("DeviceID") && (!data2.get(y).getAccess().contains("DeviceID") && !data2.get(y).getAccess().contains("Stolen Device"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		else if(data.get(x).getAuthentications().contains("Random Number") && (!data2.get(y).getAccess().contains("SMS") && !data2.get(y).getAccess().contains("App") && !data2.get(y).getAccess().contains("Stolen Device"))) {
        			data.get(x).incrementSuccess();
        			data.get(x).addToSuccess(data2.get(y).getHackerName());
        		}
        		
        		else {
        			data.get(x).incrementFails();
        			data.get(x).addToFail(data2.get(y).getHackerName());
        		}
        	}
        }
      
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(10,20,80,25);
        panel.add(usernameLabel);
        
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        
        passText = new JPasswordField(20);
        passText.setBounds(100,50,165,25);
        panel.add(passText);
        
        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(new ActionListener() {
      
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//String auth = "";
		        
				String username = userText.getText();
				String password = passText.getText();
				String authentications = "";
				boolean a = true; //this is used to hide error if username and password do not match with database information
				
				
				for(int x = 0; x < data.size(); x++) {
					if(username.equals(data.get(x).getUserName()) && password.equals(data.get(x).getPassword())) {
						a = false;//if false it will display an error message saying username of password are incorrect
						authentications = data.get(x).getAuthentications();
						JFrame authFrame = new JFrame("Authentication");
						authFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						authFrame.setSize(1150,1000);
						JPanel authPanel = new JPanel();
						authFrame.add(authPanel);
						
						JLabel first = new JLabel();
						first.setText("");
						JLabel second = new JLabel();
						second.setText("");
						JLabel third = new JLabel();
						third.setText("");
						JLabel fourth = new JLabel();
						fourth.setText("");
						JLabel fifth = new JLabel();
						fifth.setText("");
						JLabel sixth = new JLabel();
						sixth.setText("");
						JLabel seventh = new JLabel();
						seventh.setText("");
						
						if(authentications.contains("Password")) {
			        		first.setText("<html><br>Password Authentication: Success</html>");
			        		first.setBounds(10, 10, 200, 100);
			        		authPanel.add(first);
						}
						if(authentications.contains("App")) {
							String label = "App-Based Authenticate following user: " + data.get(x).toStringToGUI();
							JLabel text = new JLabel("<html>" +label + "</html>");
							text.setBounds(10,50,200,100);
							authPanel.add(text);
							JButton authButton = new JButton("Authenticate");
							authButton.setBounds(300,150,200,25);
							authButton.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									second.setText("Success");
								}
							});
							JButton authButton2 = new JButton("Don't Authenticate");
					        authButton2.setBounds(300,150,200,25);
					        authButton2.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		second.setText("Login Fail");
					        	}
					        });
					        second.setBounds(30, 1200, 200, 100);
					        authPanel.add(second);
					        authPanel.add(authButton);
					        authPanel.add(authButton2);
						}
						if(authentications.contains("Location")) {
							String label = "<br>This uses the location of the user's phone and compares it to the location of the device being used to sign into account"
									+"<br>If they match then the user is authenticated without having to enter anything just give access to cerrent location"
									+"<br>Since this is a simulation just enter address used to create account"; 
							JLabel text = new JLabel("<html>" + label +"</html>");
							text.setBounds(10,100,200,100);
							authPanel.add(text);
							JButton authButton = new JButton("Location Match");
							authButton.setBounds(500,100,200,25);
							authButton.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									third.setText("Success");
								}
							});
							JButton authButton2 = new JButton("Location Mismatch");
							authButton2.setBounds(600,100,200,25);
							authButton2.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									third.setText("Login Fail");
								}
							});
							third.setBounds(50, 1200, 200, 100);
							authPanel.add(third);
					        authPanel.add(authButton);
					        authPanel.add(authButton2);
						}
						if(authentications.contains("Facial Recognition")) {
							String label ="<br>Facial Recognition:<br>Authenticate following user:<br>" + data.get(x).toStringToGUI();
							JLabel text = new JLabel("<html>" + label + "</html>");
							text.setBounds(10,400,200,100);
							authPanel.add(text);
							JButton authButton = new JButton("Authenticate");
							authButton.setBounds(500,400,200,25);
							authButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									fourth.setText("Facial Recognition Successful");
								}
							});
							JButton authButton2 = new JButton("Facial Recognition Unsuccessful");
							authButton2.setBounds(600,400,200,25);
							authButton2.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									fourth.setText("Login Fail");
								}
							});
							fourth.setBounds(70, 1200, 200, 100);
							authPanel.add(fourth);
					        authPanel.add(authButton);
					        authPanel.add(authButton2);
						}
						if(authentications.contains("Fingerprint scan")) {
							String label = "<br>Fingerprint Scan: <br>Authenticate following user:<br>" + data.get(x).toStringToGUI();
							JLabel text = new JLabel("<html>" + label + "</html>");
							text.setBounds(10,700,200,100);
							authPanel.add(text);
							JButton authButton = new JButton("Fingerprint Scan Successful");
					        authButton.setBounds(500,700,200,25);
					        authButton.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		fifth.setText("Success");
					        	}
					        });
					        JButton authButton2 = new JButton("Fingerprint Scan Unsuccessful");
							authButton2.setBounds(600,700,200,25);
							authButton2.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									fifth.setText("Login Fail");
								}
							});
							fifth.setBounds(90, 1200, 200, 100);
							authPanel.add(fifth);
					        authPanel.add(authButton);
					        authPanel.add(authButton2);
						}
						if(authentications.contains("Text-Message")) {
							String label = "<br>SMS Authenticate following user:<br>" + data.get(x).toString();
							JLabel text = new JLabel("<html>" + label + "</html>");
							text.setBounds(10,900,200,100);
							authPanel.add(text);
							JButton authButton = new JButton("Yes");
					        authButton.setBounds(500,900,200,25);
					        authButton.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		sixth.setText("Success");
					        	}
					        });
					        JButton authButton2 = new JButton("No");
					        authButton2.setBounds(600,900,200,25);
					        authButton2.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		sixth.setText("Login Fail");
					        	}
					        });
					        sixth.setBounds(110, 1200, 200, 100);
					        authPanel.add(sixth);
					        authPanel.add(authButton);
					        authPanel.add(authButton2);
						}
						if(authentications.contains("Random Generated Number")) {
							Random ranNum = new Random();
							int num1 = ranNum.nextInt();
							int num2 = ranNum.nextInt();
							int num3 = ranNum.nextInt();
							int num4 = ranNum.nextInt();
							String label = "<br>Random Number sent to " +data.get(x).getPhoneNum()+": " +num1 + "<br>Enter random number:";
							JLabel text = new JLabel("<html>" + label + "</html>");
							text.setBounds(10, 1000, 200, 100);
							authPanel.add(text);
							JButton authButton1 = new JButton(String.valueOf(num2));
							authButton1.setBounds(10,1100,200,25);
							authButton1.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		seventh.setText("Unsuccess");
					        	}
					        });
							JButton authButton2 = new JButton(String.valueOf(num3));
							authButton2.setBounds(40,1100,200,25);
							authButton2.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		seventh.setText("Unsuccess");
					        	}
					        });
							JButton authButton3 = new JButton(String.valueOf(num1));
							authButton3.setBounds(70,1100,200,25);
							authButton3.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		seventh.setText("Success");
					        	}
					        });
							JButton authButton4 = new JButton(String.valueOf(num4));
							authButton4.setBounds(100,1100,200,25);
							authButton4.addActionListener(new ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		seventh.setText("Unsuccess");
					        	}
					        });
							seventh.setBounds(130, 1200, 200, 100);
							authPanel.add(seventh);
					        authPanel.add(authButton1);
					        authPanel.add(authButton2);
					        authPanel.add(authButton3);
					        authPanel.add(authButton4);
						}
						
						JLabel access = new JLabel();
						access.setText("<html><br><br><br><br><br><br> If all forms of authentication are successful login was a success else access was not given</html>");
						access.setBounds(10, 1500, 200, 100);
						authPanel.add(access);
						authFrame.setVisible(true);					
						
					}
				}
				if(a) {
					success.setText("Username or password incorrect");
				}
			}
        	
        });
        panel.add(button);
        
        button2 = new JButton("Create Account");
        button2.setBounds(100, 80, 130,25);
        button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame2 = new JFrame();
		        
		        JPanel panel2 = new JPanel();
		        frame2.setSize(500,600);
		        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame2.add(panel2);
		        
		        panel2.setLayout(null);
		        
		        JLabel usernameLabel2 = new JLabel("* Username: ");
		        usernameLabel2.setBounds(10,20,200,25);
		        panel2.add(usernameLabel2);
		        JTextField userText2 = new JTextField(20);
		        userText2.setBounds(120,20,165,25);
		        panel2.add(userText2);
		        
		        JLabel passwordLabel2 = new JLabel("* Password: ");
		        passwordLabel2.setBounds(10,50,200,25);
		        panel2.add(passwordLabel2);
		        JPasswordField passText2 = new JPasswordField(20);
		        passText2.setBounds(120,50,165,25);
		        panel2.add(passText2);
		        
		        JLabel nameLabel = new JLabel("* Name: ");
		        nameLabel.setBounds(10,80,200,25);
		        panel2.add(nameLabel);
		        JTextField nameText = new JTextField(20);
		        nameText.setBounds(120,80,165,25);
		        panel2.add(nameText);
		        
		        JLabel addressLabel = new JLabel("* Address: ");
		        addressLabel.setBounds(10,110,200,25);
		        panel2.add(addressLabel);
		        JTextField addressText = new JTextField(20);
		        addressText.setBounds(120,110,165,25);
		        panel2.add(addressText);
		        
		        JLabel phoneNumLabel = new JLabel("* Phone Number: ");
		        phoneNumLabel.setBounds(10,140,200,25);
		        panel2.add(phoneNumLabel);
		        JTextField phoneNumText = new JTextField(20);
		        phoneNumText.setBounds(120,140,165,25);
		        panel2.add(phoneNumText);
		        
		        JLabel deviceLabel = new JLabel("* Device: ");
		        deviceLabel.setBounds(10,170,200,25);
		        panel2.add(deviceLabel);
		        JTextField deviceText = new JTextField(20);
		        deviceText.setBounds(120,170,165,25);
		        panel2.add(deviceText);
		        
		        JLabel label = new JLabel("* Check one or up to five boxes:");
		        label.setBounds(10,200,200,25);
		        panel2.add(label);
		        
		        JCheckBox mobileBased,locationBased,facialRec,fingerScan,txtMessage, randNum, passwrd;
		        
		        int boxCounter = 0;
		        mobileBased = new JCheckBox("App-Based");
		        mobileBased.setBounds(10,230,200,25);
		        panel2.add(mobileBased);
		        
		        locationBased = new JCheckBox("Location-Based");
		        locationBased.setBounds(10,260,200,25);
		        panel2.add(locationBased);
		        
		        facialRec = new JCheckBox("Facial Recognition");
		        facialRec.setBounds(10,290,200,25);
		        panel2.add(facialRec);
		        
		        fingerScan = new JCheckBox("Fingerprint Scan");
		        fingerScan.setBounds(10,320,200,25);
		        panel2.add(fingerScan);
		        
		        txtMessage = new JCheckBox("Text-Message");
		        txtMessage.setBounds(10,350,200,25);
		        panel2.add(txtMessage);
		        
		        randNum = new JCheckBox("Random Number Generator");
		        randNum.setBounds(10,380,270,25);
		        panel2.add(randNum);
		        
		        passwrd = new JCheckBox("Password");
		        passwrd.setBounds(10,410,200,25);
		        panel2.add(passwrd);
		        
		        JButton create = new JButton("Create Account");
		        create.setBounds(10,440,200,25);
		        create.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ArrayList<String> choices = new ArrayList<String>();
						String query = "";
						boolean a = true, b = true;
						int counter = 0;
						for(int x = 0; x < data.size(); x++) {
							if(userText2.getText().equals(data.get(x).getUserName())) {
								a = false;
								break;
							}
						}
						if(mobileBased.isSelected()) {
							counter++;
							choices.add("App");
						}
						if(locationBased.isSelected()) {
							counter++;
							choices.add("Location");
						}
						if(facialRec.isSelected()) {
							counter++;
							choices.add("Facial Recognition");
						}
						if(fingerScan.isSelected()) {
							counter++;
							choices.add("Fingerprint Scan");
						}
						if(txtMessage.isSelected()) {
							counter++;
							choices.add("Text-Message");
						}
						if(randNum.isSelected()) {
							counter++;
							choices.add("Random Generated Number");
						}
						if(passwrd.isSelected()) {
							counter++;
							choices.add("Password");
						}
						
						if(userText2.getText().isEmpty() || passText2.getText().isEmpty() || nameText.getText().isEmpty()|| addressText.getText().isEmpty() || phoneNumText.getText().isEmpty() || deviceText.getText().isEmpty()) {
							//JOptionPane.showMessageDialog(panel2, "All *required fields must be completed");
							success2.setText("All *required fields must be completed");
							b = false;
						}
						if(!a) {
							//JOptionPane.showMessageDialog(panel2, "Username is already taken");
							success2.setText("Username is already taken");
						}
						if(counter == 0) {
							//JOptionPane.showMessageDialog(panel2, "Must select atleast one additional form of authentication");
							success2.setText("Must select atleast one additional form of authentication");
						}
						if(counter > 5) {
							//JOptionPane.showMessageDialog(panel2, "Must select no more then 5 forms of authentication");
							success2.setText("Must select no more then 5 forms of authentication");
						}
						if(a && counter > 0 && counter <= 5 && b){
							success2.setText("Account created successfully. \nYou may now close this page.");
							
							query += "INSERT INTO Account (Username, PassWrd, RealName, Address, PhoneNumber, Device,\n"
									+ " Authentication1, Authentication2, Authentication3, Authentication4, Authentication5)"
									+ "VALUES ('" + userText2.getText().toString() + "','" + passText2.getText().toString() + "','" + nameText.getText().toString() 
							+ "','" +	addressText.getText().toString() + "','" + phoneNumText.getText().toString() + "','" + deviceText.getText().toString(); 
							
							
							for(int x = 0; x<5; x++) {
								if(choices.size()>x && choices.get(x)!=null) {
									query += "','" + choices.get(x);
								}
								else {
									query += "','" + "null";
								}
							}
							query += "')";
							try {
								stmt.executeUpdate(query);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							close = true;
						}
						
					}
		        });
		        
		        panel2.add(create);
		        success2 = new JLabel("");
		        success2.setBounds(10,470,500,25);
		        panel2.add(success2);
		        
		        frame2.setVisible(true);
		        
			}
        	
        });
        panel.add(button2);
        
        JButton hackerButton = new JButton("HACKER");
        hackerButton.setBounds(10,120,80,25);
        hackerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String a = "";
        		for(int x = 0; x<data.size(); x++) {
        			a += data.get(x).SuccessToFail() +  "\n\n";
        		}
        		
                System.out.println(a);
        	}
        });
        panel.add(hackerButton);
        
        success = new JLabel("");
        success.setBounds(10,110,300,25);
        panel.add(success);
        frame.setVisible(true);
  	  
        
        if(close) {
        	conn.close();
        	System.out.println("Closing Connection...");
        }
        
      }
      catch (Exception ex)
      {
          System.err.println ("Cannot connect to database server");
          ex.printStackTrace();
      }
      
       
	}
	

}

