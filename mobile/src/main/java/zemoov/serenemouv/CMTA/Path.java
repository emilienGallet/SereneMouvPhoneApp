package zemoov.serenemouv.CMTA;

import java.util.ArrayList;
import java.util.List;

/**
 * Source code taken from IConvoit (https://github.com/emilienGallet/IConvoit). And also is my code ^_^
 * @author Émilien Corresponding to a way from 1 localisation to an other. Each
 *         points are coordinate.
 *
 */
public class Path {

	private Long id;
	private String name;
	private List<Localisation> points;
	
	private Integer trajectTime;

	public Path() {
		
	}
	
	public Path(ArrayList<Localisation> jsonArrayToList, Integer time) {
		this.setPoints(jsonArrayToList);
		this.setTrajectTime(time);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
