
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

public class fixKDE {
	Point2D p = new Point2D();
	
	public void data() {
		 File file = new File("./prop/input_fixKDE.txt");
		 File fileOut = new File("./prop/fixKDE_datamodified");
		 
		 try
		 {
			 FileInputStream fis = new FileInputStream(file);
		     FileOutputStream fout = new FileOutputStream(fileOut);
		     char current=' ';
		     
		     while (fis.available() > 0)
		     {
		    	 current = (char) fis.read();
		    	 if (current == '|' || current == '(' || current == ')') current = ' ';
		    	 fout.write(current);
		    	 //System.out.print(current);
		     }
		      
		     fis.close();
		     fout.close();
		     
		     BufferedReader r = new BufferedReader(new FileReader("./prop/fixKDE_datamodified"));
		     String line = "";
		     int aux=3;
		     int count_line = 0;
		     long number_points = 0, number_points_ref = 0;
		     double xValue[] = null, yValue[] = null;
		     double xListValue[] = null, yListValue[] = null;
		     double h[] = null;
		     
			 while ((line = r.readLine()) != null)
			 {
				 line = line.trim();
			     if (line.startsWith("#")) continue; // Comment line
			      	
			     String[] parts = line.split("\\s+");
			     Scanner input = new Scanner(System.in);
			     
			     if (count_line %2 == 0) 
			     { 
			    	 number_points = Long.parseLong(parts[1]);
			    	 xValue = new double[(int) number_points];
			    	 yValue = new double[(int) number_points];
			    	 System.out.print("Enter " + number_points + " h values: ");
			    	 h = new double[(int) number_points];
			    	 
			    	 for (int i=0; i<number_points; i++) h[i] = input.nextDouble();
			    	 System.out.println();
			    	 
			    	 for (int point=0; point<number_points; point++)
			    	 {
			    		 xValue[point] = Double.parseDouble(parts[aux]);
			    		 aux = aux+1;
			    		 yValue[point] = Double.parseDouble(parts[aux]);
			    		 System.out.print("POINT: " + xValue[point] + ", " + yValue[point] + "\t");
			    		 aux +=2;
			    	 }
			     }

			     else
			     {
			    	 number_points_ref = Long.parseLong(parts[1]);
			    	 System.out.print("\n\nREFERENCE LIST: \n"); 
			    	 xListValue = new double[(int) number_points_ref];
			    	 yListValue = new double[(int) number_points_ref];

			    	 for (int point=0; point<number_points_ref; point++)
			    	 {		 
			    		 xListValue[point] = Double.parseDouble(parts[aux]);
			    		 aux = aux+1;
			    		 yListValue[point] = Double.parseDouble(parts[aux]);
			    		 System.out.print("POINT " + (point+1) + ": " + xListValue[point] + ", " + yListValue[point] + "\n");
			    		 aux +=2;
			    	 }
			    	 
			    	 System.out.println("Output: ");
			    	 fixedKDE(h, xValue, yValue, xListValue, yListValue, (int)number_points, (int)number_points_ref);
			    	 
			      }
			     aux = 3;
			     count_line++;
			 }
			 r.close(); 
		 }
		    
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static double distanceBetweenTwoPoints(double xValue, double yValue, double xListValue, double yListValue)
	{
		return Math.sqrt((Math.pow((xValue - xListValue), 2) + (Math.pow((yValue - yListValue), 2))));
	}
	
	public static void fixedKDE(double h[], double xListValue[], double yListValue[], double xListValueRef[], double yListValueRef[], int number_points, int number_points_ref)
	{
		double output = 0;
		for (int i = 0; i < number_points; i++)
		{
			output = 0;
			for (int j = 0; j < number_points_ref; j++)
			{
				output += (1/(2*Math.PI*h[i])) * Math.exp((-1/(2*Math.PI) * distanceBetweenTwoPoints(xListValue[i], yListValue[i], xListValueRef[j], yListValueRef[j])));
				
			}
			System.out.println("OUTPUT POINT " + (i+1) + ": " + output + " ");
		}
	}
	
	
}
