package com.pochoF1.enums;

public enum Sesiones {
	FP1("Free Practice 1"),
	FP2("Free Practice 2"),
	FP3("Free Practice 3"),
	QUALY("Qualifying"),
	RACE("Race"),
	FAST("Fast"),
	RESULTS("Results");
	
	
	 private final String _sesionString;
	
    private Sesiones(String sesionString) {
        this._sesionString = sesionString;
    }

    public String getSesionString() {
        return _sesionString;
    }

}
