package termproject;

public class AngleOftwopoints {
    private int fibot;
    private int anotherpoint;
    private double angle;

    // constructors
    public AngleOftwopoints() {
    	setFibot(0);
    	setAnotherpoint(0);
        setAngle(0);
    }

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getFibot() {
		return fibot;
	}

	public void setFibot(int fibot) {
		this.fibot = fibot;
	}

	public int getAnotherpoint() {
		return anotherpoint;
	}

	public void setAnotherpoint(int anotherpoint) {
		this.anotherpoint = anotherpoint;
	}
 
}
