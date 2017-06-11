package br.com.xyInc.service;

import java.util.List;

import br.com.xyInc.model.PoiEntity;

/**
 * 
 * Interface da classe PoiService
 * 
 * @author Tha1208
 * 
 * @since 09/06/2017
 *
 */
public interface IPoiService {
	
	PoiEntity salvar(PoiEntity poi);

	List<PoiEntity> listarTodos();

	List<PoiEntity> recuperarPorDistancia(Integer coordX, Integer coordY, Integer distMax);


}
