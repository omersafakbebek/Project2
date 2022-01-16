
public class PhysiotherapyAttempt  implements Comparable<PhysiotherapyAttempt>{
	
	Player player;
	double time;	
	PhysiotherapyAttempt(Player player, double time) {
		this.player=player;
		this.time=time;		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(PhysiotherapyAttempt o) {
		// TODO Auto-generated method stub
		if(Math.abs(player.lastTrainingTime-o.player.lastTrainingTime)<0.0000000001) {
			if(Math.abs(this.time-o.time)<0.0000000001){
				return this.player.ID-o.player.ID;
			}
			else if(this.time<o.time) {
				return -1;
			}else {
				return 1;
			}
		}
		if(player.lastTrainingTime>o.player.lastTrainingTime) {
			return -1;
		}
		else  {
			return 1;
		}
		
	}

}
