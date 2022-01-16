
public class Student implements Comparable<Student> {
	public int ID;
	public String name;
	public int duration;
	public double rating;
	public boolean hasHouse;
	Student(int ID,String name,int duration,double rating){
		this.ID=ID;
		this.name=name;
		this.duration=duration;
		this.rating=rating;
		this.hasHouse=false;
	}
	public int compareTo(Student o) {
		if (this.ID>o.ID) {
			return 1;
		}else if (this.ID<o.ID) {
			return -1;
		}else {
		return 0;
		}
	}
}
