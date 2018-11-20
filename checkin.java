	
	import java.math.*;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.applet.*;
	import java.awt.*;
	import java.net.*;
	import java.awt.event.*;
	import java.awt.Graphics.*;
	import javax.swing.*;
	
	/*
	<applet code="login" width=300 height=300>
	</applet>
	*/
	
	public class checkin extends Applet implements ActionListener, KeyListener{
	
	Label l1,l2;
	TextField passportt,flightt;
	Button b;
	Image img;
	
	public void init(){
	setLayout(new FlowLayout(FlowLayout.CENTER));
	setBackground(Color.GREEN);
	l1=new Label("Passport Number:", Label.LEFT);
	l2=new Label("FLight Number  ", Label.LEFT);
	
	passportt=new TextField(20);
	flightt=new TextField(20);
	
	flightt.setEchoChar('*');
	
	b=new Button("Get in");
	
	add(l1);
	add(passportt);
	add(l2);
	add(flightt);
	add(b);
	
	b.addActionListener(this);
	passportt.addActionListener(this);
	flightt.addActionListener(this);
	
	passportt.addKeyListener(this);
	flightt.addKeyListener(this);
	
	}
	
	public void addHorizontalLine(Color c)
	   {
	      // Add a Canvas 10000 pixels wide but
	      // only 1 pixel high, which acts as
	      // a horizontal line.
	      Canvas line = new Canvas();
	      line.setSize(1000, 75);
	      line.setBackground(c);
	      add(line);
	   }
	
	
	
	public void start(){
	//setBackground(Color.white);
	
	//setBackground(Color.gray);
	}
	
	
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}
	
	
	public void keyPressed(KeyEvent ke){
	int key=ke.getKeyCode();
	
	if(key==KeyEvent.VK_ENTER)
		try {
			checkpass(passportt.getText(),flightt.getText());
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //keyPressed close
	
	
	public void paint(Graphics g){
	img = Toolkit.getDefaultToolkit().createImage("aai.jpeg");
	g.drawImage(img, 0, 0, null);
	} //paint close
	
	public void actionPerformed(ActionEvent ae){
	
	String str=ae.getActionCommand();
	if(str.equals("Get in"))
		try {
			checkpass(passportt.getText(),flightt.getText());
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}//actionPerformed Close
	
	
	
	public boolean login(String passport, String flightno) throws SQLException
	{
		
		boolean bool=false;
		 String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		    String DATABASE_URL = "jdbc:mysql://localhost:9080/airport";
		   	 String USERNAME = "root";
		   	String PASSWORD = "root";
		Connection con= null;
	
	
		Statement stmt = null;
	
		  
	
		try {
		                	Class.forName(JDBC_DRIVER);
		                	con = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
	
	
		 stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT COUNT("+Integer.valueOf(flightno)+") FROM flightbook WHERE passptid= "+passport+"?;");
			String s=rs.getString("COUNT("+Integer.valueOf(flightno)+")");
			
			int a=Integer.valueOf(s);
		if(a>0) bool=true;
	
		
		    } catch (Exception e ) {
		        e.printStackTrace();}
		     finally{
		        if (stmt != null) { stmt.close(); }
		    }
		
		return bool;
	}
	
	
	
	
	
	
	public void checkpass(String user, String pass) throws HeadlessException, SQLException{
	
	if(login(user,pass)) {
	
	AppletContext ac=getAppletContext();
	URL url=getCodeBase();
	
	try{
	ac.showDocument(new URL(url+ "loggedin.html"));
	System.out.println(url.toString());
	showStatus(url+"loggedin.html");
	}
	
	catch(MalformedURLException e){
	showStatus("Database is down.");}
	
	
	repaint();
	}//if close
	
	else JOptionPane.showMessageDialog(null,"You aint permitted!","Prompt",JOptionPane.ERROR_MESSAGE);
	//showStatus("Invalid Username-Password combination");
	
	
	}//checkpass close
	
	
	}//class close