package com.jgg.controlloEpidemia.service;

import com.jgg.controlloEpidemia.dao.UtenteDao;
import com.jgg.controlloEpidemia.model.Utente;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UtenteService {
    private static UtenteDao utenteDao = new UtenteDao();

    public void save(Utente utente) {
        utenteDao.save(utente);
    }

    public Utente findById(Integer id) {
        return utenteDao.findById(id);
    }

    public void deleteById(Integer id) {
      utenteDao.deleteById(id);
    }

    public List<Utente> findAll() {
        return utenteDao.findAll();
    }
}
