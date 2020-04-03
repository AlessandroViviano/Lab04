package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model = new Model();
	
	private ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;
    
    @FXML
    private CheckBox btnV;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	if(txtMatricola.getText().compareTo("")==0) {
    		txtResult.setText("Devi inserire una matricola!");
    		return ;
    	}
    	
    	int inputMatricola = Integer.parseInt(txtMatricola.getText());
    	
    	
    	Studente sTemp = null;
    	
    	for(Studente s: model.getTuttiGliStudenti()) {
    		if(s.getMatricola()==inputMatricola) {
    			sTemp = s;
    		}
    	}
    	
    	List<Corso> corsi = model.getCorsiStudente(sTemp);
    	String str = "";
    	
    	for(Corso c: corsi) {
    		str += c.getCodins()+"  "+c.getNumeroCrediti()+"  "+c.getNome()+"  "+c.getPeriodoDidattico()+"\n";
    	}
    	
    	if(str.compareTo("")==0)
    		txtResult.setText("Studente non iscritto a nessun corso!");
    	
    	txtResult.setText(str);
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    		Corso cTemp = null;
        	
        	for(Corso c: model.getTuttiICorsi()) {
        		if(c.getNome().compareTo(comboBox.getValue())==0) {
        			cTemp = c;
        		}
        	}
        	
        	List<Studente> studenti = model.getStudentiIscrittiAlCorso(cTemp);
        	String str = "";
        	
        	for(Studente s: studenti) {
        		str += s.getMatricola()+"  "+s.getCognome()+"  "+s.getNome()+"  "+s.getCDS()+"\n";
        	}
        	
        	if(str.compareTo("")==0)
        		txtResult.setText("Corso senza iscritti!");
        	
        	txtResult.setText(str);
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	Corso cTemp = null;
    	Studente sTemp = null;
    	
    	for(Corso c: model.getTuttiICorsi()) {
    		if(c.getNome().compareTo(comboBox.getValue())==0) {
    			cTemp = c;
    		}
    	}
    	
    	for(Studente s: model.getTuttiGliStudenti()) {
    		if(s.getMatricola()==Integer.parseInt(txtMatricola.getText())) {
    			sTemp = s;
    		}
    	}
    	
    	if(model.iscriviStudenteACorso(sTemp, cTemp)==true) {
    		txtResult.setText("Studente iscritto correttamente al corso.");
    	}
    	else {
    		txtResult.setText("Studente già iscritto a questo corso.");
    	}

    }
    
    @FXML
    void doCompletamento(ActionEvent event) {
    	String inputMatricola = txtMatricola.getText();
    		
    	boolean trovato = false;
    	
    	for(Studente s: model.getTuttiGliStudenti()) {
    		if(s.getMatricola()==Integer.parseInt(inputMatricola)) {
    			txtCognome.setText(s.getCognome());
    			txtNome.setText(s.getNome());
    			trovato = true;
    		}
    	}
    	if(trovato == false) {
    		txtResult.setText("Studente non esistente!");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	btnV.setSelected(false);
    }

    @FXML
    void initialize() {
    	assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnV != null : "fx:id=\"btnV\" was not injected: check your FXML file 'Scene.fxml'.";
        
        for(Corso c: model.getTuttiICorsi()) {
        	list.add(c.getNome());
        }
        list.add("");
        comboBox.setItems(list);;
    }
    
    //public void setModel(Model model) {
    	//this.model = model;
    //}
}
