import java.util.PriorityQueue;

public class Massage {
	int numberOfEvents=0;
	double totalWaitingTime=0;
	double totalMassageTime=0;
	int maxLength=0;
	int totalNumberOfCancelledAttempts=0;
	int totalNumberOfInvalidAttempts=0;
	PriorityQueue<Masseur> availableMasseurs=new PriorityQueue<>();
	PriorityQueue<Masseur> busyMasseurs=new PriorityQueue<>();
	PriorityQueue<MassageAttempt> queue=new PriorityQueue<>();
	public void checkEvents(double time) {
		while(queue.isEmpty()==false&&busyMasseurs.peek().availableTime<=time) {
			MassageAttempt attempt=queue.poll();
			Player player=attempt.player; Masseur masseur=busyMasseurs.poll();
			numberOfEvents+=1;
			totalMassageTime+=attempt.duration;
			totalWaitingTime+=masseur.availableTime-attempt.time;
			player.timeInMassageQueue+=masseur.availableTime-attempt.time;
			masseur.availableTime+=attempt.duration;
			player.availableTime=masseur.availableTime;
			busyMasseurs.add(masseur);
			player.inQueue=false;
		}
		while(busyMasseurs.isEmpty()==false&&busyMasseurs.isEmpty()==false&&busyMasseurs.peek().availableTime<=time) {
			Masseur masseur=busyMasseurs.poll();
			masseur.isAvailable=true;
			availableMasseurs.add(masseur);
		}
	}
	public void takeNewAttempt(MassageAttempt attempt) {
		Player player = attempt.player;		
		if(player.numberOfMassages==3) {
			totalNumberOfInvalidAttempts+=1;
			
		}
		else if(player.availableTime>attempt.time||player.inQueue) {			
			
			totalNumberOfCancelledAttempts+=1;
		}
		else {
			player.numberOfMassages+=1;
			if(availableMasseurs.isEmpty()==false) {
				Masseur masseur = availableMasseurs.poll();
				numberOfEvents+=1;
				totalMassageTime+=attempt.duration;
				masseur.availableTime=attempt.time+attempt.duration;
				player.availableTime=masseur.availableTime;
				masseur.isAvailable=false;
				busyMasseurs.add(masseur);
			}else {
				queue.add(attempt);
				player.inQueue=true;
				if(queue.size()>maxLength) {
					maxLength=queue.size();
				}
			}
		}
	}
}
		

