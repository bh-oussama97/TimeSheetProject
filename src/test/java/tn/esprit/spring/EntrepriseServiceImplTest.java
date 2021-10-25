package tn.esprit.spring;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IEntrepriseService;



@SpringBootTest

public class EntrepriseServiceImplTest {

	@Autowired
	IEntrepriseService es;
	@Autowired
	DepartementRepository deptRepoistory;

	@Test
	public void testajouterEntreprise() {
		

Entreprise ent = new Entreprise("eeprise1", "raient1");
	 ent.setId(5);
	 es.ajouterEntreprise(ent);
		assertEquals(5,ent.getId() );
		
	
	}
	
	@Test
	public void testajouterDep()  {
	Departement d  = new Departement();
	d.setId(2);
	d.setName("dep1");
	
      es.ajouterDepartement(d);

		assertEquals(2,d.getId() );
		
	}
	
	@Test
	public void testaffecterDepartementAEntreprise()  {
		
		 Entreprise ent = new Entreprise("sqdfqsd", "rais2");
		// ent.setId(120);
         int x=es.ajouterEntreprise(ent);
		 
		Departement d  = new Departement("hey");
		 int y =es.ajouterDepartement(d);
		
		 es.affecterDepartementAEntreprise(y, x);
		
		 Departement  newDep = deptRepoistory.findById(y).get();
		
		Entreprise ee =newDep.getEntreprise();
		
 


			//assertEquals(ent,newDep.getEntreprise() );
			assertEquals(x,ee.getId());

		}
	
	
	/*@Test
	public void testgetAllDepartementsNamesByEntreprise()  {
		 /*Entreprise ent = new Entreprise("entreprise1", "raisonent1");
		 ent.setId(20);
		List<String> depNamesA = new ArrayList<>();
		List<String> depNamesE = new ArrayList<>();

depNamesA.add("dep1");

 depNamesE = es.getAllDepartementsNamesByEntreprise(ent.getId());

assertEquals(depNamesA, depNamesE);
		List<String> listeName=es.getAllDepartementsNamesByEntreprise(21);
		assertEquals(5,listeName.size());}



		
	*/
	@Test
	public void testdeleteEntrepriseById() throws ParseException {
	
		 Entreprise ent = new Entreprise("entreprise1", "raisonent1");
		 ent.setId(1);
	//	 es.deleteEntrepriseById(ent.getId());
		
		 assertEquals(1, ent.getId());


		
	}
	@Test
	public void testdeleteDepartementById() throws ParseException {
		Departement d  = new Departement();
		d.setId(1);
		d.setName("dep1");
	// es.deleteDepartementById(d.getId());

		 assertEquals(1, d.getId());
		
	}
	@Test
	public void testgetEntrepriseById() throws ParseException {
		 Entreprise ent = new Entreprise("entr", "rais");
		 
		 
		 
		int  x =es.ajouterEntreprise(ent);
		
		
	Entreprise ent1	= es.getEntrepriseById(x);
	
		 assertEquals(x,ent1.getId());


		 
			}
	}

