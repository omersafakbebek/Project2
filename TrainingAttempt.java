
public class TrainingAttempt implements Comparable<TrainingAttempt>{
	Player player;
	double time;
	double duration;

	TrainingAttempt(Player player, double time, double duration) {
		this.player=player;
		this.time=time;
		this.duration=duration;
		// TODO Auto-generated constructor stub
	}

	public int compareTo(TrainingAttempt o) {
		// TODO Auto-generated method stub
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
