package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.PlaceOrder;

public interface OrderService {

	public PlaceOrder save(PlaceOrder order);

	public void delete(Long order_id);

	public PlaceOrder findById(Long order_id);

}
