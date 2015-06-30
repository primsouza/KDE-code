/*************************************************************************
 *  Compilation:  javac RangeSearchVisualizer.java
 *  Execution:    java RangeSearchVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java Point2D.java RectHV.java
 *                StdDraw.java In.java
 *
 *  Read points from a file (specified as a command-line arugment) and
 *  draw to standard draw. Also draw all of the points in the rectangle
 *  the user selects by dragging the mouse.
 *
 *  The range search results using the brute-force algorithm are drawn
 *  in red; the results using the kd-tree algorithms are drawn in blue.
 *
 *************************************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;

//import KdTree.Node;


public class AverageExperiment {

    private static ArrayList<Point2D> kdpoints;

    private static KdTree kdtree;
    
    public static void readFile() {
    	File file = new File("./data/input_fixKDE.txt");
		 File fileOut = new File("./data/fixKDE_datamodified");
		 //Scanner input = new Scanner(System.in);
		 
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
		     
		     BufferedReader r = new BufferedReader(new FileReader("./data/fixKDE_datamodified"));
		     String line = "";
		     int aux=2;
		     //int count_line = 0;
		     long number_points = 0;//, number_points_ref = 0;
		     double xValue = 0, yValue = 0;
		     //double xListValue[] = null, yListValue[] = null;
		     double h[] = null;
		     
		     
			 while ((line = r.readLine()) != null)
			 {
				 line = line.trim();
			     if (line.startsWith("#")) continue; // Comment line
			      	
			     String[] parts = line.split("\\s+");
			     
			     
			     //if (count_line %2 == 0) 
			     //{ 
			    	 number_points = Long.parseLong(parts[0]);

			    	 //System.out.print("Enter " + number_points + " h values: ");
			    	 //h = new double[(int) number_points];
			    	 
			    	 //for (int i=0; i<number_points; i++) h[i] = input.nextDouble();
			    	// System.out.println();
			    	 
			    	 Point2D[] points = new Point2D[(int)number_points];
			    	 for (int point=0; point<number_points; point++)
			    	 {
			    		 xValue = Double.parseDouble(parts[aux]);
			    		 aux = aux+1;
			    		 yValue = Double.parseDouble(parts[aux]);
			    		 System.out.print("POINT: " + xValue + ", " + yValue + "\t");
			    		 aux +=2;
			    		 points[point] = new Point2D(xValue, yValue);
			    		 kdpoints.add(points[point]);
			    		 kdtree.insert(points[point]);
			    		 
			    		 //double x = StdRandom.uniform(100);
		    	           // double y = StdRandom.uniform(100);
		    	            //x = x/100;
		    	           // y = y/100;
		    	           // points[i] = new Point2D(x, y);
		    	            //points[point].draw();
		    	            //System.out.printf("%f %f\n", points[i].x(), points[i].y());
		    	            //kdtree.insert(points[i]);
		    	            
		    	        // kdpoints.add(points[point]);
		    	            //brute.insert(points[i]);
		
			    	 }
			     //}

			     /*else
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
			     count_line++;*/
			 }
			 r.close(); 
		 }
		    
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 /*System.out.println("\nEnter the number of reference points: ");
		 int number_ref_points = input.nextInt();
		 System.out.println("Enter the reference points: x y separeted by space");
		 Point2D[] ref_points = new Point2D[number_ref_points];
		 for (int i=0; i<number_ref_points; i++) {
			 double x = input.nextDouble();
			 double y = input.nextDouble();
			 ref_points[i] = new Point2D(x, y);
		 }*/
		 
    }

    
    public static void main(String[] args) {

       // int N = Integer.parseInt("990000"); // for how many points
        long iterations = 1;
        //long totalBrute = 0;
        long totalKd = 0;
        
        Scanner input = new Scanner(System.in);
        
        for (int iter=0; iter < iterations ; iter++) {
        //PointSET brute = new PointSET();
        kdtree = new KdTree();
        kdpoints = new ArrayList<Point2D>();
        readFile();
        
        //System.out.println("\nEnter a point x y to find out the 3 nearest points: ");
		 double x = input.nextDouble();
		 double y = input.nextDouble();
		 Point2D p = new Point2D(kdpoints.get(0).x(), kdpoints.get(0).y());
		 Point2D p2 = new Point2D(x, y);
		 System.out.println("\nGet: " + kdpoints.get(0).y());
		 //kdtree.SearchKDTree(p, p2, 0);
		 //p.distanceTo();
		 

        // Generates N points between 0-1 and intialize the data structures
        //Point2D[] points = new Point2D[N];
        //for (int i = 0; i < N; i++) {
            //double x = StdRandom.uniform(100);
            //double y = StdRandom.uniform(100);
            //x = x/100;
           // y = y/100;
            //points[i] = new Point2D(x, y);
            //points[i].draw();
            //System.out.printf("%f %f\n", points[i].x(), points[i].y());
            //kdtree.insert(points[i]);
            
            //kdpoints.add(points[i]);
            //brute.insert(points[i]);
        //}

        buildTree(kdpoints,true); 
        //StdDraw.show();

        //Random rectangle between 0-0.1
        double xmin = StdRandom.uniform(100);
        double ymin = StdRandom.uniform(100);

        xmin = xmin / 100;
        ymin = ymin / 100;

        double xmax = xmin + 0.1;
        double ymax = ymin + 0.1;

        // draw rectangle 
        RectHV rect = new RectHV(xmin, ymin, xmax, ymax);
        //RectHV rect = new RectHV(0.3, 0.4, 0.5, 0.5);   //uncomment this line to see the big rectangle, original rectangle is very small (0-.1)
       
        //////////////////////////////////////////////////
        // draw the range search results for brute-force data structure in red

        long startTime, endTime;
        
        //////
        startTime = System.nanoTime();
       // Iterable<Point2D> bruteRange = brute.range(rect);
        endTime = System.nanoTime();
        ///////

        //totalBrute += (endTime - startTime); 
        //System.out.printf("Time Taken by Brute Force %d \n", endTime - startTime); 
        
        //StdDraw.clear();
           
        // draw the range search results for kd-tree in blue
        
        /////////
        startTime = System.nanoTime();

        Iterable<Point2D> kdRange = kdtree.range(rect);
        endTime = System.nanoTime();
        /////////
        totalKd += (endTime - startTime); 
        //System.out.printf("Time Taken by KDTree %d \n", endTime - startTime); 
    
        }

        totalKd /= iterations;
        //totalBrute /= iterations;

        //System.out.printf("Average Time Taken by Brute Force %d \n", totalBrute); 
        System.out.printf("\nAverage Time Taken by KDTree %d \n", totalKd);   
        
        test();
    }

    public static void test() {
    	Point2D ptest = new Point2D(-1.6051, 54.9888);
    	System.out.println("\nDoes tree contain the point -1.6051, 54.9888? " + kdtree.contains(ptest));
    	
    	ptest = new Point2D(-2, 2);
    	System.out.println("\nDoes tree contain the point -2, 2? " + kdtree.contains(ptest));
    	
    	RectHV rectest = new RectHV(-1.6053, 54.8999, -1.6, 54.9988);
    	System.out.println("\nRange: "+ kdtree.range(rectest));

    }


    public static void buildTree(ArrayList<Point2D> points, boolean xysort) {
        //if xy sort = 1, sort by x
        //otherwise sort by y

        ArrayList<Point2D> sortedPoints;
        if(points.size() > 2) {
            if(xysort) {
                sortedPoints = sortMeByX(points);
                xysort = false;
            }else {
                sortedPoints = sortMeByY(points);
                xysort = true;
            }

            int size = sortedPoints.size();
            int median = size/2;

            kdtree.insert(sortedPoints.get(median));
            //kdtree.draw();
            //StdDraw.show();

            ArrayList<Point2D> right = new ArrayList<Point2D>(sortedPoints.subList(median+1,size));
            ArrayList<Point2D> left = new ArrayList<Point2D>(sortedPoints.subList(0,median));

            buildTree(right,xysort);
            buildTree(left,xysort);
        }else{
            for(Point2D p : points ) {
                kdtree.insert(p);
                //kdtree.draw();
                //StdDraw.show();
            }
        }


    }

    public static ArrayList<Point2D> sortMeByX(ArrayList<Point2D> points){
        int n = points.size();
        int c, d;
        Point2D swap;

        for (c = 0; c < ( n - 1 ); c++) {
          for (d = 0; d < n - c - 1; d++) {
            if (points.get(d).x() > points.get(d+1).x()) {
              swap          = points.get(d);
              points.set(d, points.get(d+1));
              points.set((d+1), swap);
            }
          }
        }
        return points;
    }


    public static ArrayList<Point2D> sortMeByY(ArrayList<Point2D> points){
        int n = points.size();
        int c, d;
        Point2D swap;

        for (c = 0; c < ( n - 1 ); c++) {
          for (d = 0; d < n - c - 1; d++) {
            if (points.get(d).y() > points.get(d+1).y()) {
              swap          = points.get(d);
              points.set(d, points.get(d+1));
              points.set((d+1), swap);
            }
          }
        }

        return points;
    }
}