package com.ubs.opsit.interviews.model;

public enum ClockState {
	YELLOW("Y"), 
	OFF("O"), 
	RED("R");

    private String state;

    ClockState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
