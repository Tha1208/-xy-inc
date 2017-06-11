package br.com.xyInc.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.xyInc.controller.PoiController;
import br.com.xyInc.dao.PoiDAO;
import br.com.xyInc.model.PoiEntity;
import br.com.xyInc.service.PoiService;


/**
 * Classe que contêm os métodos REST de acesso ao webservice
 * 
 * @author Tha1208
 * 
 * @since 20/05/2017
 * 
 * @Path - Caminho para a chamada da classe que vai representar o serviço
 * */
@Path("/service")
public class PoiResource {

	/**
	 * Método para cadastrar um novo POI
	 * 
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/plain")
	@Path("/cadastrar")
	public String cadastrar(PoiEntity poi){
		try {			

			PoiEntity result = new PoiController(new PoiService(PoiDAO.getInstance())).salvar(poi);
			if(result == null){

				return "Falta algum atributo do registro";	

			}
			return "Registro cadastrado com sucesso!";

		} catch (Exception e) {

			return "Erro ao cadastrar o registro " + e.getStackTrace();
		}

	}


	/**
	 * Método para listar todos os POIs do banco
	 * 
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listarTodos")
	public List<PoiEntity> listarTodos(){	 

		return new PoiController(new PoiService(PoiDAO.getInstance())).listarTodos();

	}


	/**
	 * Método para buscar POIs a partir de uma coordenada x e y e uma distância
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/buscarPerto")	
	public List<PoiEntity> RecuperarDistancia(@QueryParam("coordX") Integer coordX, @QueryParam("coordY") Integer coordY, @QueryParam("distMax") Integer distMax){

		return new PoiController(new PoiService(PoiDAO.getInstance())).recuperarPorDistancia(coordX, coordY, distMax);

	}


}
