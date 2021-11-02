package tn.esprit.spring.services;


import java.util.List;

import tn.esprit.spring.entities.Contrat;


public interface IContratService {
	
	public int ajouterContrat(Contrat contrat);
	public Contrat modifierContrat(Contrat e);
	public Contrat updateContrat(Contrat c, int contratId);
	public void deleteContrat(int contratId);
	public List<Contrat> getAllContrat();
	public void affecterContratAEmploye(int contratId, int employeId);
	public int getNombreContratJPQL();
	

	
	
	
	

	
}
