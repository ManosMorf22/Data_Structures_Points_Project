import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class TwoDTree {
	private class Node{
		Node next;
		private Point pp;
		Node(Point p1,Node a){
			pp=p1;
			next=a;
		}
		Node(Point p1){
			this(p1,null);
		}
		Node(){
			this(null,null);
		}
		public Point gp(){
			return pp;
		}
		
	}
	private class TreeNode{
		public TreeNode l1;
		public TreeNode r1;
		private Point p1;
		
		
		public TreeNode(){
			
		}
	
		public void putP(Point p){
			p1=p;
		
			 }
		 public Point GETP(){
			 return p1;
		 }
			 
		 }
private Node pro;
private TreeNode h;
private  boolean flag;
 public TwoDTree(){
	 h=new TreeNode();
 }

 public int size(){
 return size(h);
 }
 
 public boolean isEmpty(){
	 return (h.GETP()==null);
 }
 public void insert(Point p){
	 if(search(p)){
		 System.out.println("The point"+p.x()+" "+p.y()+" is existing");
		 System.exit(0);
	 }
	 flag=false;
	 h=insert(h,p);
 }

 private TreeNode insert(TreeNode h1,Point p){
	 if(h1==null) h1=new TreeNode();
	 if(flag==false) flag=true;
	 else flag=false;
	   if(h1.GETP()!=null){
		   if(flag==true){//when flag is true we play with x
			   if(h1.GETP().x()>=p.x()) h1.l1=insert(h1.l1,p);
			   else h1.r1=insert(h1.r1,p);
			   return h1;
		   }else{//when flag is false we play with y
			   if(h1.GETP().y()>=p.y()) h1.l1=insert(h1.l1,p);
			   else h1.r1=insert(h1.r1,p);
			   return h1;
		   }}else{ h1.putP(p);
	   if(pro==null)pro=new Node(p);
	   else pro=new Node(p,pro);
	   return h1;
	   }
}
private boolean search(TreeNode h1,Point p){
	if(h1==null) return false;
	if(h1.GETP()==null) return false;
	else{
		 if(flag==false) flag=true;
		 else flag=false;
		 if(h1.GETP().x()==p.x()&&h1.GETP().y()==p.y())return true;
		 if(flag==true){//when flag is true we play with x
			 if(h1.GETP().x()>=p.x()) return search(h1.l1,p);
			 else return search(h1.r1,p);
		 }else{//when flag is false we play with y
			 if(h1.GETP().y()>=p.y()) return search(h1.l1,p);
			 else return search(h1.r1,p);
		 }
	}
	
	}
public boolean search(Point p){
	flag=false;
	return search(h,p);
}
private int size(TreeNode w){
	if(w==null) return 0;
	if(w.GETP()==null) return 0;
	return 1+size(w.l1)+size(w.r1);
}

public Point nearestNeighbour(Point p,TreeNode h1){
	Point min=null;
	if(h1!=null)
	min=h1.GETP();
	else return null;
	if(h1.GETP()==null) return min;
	min=h1.GETP();
	Point p2=nearestNeighbour(p,h1.l1);
	if(p2!=null&&min.DistanceTo(p)>p2.DistanceTo(p))
	min=p2;
	Point p3=nearestNeighbour(p,h1.r1);
	if(p3!=null&&min.DistanceTo(p)>p3.DistanceTo(p))
	min=p3;
	
	return min;
}
public Node rangesearch(Rectangle rect){
	Node w=new Node();//this Node is going to be the last and the point in there is null
	Node w2=pro;
	if(isEmpty()) return w;//pro==null because we do not have points
	while(w2!=null){
		if(rect.distanceTo(w2.gp())==0)
		 w=new Node(w2.gp(),w);
		w2=w2.next;
	}
	return w;
}
public Point nearestNeighbour(Point p){
	if(isEmpty()) return null;
	return nearestNeighbour(p,h);
}
public static void main(String[] args){
	String l;
	Scanner input=new Scanner(System.in);
	System.out.println("Give the file with the path");
	String f=input.nextLine();
	TwoDTree TD=new TwoDTree();
	try{
	FileReader f2=new FileReader(f);
	BufferedReader f3=new BufferedReader(f2);
	int N=Integer.parseInt(f3.readLine());//the number that I say how man are the Points
	l=f3.readLine();
	while(l!=null){
		TD.insert(new Point(number(l),number2(l)));
	     //the numbers of points
		l=f3.readLine();
	}
	f3.close();
	if(N!=TD.size()){//did I keep my promise?
		System.out.println("Wrong quantity of points");
		System.exit(0);
	}
	}catch(IOException error){
		System.err.println("permision terminated");
	}
	while(true){
	System.out.println("Do you want to give a query rectangle or a query point?.If you want to finish type something else");
	String answer=input.nextLine();
	answer=answer.toLowerCase();
	if(!answer.equals("query point")&&!answer.equals("query rectangle")) break;
	if(answer.equals("query point")){
		System.out.println("Give the query point(cordinates)");
		String l2=input.nextLine();
		Point p=new Point(number(l2),number2(l2));
		System.out.println("The cordinate of the nearest point are "+TD.nearestNeighbour(p));
		
	}else{
		System.out.println("Give the query rectangle(cordinates)");
		String l2=input.nextLine();
		Point p= new Point(number(l2),number2(l2));
		l2=input.nextLine();
		Point p2=new Point(number(l2),number2(l2));
		Rectangle rect;
		try{
		 rect=new Rectangle(p.x(),p.y(),p2.x(),p2.y());
		 Node nod=TD.rangesearch(rect);
		 if(nod==null)
			 System.out.println("There are no points in this area");
		 else
		     System.out.println("The points in rectangle are ");
		 while(nod.gp()!=null){//the last node is null see rangesearch
			 System.out.println(nod.gp());
			 nod=nod.next;
		 }
		}catch(Exception e){
			System.out.println(e);
		}

	}
	}
}
static int number(String a){//we take the part of line  behind the first space
	int ret;
	String re="";
	for(int i=0; a.charAt(i)!=' '; i++){
		re=re+a.charAt(i);
	}
	ret=Integer.parseInt(re);
	return ret;
}
static int number2(String a){//we take the part of line after the first space
	int ret;
	String re="";
	int i=0;
	while(a.charAt(i)!=' ')
		i++;
	i++;
	for(int j=i; j<a.length(); j++)
		re=re+a.charAt(j);
	ret=Integer.parseInt(re);
	return ret;
}	  
}