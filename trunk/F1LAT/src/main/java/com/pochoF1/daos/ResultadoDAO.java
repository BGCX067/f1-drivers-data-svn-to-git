package com.pochoF1.daos;

import java.util.List;

import org.hibernate.Query;

import com.pochoF1.stats.Piloto;
import com.pochoF1.stats.Resultado;

@SuppressWarnings("unchecked")
public class ResultadoDAO extends GenericDAO<Resultado> {

	private static ResultadoDAO instance = null;

	protected ResultadoDAO() {
	}

	public static ResultadoDAO getInstance() {
		if (instance == null) {
			instance = new ResultadoDAO();
		}
		return instance;
	}

	
	public List<Resultado> getResultadosByPiloto(Piloto p){
		Query query = getHibernateTemplate().createQuery("from " + Resultado.class.getName() + " rs where rs.piloto = :piloto");
		query.setParameter("piloto", p);
		List<Resultado> resultados = (List<Resultado>)query.list();
		getSession().getTransaction().commit();
		return resultados;
	}
	
	public List<Resultado> getResultadosByPiloto(String nombrePiloto){
		Query query = getHibernateTemplate().createQuery("from " + Resultado.class.getName() + " rs where rs.piloto.driverRef = :piloto");
		query.setParameter("piloto", nombrePiloto);
		List<Resultado> resultados = (List<Resultado>)query.list();
		getSession().getTransaction().commit();
		return resultados;
	}
	
	public List<Resultado> getResultadosByPiloto(Integer idPiloto){
		Query query = getHibernateTemplate().createQuery("from " + Resultado.class.getName() + " rs where rs.piloto.driverId = :piloto");
		query.setParameter("piloto", idPiloto);
		List<Resultado> resultados = (List<Resultado>)query.list();
		getSession().getTransaction().commit();
		return resultados;
	}
}
