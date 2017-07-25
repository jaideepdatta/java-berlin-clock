package com.ubs.opsit.interviews.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.ubs.opsit.interviews.Constants;
import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.model.Clock;

public class BerlinClockTimeConverter implements TimeConverter {
	TimeConverter timeUnitConverter;
	TimeUnit tu;
	Clock clock;
	
	public BerlinClockTimeConverter(Clock clock, TimeUnit tu, TimeConverter timeUnitConverter) {
		this.clock = clock;
		this.tu = tu;
		this.timeUnitConverter = timeUnitConverter;
	}

	@Override
	public String convertTime(String aTime) {
		DateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
		try {
			Date date = sdf.parse(aTime);
			if(tu.equals(TimeUnit.HOURS)) {
				int hours = date.getHours();
				clock.setHours(hours);
			}
			if(tu.equals(TimeUnit.MINUTES)) {
				int minutes = date.getMinutes();
				clock.setMinutes(minutes);
			}
			if(tu.equals(TimeUnit.SECONDS)) {
				int seconds = date.getSeconds();
				clock.setSeconds(seconds);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return timeUnitConverter != null ? timeUnitConverter.convertTime(aTime) : clock.toString();
		
	}

}
