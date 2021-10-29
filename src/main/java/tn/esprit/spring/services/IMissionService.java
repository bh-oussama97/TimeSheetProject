package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

public interface IMissionService {
	
	public Mission ajouterMission(Mission mission);
	public void deleteMissionById(int entrepriseId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
	public List<Mission> getAllMission();

}