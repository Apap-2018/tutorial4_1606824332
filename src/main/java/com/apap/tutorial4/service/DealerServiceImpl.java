package com.apap.tutorial4.service;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}

	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}

	@Override
	public void deleteDealer(long id) {
		dealerDb.deleteById(id);
	}

	@Override
	public void update(DealerModel dealer) {
		DealerModel now = dealerDb.getOne(dealer.getId());
		now.setAlamat(dealer.getAlamat());
		now.setNoTelp(dealer.getNoTelp());
		dealerDb.save(now);
	}

	@Override
	public List<DealerModel> viewAll() {
		return dealerDb.findAll();
	}
	
	
}
