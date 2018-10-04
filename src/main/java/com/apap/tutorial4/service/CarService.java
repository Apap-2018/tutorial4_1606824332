package com.apap.tutorial4.service;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial4.model.CarModel;

public interface CarService {
	void addCar(CarModel car);
	
	List<CarModel> findAllByPriceAsc();
	
	void deleteById(long id);
	
	void update(CarModel car);
	
	Optional<CarModel> getCarDetail(long Id);
}
