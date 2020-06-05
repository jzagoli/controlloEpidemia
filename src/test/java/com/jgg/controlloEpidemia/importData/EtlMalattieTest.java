package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import com.jgg.controlloEpidemia.service.UtenteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtlMalattieTest {
    @Test
    void testEtlMalattie() throws IOException {
        App.utenteCorrente = new UtenteService().findById(1);
        new EtlMalattie().load("src\\main\\resources\\csvToLoad\\malattiesettimanali.csv");

        List<MalattieSettimanali> malattieSettimanaliList = new MalattieSettimanaliService().findAll();
        assertEquals(malattieSettimanaliList.size(), 11);
    }
}
