package edu.miu.waa.onlineShopping.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.repository.OrderRepository;
import edu.miu.waa.onlineShopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public PlaceOrder save(PlaceOrder order) {
		return orderRepository.save(order);
	}

	@Override
	public void delete(Long order_id) {
		orderRepository.deleteById(order_id);
	}

	@Override
	public PlaceOrder findById(Long order_id) {
		return orderRepository.findById(order_id).get();
	}

}
