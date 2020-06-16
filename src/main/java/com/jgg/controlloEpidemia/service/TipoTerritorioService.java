package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.TipoTerritorioDao;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TipoTerritorioService {

    private static final TipoTerritorioDao tipoTerritorioDao = new TipoTerritorioDao();

    public void save(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.save(tipoTerritorio);
    }

    public void deleteById(Integer id) {
        tipoTerritorioDao.deleteById(id);
    }

    public void update(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.update(tipoTerritorio);
    }

    public void saveOrUpdate(TipoTerritorio tipoTerritorio) {
        tipoTerritorioDao.saveOrUpdate(tipoTerritorio);
    }

    public TipoTerritorio findById(Integer id) {
        return tipoTerritorioDao.findById(id);
    }

    public TipoTerritorio findByNome(String nome) {
        return tipoTerritorioDao.findByNome(nome);
    }

    public List<TipoTerritorio> findAll() {
        return tipoTerritorioDao.findAll();
    }

}
