
public class MassageAttempt implements Comparable<MassageAttempt> {
	Player player;
	double time;
	double duration;
	MassageAttempt(Player player, double time, double duration) {
		this.player=player;
		this.time=time;
		this.duration=duration;
		// TODO Auto-generated constructor stub
	}

	


	@Override
	public int compareTo(MassageAttempt o) {
		if (this.player.skill>o.player.skill) {
			return -1;
		}else if(this.player.skill<o.player.skill) {
			return 1;
		}else {
			if(Math.abs(this.time-o.time)<0.0000000001){
				return this.player.ID-o.player.ID;
			}
			else if(this.time<o.time) {
				return -1;
			}else {
				return 1;
			}
			
		}
		
	}

}
