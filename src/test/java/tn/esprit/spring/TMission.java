package tn.esprit.spring;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.MissionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TMission {

	@Autowired
	MissionServiceImpl es; 
	@Autowired
	MissionRepository missionRepository; 
	@Autowired
	EmployeRepository  emprepo; 
	
	
	private static final Logger l = LogManager.getLogger(TMission.class);
	
	
	
	
	
	

	@Test
	public void modifierMission() {
           Mission mission = es.ajouterMission(new Mission("mission_modifier","description_modifier"));
		mission.setDescription("missionmodidifer_test");
	Mission modifier =	missionRepository.save(mission);
		l.info("Mission_description: " + modifier.getDescription());
		assertEquals("missionmodidifer_test",modifier.getDescription());
		missionRepository.delete(mission);
		
	}
	
	
	@Test
	public void testCreateMission() {
	
		  Mission m = es.ajouterMission(new Mission("mission2","description1"));
		 assertThat(m.getId()).isGreaterThan(0);
			missionRepository.delete(m);
		
	
	}

	@Test
	public void verifNameMission() {
		  Mission mission = es.ajouterMission(new Mission("mission1","description1"));
	
		
		missionRepository.delete(mission);

	}
	

	
	
	@Test
	public void verifTaille() {
		List<Mission> missions = es.getAllMission();
		assertTrue(missions.size() == 0);
	}
	

	
	@Test
	public void supprimerMission() {
		  Mission mission = es.ajouterMission(new Mission("mission3","description1"));
	

		missionRepository.delete(mission);
		
		
		assertEquals(null,missionRepository.findById(mission.getId()).orElse(null));
	
		
	}

	}