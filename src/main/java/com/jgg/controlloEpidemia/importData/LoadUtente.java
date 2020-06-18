package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Permesso;
import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.PermessoService;
import com.jgg.controlloEpidemia.service.RuoloService;
import com.jgg.controlloEpidemia.service.UtenteService;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LoadUtente {

    List<Utente> utenteList = new ArrayList<>();
    List<Ruolo> ruoloList = new RuoloService().findAll();
    List<Permesso> permessoList = new PermessoService().findAll();

    public void load() {
        Utente u = new Utente("Admin", "aa", "Gianni", "Bolla", ruoloList.get(0));
        Utente u2 = new Utente("Contratto", "cc", "Costante", "Napolitani", ruoloList.get(1));
        Utente u3 = new Utente("Personale", "pp", "Ignazio", "Romano", ruoloList.get(2));
        Utente u4 = new Utente("Ricercatore", "rr", "Dalila", "Ferri", ruoloList.get(3));

        u.getPermesso().add(permessoList.get(0));
        u4.getPermesso().add(permessoList.get(0));

        utenteList.add(u);
        utenteList.add(u2);
        utenteList.add(u3);
        utenteList.add(u4);

        new UtenteService().saveOrUpdate(utenteList);
    }

}
