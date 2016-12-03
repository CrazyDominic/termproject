package termproject;

import java.util.*;
import java.io.*;

public class program2 {
	static int numberOfInputPoints;
	static Point [] Points;
	static ArrayList<Triangle> triangle = new ArrayList<Triangle>();
	static int numberOftriangle = 0;
	public static void main(String[] args)
	{
		readPointSetFile("./dat/P1_100_U.dat");
		Point superPointa = new Point(-3000,-3000);
		Point superPointb = new Point(0,3000);
		Point superPointc = new Point(3000,-3000);
		Triangle superTriangle = new Triangle(superPointa,superPointb,superPointc);
		ArrayList<Triangle> triangleset = new ArrayList<Triangle>();
		triangleset.add(superTriangle);
		for(int n = 0 ; n<Points.length;n++)
		{	
			System.out.println("n : "+ n);
			for(int i = 0 ; i < triangleset.size(); i++)
			{
				if(triangleset.get(i).hasVertex(Points[n])==false)
				{
					if(triangleset.get(i).containCricle(Points[n])==true)
					{
						
						Triangle triangle1 = new Triangle(triangleset.get(i).getPOINT(0),triangleset.get(i).getPOINT(1),Points[n]);
						Triangle triangle2 = new Triangle(triangleset.get(i).getPOINT(1),triangleset.get(i).getPOINT(2),Points[n]);
						Triangle triangle3 = new Triangle(triangleset.get(i).getPOINT(2),triangleset.get(i).getPOINT(0),Points[n]);
						triangleset.remove(i);
						triangleset.add(triangle1);
						triangleset.add(triangle2);
						triangleset.add(triangle3);
					}	
					
				}
			}
		}
		for(int n = 0 ; n<triangleset.size();n++)
		{
			if(triangleset.get(n).getPOINT(0)==superPointa||triangleset.get(n).getPOINT(1)==superPointa||triangleset.get(n).getPOINT(2)==superPointa||triangleset.get(n).getPOINT(0)==superPointb||triangleset.get(n).getPOINT(1)==superPointb||triangleset.get(n).getPOINT(2)==superPointb||triangleset.get(n).getPOINT(0)==superPointc||triangleset.get(n).getPOINT(1)==superPointc||triangleset.get(n).getPOINT(2)==superPointc)
			{
				triangleset.remove(n);
			}
		}
		System.out.println("End");
	}
	
	
	
	//file IO
	public static void writeTriangleSet(ArrayList<Triangle> triangleArray,String fileName){
		BufferedWriter fileWriter;
		try {
			 if(triangleArray.size() == 0)
	                return;
	            
	        fileWriter = new BufferedWriter(new FileWriter(fileName));
	
	        fileWriter.write(Integer.toString(triangleArray.size())); // number of triangles
	        fileWriter.newLine();
	        
	        fileWriter.write( new String("vertex") );
	        fileWriter.write( new String(" ") );
	        fileWriter.write( new String("edge") );
	        fileWriter.write( new String(" ") );
	        fileWriter.write( new String("face") );
	        fileWriter.write( new String(" ") );
	        fileWriter.newLine();
	        
	            for(int i = 0;i < triangleArray.size();i++)
	            {           
	                fileWriter.write( Integer.toString(triangleArray.get(i).getINDEX()));
	                fileWriter.write( new String(" ") );
	                fileWriter.write( Integer.toString(triangleArray.get(i).getPOINT(0).getID())); 
	                fileWriter.write( new String(" ") );
	                fileWriter.write( Integer.toString(triangleArray.get(i).getPOINT(1).getID()));
	                fileWriter.write( new String(" ") );
	                fileWriter.write( Integer.toString(triangleArray.get(i).getPOINT(2).getID()));
	                /*
	                 fileWriter.write( new String(" ") );
	                fileWriter.write( Double.toString(triangleArray.get(i).getAREA()));
	                */
	                fileWriter.newLine();
	                
	            }
	            fileWriter.close();      
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception!");
		}
	}
	public static void readPointSetFile(String fileName) {
	    
	    Scanner fileScanner = null;
	    
	    try
	    {
	        fileScanner = new Scanner(new BufferedReader(new FileReader(fileName)));
	        
	        // get the number of input point set
	        numberOfInputPoints = fileScanner.nextInt();
	        Points = new Point[numberOfInputPoints];
	        
	        // get each point and construct corresponding vertex
	        while(fileScanner.hasNext()) {
	            // point ID                
	            int pointID = fileScanner.nextInt();
	            // x- and y-coordinate
	            double xCoordinate = fileScanner.nextDouble();
	            double yCoordinate = fileScanner.nextDouble();
	            Points[pointID-1] = new Point(xCoordinate, yCoordinate, pointID);
	        }
	    }
	    catch(IOException e) 
	    {
	        System.out.println("IO Exception!");
	    }
	    finally {
	        if (fileScanner != null) {
	            fileScanner.close();
	        }
	    }
	}
}

