public class Point{
	private int x;
	private int y;
	public Point(int x,int y){
		this.x=x;
		this.y=y;
		if(this.x>100 || this.x<0 || this.y<0 || this.y>100){
			System.out.println("Point out of bounds");
			System.exit(0);
		}                                  //we want the program to end with these values
	}
	public int x(){
		return x;
	}
	public int y(){
		return y;
	}
	public int squareDistanceTo(Point z){
		return (int) Math.pow(this.x()-z.x(),2)+(int)Math.pow(this.y()-z.y(),2);
	}
	public double DistanceTo(Point z){
		return Math.sqrt(squareDistanceTo(z));
	}
	public String toString(){
		return "x= "+x+" y= "+y;
	}	
}