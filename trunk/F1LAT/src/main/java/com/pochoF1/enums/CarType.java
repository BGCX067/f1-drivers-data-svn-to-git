package com.pochoF1.enums;

import java.util.HashMap;
import java.util.Map;

public enum CarType {
	Position(0),
	History(15);
	
	private final int _carType;
	private static Map<Integer, CarType> codeToStatusMapping;

	private CarType(int carType) {
		this._carType = carType;
	}

	public int getCarType() {
		return _carType;
	}
	
	public static CarType getCarType(int carType) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(carType);
    }
 
    private static void initMapping() {
        codeToStatusMapping = new HashMap<Integer, CarType>();
        for (CarType s : values()) {
            codeToStatusMapping.put(s._carType, s);
        }
    }
}
