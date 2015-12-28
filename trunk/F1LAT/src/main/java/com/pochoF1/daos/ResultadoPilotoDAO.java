package com.pochoF1.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.pochoF1.stats.Piloto;
import com.pochoF1.stats.PilotoPunPosTemp;
import com.pochoF1.stats.ResultadoPiloto;

@SuppressWarnings("unchecked")
public class ResultadoPilotoDAO extends GenericDAO<ResultadoPiloto> {

	private static ResultadoPilotoDAO instance = null;

	protected ResultadoPilotoDAO() {
	}

	public static ResultadoPilotoDAO getInstance() {
		if (instance == null) {
			instance = new ResultadoPilotoDAO();
		}
		return instance;
	}
	
	public List<ResultadoPiloto> getResultadosByNombrePiloto(String name){
		Query query = getHibernateTemplate().createQuery("from " + ResultadoPiloto.class.getName() + " rp left join fetch rp.carrera where rp.piloto.driverRef = :driverRef");
		query.setParameter("driverRef", name);
		List<ResultadoPiloto> resultadosPiloto = (List<ResultadoPiloto>)query.list();
		getSession().getTransaction().commit();
		return resultadosPiloto;
	}
	
	public List<PilotoPunPosTemp> getResultadosPuntajePosicionByPiloto(String name){
		Piloto p = PilotoDAO.getInstance().getPilotoByName(name);
		String sqlQuery = "select max(ds.points) points, ds.position , r.year season from driverstandings ds inner join races r on ds.raceId = r.raceId left join races r2 on (r.year = r2.year and r.raceId < r2.raceId) inner join drivers d on ds.driverId = d.driverId where r2.raceId is null and d.driverId = :driverId group by r.year";
		List<PilotoPunPosTemp> objects = getHibernateTemplate().createSQLQuery(sqlQuery).setResultTransformer(Transformers.aliasToBean(PilotoPunPosTemp.class)).setParameter("driverId", p.getDriverId()).list();
		return objects;
	}
	
	public List<PilotoPunPosTemp> getResultadosPuntajePosicionByPiloto(Piloto p){
		String sqlQuery = "select max(ds.points) points, ds.position , r.year season from driverstandings ds inner join races r on ds.raceId = r.raceId left join races r2 on (r.year = r2.year and r.raceId < r2.raceId) inner join drivers d on ds.driverId = d.driverId where r2.raceId is null and d.driverId = :driverId group by r.year";
		List<PilotoPunPosTemp> objects = getHibernateTemplate().createSQLQuery(sqlQuery).setResultTransformer(Transformers.aliasToBean(PilotoPunPosTemp.class)).setParameter("driverId", p.getDriverId()).list();
		return objects;
	}
	
	public List<PilotoPunPosTemp> getResultadosPuntajePosicionByPiloto(Integer idPiloto){
		String sqlQuery = "select max(ds.points) points, ds.position , r.year season from driverstandings ds inner join races r on ds.raceId = r.raceId left join races r2 on (r.year = r2.year and r.raceId < r2.raceId) inner join drivers d on ds.driverId = d.driverId where r2.raceId is null and d.driverId = :driverId group by r.year";
		List<PilotoPunPosTemp> objects = getHibernateTemplate().createSQLQuery(sqlQuery).setResultTransformer(Transformers.aliasToBean(PilotoPunPosTemp.class)).setParameter("driverId", idPiloto).list();
		return objects;
	}

}
