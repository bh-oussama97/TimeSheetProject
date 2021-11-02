package tn.esprit.spring.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;


@Service
public class ContratServiceImpl implements IContratService {
	
	
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	EmployeRepository employeRepository;

	@Override
	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
		}

	public Contrat updateContrat(Contrat c, int contratId) {
		Contrat contrat = contratRepoistory.findById(contratId).orElse(null);
		
		 if ( contrat != null) {
				
			 contrat.setDateDebut(c.getDateDebut());
			 contrat.setSalaire(c.getSalaire());
			 contrat.setTypeContrat(c.getTypeContrat());
				
		 }
				
				return contratRepoistory.save(contrat);
		
	}
	
	public Contrat modifierContrat(Contrat e)
	{
		
		contratRepoistory.save(e);
		
		return e;
	}
	

	@Override
	public void deleteContrat(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		contratRepoistory.delete(contratManagedEntity);
	}

	
	@Override
	public void affecterContratAEmploye(int contratId, int employeId) {
		
		Optional<Contrat> optionalcontrat = contratRepoistory.findById(contratId);
		
		Optional<Employe> optionalemploye = employeRepository.findById(employeId);
		
		if (optionalcontrat.isPresent() && optionalemploye.isPresent())
		{
			Contrat contratManagedEntity = optionalcontrat.get();
			Employe employeManagedEntity = optionalemploye.get();
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
		}
	}
	
	
	@Override
	public List<Contrat> getAllContrat() {
		return (List<Contrat>) contratRepoistory.findAll();
	}



	


	@Override
	public int getNombreContratJPQL() {
		return contratRepoistory.countCont();
	}



}
