package com.pochoF1.test;

import com.pochoF1.daos.ResultadoDAO;
import com.pochoF1.enums.PilotosEnum;
import com.pochoF1.stats.Resultado;

public class Test {

	/**
	 * @param args
	 *            { name: 'Alonso', data: [[1,135] , [2,140] , 150, 160 , 170]
	 *            },
	 */
	public static void main(String[] args) {


		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		// SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		// SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		// String fecha = "17/03/2013";
		//
		// try {
		// Calendar c = Calendar.getInstance();
		// c.setTime(formatter.parse(fecha));
		// Date domingo = c.getTime();
		// c.add(Calendar.DATE, -1);
		// Date sabado = c.getTime();
		// c.add(Calendar.DATE, -1);
		// Date viernes = c.getTime();
		// System.out.println(dayFormat.format(viernes) + "," +
		// dayFormat.format(sabado) + "," + dayFormat.format(domingo) + " " +
		// monthFormat.format(domingo));
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }


		try {
			for (Resultado res : ResultadoDAO.getInstance().getResultadosByPiloto(PilotosEnum.hamilton.toString())) {
				System.out.println(res.getPosicionFinalTexto());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
