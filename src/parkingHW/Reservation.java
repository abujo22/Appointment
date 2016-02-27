package parkingHW;
import java.util.*;
import java.text.*;

public class Reservation {
	private Date startTime;
	private Date endTime;
	private int lotNum;
	SimpleDateFormat df= new SimpleDateFormat("M d y H m a");
	
	public Reservation( Date s, Date e, int l){
		startTime=s;
		endTime=e;
		lotNum=l;
	}
	public Reservation(){
		startTime=null;
		endTime=null;
		lotNum=0;
		
	}
	
	public void setStart(String d, int h, int m, String a ){
		try{
			startTime=df.parse(d+" "+h+" "+m+" "+a);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}
	public void setEnd(String d, int h, int m, String a ){
		try{
			endTime=df.parse(d+" "+h+" "+m+" "+a);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}
	public void setEnd(Date n){
		endTime=n;
	}
	public void setLotNum(int l){
		lotNum=l;
	}
	
	public Date getStart(){
		return startTime;
	}
	public Date getEnd(){
		return endTime;
	}
	public int getLotNum(){
		return lotNum;
	}

}
