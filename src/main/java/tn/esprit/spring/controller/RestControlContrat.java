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
import tn.esprit.spring.services.IContratService;

@RestController
public class RestControlContrat {
	
	@Autowired
	IContratService contratService;

	
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
	
	
	
	
	

	

    @DeleteMapping("/deleteContrat/{idcontrat}") 
	@ResponseBody
	public void deleteContrat(@PathVariable("idcontrat")int contratId) {
    	contratService.deleteContrat(contratId);
	}

   	@GetMapping(value = "/getAllContrats")
       @ResponseBody
   	public List<Contrat> getAllContarts() {
   		
   		return contratService.getAllContrat();
   	}
   	
   	
	
	   @PutMapping(value = "/affecterContratAEmploye/{idcontrat}/{idemp}") 
		public void affectContratAEmploye(@PathVariable("idcontrat")int contratId, @PathVariable("idemp")int employeId)
		{
		   contratService.affecterContratAEmploye(contratId, employeId);
		}
	   
	
	    
	   
	    @GetMapping(value = "getNombreContratJPQL")
	    @ResponseBody
		public int getNombreContratJPQL() {
			
			return contratService.getNombreContratJPQL();
		}
	    
	 
}
