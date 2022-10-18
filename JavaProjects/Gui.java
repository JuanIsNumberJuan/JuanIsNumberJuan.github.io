package project;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
public class Gui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n\n***** MySQL JDBC Testing *****");
        Connection conn = null;
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");

        String userName = ""; //Your user name
        String password = ""; //Your password
        String url = "jdbc:mysql://localhost:3306/final_phone";
        String url2 = "jdbc:mysql://localhost:3306/final_phone?autoReconnect=true&useSSL=false";
        //conn = DriverManager.getConnection(url,userName,password);
        conn = DriverManager.getConnection(url2, userName, password);

        System.out.println ("\nDatabase Connection Established...");
        Statement stmt=conn.createStatement();

        String[] optionsToChoose = {"Price (Low to High)", "Price (High to Low)",
         "Check Provider and Phones", "Customer Phones", "Customer Info", "Phone Capacity of 512GB",
         "iPhone Users", "One Customer Info", "Monthly Payment of Customers", "Phone Information", "No Information Needed"};

        String getQuery = (String) JOptionPane.showInputDialog(
                null,
                "What Query do you want to see",
                "Choose Query",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[0]);

        JFrame frame;
        JLabel label;
        JTextArea textArea;
        JScrollPane scrollPane;
        JPanel panel;


        //String data = "";
        String info = "";
        boolean a = true;

        while(!getQuery.equals(optionsToChoose[10])){
          ResultSet Query;
          DefaultListModel<String> data = new DefaultListModel<String>();
          if(getQuery.equals(optionsToChoose[0])){
            //Order from low to high
            Query = stmt.executeQuery("select Phone.modelName, capacity, price from phone order by phone.price;");
            info = "Price (Low to High)";
            data.addElement(String.format("%-50s%-20s%-20s", "Phone Model", "Capacity", "Price") + "\n");
            while(Query.next()){
            	data.addElement("\n" +String.format("%-50s %-20s %-20s", Query.getString(1), Query.getString(2), "$"+Query.getString(3)));
            }
          }
          else if(getQuery.equals(optionsToChoose[1])){
            //Order from high to low
        	info = "Price (High to Low)";
            Query = stmt.executeQuery("select Phone.modelName, capacity, price from phone order by phone.price desc;");
            data.addElement(String.format("%-50s%-20s%-20s", "Phone Model", "Capacity", "Price") + "\n");
            while(Query.next()){
            	data.addElement("\n" +String.format("%-50s%-20s%-20s", Query.getString(1), Query.getString(2), "$"+Query.getString(3)));
            }
          }
          else if(getQuery.equals(optionsToChoose[2])){
            //Check provider and phones
        	info = "Check Provider and Phones";
            Query = stmt.executeQuery("select Provider.providerName, Phone.modelName, capacity, price from phone, provider where provider.modelName = phone.modelName order by provider.providerName;");
            data.addElement(String.format("%-20s%-40s%-20s%-20s", "Provider", "Phone Model", "Capacity", "Price") + "\n");
            while(Query.next()){
            	data.addElement("\n" + String.format("%-20s%-40s%-20s%-20s", Query.getString(1), Query.getString(2), Query.getString(3), "$"+ Query.getString(4)));
            }
          }
          else if(getQuery.equals(optionsToChoose[3])){
            //Check what phones a customer has
        	info = "Customer Phones";
            Query = stmt.executeQuery("select distinct Customer.firstName, lastName, phone.modelName from customer, customerplan, plan, provider, phone where customer.customerID = customerplan.customerID and customerplan.planID = plan.planID and plan.providerID = provider.providerID and provider.modelName = phone.modelName order by lastName, firstName;");
            data.addElement(String.format("%-30s%-30s%-20s", "Customer First Name", "Customer Last Name", "Phone Model") + "\n");
            while(Query.next()){
            	data.addElement("\n" + String.format("%-30s%-30s%-20s", Query.getString(1), Query.getString(2), Query.getString(3)));
            }
          }
          else if(getQuery.equals(optionsToChoose[4])){
            //Outputs customer name, the plan they got, from which provider, and at what time
            info = "Customer Info";
        	Query = stmt.executeQuery("select DISTINCT Customer.lastName, firstName, Plan.planDescription, Provider.providerName, CustomerPlan.timeActivated from Customer, CustomerPlan, Plan, Provider where Customer.customerID = customerPlan.customerID and customerplan.planID = plan.planID and plan.providerID = provider.providerID order by lastName;");
        	data.addElement(String.format("%-20s%-20s%-70s%-20s%-20s", "Customer Last Name", "Customer First Name", "Plan", "Provider", "Time Activated") + "\n");
        	while(Query.next()){
            	data.addElement("\n" + String.format("%-20s%-20s%-70s%-20s%-20s", Query.getString(1), Query.getString(2), Query.getString(3),  Query.getString(4),  Query.getString(5)));
            }
          }
          else if(getQuery.equals(optionsToChoose[5])){
            //Gives phone with a certain amount of capacity
            //and output them from lowest to highest and vice
        	info = "Phone Capacity of 512GB";
            Query = stmt.executeQuery("select Phone.modelName, capacity, price from phone where capacity = \"512 GB\" order by price desc;");
            data.addElement(String.format("%-40s%-20s%-20s", "Phone Model", "Capacity", "Price") + "\n");
            while(Query.next()){
            	data.addElement("\n" +String.format("%-40s%-20s%-20s", Query.getString(1), Query.getString(2), "$"+Query.getString(3)));
            }
          }
          else if(getQuery.equals(optionsToChoose[6])){
            //iPhone users (Join)
        	info = "iPhone Users";
            Query = stmt.executeQuery("select customer.firstName, lastName, Provider.providerName, modelName from customer join customerplan using (customerID) join plan using (planID) join provider using(providerID) where modelName like \"I%\";");
            data.addElement(String.format("%-30s%-20s%-20s%-20s", "Customer First Name", "Customer Last Name", "Provider", "Phone Model") + "\n");
            while(Query.next()){
            	data.addElement("\n" + String.format("%-30s%-20s%-20s%-20s", Query.getString(1), Query.getString(2), Query.getString(3), Query.getString(4)));
            }
          }
          else if(getQuery.equals(optionsToChoose[7])){
            //One Customer Info (Join)
        	info = "One Customer Info";
            Query = stmt.executeQuery("select customer.firstName, lastName,  Provider.providerName, modelName, numOfLines from customer join customerplan using (customerID)  join plan using (planID) join provider using(providerID) where firstName = \"Brian\" and lastName = \"Smith\";");
            data.addElement(String.format("%-30s%-20s%-20s%-20s%-20s", "Customer First Name", "Customer Last Name", "Provider", "Model Name", "Number of Lines") + "\n");
            while(Query.next()){
            	data.addElement("\n" + String.format("%-30s%-20s%-20s%-20s%-20s", Query.getString(1), Query.getString(2), Query.getString(3), Query.getString(4), Query.getString(5)));
            }
          }
          else if (getQuery.equals(optionsToChoose[8])){
            //Monthly Payments (Join)
        	info = "Monthly Payment of Customers";
        	Query = stmt.executeQuery("select customer.firstName, lastName, (numOfLines * planRate) as TotalMonthlyPayment from customer join customerplan using (customerID) join plan using (planID);");
        	data.addElement(String.format("%-40s%-30s%-20s", "Customer First Name", "Customer Last Name", "MonthlyPayment") + "\n");
        	while(Query.next()){
            	data.addElement("\n" + String.format("%-40s%-30s%-20s", Query.getString(1), Query.getString(2), "$"+Query.getString(3)));
            }
          }
          else if(getQuery.equals(optionsToChoose[9])){
            //Phone Infromation (Join)
        	info =  "Phone Information";
        	Query = stmt.executeQuery("select Phone.modelName, capacity, price, Manufacturer.manufacturerName, OS.osType from phone join manufacturer using (manufacturerID) join os using (osID) order by manufacturerName;");
        	data.addElement(String.format("%-50s%-20s%-20s%-20s%-20s", "Phone Model", "Capacity", "Price", "Manufacturer", "OS Type") + "\n");
        	while(Query.next()){
            	data.addElement("\n" + String.format("%-50s%-20s%-20s%-20s%-20s", Query.getString(1), Query.getString(2), "$"+Query.getString(3), Query.getString(4), Query.getString(5)));
            }
          }
          else{
            Query = stmt.executeQuery("No more Information need ending program");
            a = false;
          }
          if(a) {
        	  /*textArea = new JTextArea(data, 20,50);
        	  scrollPane = new JScrollPane(textArea);
              JOptionPane.showMessageDialog(null, scrollPane);
        	  */
        	  JList<String> jList = new JList<>(data);
        	  Font defaultListFont = jList.getFont();
        	  jList.setFont(new Font("monospaced", defaultListFont.getStyle(), defaultListFont.getSize()));


        	  frame = new JFrame(info);
        	  //label = new JLabel(data);
        	  frame.setSize(1200,700);
        	  frame.setVisible(true);
        	  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	  //frame.add(label);

        	  Container contentPane = frame.getContentPane();
        	  contentPane.add(jList);

        	  /*panel = new JPanel();
        	  scrollPane = new JScrollPane(panel);
        	  frame.getContentPane().add(scrollPane);
        	  */
        	  /*
        	  textArea = new JTextArea(20,20);
        	  scrollPane = new JScrollPane(textArea);
        	  scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        	  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        	  frame.getContentPane().add(scrollPane);
        	  */
        	  //frame.getContentPane().add(scrollPane);

              //data = "";
          }


          getQuery = (String) JOptionPane.showInputDialog(
                  null,
                  "What Query do you want to see",
                  "Choose Query",
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  optionsToChoose,
                  optionsToChoose[0]);
                  System.out.println("Information: " + getQuery);

        }
        conn.close();
      }
      catch (Exception ex)
      {
          System.err.println ("Cannot connect to database server");
          ex.printStackTrace();
      }
	}
}
