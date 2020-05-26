package com.jgg.controlloEpidemia.dao;

import com.jgg.controlloEpidemia.model.TipoTerritorio;

import java.util.List;

public class TipoTerritorioDao implements TipoTerritorioDaoInterface {

    private static Session session = new Session();

    @Override
    public void save(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().save(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        session.openCurrentSessionWithTransaction();
        TipoTerritorio tipoTerritorio = session.getCurrentSession().get(TipoTerritorio.class, id);
        session.getCurrentSession().delete(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().update(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public void saveOrUpdate(TipoTerritorio tipoTerritorio) {
        session.openCurrentSessionWithTransaction();
        session.getCurrentSession().saveOrUpdate(tipoTerritorio);
        session.closeCurrentSessionWithTransaction();
    }

    @Override
    public TipoTerritorio findById(Integer id) {
        session.openCurrentSession();
        TipoTerritorio tipoTerritorio = session.getCurrentSession().get(TipoTerritorio.class, id);
        session.closeCurrentSession();
        return tipoTerritorio;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TipoTerritorio> findAll() {
        session.openCurrentSession();
        List<TipoTerritorio> tipoTerritorio = session.getCurrentSession().createQuery("from TipoTerritorio").list();
        session.closeCurrentSession();
        return tipoTerritorio;
    }
}
