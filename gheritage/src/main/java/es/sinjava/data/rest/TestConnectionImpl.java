package es.sinjava.data.rest;

import java.util.List;

import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sinjava.data.jpa.model.domain.City;
import es.sinjava.data.jpa.model.domain.Hotel;
import es.sinjava.data.manager.model.CityManagerImpl;

@Component
public class TestConnectionImpl implements ITestConnection {

	private static final Logger LOG = LoggerFactory.getLogger(TestConnectionImpl.class);

	@Context
	private MessageContext ctx;

	@Autowired
	private CityManagerImpl cityManager;

	@Override
	public City test(Long x) {
		LOG.trace("Init");
		assert x != null : "Parametros incorrectos";
		LOG.debug("");
		City city = cityManager.getCityById(x);
		LOG.debug(city.getName() + " y el id era " + city.getId());
		for (Hotel hotel : city.getHotels()) {
			hotel.setCity(null);
		}
		return city;
	}

	@Override
	public List<City> listado() {
		LOG.trace("Init");
		List<City> list = cityManager.findAll();
		for (City city : list) {
			city.setHotels(null);
		}
		return list;
	}

}
