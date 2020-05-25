package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.Regione;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RegioneDao implements RegioneDaoInterface {
    private static Session session = new Session();

    @Override
    public void save(Regione regione) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        Regione regione = session.getCurrentSession().get(Regione.class, id);
        session.getCurrentSession().delete(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Regione regione) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(regione);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public Regione findById(Integer id) {
        session.openCurrentSession();
        Regione regione = session.getCurrentSession().get(Regione.class, id);
        session.closeCurrentSession();
        return regione;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Regione> findAll() {
        session.openCurrentSession();
        List<Regione> regione = session.getCurrentSession().createQuery("from Regione").list();
        session.closeCurrentSession();
        return regione;
    }
}
