package es.sinjava.data.manager.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sinjava.data.jpa.model.domain.City;
import es.sinjava.data.jpa.model.domain.Hotel;
import es.sinjava.data.repository.model.CityRepository;

@Component
public class CityManagerImpl {

	@Autowired
	CityRepository cityRepository;

	public City getCityById(Long idCity) {
		City city = cityRepository.findOne(idCity);
		for (Hotel hotel : city.getHotels()) {
			hotel.setCity(null);
		}
		return city;
	}

	public Iterable<City> findAll() {
		return cityRepository.findAll();
	}

}
