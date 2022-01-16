
public class Player {
	int ID;
	int skill;
	boolean inQueue=false;
	double availableTime=0;
	int numberOfMassages=0;
	double timeInTrainingQueue=0;
	double timeInMassageQueue=0;
	double timeInPhysiotherapyQueue=0;
	double lastTrainingTime;
	double enteranceTime;
	Player(int ID,int skill){
		this.ID=ID;
		this.skill=skill;
	}
}
