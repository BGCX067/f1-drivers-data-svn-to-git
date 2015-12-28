package com.pochoF1.enums;

import java.util.HashMap;
import java.util.Map;

public enum Equipos {
	RBR("Red Bull Racing-Renault"),
	FERRARI("Ferrari"),
	MCLAREN("McLaren-Mercedes"),
	LOTUS("Lotus-Renault"),
	MERCEDES("Mercedes"),
	FORCEINDIA("Force India-Mercedes"),
	SAUBER("Sauber-Ferrari"),
	WILLIAMS("Williams-Renault"),
	TORO_ROSSO("STR-Ferrari"),
	CATERHAM("Caterham-Renault"),
	MARUSSIA("Marussia-Cosworth"),
	DESCONOCIDO("DESCONOCIDO");
	
    private final String _EquipoString;
    private static Map<String, Equipos> codeToStatusMapping;
    
    private Equipos(String carreraString) {
        this._EquipoString = carreraString;
    }

    public String getEquipo() {
        return _EquipoString;
    }
    
    
    
    public static Equipos getEquipos(String equipo) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(equipo);
    }
 
    private static void initMapping() {
        codeToStatusMapping = new HashMap<String, Equipos>();
        for (Equipos s : values()) {
            codeToStatusMapping.put(s._EquipoString, s);
        }
    }

	@Override
	public String toString() {
		return  getEquipo();
	}
    
	
    

}
