package br.com.xyInc.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.xyInc.dao.PoiDAO;
import br.com.xyInc.model.PoiEntity;

/**
 * 
 * Classe responsável por ser o controlador entre o Resource e a Camada DAO
 * 
 * @author Tha1208
 * 
 * @since 20/05/2017
 *
 */
public class PoiController {

	private PoiDAO poiDao;

	//Construtor
	public PoiController(PoiDAO poiDao){
		this.poiDao = poiDao;		
	}

	public PoiEntity salvar(PoiEntity poi){
		//Se o Nome do POI for null ou vazio, ou a coordenada X for vazia ou negativa, 
		//ou a coordenada Y for vazia ou negativa deve-se retornar null para não salvar
		if(poi.getNomePoi() == null || poi.getNomePoi().isEmpty() ||
				poi.getCoordX() == null || poi.getCoordX() < 0 || 
				poi.getCoordY() == null || poi.getCoordY() < 0){
			return null;
		}

		return this.poiDao.salvar(poi);

	}		

	public List<PoiEntity> listarTodos(){ 

		return this.poiDao.listarTodos();

	}


	/**
	 * Método responsável pela recuperação dos POIs a partir de uma coordenada x e y
	 * dada uma distância máxima
	 * @return List<PoiEntity> listaPontos
	 * */
	public List<PoiEntity> recuperarPorDistancia(Integer coordX, Integer coordY, Integer distMax){ 
		//Se um dos parâmetros for null, ou a distância for menor que zero
		//deve-se retornar null para não continuar
		if(coordX == null || coordY == null ||
				distMax == null || distMax < 0){
			return null;
		}

		Integer minX, maxX, minY, maxY;

		//calcular o quadrante
		minX = coordX - distMax;
		maxX = coordX + distMax;
		minY = coordY - distMax;
		maxY = coordY + distMax;

		List<PoiEntity> listaPontosQuadrante = this.poiDao.recuperarPorDistancia(minX, maxX, minY, maxY);

		List<PoiEntity> resultado = new ArrayList<>();

		for(PoiEntity ponto: listaPontosQuadrante){		
			double distancia = Math.hypot(ponto.getCoordX()-coordX, ponto.getCoordY() - coordY); 		    	
			if(distancia <= distMax){
				resultado.add(ponto);
			}
		}
		return resultado;

	}



}
