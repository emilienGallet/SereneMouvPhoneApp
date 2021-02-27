package zemoov.serenemouv.CMTA;

import java.util.ArrayList;
import java.util.List;

/**
 * Source code taken from IConvoit (https://github.com/emilienGallet/IConvoit). And also is my code ^_^
 * @author Émilien Corresponding to a way from 1 localisation to an other. Each
 *         points are coordinate.
 * Note : Ce fichier va être modifier pour prendre ne considération des spécification propre a notre projet
 */
public class Path {

	private String name;
	private List<Localisation> points;
	public Joules necessaire;//puissanceParSecode
	private Integer trajectTime;
	Boolean carrefourDangereux;
	Boolean travauxSector;
	Integer score;
	Boolean asPeage;
	public Path() {
		
	}
	
	public Path(ArrayList<Localisation> jsonArrayToList, Integer time) {
		this.setPoints(jsonArrayToList);
		this.setTrajectTime(time);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Localisation> getPoints() {
		return points;
	}

	public void setPoints(List<Localisation> points) {
		this.points = points;
	}

	public Integer getTrajectTime() {
		return trajectTime;
	}

	public void setTrajectTime(Integer trajectTime) {
		this.trajectTime = trajectTime;
	}
	public void setNecessaire(Joules necessaire) {
		this.necessaire = necessaire;
	}

	public void setCarrefourDangereux(Boolean carrefourDangereux) {
		this.carrefourDangereux = carrefourDangereux;
	}

	public void setTravauxSector(Boolean travauxSector) {
		this.travauxSector = travauxSector;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
