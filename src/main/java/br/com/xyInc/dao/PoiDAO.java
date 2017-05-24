package br.com.xyInc.dao;

import java.util.List;

import br.com.xyInc.model.PoiEntity;

/**
 * 
 * Classe que extende a classe genérica, que contêm os métodos do CRUD, e contêm
 * métodos específicos da classe POI
 * 
 * @author Tha1208
 * 
 * @since 20/05/2017
 *
 */
public class PoiDAO extends GenericDAO<PoiEntity, Integer>{
	
	private static PoiDAO instance;

	public PoiDAO() {
		super(PoiEntity.class);
	}
	
	/**	 
	 * Método que criar uma instancia da classe PoiDAO
	 *	
	 */
	public static PoiDAO getInstance(){
		if(instance == null)
			instance = new PoiDAO();
		return instance;
	}	 


	/**	 
	 * Método que busca os POIs a partir de um quadrante 
	 *	@param Integer minX
	 *  @param Integer maxX
	 *  @param Integer minY
	 *  @param Integer maxY
	 *  @return Retorna a lista de POIs contidos no quadrante
	 */
	@SuppressWarnings("unchecked")
	public List<PoiEntity> recuperarPorDistancia(Integer minX, Integer maxX, Integer minY, Integer maxY){

		StringBuilder sb = new StringBuilder(" SELECT p FROM ").append(PoiEntity.class.getName());
		sb.append(" p  WHERE coordX >= :minX ");
		sb.append(" AND coordX <= :maxX ");
		sb.append(" AND coordY >= :minY ");
		sb.append(" AND coordY <= :maxY ");


		return this.entityManager.createQuery(sb.toString())
				.setParameter("minX", minX)
				.setParameter("maxX", maxX)
				.setParameter("minY", minY)
				.setParameter("maxY", maxY)
				.getResultList();
	}



}
