package com.pochoF1.daos;

import java.util.List;

import org.hibernate.Query;

import com.pochoF1.stats.Clasificacion;
import com.pochoF1.stats.Piloto;

@SuppressWarnings("unchecked")
public class ClasificacionDAO extends GenericDAO<Clasificacion> {
	
	private static ClasificacionDAO instance = null;

	protected ClasificacionDAO() {
	}

	public static ClasificacionDAO getInstance() {
		if (instance == null) {
			instance = new ClasificacionDAO();
		}
		return instance;
	}
	
	public List<Clasificacion> getClasificacionByPiloto(String name){
		Query query = getHibernateTemplate().createQuery("from " + Clasificacion.class.getName() + " q where q.piloto.driverRef = :nombre order by q.position");
		query.setParameter("nombre", name);
		List<Clasificacion> listaClasificacion = (List<Clasificacion>)query.list();
		getSession().getTransaction().commit();
		return listaClasificacion;
	}
	
	public List<Clasificacion> getClasificacionByPiloto(Piloto piloto){
		Query query = getHibernateTemplate().createQuery("from " + Clasificacion.class.getName() + " q where q.piloto = :piloto order by q.position");
		query.setParameter("piloto", piloto);
		List<Clasificacion> listaClasificacion = (List<Clasificacion>)query.list();
		getSession().getTransaction().commit();
		return listaClasificacion;
	}

}
