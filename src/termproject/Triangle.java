package termproject;

import java.lang.Double;

public class Triangle implements Comparable<Triangle>{
	private int index;
	private Point[] points = new Point[3];
	double area;
	
	public Triangle(Point i,Point j,Point k) {
        this.points[0] = i;
        this.points[1] = j;
        this.points[2] = k;
        this.area = Math.abs(((i.getX()*j.getY()+j.getX()*k.getY()+k.getX()*i.getY())-(i.getY()*j.getX()+j.getY()*k.getX()+k.getY()*i.getX()))/2);
    }
	
	
	public int getINDEX()
	{
		return index;
	}
	
	public Point getPOINT(int n)
	{
		return points[n];
	}
	
	public double getAREA()
	{
		return area;
	}
	
	public int compareTo(Triangle o) {
		Triangle other = o;
		return Double.compare(this.area, other.area);
	}
	
	public boolean containCricle(Point i){
		double edgelength1=points[0].distance(points[1]);
		double edgelength2=points[1].distance(points[2]);
		double edgelength3=points[2].distance(points[0]);
		double x1 = points[0].getX();
		double x2 = points[1].getX();
		double x3 = points[2].getX();
		double y1 = points[0].getY();
		double y2 = points[1].getY();
		double y3 = points[2].getY();
		
		Point origin = new Point(0,0);
		
		double p1sq = Math.pow(x1, 2) + Math.pow(y1, 2);
		double p2sq = Math.pow(x2, 2) + Math.pow(y2, 2);
		double p3sq = Math.pow(x3, 2) + Math.pow(y3, 2);
		double crossproduct = (x2-x1) * (y3-y1) - (y2-y1) * (x3-x1);
		
		
		double radius = ( (edgelength1*edgelength2*edgelength3) / Math.sqrt((edgelength1+edgelength2+edgelength3)*(edgelength2+edgelength3-edgelength1)*(edgelength3+edgelength1-edgelength2)*(edgelength1+edgelength2-edgelength3)) ) ;
		double cx = (p1sq*(y2-y3) + p2sq *(y3-y1) + p3sq*(y1-y2)) / ((double)2 * crossproduct);
		double cy = (p1sq*(x3-x2) + p2sq *(x1-x3) + p3sq*(x2-x1)) / ((double)2 * crossproduct);
		
		Point center = new Point(cx,cy);
		
		if(i.distance(center)<=radius)
			return true;
		else
			return false;
	}
	
	public boolean hasVertex(Point p){
		if(p==points[0]||p==points[1]||p==points[2])
			return true;
		else
			return false;
	}
	
}
