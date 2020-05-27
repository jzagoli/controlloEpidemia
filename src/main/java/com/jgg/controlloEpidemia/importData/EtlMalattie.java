package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EtlMalattie {

    static final ComuneService comuneService = new ComuneService();
    static final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

    private static void caricaMalattia(String[] vett) {

        for (int i = 1; i < vett.length - 1; i++) {
            if (Integer.parseInt(vett[0]) < 1900) {
                System.out.println("Errore");
            }
            if (Integer.parseInt(vett[i]) <= 0) {
                System.out.println("Errore");
            }
        }
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(Integer.parseInt(vett[0]), Integer.parseInt(vett[1]), Integer.parseInt(vett[2]), Integer.parseInt(vett[3]), Integer.parseInt(vett[4]), Integer.parseInt(vett[5]), Integer.parseInt(vett[6]), Integer.parseInt(vett[7]), Integer.parseInt(vett[8]), Integer.parseInt(vett[9]), Integer.parseInt(vett[10]), Integer.parseInt(vett[11]), Integer.parseInt(vett[12]), Integer.parseInt(vett[13]), Integer.parseInt(vett[14]), Integer.parseInt(vett[15]), Integer.parseInt(vett[16]), comuneService.findByCodiceIstat(Integer.parseInt(vett[17])));
        malattieSettimanaliService.save(malattieSettimanali);
    }

    public void etl(String path) throws FileNotFoundException {
        File fileMalattie = new File(path);
        Scanner reader = new Scanner(fileMalattie);
        while (reader.hasNextLine()) {
            String riga = reader.nextLine();
            String[] vettore;
            if (!riga.equals("")) {
                vettore = riga.split(";");
                if (vettore.length == 18) {
                    caricaMalattia(vettore);
                }
            }
        }
        reader.close();
    }
}
