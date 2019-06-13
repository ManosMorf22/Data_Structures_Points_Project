public class Rectangle{
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
	public Rectangle(int xminn,int yminn,int xmaxx,int ymaxx) throws Exception{
		if(xminn>xmaxx) throw new Exception("xmin>xmax");
		if(yminn>ymaxx) throw new Exception("ymin>ymax");
		xmin=xminn;
		ymin=yminn;
		xmax=xmaxx;
		ymax=ymaxx;
		if(xmin<0 || xmin>100 || ymin<0 || ymin>100 || xmax>100 || xmax<0 || ymax>100 || ymax<0){
			System.out.println("Rectangle out of bounds");
			System.exit(0);    //we want the program to end with values out of bounds
		}
		
	}
	public int xmin(){
		return xmin;
	}
	public int ymin(){
		return ymin;
	}
	public int xmax(){
		return xmax;
	}
	public int ymax(){
		return ymax;
	}
	public boolean contains(Point p){
		if(p.x()<=xmax && p.x()>=xmin && p.y()>=ymin && p.y()<=ymax) return true;
		else return false;
	}
	public boolean intersects(Rectangle that){
		if(that.xmin()>this.xmax() || that.ymin()>this.ymax() || this.xmin()>that.xmax() || this.ymin()>that.ymax()) return false;
		return true;
		
	}
	public int squareDistanceTo(Point p){
		double sum=0;
		if(contains(p)) return (int)sum;
		if(p.x()<=xmax && p.x()>=xmin){//checking if the point is right in front of the table
			int b=Math.abs(p.y()-this.ymin());
			int b2=Math.abs(p.y()-this.ymax());
			if(b<b2) sum=sum+Math.pow(b,2);//the shortest distance
			else sum=sum+Math.pow(b2,2);
			return (int)sum;
		}
		if(p.y()>=ymin && p.y()<=ymax){//checking if the point is right in front of the table
			int a=Math.abs(p.x()-this.xmin());
			int a2=Math.abs(p.x()-this.xmax());
			if(a<a2)sum=sum+Math.pow(a,2);//the shortest distance
			else sum=sum+Math.pow(a2,2);
			return (int)sum;
		}
		int a=Math.abs(p.x()-this.xmin());
		int a2=Math.abs(p.x()-this.xmax());
		int b=Math.abs(p.y()-this.ymin());
		int b2=Math.abs(p.y()-this.ymax());
		if(a<a2)sum=sum+Math.pow(a,2);//the shortest distance
		else sum=sum+Math.pow(a2,2);
		if(b<b2) sum=sum+Math.pow(b,2);//the shortest distance
		else sum=sum+Math.pow(b2,2);
		return (int)sum;
	}
	public double distanceTo(Point p){
		return Math.sqrt(squareDistanceTo(p));
	}
	
	public String ToString(){
		return "["+xmin+", "+xmax+"] X ["+ymin+", "+ymax+"]";
	}
}