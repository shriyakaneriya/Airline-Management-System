import java.util.Scanner;
import java.lang.Math;
public class crcheck {

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int  t=sc.nextInt();
		int x,y;
		double p;
		for(int i=0;i<t;i++)
		{
			p=sc.nextDouble();
			x=sc.nextInt();
			y=sc.nextInt();
			double angle=p<25?90-p*360/100:p<50?270-p*360/100:p<75?270-p*360/100:450-p*360/100;
			double abs=(x-50)*(x-50)+(y-50)*(y-50);
			
			double m= Math.tan(Math.toRadians(angle));
			double right=x*m+50-50*m;
			if(abs<=2500.0){
				if(p==0)System.out.println("Case #"+(i+1)+": "+"white");
				else if(p==50){if(x>=50)System.out.println("Case #"+(i+1)+": "+"black"); else System.out.println("Case #"+(i+1)+": "+"white");}
				else if(p==100)System.out.println("Case #"+(i+1)+": "+"black");
			else if(p<50){
					if(x>=50)
					{
					       if(y>=right)System.out.println("Case #"+(i+1)+": "+"black");
					       else System.out.println("Case #"+(i+1)+": "+"white");
				    }
					else{System.out.println("Case #"+(i+1)+": "+"white");}
				       }
			   else{
					if(x<50){if(y<=right)System.out.println("Case #"+(i+1)+": "+"black");
					          else{System.out.println("Case #"+(i+1)+": "+"white");}
					}
					else{System.out.println("Case #"+(i+1)+": "+"black");}
			}
				         }
			else
			{
				System.out.println("Case #"+(i+1)+": "+"white");
			}
		}
	}
}

