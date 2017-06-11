package br.com.xyInc.service;

import java.util.ArrayList;
import java.util.List;

import br.com.xyInc.dao.PoiDAO;
import br.com.xyInc.model.PoiEntity;

/**
 * 
 * Classe responsável por conter a lógica de negócio
 * 
 * @author Tha1208
 * 
 * @since 09/06/2017
 *
 */
public class PoiService implements IPoiService{

	private PoiDAO poiDao;

	//Construtor
	public PoiService(PoiDAO poiDao){
		this.poiDao = poiDao;		
	}
	
	@Override
	public PoiEntity salvar(PoiEntity poi){
		
		return this.poiDao.salvar(poi);

	}		

	@Override
	public List<PoiEntity> listarTodos(){ 

		return this.poiDao.listarTodos();

	}


	/**
	 * Método responsável pela recuperação dos POIs a partir de uma coordenada x e y
	 * dada uma distância máxima
	 * NESSE MÉTODO FOI BUSCADO APENAS OS PONTOS DENTRO DE UM QUADRANTE DETERMINADO PELA
	 * DISTANCIA MÁXIMA, DESSA FORMA A BUSCA NÃO TRAZ TODOS OS PONTOS DA BASE, PARA NÃO ONERAR
	 * A MEMÓRIA CASO O NÚMERO DE PONTOS FOR MUITO GRANDE.
	 * @return List<PoiEntity> listaPontos
	 * */
	@Override
	public List<PoiEntity> recuperarPorDistancia(Integer coordX, Integer coordY, Integer distMax){ 
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
