package com.pochoF1.daos;

import org.hibernate.Query;

import com.pochoF1.enums.PilotosEnum;
import com.pochoF1.stats.Piloto;

public class PilotoDAO extends GenericDAO<Piloto> {

	private static PilotoDAO instance = null;

	protected PilotoDAO() {
	}

	public static PilotoDAO getInstance() {
		if (instance == null) {
			instance = new PilotoDAO();
		}
		return instance;
	}
	
	
	public Piloto getPilotoByName(String name){
		Query query = getHibernateTemplate().createQuery("from " + Piloto.class.getName() + " p where p.driverRef = :nombre");
		query.setParameter("nombre", name);
		Piloto piloto = (Piloto)query.uniqueResult();
		getSession().getTransaction().commit();
		return piloto;
	}
	
	public Piloto getPilotoByName(PilotosEnum name){
		Query query = getHibernateTemplate().createQuery("from " + Piloto.class.getName() + " p where p.driverRef = :nombre");
		query.setParameter("nombre", name.toString());
		Piloto piloto = (Piloto)query.uniqueResult();
		getSession().getTransaction().commit();
		return piloto;
	}

}
