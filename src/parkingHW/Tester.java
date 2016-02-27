package parkingHW;



import java.util.*;
import java.text.*;

public class Tester {
	 public static void main (String[]args) {

Scanner kb=new Scanner(System.in);
SimpleDateFormat dt= new SimpleDateFormat("M d y");

int count=0;


Reservation [] res= new Reservation [100];

boolean finished=false;

do { //ask for input
System.out.println("What would you like to do?\n");
System.out.println("Enter 'create' to make a reservation");
System.out.println("Enter 'modify' if you have a reservation in the future that you would like to change");
System.out.println("Enter 'extend' if you would like to add more time to a current reservation");
System.out.println("Enter 'view' if you would like to view your reservation.");
System.out.println("Enter 'quit' to end");

String response=kb.next();
//decide whether to create, modify, or extend a reservation

switch (response) {

case "create" :
	//ask for time and date
	System.out.println("Please enter the date of the reservation. For example: 09 30 2015");
		int m=kb.nextInt();
		int d=kb.nextInt();
		int y=kb.nextInt();
		Date td= new Date();
		
		int cm=td.getMonth()+1;
		int cd=td.getDate();
		int cy=td.getYear()+1900;
		
				//System.out.println(cm+" "+cd+" "+cy);
		
		// Check that entered date isnt before current date
		if(y<cy){
			System.out.println("E1: INVALID DATE ENTRY: Please try again");
			break;
		}
		else if(y== cy){
			if(m<cm){
				System.out.println("E2: INVALID DATE ENTRY: Please try again");
				break;
			}
			else if(m==cm){
				if(d<cd){
					System.out.println("E3: INVALID DATE ENTRY: Please try again");
					break;
				}
				else{
					//System.out.println("Code A1: Date check successful");
					String tempd=m+"-"+d+"-"+y;
				}
			}
		}
		
		//puts date components in format to be parsed
		String tempd=m+" "+d+" "+y;
		
		int ch=td.getHours();
		int cmin=td.getMinutes();
		//System.out.println(cmin);
	
	//User enters starting time
	System.out.println("Please enter the starting time of the reservation. For Example: 1 30 pm");
		int h1=kb.nextInt();
		int m1=kb.nextInt();
		String am1=kb.next();
	//User enters ending time
	System.out.println("Please enter the ending time");
		int h2=kb.nextInt();
		int m2=kb.nextInt();
		String am2=kb.next();
		
		
		//Converts 12 hour time to 24 hour time so its easier to compare
		if(am1.equalsIgnoreCase("pm")){
			h1=h1+12;
		}
		if(am2.equalsIgnoreCase("pm")){
			h2=h2+12;
		}
		
		
		//tests reseverations that are made for the present date
		if(d==cd && m==cm && y==cy ){
		//hour check fail
		if(h1<ch||h2<ch){
			System.out.println("E4: INVALID DATE ENTRY. Please try again.");
			break;
		}
		//hour check successful
		else if(h1==ch ){
			//minute check fail
			if(m1<cmin){
				System.out.println("E5: INVALID DATE ENTRY. Please try again.");
				break;
			}
			}
		else if(h2==ch){
			if(m2<cmin){
				System.out.println("E6: INVALID DATE ENTRY. Please try again.");
				break;	
			}
		}
		}
		
		//checks that starting time is before ending time
		if(h1>h2){
			System.out.println("E7: INVALID DATE ENTRY. Please try again.");
			break;
		}
		else if(h1==h2){
			if(m1>m2){
				System.out.println("E8: INVALID DATE ENTRY. Please try again.");
				break;
			}
			else{
				//System.out.println("Code A2: Creation of reservation");
				res [count]=new Reservation ();
				res [count].setStart(tempd, h1, m1, am1);
				res[count].setEnd(tempd, h2, m2, am2);
				
				}
			}
		else{
			//System.out.println("Code A3: Creation of reservation");
			res [count]=new Reservation ();
			res [count].setStart(tempd, h1, m1, am1);
			res[count].setEnd(tempd, h2, m2, am2);
			
		}
		
		//set the parking lot number
		int lotNum=1;
		
		
		//for the first reservation, the parking spot is always 1
		if(count==0){
			res[count].setLotNum(lotNum);
			System.out.println("Resveration number: "+count+"\nStart time: "+res[count].getStart()+"\nEnd Time: "+res[count].getEnd()+"\nParking Spot Number: "+res[count].getLotNum());
			System.out.println();
			System.out.println("Enter any key to go to the menu");
			kb.nextLine();
			kb.nextLine();
			count++;
		}
		else{
			for(int i=0;i<count;i++){
				if(lotNum<=50){
					//if new res starts before existing res
					if(res[count].getStart().before(res[i].getStart())){
						//if new res starts and ends before existing res ->no conflict
						if(res[count].getEnd().before(res[i].getStart())){
							res[count].setLotNum(lotNum);
							}
						//if new res starts before existing and new res ends after existing starting time -> time conflict
						else if(res[count].getEnd().after(res[i].getStart())){
							lotNum++;
							res[count].setLotNum(lotNum);
							}
						}
					//if new res starts after existing res start
					else if(res[count].getStart().after(res[i].getStart())){
						//if new res starts after existing res has started and ended -> no conflict
						if(res[count].getStart().after(res[i].getEnd())){
							res[count].setLotNum(lotNum);
							}
						//if new res starts after existing res but not after existing res ends -> time conflict
						else if(res[count].getStart().before(res[i].getEnd())){
							lotNum++;
							res[count].setLotNum(lotNum);
							}
						}
					//if new res starts or ends at the same time as an existing res -> time conflict
					else if(res[count].getStart().equals(res[i].getStart())||res[count].getEnd().equals(res[i].getEnd())){
						lotNum++;
						res[count].setLotNum(lotNum);
				
						}
					}
			//parking lot has recieve maxium cap.
			else{
				System.out.println("There are no more spaces available for that time");
				break;
				}
			}
		//All checks show no conflict and have assigned parking spot	
		System.out.println("Resveration number: "+count+"\nStart time: "+res[count].getStart()+"\nEnd Time: "+res[count].getEnd()+"\nParking Spot Number: "+res[count].getLotNum());
		System.out.println();
		System.out.println("Enter any key to go to the menu");
		kb.nextLine();
		kb.nextLine();
		count++;
		}
	break;
	
	
case "modify" :
	//ask user for res number 
	System.out.println("Please enter the reservation number you would like to edit");
		int r=kb.nextInt();
		
	System.out.println("Enter 'edit' to change the date and time.\nEnter 'delete' to delete the reseveration\nEnter 'cancel' to return the menu ");
		String choice=kb.next();
		
		switch(choice){
		case "edit":
			//change all the res info
			System.out.println("Please enter the new date of the reservation. For example: 09 30 2015");
			 m=kb.nextInt();
			 d=kb.nextInt();
			 y=kb.nextInt();
			Date td2=new Date();
			
			cm=td2.getMonth();
			cd=td2.getDate();
			cy=td2.getYear()+1900;
			
			//System.out.println(cm+" "+cd+" "+cy);
			if(y<cy){
				System.out.println("E1: INVALID DATE ENTRY: Please try again");
				break;
			}
			else if(y== cy){
				if(m<cm){
					System.out.println("E2: INVALID DATE ENTRY: Please try again");
					break;
				}
				else if(m==cm){
					if(d<cd){
						System.out.println("E3: INVALID DATE ENTRY: Please try again");
						break;
					}
					else{
						//System.out.println("Code A1: Date check successful");
						
					}
				}
			}
			tempd=m+" "+d+" "+y;
			
			 ch=td2.getHours()+1;
			 cmin=td2.getMinutes();
			
			
		System.out.println("Please enter the starting time of the reservation. For Example: 1 30 pm");
			 h1=kb.nextInt();
			 m1=kb.nextInt();
			 am1=kb.next();
		System.out.println("Please enter the ending time");
			 h2=kb.nextInt();
			 m2=kb.nextInt();
			 am2=kb.next();
			
			if(am1.equalsIgnoreCase("pm")){
				h1=h1+12;
			}
			if(am2.equalsIgnoreCase("pm")){
				h2=h2+12;
			}
			//tests reseverations that are made for the present date
			if(d==cd && m==cm && y==cy ){
				//hour check fail
				if(h1<ch||h2<ch){
					System.out.println("E4: INVALID DATE ENTRY. Please try again.");
					break;
				}
				//hour check successful
				else if(h1>=ch ){
					//minute check fail
					if(m1<cmin){
						System.out.println("E5: INVALID DATE ENTRY. Please try again.");
						break;
					}
					}
				else if(h2>=ch){
					if(m2<cmin){
						System.out.println("E6: INVALID DATE ENTRY. Please try again.");
						break;	
					}
				}
			
			}
			if(h1>h2){
				System.out.println("E7: INVALID DATE ENTRY. Please try again.");
				break;
			}
			else if(h1==h2){
				if(m1>m2){
					System.out.println("E8: INVALID DATE ENTRY. Please try again.");
					break;
				}
				else{
					//System.out.println("Code A2: Creation of reservation");
					res[r]= new Reservation();
					res [r].setStart(tempd, h1, m1, am1);
					res[r].setEnd(tempd, h2, m2, am2);
					
				}
			}
			else{
				//System.out.println("Code A3: Creation of reservation");
				res [r]= new Reservation();
				res [r].setStart(tempd, h1, m1, am1);
				res[r].setEnd(tempd, h2, m2, am2);
				
			}
			lotNum= 1;
			
			if(r== 0){
				res[r].setLotNum(lotNum);
				System.out.println("Resveration number: "+r+"\nStart time: "+res[r].getStart()+"\nEnd Time: "+res[r].getEnd()+"\nParking Spot Number: "+res[r].getLotNum());
				System.out.println();
				System.out.println("Enter any key to go to the menu");
				kb.nextLine();
				kb.nextLine();
				break;
			}
				
			
			else{
			for(int i=0;i<count;i++){
				if(lotNum<=50){
					System.out.println(res[r].getStart()+" "+res[r].getEnd());
				if(res[r].getStart().before(res[i].getStart())){
					if(res[r].getEnd().before(res[i].getStart())){
						res[r].setLotNum(lotNum);
						
						
					}
					else if(res[r].getEnd().after(res[i].getStart())){
						lotNum++;
						res[r].setLotNum(lotNum);
					}
				}
				else if(res[r].getStart().after(res[i].getStart())){
					if(res[r].getStart().after(res[i].getEnd())){
						res[r].setLotNum(lotNum);
						
						
					}
					else if(res[r].getStart().before(res[i].getEnd())){
						lotNum++;
						res[r].setLotNum(lotNum);
					}
				}
				}
				else{
					System.out.println("There are no more spaces available for that time");
					break;
				}
			}
			
			System.out.println("Resveration number: "+r+"\nStart time: "+res[r].getStart()+"\nEnd Time: "+res[r].getEnd()+"\nParking Spot Number: "+res[r].getLotNum());
			System.out.println();
			System.out.println("Enter any key to go to the menu");
			kb.nextLine();
			kb.nextLine();
			
			}
			break;
		
		case "delete":
			//calls the constructor and nullifies values
			res[r]= new Reservation ();
			
			System.out.println("Reservation cancelled.");
			System.out.println();
			break;
			
		case "cancel":
			break;
			
		default:
			System.out.println("Invalid Input");
		}
break;

case "extend" :
	//to extend an active res
	Date ExD= new Date();
	System.out.println("Please enter the reservation number");
	r=kb.nextInt();
	if(res[r].getStart().after(ExD)){
		System.out.println("You're reservation has not started yet so you can not extend your reservation");
		break;
	}
	
		System.out.println("Please enter the new end time for your resevation");
			int newHr=kb.nextInt();
			int newMin=kb.nextInt();
			String newAm=kb.next();
			int ExMon=ExD.getMonth()+1;
			int ExDay=ExD.getDate();
			int ExY=ExD.getYear()+1900;
			String sameDay=ExMon+" "+ExDay+" "+ExY;
		
		Date n=new Date();
		n=res[r].getEnd();
		res[r].setEnd(sameDay, newHr, newMin, newAm);
		
		for(int j=0;j<count;j++){
			if(res[r].getLotNum()==res[j].getLotNum()){
				if(res[r].getEnd().before(res[j].getStart())){
					System.out.println("You're reservation was extended successfully");
				}
				else{
					System.out.println("There is already a reservation for this time. Unable to extend reservation.");
					res[r].setEnd(n);
				}
			}
		}
		System.out.println("Resveration number: "+r+"\nStart time: "+res[r].getStart()+"\nEnd Time: "+res[r].getEnd()+"\nParking Spot Number: "+res[r].getLotNum());
		System.out.println();
		System.out.println("Enter any key to go to the menu");
		kb.nextLine();
		kb.nextLine();
	
break;
case "view":
	System.out.println("Enter reservation number");
	r=kb.nextInt();
	
	System.out.println(res[r].getStart());
	System.out.println(res[r].getEnd());
	System.out.println(res[r].getLotNum());
	
	break;
	
case "quit" :
finished=true;
break;
default :
System.out.println("Invalid input");
}
}
while (!finished);

System.out.println("Have a good day!");
kb.close();
}
}




