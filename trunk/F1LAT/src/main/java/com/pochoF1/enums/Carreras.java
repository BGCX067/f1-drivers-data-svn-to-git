package com.pochoF1.enums;

import java.util.HashMap;
import java.util.Map;


public enum Carreras {
	AUSTRALIA("01"),
	MALAYSIA("02"),
	CHINA("03"),
	BAHRAIN("04"),
	SPAIN("05"),
	MONACO("06"),
	CANADA("07"),
	BRITISH("08"),
	GERMANY("09"),
	HUNGARY("10"),
	BELGIUM("11"),
	ITALY("12"),
	SINGAPORE("13"),
	KOREA("14"),
	JAPAN("15"),
	INDIA("15"),
	EAU("17"),
	USA("18"),
	BRAZIL("19");
	
    private final String _carreraString;

    private Carreras(String carreraString) {
        this._carreraString = carreraString;
    }

    public String getCarreraString() {
        return _carreraString;
    }


    private static Map<String, Carreras> map = new HashMap<String, Carreras>();

    static {
        for (Carreras carrera : Carreras.values()) {
            map.put(carrera._carreraString, carrera);
        }
    }

    public static Carreras getCarrera(String carreraString) {
        return map.get(carreraString);
    }
}
