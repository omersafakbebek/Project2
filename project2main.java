import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class project2main {
	public static void main(String[] args) throws FileNotFoundException {
		Locale.setDefault(new Locale("en", "US"));
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		ExcelFed excelFed=new ExcelFed();
		
			int numberOfPlayers=in.nextInt();
			for(int i=0;i<numberOfPlayers;i++) {
				excelFed.players.add(new Player(in.nextInt(),in.nextInt()));
			}
			int numberOfArrivals=in.nextInt();
			for(int i=0;i<numberOfArrivals;i++) {				
				char type=in.next().charAt(0); Player player=excelFed.players.get(in.nextInt()); double time=in.nextDouble(); double duration=in.nextDouble();
				excelFed.schedule.add(new Arrival(type, player, time, duration));						
			}
			int numberOfPhysiotherapists=in.nextInt();
			for(int i=0;i<numberOfPhysiotherapists;i++) {
				excelFed.physiotherapyDepartment.availablePhysiotherapists.add(new Physiotherapist(i,in.nextDouble()));
			}
			int numberOfCoaches=in.nextInt();
			for(int i=0;i<numberOfCoaches;i++) {
				excelFed.trainingDepartment.availableTrainers.add(new Trainer(i));
			}
			int numberOfMasseurs=in.nextInt();
			for(int i=0;i<numberOfMasseurs;i++) {
				excelFed.massageDepartment.availableMasseurs.add(new Masseur(i));
			}
			excelFed.simulator();	
			excelFed.findminmax();
			out.println(excelFed.trainingDepartment.maxLength);		
			out.println(excelFed.physiotherapyDepartment.maxLength); ;
			out.println(excelFed.massageDepartment.maxLength); 
			if(excelFed.trainingDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.trainingDepartment.totalWaitingTime/excelFed.trainingDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}
			if(excelFed.physiotherapyDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.physiotherapyDepartment.totalWaitingTime/excelFed.physiotherapyDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}
			if(excelFed.massageDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.massageDepartment.totalWaitingTime/excelFed.massageDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}
			if(excelFed.trainingDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.trainingDepartment.totalTrainingTime/excelFed.trainingDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}
			if(excelFed.physiotherapyDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.physiotherapyDepartment.totalPhysiotherapyTime/excelFed.physiotherapyDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}
			if(excelFed.massageDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.massageDepartment.totalMassageTime/excelFed.massageDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}		
			if(excelFed.trainingDepartment.numberOfEvents!=0) {
				out.printf("%.3f",excelFed.physiotherapyDepartment.totalTurnaroundTime/excelFed.trainingDepartment.numberOfEvents); out.println();
			}
			else {
				out.printf("%.3f",0.000);out.println();
			}					
			out.print(excelFed.IdOfmaxWaitInPhysiotherapy+" "); out.printf("%.3f", excelFed.maxWaitTimeInPhysiotherapy);out.println();
			out.print(excelFed.IdOfMinWaitInMassage+" "); 
			if(excelFed.minWaitTimeInMassage!=-1) {
				out.printf("%.3f", excelFed.minWaitTimeInMassage);
			}else {
				out.print(-1);
			}out.println();			
			out.println(excelFed.massageDepartment.totalNumberOfInvalidAttempts);
			out.println(excelFed.massageDepartment.totalNumberOfCancelledAttempts+excelFed.trainingDepartment.totalNumberOfCancelledAttempts);
			out.printf("%.3f",excelFed.time); out.println();
	}
}
