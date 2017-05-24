import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import br.com.xyInc.controller.PoiController;
import br.com.xyInc.dao.PoiDAO;
import br.com.xyInc.model.PoiEntity;

@RunWith(MockitoJUnitRunner.class)
public class TesteUnitario {
	
    private PoiDAO poiDaoMock = Mockito.mock(PoiDAO.class);	
	private PoiController poiControllerMock = new PoiController(poiDaoMock);

	@Test
	public void testSalvar() {
		PoiEntity poi = new PoiEntity("Teste", 10, 10);
		Mockito.when(poiDaoMock.salvar(poi)).thenReturn(poi);
		PoiEntity result = poiControllerMock.salvar(poi);
		Assert.assertEquals("Esperado que o nome do objeto seja igual do retorno", poi.getNomePoi(), result.getNomePoi());
		Assert.assertEquals("Esperado que o x do objeto seja igual do retorno", poi.getCoordX(), result.getCoordX());
		Assert.assertEquals("Esperado que o y do objeto seja igual do retorno", poi.getCoordY(), result.getCoordY());
	}
	
	@Test
	public void testNaoSalvar() {		
		//Parametro null
		PoiEntity poi = new PoiEntity("", 10, 10);
		Mockito.when(poiDaoMock.salvar(poi)).thenReturn(poi);
		PoiEntity result = poiControllerMock.salvar(poi);
		Assert.assertEquals("Esperado que retorne null", result, null);
	}
	
	@Test
	public void testNaoSalvarCoordNegativa() {		
		//Coordenada negativa
		PoiEntity poi = new PoiEntity("Local teste", -10, 10);
		Mockito.when(poiDaoMock.salvar(poi)).thenReturn(poi);
		PoiEntity result = poiControllerMock.salvar(poi);
		Assert.assertEquals("Esperado que retorne null", result, null);
	}


	@Test
	public void testListarTodos() {
		List<PoiEntity> listaPoi = new ArrayList<>();
		listaPoi.add(new PoiEntity("LanchoneteTeste",27, 12));
		listaPoi.add(new PoiEntity("PostoTeste",31, 18));
		listaPoi.add(new PoiEntity("JoalheriaTeste", 15, 12));
		Mockito.when(poiDaoMock.listarTodos()).thenReturn(listaPoi);	
		List<PoiEntity> result = poiControllerMock.listarTodos();
		Assert.assertEquals("Esperado que o tamanho da lista retornada seja 3", result.size(), 3);		
	}

	@Test
	public void testRecuperarPorDistancia() {
		List<PoiEntity> listaPoi = new ArrayList<>();
		listaPoi.add(new PoiEntity("LanchoneteTeste",27, 12));
		listaPoi.add(new PoiEntity("PostoTeste",31, 18));
		listaPoi.add(new PoiEntity("JoalheriaTeste", 15, 12));
		
		Mockito.when(poiDaoMock.recuperarPorDistancia(10, 30, 0, 20)).thenReturn(listaPoi);		
		List<PoiEntity> result = poiControllerMock.recuperarPorDistancia(20, 10, 10);
		Assert.assertEquals("Esperado que o tamanho da lista retornada seja 2", result.size(), 2);
		Assert.assertEquals("Esperado  que o  POI fora do quadrante não esteja na lista retornada", result.contains(listaPoi.get(1)), false);
	}
	
	public void testNaoRecuperarPorDistancia() {
		//Nao tem nenhum POI próximo a distância fornecida
		List<PoiEntity> listaPoi = new ArrayList<>();
		listaPoi.add(new PoiEntity("LanchoneteTeste",27, 12));
		listaPoi.add(new PoiEntity("PostoTeste",31, 18));
		listaPoi.add(new PoiEntity("JoalheriaTeste", 15, 12));
		
		Mockito.when(poiDaoMock.recuperarPorDistancia(10, 30, 0, 20)).thenReturn(listaPoi);		
		List<PoiEntity> result = poiControllerMock.recuperarPorDistancia(20, 10, 1);
		Assert.assertEquals("Esperado retorne null", result, null);
	}
		
	
	@Test
	public void testNaoRecuperarPorDistanciaParamErrado() {
		//Caso a distancia for negativa
		List<PoiEntity> listaPoi = new ArrayList<>();
		listaPoi.add(new PoiEntity("LanchoneteTeste",27, 12));
		listaPoi.add(new PoiEntity("PostoTeste",31, 18));
		listaPoi.add(new PoiEntity("JoalheriaTeste", 15, 12));
		
		Mockito.when(poiDaoMock.recuperarPorDistancia(10, 30, 0, 20)).thenReturn(listaPoi);		
		List<PoiEntity> result = poiControllerMock.recuperarPorDistancia(20, 10, -10);
		Assert.assertEquals("Esperado retorne null", result, null);
	}
	
	
}
