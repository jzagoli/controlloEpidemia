package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.model.*;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RegioneService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AnalisiDatiController implements Initializable {

    private final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    private final RegioneService regioneService = new RegioneService();
    private final ProvinciaService provinciaService = new ProvinciaService();
    private final List<MalattieSettimanali> malattieSettimanaliList = new MalattieSettimanaliService().findAll();

    @FXML
    private BorderPane analisiDatiBorderPane;
    @FXML
    private TabPane analisiDatiTabPane;

    @FXML
    private TableView<DecessiAnnuali> decessiAnnualiTableView;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIdColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Provincia> decessiAnnualiProvinciaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiAnnoColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIncidentiStradaliColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieTumoraliColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieCardiovascolariColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieContagioseColumn;

    @FXML
    private TableView<MalattieSettimanali> malattieSettimanaliTableView;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliIdColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliAnnoColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliSettimanaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConInfluenzaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConInfluenzaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliComplicanzeRespiratorieColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConPolmoniteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConPolmoniteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConMeningiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConMeningiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConEpatiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConEpatiteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConMorbilloColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConMorbilloColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConTubercolosiColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConTubercolosiColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConGastroenteriteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConGastroenteriteColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Comune> malattieSettimanaliComuneColumn;

    @FXML
    private TableView<DecessiAnnuali> decessiAnnualiConfrontaTableView;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIdConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Provincia> decessiAnnualiProvinciaConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiAnnoConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiIncidentiStradaliConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieTumoraliConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieCardiovascolariConfrontaColumn;
    @FXML
    private TableColumn<DecessiAnnuali, Integer> decessiAnnualiMalattieContagioseConfrontaColumn;

    @FXML
    private TableView<MalattieSettimanali> malattieSettimanaliConfrontaTableView;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliIdConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliAnnoConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliSettimanaConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConInfluenzaConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConInfluenzaConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliComplicanzeRespiratorieConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConPolmoniteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConPolmoniteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConMeningiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConMeningiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConEpatiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConEpatiteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConMorbilloConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConMorbilloConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConTubercolosiConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConTubercolosiConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliRicoveratiConGastroenteriteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Integer> malattieSettimanaliInCuraConGastroenteriteConfrontaColumn;
    @FXML
    private TableColumn<MalattieSettimanali, Comune> malattieSettimanaliComuneConfrontaColumn;

    @FXML
    private Button analisiDatiVisualizzaDecessiConfrontaConMalattieButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiAnnualiIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnualiAnnoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiAnnualiProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        decessiAnnualiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getNome());
                }
            }
        });
        decessiAnnualiIncidentiStradaliColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        decessiAnnualiMalattieTumoraliColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        decessiAnnualiMalattieContagioseColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        decessiAnnualiMalattieCardiovascolariColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));

        malattieSettimanaliIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        malattieSettimanaliAnnoColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        malattieSettimanaliTableView.getSortOrder().add(malattieSettimanaliAnnoColumn);
        malattieSettimanaliSettimanaColumn.setCellValueFactory(new PropertyValueFactory<>("settimana"));
        malattieSettimanaliRicoveratiConInfluenzaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiInfluenza"));
        malattieSettimanaliInCuraConInfluenzaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraInfluenza"));
        malattieSettimanaliComplicanzeRespiratorieColumn.setCellValueFactory(new PropertyValueFactory<>("complicanzeRespiratorie"));
        malattieSettimanaliRicoveratiConPolmoniteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiPolmonite"));
        malattieSettimanaliInCuraConPolmoniteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraPolmonite"));
        malattieSettimanaliRicoveratiConMeningiteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMeningite"));
        malattieSettimanaliInCuraConMeningiteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMeningite"));
        malattieSettimanaliRicoveratiConEpatiteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiEpatite"));
        malattieSettimanaliInCuraConEpatiteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraEpatite"));
        malattieSettimanaliRicoveratiConMorbilloColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMorbillo"));
        malattieSettimanaliInCuraConMorbilloColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMorbillo"));
        malattieSettimanaliRicoveratiConTubercolosiColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiTubercolosi"));
        malattieSettimanaliInCuraConTubercolosiColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraTubercolosi"));
        malattieSettimanaliRicoveratiConGastroenteriteColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiGastroenterite"));
        malattieSettimanaliInCuraConGastroenteriteColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraGastroenterite"));
        malattieSettimanaliComuneColumn.setCellValueFactory(new PropertyValueFactory<>("comune"));
        malattieSettimanaliComuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getNome());
                }
            }
        });

        decessiAnnualiIdConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        decessiAnnualiAnnoConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        decessiAnnualiProvinciaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        decessiAnnualiProvinciaConfrontaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getNome());
                }
            }
        });
        decessiAnnualiIncidentiStradaliConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("incidentiStradali"));
        decessiAnnualiMalattieTumoraliConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("malattieTumorali"));
        decessiAnnualiMalattieContagioseConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("malattieContagiose"));
        decessiAnnualiMalattieCardiovascolariConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("malattieCardiovascolari"));

        malattieSettimanaliIdConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        malattieSettimanaliAnnoConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("anno"));
        malattieSettimanaliConfrontaTableView.getSortOrder().add(malattieSettimanaliAnnoConfrontaColumn);
        malattieSettimanaliSettimanaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("settimana"));
        malattieSettimanaliRicoveratiConInfluenzaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiInfluenza"));
        malattieSettimanaliInCuraConInfluenzaConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraInfluenza"));
        malattieSettimanaliComplicanzeRespiratorieConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("complicanzeRespiratorie"));
        malattieSettimanaliRicoveratiConPolmoniteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiPolmonite"));
        malattieSettimanaliInCuraConPolmoniteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraPolmonite"));
        malattieSettimanaliRicoveratiConMeningiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMeningite"));
        malattieSettimanaliInCuraConMeningiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMeningite"));
        malattieSettimanaliRicoveratiConEpatiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiEpatite"));
        malattieSettimanaliInCuraConEpatiteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraEpatite"));
        malattieSettimanaliRicoveratiConMorbilloConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiMorbillo"));
        malattieSettimanaliInCuraConMorbilloConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraMorbillo"));
        malattieSettimanaliRicoveratiConTubercolosiConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiTubercolosi"));
        malattieSettimanaliInCuraConTubercolosiConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraTubercolosi"));
        malattieSettimanaliRicoveratiConGastroenteriteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("ricoveratiGastroenterite"));
        malattieSettimanaliInCuraConGastroenteriteConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("inCuraGastroenterite"));
        malattieSettimanaliComuneConfrontaColumn.setCellValueFactory(new PropertyValueFactory<>("comune"));
        malattieSettimanaliComuneConfrontaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getNome());
                }
            }
        });

        analisiDatiVisualizzaDecessiConfrontaConMalattieButton.disableProperty().bind(Bindings.isEmpty(decessiAnnualiTableView.getSelectionModel().getSelectedItems()).or(decessiAnnualiProvinciaColumn.textProperty().isEqualTo("REGIONE")));
        updateListVisualizzaDatiDecessi();
        updateListVisualizzaDatiMalattie();
    }

    @FXML
    void homepageButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        analisiDatiBorderPane.getScene().setRoot(root);
    }

    @FXML
    void analisiDatiVisualizzaGraficiButtonOnClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/grafici.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        analisiDatiBorderPane.getScene().setRoot(root);
    }

    private void updateListVisualizzaDatiDecessi() {
        decessiAnnualiTableView.getItems().clear();
        decessiAnnualiProvinciaColumn.setText("PROVINCIA");

        decessiAnnualiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getNome());
                }
            }
        });

        for (DecessiAnnuali decessiAnnuali : decessiAnnualiService.findAll()) {
            decessiAnnualiTableView.getItems().add(decessiAnnuali);
        }
    }

    private void updateListVisualizzaDatiMalattie() {
        malattieSettimanaliTableView.getItems().clear();
        malattieSettimanaliComuneColumn.setText("COMUNE");

        malattieSettimanaliComuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getNome());
                }
            }
        });

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            malattieSettimanaliTableView.getItems().add(malattieSettimanali);
        }
    }


    @FXML
    private void analisiDatiVisualizzaDecessiVisualizzaDatiButtonOnClicked() {
        updateListVisualizzaDatiDecessi();
    }

    @FXML
    private void analisiDatiVisualizzaDecessiAggregaPerRegioneButtonOnClicked() {
        if (!analisiDatiVisualizzaDecessiConfrontaConMalattieButton.isDisabled()) {
            analisiDatiVisualizzaDecessiConfrontaConMalattieButton.setDisable(true);
        }

        decessiAnnualiTableView.getItems().clear();
        decessiAnnualiProvinciaColumn.setText("REGIONE");

        decessiAnnualiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getRegione().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        Provincia provincia;
        Integer incidenti,
                tumorali,
                cardiovascolari,
                contagiose,
                id = 0;

        for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
            if (!anniList.contains(decessiAnnuali.getAnno())) {
                anniList.add(decessiAnnuali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            for (Regione r : regioneService.findAll()) {
                incidenti = 0;
                tumorali = 0;
                cardiovascolari = 0;
                contagiose = 0;
                provincia = null;
                id++;

                for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
                    if (decessiAnnuali.getProvincia().getRegione().getId().equals(r.getId()) && decessiAnnuali.getAnno().equals(anno)) {
                        incidenti += decessiAnnuali.getIncidentiStradali();
                        tumorali += decessiAnnuali.getMalattieTumorali();
                        cardiovascolari += decessiAnnuali.getMalattieCardiovascolari();
                        contagiose += decessiAnnuali.getMalattieContagiose();
                        provincia = decessiAnnuali.getProvincia();
                    }
                }
                if (provincia != null) {
                    DecessiAnnuali decessiRegione = new DecessiAnnuali(id, anno, incidenti, tumorali, cardiovascolari, contagiose, provincia);
                    decessiAnnualiTableView.getItems().add(decessiRegione);
                }
            }
        }
    }

    @FXML
    private void analisiDatiVisualizzaDecessiAggregaPerNazioneButtonOnClicked() {
        if (!analisiDatiVisualizzaDecessiConfrontaConMalattieButton.isDisabled()) {
            analisiDatiVisualizzaDecessiConfrontaConMalattieButton.setDisable(true);
        }

        decessiAnnualiTableView.getItems().clear();
        decessiAnnualiProvinciaColumn.setText("NAZIONALE");
        decessiAnnualiProvinciaColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Provincia provincia, boolean empty) {
                super.updateItem(provincia, empty);
                if (empty || provincia == null) {
                    setText("");
                } else {
                    setText(provincia.getRegione().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        Integer incidentiNazionale,
                tumoraliNazionale,
                cardiovascolariNazionale,
                contagioseNazionale,
                id = 0;
        Regione regioneNazionale = new Regione(777, "Nazionale", 0, "999999");
        Provincia provinciaNazionale = new Provincia("Provincia", 0, "999999", regioneNazionale);

        for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
            if (!anniList.contains(decessiAnnuali.getAnno())) {
                anniList.add(decessiAnnuali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            incidentiNazionale = 0;
            tumoraliNazionale = 0;
            cardiovascolariNazionale = 0;
            contagioseNazionale = 0;
            for (DecessiAnnuali decessiAnnuali : decessiAnnualiList) {
                if (decessiAnnuali.getAnno().equals(anno)) {
                    incidentiNazionale += decessiAnnuali.getIncidentiStradali();
                    tumoraliNazionale += decessiAnnuali.getMalattieTumorali();
                    cardiovascolariNazionale += decessiAnnuali.getMalattieCardiovascolari();
                    contagioseNazionale += decessiAnnuali.getMalattieContagiose();
                }
            }
            id++;
            DecessiAnnuali decessiNazione = new DecessiAnnuali(id, anno, incidentiNazionale, tumoraliNazionale, cardiovascolariNazionale, contagioseNazionale, provinciaNazionale);
            decessiAnnualiTableView.getItems().add(decessiNazione);
        }
    }

    @FXML
    private void analisiDatiVisualizzaDecessiConfrontaConMalattieButtonOnClicked() {
        analisiDatiTabPane.getSelectionModel().select(2);
        malattieSettimanaliConfrontaTableView.getItems().clear();
        decessiAnnualiConfrontaTableView.getItems().clear();

        DecessiAnnuali eDecessiAnnuali = decessiAnnualiTableView.getSelectionModel().getSelectedItem();
        decessiAnnualiConfrontaTableView.getItems().add(eDecessiAnnuali);
        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (malattieSettimanali.getAnno().equals(eDecessiAnnuali.getAnno()) && malattieSettimanali.getComune().getProvincia().getId().equals(eDecessiAnnuali.getProvincia().getId())) {
                malattieSettimanaliConfrontaTableView.getItems().add(malattieSettimanali);
            }
        }

    }

    @FXML
    private void analisiDatiDecessiAnnualiEsportaDatiButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("decessi");
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            Configuration configuration = new Configuration().configure();
            org.hibernate.Session currentSession = configuration.buildSessionFactory().openSession();
            currentSession.doWork(connection -> {
                CallableStatement cs = connection.prepareCall("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (null,'DECESSIANNUALI',?,';',null,'UTF-8')");
                cs.setString(1, selectedFile.getAbsolutePath());
                cs.execute();
            });
            currentSession.close();
        }
    }


    @FXML
    private void analisiDatiVisualizzaMalattieVisualizzaDatiButtonOnClicked() {
        updateListVisualizzaDatiMalattie();
    }

    @FXML
    private void analisiDatiVisualizzaMalattieAggregaPerProvinciaButtonOnClicked() {
        malattieSettimanaliTableView.getItems().clear();

        malattieSettimanaliComuneColumn.setText("PROVINCIA");
        malattieSettimanaliComuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getProvincia().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        Comune comune;
        Integer rInfluenza,
                cInfluenza,
                cRespiratorie,
                rPolmonite,
                cPolmonite,
                rMeningite,
                cMeningite,
                rEpatite,
                cEpatite,
                rMorbillo,
                cMorbillo,
                rTubercolosi,
                cTubercolosi,
                rGastroenterite,
                cGastroenterite,
                id = 0;

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (!anniList.contains(malattieSettimanali.getAnno())) {
                anniList.add(malattieSettimanali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            for (Provincia p : provinciaService.findAll()) {
                rInfluenza = 0;
                cInfluenza = 0;
                cRespiratorie = 0;
                rPolmonite = 0;
                cPolmonite = 0;
                rMeningite = 0;
                cMeningite = 0;
                rEpatite = 0;
                cEpatite = 0;
                rMorbillo = 0;
                cMorbillo = 0;
                rTubercolosi = 0;
                cTubercolosi = 0;
                rGastroenterite = 0;
                cGastroenterite = 0;
                comune = null;
                id++;

                for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
                    if (malattieSettimanali.getComune().getProvincia().getId().equals(p.getId()) && malattieSettimanali.getAnno().equals(anno)) {
                        rInfluenza += malattieSettimanali.getRicoveratiInfluenza();
                        cInfluenza += malattieSettimanali.getInCuraInfluenza();
                        cRespiratorie += malattieSettimanali.getComplicanzeRespiratorie();
                        rPolmonite += malattieSettimanali.getRicoveratiPolmonite();
                        cPolmonite += malattieSettimanali.getInCuraPolmonite();
                        rMeningite += malattieSettimanali.getRicoveratiMeningite();
                        cMeningite += malattieSettimanali.getInCuraMeningite();
                        rEpatite += malattieSettimanali.getRicoveratiEpatite();
                        cEpatite += malattieSettimanali.getInCuraEpatite();
                        rMorbillo += malattieSettimanali.getRicoveratiMorbillo();
                        cMorbillo += malattieSettimanali.getInCuraMorbillo();
                        rTubercolosi += malattieSettimanali.getRicoveratiTubercolosi();
                        cTubercolosi += malattieSettimanali.getInCuraTubercolosi();
                        rGastroenterite += malattieSettimanali.getRicoveratiGastroenterite();
                        cGastroenterite += malattieSettimanali.getInCuraGastroenterite();
                        comune = malattieSettimanali.getComune();
                    }
                }
                if (comune != null) {
                    MalattieSettimanali malattieProvincia = new MalattieSettimanali(id, anno, 0, rInfluenza, cInfluenza, cRespiratorie, rPolmonite, cPolmonite, rMeningite, cMeningite, rEpatite, cEpatite, rMorbillo, cMorbillo, rTubercolosi, cTubercolosi, rGastroenterite, cGastroenterite, comune);
                    malattieSettimanaliTableView.getItems().add(malattieProvincia);
                }
            }
        }
    }

    @FXML
    private void analisiDatiVisualizzaMalattieAggregaPerRegioneButtonOnClicked() {
        malattieSettimanaliTableView.getItems().clear();

        malattieSettimanaliComuneColumn.setText("REGIONE");

        malattieSettimanaliComuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getProvincia().getRegione().getNome());
                }
            }
        });

        ArrayList<Integer> anniList = new ArrayList<>();
        Comune comune;
        Integer rInfluenza,
                cInfluenza,
                cRespiratorie,
                rPolmonite,
                cPolmonite,
                rMeningite,
                cMeningite,
                rEpatite,
                cEpatite,
                rMorbillo,
                cMorbillo,
                rTubercolosi,
                cTubercolosi,
                rGastroenterite,
                cGastroenterite,
                id = 0;

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (!anniList.contains(malattieSettimanali.getAnno())) {
                anniList.add(malattieSettimanali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            for (Regione r : regioneService.findAll()) {
                rInfluenza = 0;
                cInfluenza = 0;
                cRespiratorie = 0;
                rPolmonite = 0;
                cPolmonite = 0;
                rMeningite = 0;
                cMeningite = 0;
                rEpatite = 0;
                cEpatite = 0;
                rMorbillo = 0;
                cMorbillo = 0;
                rTubercolosi = 0;
                cTubercolosi = 0;
                rGastroenterite = 0;
                cGastroenterite = 0;
                comune = null;
                id++;

                for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
                    if (malattieSettimanali.getComune().getProvincia().getRegione().getId().equals(r.getId()) && malattieSettimanali.getAnno().equals(anno)) {
                        rInfluenza += malattieSettimanali.getRicoveratiInfluenza();
                        cInfluenza += malattieSettimanali.getInCuraInfluenza();
                        cRespiratorie += malattieSettimanali.getComplicanzeRespiratorie();
                        rPolmonite += malattieSettimanali.getRicoveratiPolmonite();
                        cPolmonite += malattieSettimanali.getInCuraPolmonite();
                        rMeningite += malattieSettimanali.getRicoveratiMeningite();
                        cMeningite += malattieSettimanali.getInCuraMeningite();
                        rEpatite += malattieSettimanali.getRicoveratiEpatite();
                        cEpatite += malattieSettimanali.getInCuraEpatite();
                        rMorbillo += malattieSettimanali.getRicoveratiMorbillo();
                        cMorbillo += malattieSettimanali.getInCuraMorbillo();
                        rTubercolosi += malattieSettimanali.getRicoveratiTubercolosi();
                        cTubercolosi += malattieSettimanali.getInCuraTubercolosi();
                        rGastroenterite += malattieSettimanali.getRicoveratiGastroenterite();
                        cGastroenterite += malattieSettimanali.getInCuraGastroenterite();
                        comune = malattieSettimanali.getComune();
                    }
                }
                if (comune != null) {
                    MalattieSettimanali malattieRegione = new MalattieSettimanali(id, anno, 0, rInfluenza, cInfluenza, cRespiratorie, rPolmonite, cPolmonite, rMeningite, cMeningite, rEpatite, cEpatite, rMorbillo, cMorbillo, rTubercolosi, cTubercolosi, rGastroenterite, cGastroenterite, comune);
                    malattieSettimanaliTableView.getItems().add(malattieRegione);
                }
            }
        }
    }

    @FXML
    private void analisiDatiVisualizzaMalattieAggregaPerNazioneButtonOnClicked() {
        malattieSettimanaliTableView.getItems().clear();

        malattieSettimanaliComuneColumn.setText("NAZIONALE");
        malattieSettimanaliComuneColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Comune comune, boolean empty) {
                super.updateItem(comune, empty);
                if (empty || comune == null) {
                    setText("");
                } else {
                    setText(comune.getProvincia().getRegione().getNome());
                }
            }
        });
        ArrayList<Integer> anniList = new ArrayList<>();
        Integer rInfluenzaNazionale,
                cInfluenzaNazionale,
                cRespiratorieNazionale,
                rPolmoniteNazionale,
                cPolmoniteNazionale,
                rMeningiteNazionale,
                cMeningiteNazionale,
                rEpatiteNazionale,
                cEpatiteNazionale,
                rMorbilloNazionale,
                cMorbilloNazionale,
                rTubercolosiNazionale,
                cTubercolosiNazionale,
                rGastroenteriteNazionale,
                cGastroenteriteNazionale,
                id = 0;
        Regione regioneNazionale = new Regione(777, "Nazionale", 0, "999999");
        Provincia provinciaNazionale = new Provincia("Provincia", 0, "999999", regioneNazionale);
        Comune comuneNazionale = new Comune("999999", "Comune", 0, new Date(), true, new TipoTerritorio("TipoTerritorio"), provinciaNazionale);

        for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
            if (!anniList.contains(malattieSettimanali.getAnno())) {
                anniList.add(malattieSettimanali.getAnno());
            }
        }

        for (Integer anno : anniList) {
            rInfluenzaNazionale = 0;
            cInfluenzaNazionale = 0;
            cRespiratorieNazionale = 0;
            rPolmoniteNazionale = 0;
            cPolmoniteNazionale = 0;
            rMeningiteNazionale = 0;
            cMeningiteNazionale = 0;
            rEpatiteNazionale = 0;
            cEpatiteNazionale = 0;
            rMorbilloNazionale = 0;
            cMorbilloNazionale = 0;
            rTubercolosiNazionale = 0;
            cTubercolosiNazionale = 0;
            rGastroenteriteNazionale = 0;
            cGastroenteriteNazionale = 0;

            for (MalattieSettimanali malattieSettimanali : malattieSettimanaliList) {
                if (malattieSettimanali.getAnno().equals(anno)) {
                    rInfluenzaNazionale += malattieSettimanali.getRicoveratiInfluenza();
                    cInfluenzaNazionale += malattieSettimanali.getInCuraInfluenza();
                    cRespiratorieNazionale += malattieSettimanali.getComplicanzeRespiratorie();
                    rPolmoniteNazionale += malattieSettimanali.getRicoveratiPolmonite();
                    cPolmoniteNazionale += malattieSettimanali.getInCuraPolmonite();
                    rMeningiteNazionale += malattieSettimanali.getRicoveratiMeningite();
                    cMeningiteNazionale += malattieSettimanali.getInCuraMeningite();
                    rEpatiteNazionale += malattieSettimanali.getRicoveratiEpatite();
                    cEpatiteNazionale += malattieSettimanali.getInCuraEpatite();
                    rMorbilloNazionale += malattieSettimanali.getRicoveratiMorbillo();
                    cMorbilloNazionale += malattieSettimanali.getInCuraMorbillo();
                    rTubercolosiNazionale += malattieSettimanali.getRicoveratiTubercolosi();
                    cTubercolosiNazionale += malattieSettimanali.getInCuraTubercolosi();
                    rGastroenteriteNazionale += malattieSettimanali.getRicoveratiGastroenterite();
                    cGastroenteriteNazionale += malattieSettimanali.getInCuraGastroenterite();

                }
            }
            id++;
            MalattieSettimanali malattieNazione = new MalattieSettimanali(id, anno, 0, rInfluenzaNazionale, cInfluenzaNazionale, cRespiratorieNazionale, rPolmoniteNazionale, cPolmoniteNazionale, rMeningiteNazionale, cMeningiteNazionale, rEpatiteNazionale, cEpatiteNazionale, rMorbilloNazionale, cMorbilloNazionale, rTubercolosiNazionale, cTubercolosiNazionale, rGastroenteriteNazionale, cGastroenteriteNazionale, comuneNazionale);
            malattieSettimanaliTableView.getItems().add(malattieNazione);
        }
    }

    @FXML
    private void analisiDatiMalattieSettimanaliEsportaDatiButtonOnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("malattie");
        fileChooser.setInitialDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            Configuration configuration = new Configuration().configure();
            org.hibernate.Session currentSession = configuration.buildSessionFactory().openSession();
            currentSession.doWork(connection -> {
                CallableStatement cs = connection.prepareCall("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (null,'MALATTIESETTIMANALI',?,';',null,'UTF-8')");
                cs.setString(1, selectedFile.getAbsolutePath());
                cs.execute();
            });
            currentSession.close();
        }
    }

}
