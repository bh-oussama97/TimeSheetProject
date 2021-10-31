package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;

@RestController
public class RestControlContrat {
	
	@Autowired
	IContratService contratService;

	
	// http://localhost:8081/SpringMVC/servlet/ajouterContrat
	//{"reference":6,"dateDebut":"2020-03-01","salaire":2000,"typeContrat":"CDD"}
	@PostMapping("/ajouterContrat")
	@ResponseBody
	public int addContrat(@RequestBody Contrat contrat,@PathVariable("idEmploye") int idEmploye) {
		contratService.ajouterContrat(contrat);
		return contrat.getReference();
	}
	
	
	@PutMapping("/updateContrat/{id}")
	public void updateContrat(@RequestBody Contrat contrat, @PathVariable(value="id") int contratId) {
		
		contratService.updateContrat(contrat,contratId);
		
	}
	
	
	
	
	

	
	// URL : http://localhost:8081/SpringMVC/servlet/deleteContratById/2
    @DeleteMapping("/deleteContrat/{idcontrat}") 
	@ResponseBody
	public void deleteContrat(@PathVariable("idcontrat")int contratId) {
    	contratService.deleteContrat(contratId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getAllContrats
   	@GetMapping(value = "/getAllContrats")
       @ResponseBody
   	public List<Contrat> getAllContarts() {
   		
   		return contratService.getAllContrat();
   	}
   	
   	
	// http://localhost:8081/SpringMVC/servlet/affecterContratAEmploye/6/1
	   @PutMapping(value = "/affecterContratAEmploye/{idcontrat}/{idemp}") 
		public void affectContratAEmploye(@PathVariable("idcontrat")int contratId, @PathVariable("idemp")int employeId)
		{
		   contratService.affecterContratAEmploye(contratId, employeId);
		}
	   
	
	    
	    
	 // URL : http://localhost:8081/SpringMVC/servlet/getNombreContratJPQL
	    @GetMapping(value = "getNombreContratJPQL")
	    @ResponseBody
		public int getNombreContratJPQL() {
			
			return contratService.getNombreContratJPQL();
		}
	    
	 
}
