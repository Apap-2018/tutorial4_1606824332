package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value = "/car/delete/{carId}")
	private String deleteCar(@PathVariable(value = "carId") Long carId, Model model) {
		System.out.println("masuk");
		carService.deleteById(carId);
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET)
	private String updateForm(@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = carService.getCarDetail(carId).get();
		System.out.println(car.getId());
		model.addAttribute("car", car);
		return "updateCar";
	}
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
	private String updateCar(@PathVariable(value = "carId") Long carId, @ModelAttribute CarModel car) {
		car.setId(carId);
		carService.update(car);
		return "add";
	}
}
