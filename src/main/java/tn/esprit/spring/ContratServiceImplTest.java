package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;
import tn.esprit.spring.services.EmployeServiceImpl;




@RunWith(MockitoJUnitRunner.class)
public class ContratServiceImplTest {
	@InjectMocks
	ContratServiceImpl contratService;

	@Mock
	ContratRepository dao;

	
	@Autowired
	EmployeServiceImpl es;
	
	 Date aujourdhui = new Date();
	 String typeContrat= "Mensuel";
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	private static final Logger l = LogManager.getLogger(ContratServiceImplTest.class);
	
	
	@Test
	public void createContratTest() {
		 
		Contrat contrat = new Contrat(aujourdhui,typeContrat, 2.f);

		contratService.ajouterContrat(contrat);

		verify(dao, times(1)).save(contrat);
	}
	

	@Test
	public void testgetAllContrats() {
		List<Contrat> contrats = new ArrayList<>();
		 
		Contrat firstContrat =  new Contrat(aujourdhui,"annuel", 10000.f);
		Contrat secondContrat = new Contrat(aujourdhui,"trimestriel", 12000.f);

		contrats.add(firstContrat);
		contrats.add(secondContrat);

		when(dao.findAll()).thenReturn(contrats);
		List<Contrat> liste=contratService.getAllContrat();
		assertEquals(2, liste.size());

	}
	
	
	@Test
	public void testDeleteContratById() {
		 Date tt = new Date();
		try {
			
			int id = contratService.ajouterContrat(
					new Contrat(tt,"contrat de travail", 2.f));

			contratService.deleteContrat(id);
			l.info("Contrat deleted successfully!");
		} catch (Exception e) {
		
			l.error(String.format("sssss : %s", e.getMessage()));
		}
	}
	
	
	@Test
	public void testUpdateContrat() {
		Contrat contrat = new Contrat(aujourdhui,typeContrat, 2.f);
		when(dao.save(contrat)).thenReturn(contrat);

		Contrat contratModif = contratService.modifierContrat(contrat);

		assertThat(contratModif).isNotNull();

		verify(dao, times(1)).save(contrat);
	}


	
	
	
	@Test
	public void affecterContratAEmployee() {
		try {
			int idE = es.ajouterEmploye(
					new Employe("zied", "test", "zied.saidi@ssiiconsulting.tn", true, Role.ADMINISTRATEUR));
			int idC = contratService.ajouterContrat(new Contrat(aujourdhui,typeContrat, 2.f));

			contratService.affecterContratAEmploye(idC, idE);

			l.log(Level.INFO, "contract avec id= {0} added successfully to Employee avec id= {1}", idC,idE);
			
		} catch (Exception e) {

			l.error(String.format("Erreur dans l'affectation %s",e.getMessage()));
		
		}

	}
	
}
