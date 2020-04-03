package it.polito.tdp.lab04.model;


import java.util.List;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<Corso> getTuttiICorsi(){
		CorsoDAO dao = new CorsoDAO();
		return dao.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiGliStudenti(){
		StudenteDAO dao = new StudenteDAO();
		return dao.getTuttiGliStudenti();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiStudente(Studente studente){
		StudenteDAO dao = new StudenteDAO();
		return dao.getCorsiStudente(studente);
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.iscriviStudenteACorso(studente, corso);
	}
}
