package termproject;

public class Point {
    // fields
    private double m_xCoordinate;
    private double m_yCoordinate;
    private int    m_id;

    // constructors
    public Point() {
        m_xCoordinate = Double.POSITIVE_INFINITY;
        m_yCoordinate = Double.POSITIVE_INFINITY;
        m_id = -1;
    }

    public Point(double xCoordinate, double yCoordinate) {
        m_xCoordinate = xCoordinate;
        m_yCoordinate = yCoordinate;
        m_id = -1;
    }
    
    public Point(double xCoordinate, double yCoordinate, int id) {
        m_xCoordinate = xCoordinate;
        m_yCoordinate = yCoordinate;
        m_id = id;
    }

    // get methods
    public double getX() {
        return m_xCoordinate;
    }

    public double getY() {
        return m_yCoordinate;
    }
    
    public int getID() {
    	return m_id;
    }

    // set methods
    public void setX(double xCoordinate) {
        m_xCoordinate = xCoordinate;	
    }

    public void setY(double yCoordinate) {
        m_yCoordinate = yCoordinate;	
    }
    
    public void setID(int id)
    {
    	m_id = id;
    }

    public void setCoordinates(double xCoordinate, double yCoordinate)
    {
    	m_xCoordinate = xCoordinate;
    	m_yCoordinate = yCoordinate;
    }
    
    public void setPoint(Point point) {
        m_xCoordinate = point.m_xCoordinate;
        m_yCoordinate = point.m_yCoordinate;
        m_id = point.m_id;
    }
    
    public void movePoint(double deltaX, double deltaY)
    {
    	m_xCoordinate = m_xCoordinate + deltaX;
    	m_yCoordinate = m_yCoordinate + deltaY;
    }
    
    
    public double distance(Point point) {
        double xDiff = this.m_xCoordinate - point.m_xCoordinate;
        double yDiff = this.m_yCoordinate - point.m_yCoordinate;
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
}
