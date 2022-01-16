
public class Arrival implements Comparable<Arrival> {
	char type;
	Player player;
	double time;
	double duration;
	Arrival(char type,Player player,double time,double duration){	
		this.type=type;
		this.player=player;
		this.time=time;
		this.duration=duration;
	}
	public int compareTo(Arrival o) {
		// TODO Auto-generated method stub
		if (Math.abs(this.time-o.time)<0.0000000001){
			return this.player.ID-o.player.ID;
		}
		else if (this.time<o.time){
			return -1;
		}else {
			return 1;
		}
	}

}
