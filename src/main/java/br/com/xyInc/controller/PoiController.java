package br.com.xyInc.controller;

import java.util.List;

import br.com.xyInc.model.PoiEntity;
import br.com.xyInc.service.IPoiService;
import br.com.xyInc.service.PoiService;

/**
 * 
 * Classe responsável por fazer as chamadas dos métodos contendo as validações
 * 
 * @author Tha1208
 * 
 * @since 20/05/2017
 *
 */
public class PoiController {

	private IPoiService poiService;

	public PoiController(PoiService poiService){
		this.poiService = poiService;		
	}

	public PoiEntity salvar(PoiEntity poi){
		//Se o Nome do POI for null ou vazio, ou a coordenada X for vazia ou negativa, 
		//ou a coordenada Y for vazia ou negativa deve-se retornar null para não salvar
		if(poi.getNomePoi() == null || poi.getNomePoi().isEmpty() ||
				poi.getCoordX() == null || poi.getCoordX() < 0 || 
				poi.getCoordY() == null || poi.getCoordY() < 0){
			return null;
		}

		return this.poiService.salvar(poi);

	}		

	public List<PoiEntity> listarTodos(){ 

		return this.poiService.listarTodos();

	}


	public List<PoiEntity> recuperarPorDistancia(Integer coordX, Integer coordY, Integer distMax){ 
		if(coordX == null || coordY == null ||
				distMax == null || distMax < 0){
			return null;
		}

		return this.poiService.recuperarPorDistancia(coordX, coordY, distMax);

	}



}
