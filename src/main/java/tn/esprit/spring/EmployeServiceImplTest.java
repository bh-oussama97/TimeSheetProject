package tn.esprit.spring;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;





@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceImplTest {

	
	
	@InjectMocks
    EmployeServiceImpl empservice;
     
    @Mock
    EmployeRepository dao;
    
    @Autowired
    EntrepriseServiceImpl entrepservice;
    EmployeServiceImpl es;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);
	
	
	
	
	@Test
	public void testGetEmployePrenomById() {
		try
		{
			int idE = es.ajouterEmploye(
					new Employe("benhassen", "oussama", "oussama.benhassen@esprit.tn", true, Role.INGENIEUR));
		String prenomEmp = es.getEmployePrenomById(idE);
		l.info(String.format("employe ajouté avec id : %d",idE));
		l.info("Prenom de lemploye est :"+prenomEmp);
		assertThat(prenomEmp).isEqualTo("oussama");
		es.deleteEmployeById(idE);
		}catch (Exception e) {
			l.error("Erreur dans Get EmployePrenom By Id : " +e);
		}
		
	}
	
	
	@Test
	public void testgetAllEmployes() {
		List<Employe> employes = new ArrayList<>();
		Employe premieremploye = new Employe("Khaled", "Kallel", "Khaled.Kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
		Employe deuxiemeemploye = new Employe("Zied", "Saidi", "Zied.saisi@esprit.tn", true, Role.TECHNICIEN);
		
		employes.add(premieremploye);
		employes.add(deuxiemeemploye);
		
		when(dao.findAll()).thenReturn(employes);
		
		//test
		 List<Employe> empList =  empservice.getAllEmployes();
         
	      assertEquals(2, empList.size());
	      
	      verify(dao, times(1)).findAll();
	
	}
	
	@Test
    public void createEmployeeTest()
    {
        Employe emp = new Employe("Zied", "Saidi", "Zied.saisi@ssiiconsulting.tn", true, Role.TECHNICIEN);
         
        empservice.ajouterEmp(emp);
         	
        verify(dao, times(1)).save(emp);
    }
	
	
	
	@Test
	public void testMettreAjourEmailByEmployeId() {
		try
		{	
		String email = "zied.saidi1997@ssiiconsulting.tn";
		int id = empservice.ajouterEmploye(
				new Employe("Zied", "Saidi", "Zied.saidi@ssiiconsulting.tn", true, Role.TECHNICIEN));
	
		empservice.mettreAjourEmailByEmployeId(email, id);

		Employe e = empservice.getEmployerById(id);

		assertThat(e.getEmail()).isEqualTo(email);
		empservice.deleteEmployeById(id);
		}catch (Exception e) {
		l.error("Erreur dans Mettre A jour Email By Employe Id : " +e);
	}
	}
	
	@Test
	public void testDeleteEmployeById()
	{
		
		try
		{	
				int id = empservice.ajouterEmploye(
						new Employe("Bochra", "Bouzid", "bochra.bouzid@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		
				empservice.deleteEmployeById(id);
		
		Employe e = empservice.getEmployerById(id);
		
		assertThat(e).isNull();
		l.info("Employe deleted successfully!");
		}catch (Exception e) {
			l.error("Erreur dans Delete Employe By Id : " +e);
		}
	}
	
	
	@Test
	public void testUpdateEmploye()
	{
	  Employe employe = new Employe(1,"Bochra", "Bouzid", "bochra.bouzid@esprit.tn", true, Role.CHEF_DEPARTEMENT);
	  
	  when(dao.save(employe)).thenReturn(employe);
	  
	  Employe employeExpected = empservice.modifierEmploye(employe);
	  
	  assertThat(employeExpected).isNotNull();
	  
	  verify(dao,times(1)).save(employe);
	}
	
	
	@Test
	public void affecterEmployeADepartement()
	{
		try
		{	
		int idE = empservice.ajouterEmploye(
		new Employe("zied", "saidi", "zied.saidi@ssiiconsulting.tn", true, Role.ADMINISTRATEUR));
		int idD = entrepservice.ajouterDepartement(
				new Departement("RH"));
				
		empservice.affecterEmployeADepartement(idE, idD);
		l.info("Employe avec id=" + idE + " added successfully to Departement avec id=" + idD);
		l.info("desaffectaion, de lemployer de departement");
		empservice.desaffecterEmployeDuDepartement(idE, idD);
		empservice.deleteEmployeById(idE);
		entrepservice.deleteDepartementById(idD);
		}catch (Exception e) {
		l.error("Erreur dans l'affectaion " +e);
	}
		
	}
	
	
	


}
