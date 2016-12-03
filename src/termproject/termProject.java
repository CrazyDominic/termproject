package termproject;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*is it work? */
public class termProject {
	static int m_numberOfInputPoints;
	static Point [] m_inputPoints;
	static Point [] P;
	static int numberOftriangle = 0;
	public static void main(String[] args) {
		
		readPointSetFile("./dat/random_pointset_origin_coordinate_1000.dat");
		P=m_inputPoints;
		for(int i = 0 ; i <m_numberOfInputPoints ; i ++){
			for(int j=i+1 ; j<m_numberOfInputPoints ; j++){
				for(int k = j+1; k<m_numberOfInputPoints;k++)
				{
					double grad_prev = (m_inputPoints[i].getY()-m_inputPoints[j].getY())/(m_inputPoints[i].getX()-m_inputPoints[j].getX());
					double grad_next = (m_inputPoints[i].getY()-m_inputPoints[k].getY())/(m_inputPoints[i].getX()-m_inputPoints[k].getX());
					if(grad_prev==grad_next)
					{
						System.out.println(m_inputPoints[i].getID());
						System.out.println(m_inputPoints[j].getID());
						System.out.println(m_inputPoints[k].getID());
						System.out.println("have same grad");
					}
				}
			}
		}
		
		/*
		for(int i = 0 ; i <m_numberOfInputPoints ; i ++){
			for(int j=i+1 ; j<m_numberOfInputPoints ; j++){
				for(int k = j+1; k<m_numberOfInputPoints;k++){
					double areaOftriangle = Math.abs(((m_inputPoints[i].getX()*m_inputPoints[j].getY()+m_inputPoints[j].getX()*m_inputPoints[k].getY()+m_inputPoints[k].getX()*m_inputPoints[i].getY())-(m_inputPoints[i].getY()*m_inputPoints[j].getX()+m_inputPoints[j].getY()*m_inputPoints[k].getX()+m_inputPoints[k].getY()*m_inputPoints[i].getX()))/2);
					double maxY = Math.max(P[i].getY(),Math.max(P[j].getY(), P[k].getY()));
					double minY = Math.min(P[i].getY(),Math.min(P[j].getY(), P[k].getY()));
					for(int n = 0 ; n < 1000 ; n++){
						int cnt=0;
						double nY = P[n].getY();
						if(n!=i && n!=j && n!=k && areaOftriangle!=0)
						{
							if(nY>=minY && nY<=maxY)
							{
								
								//  만약 점이  점i,j,k의 minimum Y값과 maximum Y값 사이에 있다면 연산을 진행한다. 
								 
		
								double x1_Of_n = (P[n].getY()-P[i].getY())*(P[j].getX()-P[i].getX())/(P[j].getY()-P[i].getY())+P[i].getX();
								double x2_Of_n = (P[n].getY()-P[j].getY())*(P[k].getX()-P[j].getX())/(P[k].getY()-P[j].getY())+P[j].getX();
								double x3_Of_n = (P[n].getY()-P[k].getY())*(P[i].getX()-P[k].getX())/(P[i].getY()-P[k].getY())+P[k].getX();
								
								if(P[n].getY()<=Math.max(P[i].getY(), P[j].getY()) && P[n].getY()>=Math.min(P[i].getY(), P[j].getY()))
								{
									if(x1_Of_n==P[n].getX())
										break;
									else if(x1_Of_n>P[n].getX())
										cnt++;
								}//점 i와 점j 사이의 Y좌표사이에 점 n이 존재하고 점n의 X좌표 값이 i와j사이에 있다면 break i와j의 n의 x값과 비교
								if(P[n].getY()<=Math.max(P[k].getY(), P[j].getY()) && P[n].getY()>=Math.min(P[k].getY(), P[j].getY()))
								{
									if(x2_Of_n==P[n].getX())
										break;
									else if(x2_Of_n>P[n].getX())
										cnt++;
								}//점 k와 점j 사이의 Y좌표사이에 점 n이 존재하고 점n의 X좌표 값이 k와j사이에 있다면 break k와j의 n의 x값과 비교
								if(P[n].getY()<=Math.max(P[i].getY(), P[k].getY()) && P[n].getY()>=Math.min(P[i].getY(), P[k].getY()))
								{
									if(x3_Of_n==P[n].getX())
										break;
									else if(x3_Of_n>P[n].getX())
										cnt++;
								}//점 i와 점j 사이의 Y좌표사이에 점 n이 존재하고 점n의 X좌표 값이 i와j사이에 있다면 break i와j의 n의 x값과 비교
								if(cnt%2==1)
									break;
								if(n==999)
								{
									numberOftriangle++;
									System.out.println("i :" +  i + " j : " + j + " k : " + k + " index : " + numberOftriangle);
									System.out.println("it can be triangle : " + areaOftriangle );
								}
							}
						}
					}
				}
			}
		}// 1-1 brute force
		*/
		
		AngleOftwopoints angleOfpoint[][] = new AngleOftwopoints[m_numberOfInputPoints][m_numberOfInputPoints-1];
		for(int i = 0 ; i<m_numberOfInputPoints ; i++)
		{
			for(int j = 0 ; j<m_numberOfInputPoints-1;j++)
			{
				angleOfpoint[i][j] = new AngleOftwopoints();
			}
		}
		for(int i = 0 ; i<m_numberOfInputPoints ; i ++)
		{
			int index=0;
			for(int n = 0 ; n<m_numberOfInputPoints; n++)
			{	
				if(n!=i)
				{	
					angleOfpoint[i][index].setFibot(i);
					angleOfpoint[i][index].setAnotherpoint(n);
					double angle = (Math.atan2(P[n].getY()-P[i].getY(), P[n].getX()-P[i].getX()) > 0) ? Math.atan2(P[n].getY()-P[i].getY(), P[n].getX()-P[i].getX()) : Math.atan2(P[n].getY()-P[i].getY(), P[n].getX()-P[i].getX()) + 2*Math.PI;
					angleOfpoint[i][index].setAngle(angle);
					index++;
				}
			}//Point i에 존재하는 각을 전부 계산.
		}	
		System.out.println("End");
		
	}

    public static void readPointSetFile(String fileName) {
        
        Scanner fileScanner = null;
        
        try
        {
            fileScanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            
            // get the number of input point set
            m_numberOfInputPoints = fileScanner.nextInt();
            m_inputPoints = new Point[m_numberOfInputPoints];
            
            // get each point and construct corresponding vertex
            while(fileScanner.hasNext()) {
                // point ID                
                int pointID = fileScanner.nextInt();
                // x- and y-coordinate
                double xCoordinate = fileScanner.nextDouble();
                double yCoordinate = fileScanner.nextDouble();
                m_inputPoints[pointID-1] = new Point(xCoordinate, yCoordinate, pointID);
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
    
    public static void swapAngleOftwopoints(AngleOftwopoints[][] angleOfpoint, int index1,int index2,int fibot)
    {
    	AngleOftwopoints temp = new AngleOftwopoints();
    	temp = angleOfpoint[fibot][index1];
    	angleOfpoint[fibot][index1] = angleOfpoint[fibot][index2]; 
    	angleOfpoint[fibot][index2] = temp;
    }
    
    public static void buildHeap(AngleOftwopoints[][] angleOfpoint, int fibot){
    	for(int i =(int)(Math.floor(angleOfpoint.length * 0.5)-1); i>=0 ; --i)
    	{
    		int parent = i ;
    		while(true)
    		{
    			int leftchild = parent * 2 +1;
    			int rightchild = leftchild + 1;
    			
    			int changeIndex = parent;
    			if(leftchild < angleOfpoint.length && angleOfpoint[fibot][leftchild].getAngle() < angleOfpoint[fibot][parent].getAngle())
    			{
    				
    			}
    		}
    	}
    }
	
}


