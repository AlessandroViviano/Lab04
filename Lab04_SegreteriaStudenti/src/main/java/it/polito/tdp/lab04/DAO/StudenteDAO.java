package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti(){
		
		final String sql = "SELECT * FROM studente";
		
		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				System.out.println(matricola+" "+cognome+" "+nome+" "+CDS);
				
				Studente s = new Studente(matricola, cognome, nome, CDS);
				studenti.add(s);
			}
			
			conn.close();
			return studenti;
		}catch(SQLException e) {
			
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getCorsiStudente(Studente studente){
		
		final String sql = "SELECT corso.codins, corso.crediti, corso.nome, corso.pd FROM corso, iscrizione WHERE corso.codins = iscrizione.codins AND matricola = ?";
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				Corso c = new Corso(codins, crediti, nome, pd);
				corsi.add(c);
			}
			conn.close();
			return corsi;
		}catch(SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
	}

}
