package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceTest {

	@Autowired
	IEntrepriseService serviceentreprise;
	@Autowired
	EntrepriseRepository entrepriserepo;
	@Autowired
	DepartementRepository departementrepo;

    Entreprise entre = new Entreprise();
    
    @Test
    public void ajouterEntreprise() {
    	Entreprise entreprise = new Entreprise("Esprit","Tunisie");
    	System.out.println("******* id avant enregstrement dans la BD: "+entreprise.getId());
    	int id=serviceentreprise.ajouterEntreprise(entreprise);
    	assertNotEquals(0, entreprise.getId());
    
	}
  @Test
 public void ajouterDepartement() {
	Departement departement= new Departement();
	departement.setName("informatique");
	int id=serviceentreprise.ajouterDepartement(departement);
	assertNotEquals(0, departement.getId());
	
	
  }
  
  @Test
  public void affecterDepartementAEntreprise() {
	  Entreprise entreprise = new Entreprise("Esprit","Tunisie");
  int id_entreprise=serviceentreprise.ajouterEntreprise(entreprise);
	  
	  Departement departement= new Departement();
		departement.setName("informatique");
		int id_dep=serviceentreprise.ajouterDepartement(departement);
		
		
		serviceentreprise.affecterDepartementAEntreprise(id_dep,id_entreprise);
		departement.getEntreprise();
		
		Departement dp_affecter=departementrepo.findById(departement.getId()).get();
		
		assertEquals(id_entreprise, dp_affecter.getEntreprise().getId());
		
	  
  }
	
  @Test
  public void getAllDepartementsNamesByEntreprise(){
	  
	  Entreprise entreprise=new Entreprise("indeed","france");
	  int id_entreprise=serviceentreprise.ajouterEntreprise(entreprise);
	  
	  Departement departement= new Departement("gestion");
	  Departement departement2= new Departement("Rh");
	  Departement departement3= new Departement("com");
	  int id_dep=serviceentreprise.ajouterDepartement(departement);
	  int id_dep2=serviceentreprise.ajouterDepartement(departement2);
	  int id_dep3=serviceentreprise.ajouterDepartement(departement3);
	  
	  serviceentreprise.affecterDepartementAEntreprise(id_dep,id_entreprise);
	  serviceentreprise.affecterDepartementAEntreprise(id_dep2,id_entreprise);
	  serviceentreprise.affecterDepartementAEntreprise(id_dep3,id_entreprise);
	  
	 List<String>listedepartname=  serviceentreprise.getAllDepartementsNamesByEntreprise(id_entreprise);
	  assertEquals(3, listedepartname.size());
	  
	  
  }
  

	
	@Test
	public void deleteEntrepriseById(){

			Entreprise entreprise = new Entreprise("Esprit","Tunisie");
			entrepriserepo.save(entreprise);
			entrepriserepo.deleteById(entreprise.getId());
			Optional optional = entrepriserepo.findById(entreprise.getId());
			assertEquals(Optional.empty(), optional);
	}
		@Test
		public void deleteDepartementById(){

			Departement dep = new Departement("Informatique");
			departementrepo.save(dep);
			departementrepo.deleteById(dep.getId());
			Optional optional = departementrepo.findById(dep.getId());
			assertEquals(Optional.empty(), optional);
		}

		@Test
		public void getEntrepriseById() {
			Entreprise entreprise1 = new Entreprise("Esprit","Tunisie");
			Entreprise entreprise2 = entrepriserepo.save(entreprise1);
		     Optional<Entreprise> optional =  entrepriserepo.findById(entreprise2.getId());
		     assertEquals(entreprise2.getId(), optional.get().getId());
		     assertEquals(entreprise2.getName(), optional.get().getName());
		}
	}
