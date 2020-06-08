package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.importData.EtlDecessi;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DecessiAnnualiController implements Initializable {

    @FXML
    private Label noDataSelectedLabel;

    @FXML
    private Tab decessiAnnualiVisualizzazioneTab;

    @FXML
    private Tab decessiAnnualiInserimentoTab;

    @FXML
    private Tab decessiAnnualiModificaTab;

    @FXML
    private Button decessiAnnualiVisualizzazioneModificaButton;

    @FXML
    private TabPane decessiAnnualiTabPane;

    @FXML
    private ListView<String> idDecessiAnnualiListView;

    @FXML
    private TextField annoInserimentoTextField;

    @FXML
    private TextField incidentiStradaliInserimentoTextField;

    @FXML
    private TextField malattieTumoraliInserimentoTextField;

    @FXML
    private TextField malattieCardiovascolariInserimentoTextField;

    @FXML
    private TextField malattieContagioseInserimentoTextField;

    @FXML
    private ComboBox<String> provinciaInserimentoComboBox;

    @FXML
    private TextField annoModificaTextField;

    @FXML
    private TextField incidentiStradaliModificaTextField;

    @FXML
    private TextField malattieTumoraliModificaTextField;

    @FXML
    private TextField malattieCardiovascolariModificaTextField;

    @FXML
    private TextField malattieContagioseModificaTextField;

    @FXML
    private ComboBox<String> provinciaModificaComboBox;

    private int selectedId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decessiAnnualiModificaTab.setDisable(true);

        ProvinciaService provinciaService = new ProvinciaService();
        List<Provincia> provinciaList = provinciaService.findAll();
        for (Provincia p : provinciaList) {
            provinciaInserimentoComboBox.getItems().add(p.getNome());
            provinciaModificaComboBox.getItems().add(p.getNome());
        }
        updateList();
        noDataSelectedLabel.setVisible(false);
        idDecessiAnnualiListView.getSelectionModel().select(0);


    }

    private void updateList(){
        idDecessiAnnualiListView.getItems().clear();
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        List<DecessiAnnuali> decessiAnnualiList = decessiAnnualiService.findAll();
        idDecessiAnnualiListView.getItems().add("ID|ANNO|INCIDENTI STRADALI|MALATTIE TUMORALI|MALATTIE CARDIOVSCOLARI|MALATTIE CONTAGIOSE|PROVINCIA");
        for (DecessiAnnuali d : decessiAnnualiList) {
            idDecessiAnnualiListView.getItems().add(d.getId()+"|"+d.getAnno()+"|"+
                    d.getIncidentiStradali()+"|"+d.getMalattieTumorali()+"|"+d.getMalattieCardiovascolari()+"|"+
                    d.getMalattieContagiose()+"|"+d.getProvincia().getNome());
        }
        noDataSelectedLabel.setVisible(false);
    }

    @FXML
    private void homepageButtonOnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
        ((Button) event.getSource()).getScene().setRoot(root);
    }


    @FXML
    private void inserisciInserimentoButtonOnClicked() {
        ProvinciaService provinciaService = new ProvinciaService();
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(Integer.parseInt(annoInserimentoTextField.getText()),
                Integer.parseInt(incidentiStradaliInserimentoTextField.getText()),
                Integer.parseInt(malattieTumoraliInserimentoTextField.getText()),
                Integer.parseInt(malattieCardiovascolariInserimentoTextField.getText()),
                Integer.parseInt(malattieContagioseInserimentoTextField.getText()),
                provinciaService.findByNome(provinciaInserimentoComboBox.getValue())
        );
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        decessiAnnualiService.save(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            System.out.println("ok");
            annoInserimentoTextField.clear();
            incidentiStradaliInserimentoTextField.clear();
            malattieTumoraliInserimentoTextField.clear();
            malattieCardiovascolariInserimentoTextField.clear();
            malattieContagioseInserimentoTextField.clear();
        }
        decessiAnnualiTabPane.getSelectionModel().select(0);
        idDecessiAnnualiListView.getSelectionModel().select(0);
        updateList();
    }

    @FXML
    private void inserisciCsvInserimentoButtonOnClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        EtlDecessi etlDecessi = new EtlDecessi();
        if (selectedFile != null) {
            System.out.println("ok");
            etlDecessi.load(selectedFile.getPath());
            decessiAnnualiTabPane.getSelectionModel().select(0);
            idDecessiAnnualiListView.getSelectionModel().select(0);
            updateList();
        } else {
            System.out.println("non ho trovato il file");
        }

    }

    @FXML
    private void modificaModificaButtonOnClicked() {
        ProvinciaService provinciaService = new ProvinciaService();
        DecessiAnnuali decessiAnnuali = new DecessiAnnuali(
                selectedId,
                Integer.parseInt(annoModificaTextField.getText()),
                Integer.parseInt(incidentiStradaliModificaTextField.getText()),
                Integer.parseInt(malattieTumoraliModificaTextField.getText()),
                Integer.parseInt(malattieCardiovascolariModificaTextField.getText()),
                Integer.parseInt(malattieContagioseModificaTextField.getText()),
                provinciaService.findByNome(provinciaModificaComboBox.getValue())
        );
        DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
        decessiAnnualiService.update(decessiAnnuali);
        if (decessiAnnualiService.findById(decessiAnnuali.getId()) != null) {
            annoModificaTextField.clear();
            incidentiStradaliModificaTextField.clear();
            malattieTumoraliModificaTextField.clear();
            malattieCardiovascolariModificaTextField.clear();
            malattieContagioseModificaTextField.clear();
        }
        decessiAnnualiTabPane.getSelectionModel().select(0);
        idDecessiAnnualiListView.getSelectionModel().select(0);
        decessiAnnualiModificaTab.setDisable(true);
        decessiAnnualiVisualizzazioneTab.setDisable(false);
        decessiAnnualiInserimentoTab.setDisable(false);
        updateList();
    }

    @FXML
    private void decessiAnnualiCancellaButtonOnClicked(){
        if(idDecessiAnnualiListView.getSelectionModel().getSelectedIndex()!=0) {
            String decessi = idDecessiAnnualiListView.getSelectionModel().getSelectedItem();
            String[] decessiEntry;
            decessiEntry = decessi.split("\\|");
            selectedId = Integer.parseInt(decessiEntry[0]);
            DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
            decessiAnnualiService.deleteById(selectedId);
            updateList();
        }
        else{
            noDataSelectedLabel.setVisible(true);
            errorAnimation();

        }
    }

    @FXML
    private void DecessiAnnualiVisualizzazioneModificaButtonOnClicked() {
        if(idDecessiAnnualiListView.getSelectionModel().getSelectedIndex()!=0) {
            decessiAnnualiModificaTab.setDisable(false);
            decessiAnnualiVisualizzazioneTab.setDisable(true);
            decessiAnnualiInserimentoTab.setDisable(true);
            String decessi = idDecessiAnnualiListView.getSelectionModel().getSelectedItem();
            String[] decessiEntry;
            decessiEntry = decessi.split("\\|");
            decessiAnnualiTabPane.getSelectionModel().select(2);
            selectedId = Integer.parseInt(decessiEntry[0]);
            annoModificaTextField.setText(decessiEntry[1]);
            incidentiStradaliModificaTextField.setText(decessiEntry[2]);
            malattieTumoraliModificaTextField.setText(decessiEntry[3]);
            malattieCardiovascolariModificaTextField.setText(decessiEntry[4]);
            malattieContagioseModificaTextField.setText(decessiEntry[5]);
            provinciaModificaComboBox.getSelectionModel().select(decessiEntry[6]);
        }
        else{
            noDataSelectedLabel.setVisible(true);
            errorAnimation();
        }


    }


    private void errorAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), noDataSelectedLabel);
        st.setByX(0.2);
        st.setByY(0.2);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
        //noDataSelectedLabel.setVisible(false);
    }

}
