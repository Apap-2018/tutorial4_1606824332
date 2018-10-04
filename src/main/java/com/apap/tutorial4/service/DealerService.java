package com.apap.tutorial4.service;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;

public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	
	void addDealer(DealerModel dealer);
	
	void deleteDealer(long Id);
	
	void update(DealerModel dealer);
	
	List<DealerModel> viewAll();
}