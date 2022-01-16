
public class House implements Comparable<House> {
	public int ID;
	public int duration;
	public double rating;
	House(int ID,int duration,double rating){
		this.ID=ID;
		this.duration=duration;
		this.rating=rating;
	}
	@Override
	public int compareTo(House o) {
		if (this.ID>o.ID) {
			return 1;
		}else if (this.ID<o.ID) {
			return -1;
		}else {
		return 0;
		}
	}
}
