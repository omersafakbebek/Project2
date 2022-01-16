import java.util.PriorityQueue;


public class Physiotherapy {
	PriorityQueue<Physiotherapist> availablePhysiotherapists=new PriorityQueue<>();
	PriorityQueue<Physiotherapist> busyPhysiotherapists=new PriorityQueue<>();
	PriorityQueue<PhysiotherapyAttempt> queue=new PriorityQueue<>();
	double totalPhysiotherapyTime=0;
	int maxLength=0;
	 int numberOfEvents;
	double totalWaitingTime;
	double totalTurnaroundTime;
	public void checkEvents(double time) {
		while(queue.isEmpty()==false&&busyPhysiotherapists.isEmpty()==false&&busyPhysiotherapists.peek().availableTime<=time) {
			PhysiotherapyAttempt attempt=queue.poll();
			Player player=attempt.player; Physiotherapist physiotherapist=busyPhysiotherapists.poll();
			numberOfEvents+=1;
			totalPhysiotherapyTime+=physiotherapist.duration;
			totalWaitingTime+=physiotherapist.availableTime-attempt.time;
			player.timeInPhysiotherapyQueue+=physiotherapist.availableTime-attempt.time;
			physiotherapist.availableTime=physiotherapist.availableTime+physiotherapist.duration;
			player.availableTime=physiotherapist.availableTime;
			totalTurnaroundTime+=player.availableTime-player.enteranceTime;
			busyPhysiotherapists.add(physiotherapist);
			player.inQueue=false;			
		}
		while(busyPhysiotherapists.isEmpty()==false&&busyPhysiotherapists.peek().availableTime<=time) {
			Physiotherapist physiotherapist=busyPhysiotherapists.poll();
			physiotherapist.isAvailable=true;
			availablePhysiotherapists.add(physiotherapist);
		}
	}
	public void takeNewAttempt(PhysiotherapyAttempt attempt) {
		Player player=attempt.player;
			if(availablePhysiotherapists.isEmpty()==false) {
			Physiotherapist physiotherapist=availablePhysiotherapists.poll();
			numberOfEvents+=1;
			totalPhysiotherapyTime+=physiotherapist.duration;
			physiotherapist.availableTime=attempt.time+physiotherapist.duration;
			player.availableTime=physiotherapist.availableTime;
			totalTurnaroundTime+=player.availableTime-player.enteranceTime;
			physiotherapist.isAvailable=false;
			busyPhysiotherapists.add(physiotherapist);
		}else {
			queue.add(attempt);
			player.inQueue=true;
			if(queue.size()>maxLength) {
				maxLength=queue.size();
			}
		}
	}
}
