package com.ubs.opsit.interviews.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.ubs.opsit.interviews.Constants;

public class BerlinUhrClock extends Clock{
	
	private ClockState second;
	private ClockState[] hourTop = new ClockState[4];
	private ClockState[] hourLow = new ClockState[4];
	private ClockState[] minuteTop = new ClockState[11];
	private ClockState[] minuteLow = new ClockState[4];
	
	public BerlinUhrClock() {
		second = ClockState.OFF;
		Arrays.fill(hourTop, ClockState.OFF);
		Arrays.fill(hourLow, ClockState.OFF);
		Arrays.fill(minuteTop, ClockState.OFF);
		Arrays.fill(minuteLow, ClockState.OFF);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BerlinUhrClock other = (BerlinUhrClock) obj;
		if (!Arrays.equals(hourLow, other.hourLow))
			return false;
		if (!Arrays.equals(hourTop, other.hourTop))
			return false;
		if (!Arrays.equals(minuteLow, other.minuteLow))
			return false;
		if (!Arrays.equals(minuteTop, other.minuteTop))
			return false;
		if (second != other.second)
			return false;
		return true;
	}



	@Override
	public String toString() {
		StringBuilder strRep = new StringBuilder();
		strRep.append(second.getState() + System.getProperty("line.separator"));
		for(int i = 0 ; i < hourTop.length ; i++) {
			strRep.append(hourTop[i].getState());
		}
		strRep.append(System.getProperty("line.separator"));
		for(int i = 0 ; i < hourLow.length ; i++) {
			strRep.append(hourLow[i].getState());
		}
		strRep.append(System.getProperty("line.separator"));
		for(int i = 0 ; i < minuteTop.length ; i++) {
			strRep.append(minuteTop[i].getState());
		}
		strRep.append(System.getProperty("line.separator"));
		for(int i = 0 ; i < minuteLow.length ; i++) {
			strRep.append(minuteLow[i].getState());
		}
		return strRep.toString();
	}
	
	public void setMinutes(int minutes) {
		int upperRow = minutes / 5;
		int lowerRow = minutes % 5;
		int firstLampUpperRow = 0;
		int firstLampLowerRow = 0;
		
		while(upperRow > firstLampUpperRow) {
			if((firstLampUpperRow+1)%3 == 0) {
				minuteTop[firstLampUpperRow] = ClockState.RED;
			} else {
				minuteTop[firstLampUpperRow] = ClockState.YELLOW;
			}
			firstLampUpperRow++;
		}
		
		while(lowerRow > 0 && lowerRow > firstLampLowerRow) {
			minuteLow[firstLampLowerRow] = ClockState.YELLOW;
			firstLampLowerRow++;
		}
		
	}
	
	public void setHours(int hours) {
		int upperRow = hours / 5;
		int lowerRow = hours % 5;
		int firstLampUpperRow = 0;
		int firstLampLowerRow = 0;
		
		while(upperRow > firstLampUpperRow) {
			hourTop[firstLampUpperRow] = ClockState.RED;
			firstLampUpperRow++;
		}
		
		while(lowerRow > 0 && lowerRow > firstLampLowerRow) {
			hourLow[firstLampLowerRow] = ClockState.RED;
			firstLampLowerRow++;
		}
		
	}
	
	public void setSeconds(int seconds) {
		if(seconds % 2 == 0) {
			second = ClockState.YELLOW;
		} else {
			second = ClockState.OFF;
		}
		
	}

	public static void main(String... args) throws Exception {
		DateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
		Date date = sdf.parse("00:00:00");
		int hours = date.getHours();
		System.out.println(hours);
	}

}
