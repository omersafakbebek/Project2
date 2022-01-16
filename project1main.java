import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class project1main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		LinkedList<Student> studentsInQueue=new LinkedList<Student>();
		LinkedList<House> houses=new LinkedList<House>();	
		LinkedList<Student> notAllocatedStudents=new LinkedList<Student>();
		Scanner in = new Scanner(new File(args[0])).useLocale(Locale.US);
		PrintStream out = new PrintStream(new File(args[1]));
		while(in.hasNext()) {
			// Check house or student
			char type=in.next().charAt(0);			
			if (type=='h') {
				houses.add(new House(in.nextInt(),in.nextInt(),in.nextDouble()));
			}
			if(type=='s') {
				studentsInQueue.add(new Student(in.nextInt(),in.next(),in.nextInt(),in.nextDouble()));
			}
		}Collections.sort(studentsInQueue);
		Collections.sort(houses);
		// Find duration of the student with the maximum duration
		int maxDuration=0;
		Iterator<Student> maxIterator=studentsInQueue.iterator();
		while(maxIterator.hasNext()) {
			Student maxCheck=maxIterator.next();
			if(maxCheck.duration>maxDuration) {
				maxDuration=maxCheck.duration;
			}
		}for(int i=0;i<maxDuration;i++) {
			Iterator<Student> sItr= studentsInQueue.iterator();
			// Take a student and check houses for the student
			while(sItr.hasNext()) {
				Student checkStudent=sItr.next();
				Iterator<House> hItr=houses.iterator();
				// Take a house and check if the student can be placed.
				while(checkStudent.duration>0&&hItr.hasNext()) {					
					House checkHouse=hItr.next();						
					if(checkHouse.duration==0&&checkHouse.rating>=checkStudent.rating) {
						checkHouse.duration=checkStudent.duration;
						checkStudent.hasHouse=true;
						sItr.remove();
						break;
					}
				}// Decrease the student's duration by 1.
				checkStudent.duration-=1;
				// Check if the student graduated and the student does not have a house. If so, add the student to the not allocated students list and remove from the queue.
				if(checkStudent.duration<=0&&checkStudent.hasHouse==false) {
					notAllocatedStudents.add(checkStudent);
					sItr.remove();
				}
			}
			// reduce the houses' duration by 1 if the duration is not 0.
			Iterator<House> newH=houses.iterator();
			while(newH.hasNext()) {	
				House newHouse=newH.next();
				if(newHouse.duration!=0) {
					newHouse.duration-=1;
				}					
			}
		}Collections.sort(notAllocatedStudents);
		// Print list of the not allocated students.
		Iterator<Student> newItr=notAllocatedStudents.iterator();
		while(newItr.hasNext()){
			Student newS=newItr.next();
			out.println(newS.name);				
		}
	}
		
}
