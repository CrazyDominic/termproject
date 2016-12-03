package termproject;

import java.util.*;
import java.io.*;

public class Program1 {
	static int numberOfInputPoints;
	static Point [] Points;
	static ArrayList<Triangle> triangle = new ArrayList<Triangle>();
	static int numberOftriangle = 0;
	
	public static void vail(String[] args) {
		readPointSetFile("./dat/random_pointset_origin_coordinate_1000.dat");
		for(int i = 0 ; i <numberOfInputPoints ; i ++){
			for(int j=i+1 ; j<numberOfInputPoints ; j++){
				for(int k = j+1; k<numberOfInputPoints;k++)
				{
					double grad_prev = (Points[i].getY()-Points[j].getY())/(Points[i].getX()-Points[j].getX());
					double grad_next = (Points[i].getY()-Points[k].getY())/(Points[i].getX()-Points[k].getX());
					if(grad_prev==grad_next)
					{
						System.out.println(Points[i].getID());
						System.out.println(Points[j].getID());
						System.out.println(Points[k].getID());
						System.out.println("have same grad");
					}
				}
			}
		}
		
		for(int i = 0 ; i <numberOfInputPoints ; i ++){
			for(int j=i+1 ; j<numberOfInputPoints ; j++){
				for(int k = j+1; k<numberOfInputPoints;k++){
					double maxY = Math.max(Points[i].getY(),Math.max(Points[j].getY(), Points[k].getY()));
					double minY = Math.min(Points[i].getY(),Math.min(Points[j].getY(), Points[k].getY()));
					for(int n = 0 ; n < numberOfInputPoints ; n++){
						int cnt=0;
						double nY = Points[n].getY();
						if(n!=i && n!=j && n!=k)
						{
							if(nY>=minY && nY<=maxY)
							{
								/*
								 * ���� ����  ��i,j,k�� minimum Y���� maximum Y�� ���̿� �ִٸ� ������ �����Ѵ�. 
								 */
		
								double x1_Of_n = (Points[n].getY()-Points[i].getY())*(Points[j].getX()-Points[i].getX())/(Points[j].getY()-Points[i].getY())+Points[i].getX();
								double x2_Of_n = (Points[n].getY()-Points[j].getY())*(Points[k].getX()-Points[j].getX())/(Points[k].getY()-Points[j].getY())+Points[j].getX();
								double x3_Of_n = (Points[n].getY()-Points[k].getY())*(Points[i].getX()-Points[k].getX())/(Points[i].getY()-Points[k].getY())+Points[k].getX();
								
								if(Points[n].getY()<=Math.max(Points[i].getY(), Points[j].getY()) && Points[n].getY()>=Math.min(Points[i].getY(), Points[j].getY()))
								{
									if(x1_Of_n==Points[n].getX())
										break;
									else if(x1_Of_n>Points[n].getX())
										cnt++;
								}//�� i�� ��j ������ Y��ǥ���̿� �� n�� �����ϰ� ��n�� X��ǥ ���� i��j���̿� �ִٸ� break i��j�� n�� x���� ��
								if(Points[n].getY()<=Math.max(Points[k].getY(), Points[j].getY()) && Points[n].getY()>=Math.min(Points[k].getY(), Points[j].getY()))
								{
									if(x2_Of_n==Points[n].getX())
										break;
									else if(x2_Of_n>Points[n].getX())
										cnt++;
								}//�� k�� ��j ������ Y��ǥ���̿� �� n�� �����ϰ� ��n�� X��ǥ ���� k��j���̿� �ִٸ� break k��j�� n�� x���� ��
								if(Points[n].getY()<=Math.max(Points[i].getY(), Points[k].getY()) && Points[n].getY()>=Math.min(Points[i].getY(), Points[k].getY()))
								{
									if(x3_Of_n==Points[n].getX())
										break;
									else if(x3_Of_n>Points[n].getX())
										cnt++;
								}//�� i�� ��j ������ Y��ǥ���̿� �� n�� �����ϰ� ��n�� X��ǥ ���� i��j���̿� �ִٸ� break i��j�� n�� x���� ��
								if(cnt%2==1)
									break;
								if(n==numberOfInputPoints-1)
								{
									numberOftriangle++;
									triangle.add(new Triangle(Points[i],Points[j],Points[k])); // ������ TRIANGLE SET�� 1���� �����ؼ� POINTS�� index�� ����Ű�� ���ؼ��� �ٽ� 1�� ������ �ʿ䰡 �ִ�.
								}
							}
						}
					}
				}
			}
		}// 1-1 brute force & crossEdgeTriangle
		
		Collections.sort(triangle);
		
		writeTriangleSet(triangle,"./output/crossEdgeTriangle.txt");
		
		System.out.println("End");
	}
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
	                fileWriter.write( Integer.toString(++i));
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


