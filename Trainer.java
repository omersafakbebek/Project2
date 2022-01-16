
public class Trainer implements Comparable<Trainer> {
	int ID;
	boolean isAvailable=true;
	double availableTime;
	Trainer(int ID){
		this.ID=ID;
	}
	@Override
	public int compareTo(Trainer o) {
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
