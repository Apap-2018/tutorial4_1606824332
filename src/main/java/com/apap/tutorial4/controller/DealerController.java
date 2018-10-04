package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value = "/dealer/view")
	private String viewDealer(@RequestParam(value="dealerId") Long id, Model model) {
		Optional<DealerModel> dealer = dealerService.getDealerDetailById(id);
		List<CarModel> carsInDealer = carService.findAllByPriceAsc();
		System.out.println(carsInDealer);
		dealer.ifPresent( o -> {
			model.addAttribute("dealer", o);
			model.addAttribute("carsInDealer", carsInDealer);
			});
		return "view-dealer.html";
	}
	
	@RequestMapping(value = "/dealer/delete/{dealerId}")
	private String deleteCar(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		System.out.println("masuk");
		dealerService.deleteDealer(dealerId);
		return "delete";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
	private String updateForm(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		System.out.println(dealer.getId());
		model.addAttribute("dealer", dealer);
		return "updatedealer";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
	private String updatedealer(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel dealer) {
		dealer.setId(dealerId);
		dealerService.update(dealer);
		return "add";
	}
	
	@RequestMapping("/view-all")
	private String viewAll(Model model) {
		List<DealerModel> allDealer = dealerService.viewAll();
		model.addAttribute("allDealer", allDealer);
		return "view-all";
	}
}
