package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;

public interface ContratRepository extends CrudRepository<Contrat, Integer>{
	
	@Query("SELECT count(*) FROM Contrat")
    public int countCont();
	
	@Modifying
    @Transactional
    @Query("DELETE from Contrat")
    public void deleteAllContratJPQL();
    
	  @Query("select c.salaire from Contrat c join c.employe e where e.id=:employeId")
	    public float getSalaireByEmployeIdJPQL(@Param("employeId")int employeId);
	  
	  

	  
} 
