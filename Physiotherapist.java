
public class Physiotherapist implements Comparable<Physiotherapist>{	
	int ID;
	double duration;
	boolean isAvailable=true;
	double availableTime;
	public Physiotherapist(int ID, double duration) {
		// TODO Auto-generated constructor stub
		this.ID=ID;
		this.duration=duration;
	}
	@Override
	public int compareTo(Physiotherapist o) {
		// TODO Auto-generated method stub
		if(this.isAvailable==true&&o.isAvailable==true) {
			return this.ID-o.ID;
		}else if(Math.abs(availableTime-o.availableTime)<0.0000000001){
			return this.ID-o.ID;
		}
		else if(o.availableTime>this.availableTime){
			return -1;			
		}else {
			return 1;
		}
	}
}
