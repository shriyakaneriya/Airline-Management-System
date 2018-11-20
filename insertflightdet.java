	
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
	
	public class insertflightdet extends Applet implements ActionListener, KeyListener{
	
	Label l1,l2,l3;
	TextField to,from,quan;
	Button b;
	Image img;
	
	public void init(){
	setLayout(new FlowLayout(FlowLayout.CENTER));
	setBackground(Color.GREEN);
	l1=new Label("TO", Label.LEFT);
	l2=new Label("From", Label.LEFT);
	l3=new Label("Quantity", Label.LEFT);
	to=new TextField(20);
	from=new TextField(20);
	quan=new TextField(20);
		b=new Button("Add");
	
	add(l1);
	add(to);
	add(l2);
	add(from);
	add(l3);
	add(quan);
	add(b);
	
	b.addActionListener(this);
	to.addActionListener(this);
	from.addActionListener(this);
	quan.addActionListener(this);
	
	to.addKeyListener(this);
	from.addKeyListener(this);
	quan.addKeyListener(this);
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
			add1(to.getText(),from.getText(),quan.getText());
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
	if(str.equals("Add"))
		try {
			add1(to.getText(),from.getText(),quan.getText());
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}//actionPerformed Close
	
	
	
	public void enter1(String passport, int flightno) throws SQLException
	{
		
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
		 String sql = "INSERT INTO flightdet " + "VALUES(\'"+passport+"\',"+"("+flightno+")";
		 stmt.executeUpdate(sql);
		
	
		
		    } catch (Exception e ) {
		        e.printStackTrace();}
		     finally{
		        if (stmt != null) { stmt.close(); }
		    }
		
	}
	
	
	
	
	
	
	public void add1(String to, String from,String qu) throws HeadlessException, SQLException{
	int flightno=Integer.valueOf(to)*10+Integer.valueOf(from);
	enter1(qu,flightno);
	
	
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
	//if close
	
	
	
	}//checkpass close
	
	
	}//class close