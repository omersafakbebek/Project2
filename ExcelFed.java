import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ExcelFed {
	double time;
	ArrayList<Player> players=new ArrayList<>();
	PriorityQueue<Arrival> schedule=new PriorityQueue<>();
	Physiotherapy physiotherapyDepartment=new Physiotherapy();
	int IdOfmaxWaitInPhysiotherapy=0;
	int IdOfMinWaitInMassage=-1;
	double maxWaitTimeInPhysiotherapy=0;
	double minWaitTimeInMassage=-1;
	Training trainingDepartment=new Training();
	Massage massageDepartment=new Massage();
	public void simulator() {
		while(schedule.isEmpty()==false) {
			Arrival arrival=schedule.poll();
			time=arrival.time;
			trainingDepartment.checkEvents(time);
			while(trainingDepartment.physiotherapySchedule.isEmpty()==false&&trainingDepartment.physiotherapySchedule.peek().time<=time) {
				Arrival pArrival=trainingDepartment.physiotherapySchedule.poll();
				physiotherapyDepartment.checkEvents(pArrival.time);
				physiotherapyDepartment.takeNewAttempt(new PhysiotherapyAttempt(pArrival.player, pArrival.time));
			}
			massageDepartment.checkEvents(time);
			physiotherapyDepartment.checkEvents(time);
			if(arrival.type=='t') {
				
				trainingDepartment.takeNewAttempt(new TrainingAttempt(arrival.player, arrival.time, arrival.duration));
			}
			else {
				massageDepartment.takeNewAttempt(new MassageAttempt(arrival.player,arrival.time,arrival.duration));
			}
		}while(trainingDepartment.busyTrainers.isEmpty()==false) {
			Trainer trainer=trainingDepartment.busyTrainers.peek();	
		
			trainingDepartment.checkEvents(trainer.availableTime);			

			if(trainer.availableTime>time) {				
				time=trainer.availableTime;				
			}
		}while(trainingDepartment.physiotherapySchedule.isEmpty()==false) {
			Arrival arrival=trainingDepartment.physiotherapySchedule.poll();
			
			physiotherapyDepartment.checkEvents(arrival.time);
			physiotherapyDepartment.takeNewAttempt(new PhysiotherapyAttempt(arrival.player, arrival.time));
		}
		while(massageDepartment.busyMasseurs.isEmpty()==false) {
			Masseur masseur=massageDepartment.busyMasseurs.peek();

			massageDepartment.checkEvents(masseur.availableTime);

			if(masseur.availableTime>time) {				
				time=masseur.availableTime;					
			}
		}while(physiotherapyDepartment.busyPhysiotherapists.isEmpty()==false) {
			Physiotherapist physiotherapist=physiotherapyDepartment.busyPhysiotherapists.peek();
			
			physiotherapyDepartment.checkEvents(physiotherapist.availableTime);
			
			if(physiotherapist.availableTime>time) {				
				time=physiotherapist.availableTime;				
			}			
		}
	}public void findminmax() {
		Iterator<Player> itr=players.iterator();
		while(itr.hasNext()) {
			Player player=itr.next();
			if(player.timeInPhysiotherapyQueue>maxWaitTimeInPhysiotherapy) {
				IdOfmaxWaitInPhysiotherapy=player.ID;
				maxWaitTimeInPhysiotherapy=player.timeInPhysiotherapyQueue;
			}
			if(player.numberOfMassages==3&&minWaitTimeInMassage==-1) {
				IdOfMinWaitInMassage=player.ID;
				minWaitTimeInMassage=player.timeInMassageQueue;
			}else if(player.numberOfMassages==3&&player.timeInMassageQueue<minWaitTimeInMassage) {
				IdOfMinWaitInMassage=player.ID;
				minWaitTimeInMassage=player.timeInMassageQueue;
			}
		}
	}
}
