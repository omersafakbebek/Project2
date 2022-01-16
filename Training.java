import java.util.PriorityQueue;

public class Training {
	int numberOfEvents=0;
	double totalWaitingTime=0;	
	double totalTrainingTime=0;
	int maxLength=0;
	int totalNumberOfCancelledAttempts=0;
	PriorityQueue<Trainer> availableTrainers=new PriorityQueue<>();
	PriorityQueue<Trainer> busyTrainers=new PriorityQueue<>();
	PriorityQueue<TrainingAttempt> queue=new PriorityQueue<>();
	PriorityQueue<Arrival> physiotherapySchedule=new PriorityQueue<>();
	public void checkEvents(double time) {
		while(queue.isEmpty()==false&&busyTrainers.isEmpty()==false&&busyTrainers.peek().availableTime<=time) {
			TrainingAttempt attempt=queue.poll();
			Player player=attempt.player; Trainer trainer=busyTrainers.poll();
			numberOfEvents+=1;
			totalTrainingTime+=attempt.duration;
			totalWaitingTime+=trainer.availableTime-attempt.time;
			player.timeInTrainingQueue+=trainer.availableTime-attempt.time;
			trainer.availableTime=trainer.availableTime+attempt.duration;
			player.availableTime=trainer.availableTime;
			busyTrainers.add(trainer);
			player.inQueue=false;
			player.lastTrainingTime=attempt.duration;
			physiotherapySchedule.add(new Arrival('p', player, trainer.availableTime,0));
		}
		while(busyTrainers.isEmpty()==false&&busyTrainers.peek().availableTime<=time) {
			Trainer trainer=busyTrainers.poll();
			trainer.isAvailable=true;
			availableTrainers.add(trainer);
		}
	}
	public void takeNewAttempt(TrainingAttempt attempt) {
		Player player=attempt.player;		
		if(player.availableTime>attempt.time||player.inQueue) {
			totalNumberOfCancelledAttempts+=1;
			
		}else {
			player.enteranceTime=attempt.time;
			if(availableTrainers.isEmpty()==false) {			 
				Trainer trainer=availableTrainers.poll();
				numberOfEvents+=1;
				totalTrainingTime+=attempt.duration;
				trainer.availableTime=attempt.time+attempt.duration;
				player.availableTime=trainer.availableTime;
				player.lastTrainingTime=attempt.duration;
				trainer.isAvailable=false;
				busyTrainers.add(trainer);
				physiotherapySchedule.add(new Arrival('p',player,trainer.availableTime,0));			
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
