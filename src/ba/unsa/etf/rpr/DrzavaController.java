package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox<Grad> choiceGrad;
    private Drzava drzava;
    private ObservableList<Grad> listGradovi;
    public RadioButton tglDrzava = new RadioButton();
    public RadioButton tglRepublika = new RadioButton();
    public RadioButton tglKraljevina = new RadioButton();
    public ToggleGroup tglGrupa = new ToggleGroup();


    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi) {
        this.drzava = drzava;
        listGradovi = FXCollections.observableArrayList(gradovi);
        tglKraljevina.setToggleGroup(tglGrupa);
        tglRepublika.setToggleGroup(tglGrupa);
        tglDrzava.setToggleGroup(tglGrupa);
    }

    @FXML
    public void initialize() {
        choiceGrad.setItems(listGradovi);
        if (drzava != null) {
            fieldNaziv.setText(drzava.getNazivBezDodataka());
            //choiceGrad.getSelectionModel().select(drzava.getGlavniGrad());
            // ovo ne radi jer grad.getDrzava() nije identički jednak objekat kao član listDrzave
            //if (drzava instanceof Drzava) tglDrzava.setSelected(true);
            if (drzava instanceof Republika) tglRepublika.setSelected(true);
            else if (drzava instanceof Kraljevina) tglKraljevina.setSelected(true);
            else tglDrzava.setSelected(true);
            for(int i=0; i < listGradovi.size(); i++)
                if (listGradovi.get(i).getId() == drzava.getGlavniGrad().getId())
                    choiceGrad.getSelectionModel().select(i);
        } else {
            choiceGrad.getSelectionModel().selectFirst();
            tglDrzava.setSelected(true);
        }
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;
        int odlId = 0;
        if(drzava != null) odlId = drzava.getId();
        if (drzava == null) {
            //if (tglDrzava.isSelected()) drzava = new Drzava();
            if (tglRepublika.isSelected()) drzava = new Republika();
            else if (tglKraljevina.isSelected()) drzava = new Kraljevina();
            else drzava = new Drzava();
        }
        drzava.setId(odlId);
        drzava.setNaziv(fieldNaziv.getText());
        drzava.setGlavniGrad(choiceGrad.getSelectionModel().getSelectedItem());
        if (tglDrzava.isSelected()) drzava = new Drzava(drzava.getId(), drzava.getNaziv(), drzava.getGlavniGrad());
        else if (tglRepublika.isSelected()) drzava = new Republika(drzava.getId(), drzava.getNaziv(), drzava.getGlavniGrad());
        else if (tglKraljevina.isSelected()) drzava = new Kraljevina(drzava.getId(), drzava.getNaziv(), drzava.getGlavniGrad());
        closeWindow();
    }

    public void clickCancel(ActionEvent actionEvent) {
        drzava = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
