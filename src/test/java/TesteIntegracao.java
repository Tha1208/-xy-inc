import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.Assert;
import javax.ws.rs.core.Response;

import br.com.xyInc.model.PoiEntity;
import br.com.xyInc.resource.PoiResource;

public class TesteIntegracao extends JerseyTest {

	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(PoiResource.class);
	}
	

	@Test
	public void testCadastrar() {
		PoiEntity poi = new PoiEntity("Nome Local", 10, 2);
		Response output = target("/service/cadastrar").request().post(Entity.json(poi));
		Assert.assertEquals("Esperado que o Status seja OK ", Response.Status.OK.getStatusCode(), output.getStatus());
		Assert.assertEquals("Esperado que a mensagem seja: Registro cadastrado com sucesso!", "Registro cadastrado com sucesso!", output.readEntity(String.class));
	}
	
	@Test
	public void testNaoCadastrar() {
		PoiEntity poi = new PoiEntity(null, 10, 2);
		Response output = target("/service/cadastrar").request().post(Entity.json(poi));
		Assert.assertEquals("Deve retornar erro de atributo faltante", "Falta algum atributo do registro", output.readEntity(String.class));
	}
	
	
	@Test
	public void testListarTodos() {
		Response output = target("/service/listarTodos").request().get();
		Assert.assertEquals("Esperado que o Status seja OK", Response.Status.OK.getStatusCode(), output.getStatus());
		Assert.assertNotNull("Esperado que retorne uma lista", output.getEntity());
	}

	@Test
	public void testRecuperarDistancia() {
		Response output = target("/service/buscarPerto")
				.queryParam("coordX", 20)
				.queryParam("coordY", 10)
				.queryParam("distMax", 10).request().get();
		Assert.assertEquals("Esperado que o Status seja OK", Response.Status.OK.getStatusCode(), output.getStatus());
		Assert.assertNotNull("Esperado que retorne uma lista", output.getEntity());
	}
	
	@Test
	public void testNaoRecuperarDistancia() {
		//Parametros não esperado, retorna null
		Response output = target("/service/buscarPerto")
				.queryParam("coordX", 10)
				.queryParam("coordY", 10)
				.queryParam("distMax", -10).request().get();
		Assert.assertEquals("Esperado que retorne null ", null, output.readEntity(List.class));
	}

	

}
