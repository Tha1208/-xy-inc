package br.com.xyInc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Classe que contêm os atributos dos Ponto de interesse (POI)
 * 
 * @author Tha1208
 * 
 * @since 20/05/2017
 *
 */
@Entity
@Table(name="poi")
@XmlRootElement
public class PoiEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="nome_poi")
	private String nomePoi;
	
	@Column(name="coord_x")
	private Integer coordX;
	
	@Column(name="coord_y")
	private Integer coordY;
	
	public PoiEntity() {		
	}
	
	public PoiEntity(String nomePoi, Integer coordX, Integer coordY) {
		this.nomePoi = nomePoi;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomePoi() {
		return nomePoi;
	}

	public void setNomePoi(String nomePoi) {
		this.nomePoi = nomePoi;
	}

	public Integer getCoordX() {
		return coordX;
	}

	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}

	public Integer getCoordY() {
		return coordY;
	}

	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}	
 
}