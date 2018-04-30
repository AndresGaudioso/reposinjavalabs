package es.sinjava.data.manager.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.sinjava.data.jpa.model.domain.City;
import es.sinjava.data.repository.model.CityRepository;

@Component
@Transactional
public class CityManagerImpl {

	@Autowired
	CityRepository cityRepository;

	public City getCityById(Long idCity) {
		City city = cityRepository.findOne(idCity);
		return city;
	}

	public List<City> findAll() {
		List<City> retorno = cityRepository.findAll();

		return retorno;
	}

}
