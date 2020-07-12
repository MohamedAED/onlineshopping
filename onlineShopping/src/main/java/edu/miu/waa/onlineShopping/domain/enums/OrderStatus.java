package edu.miu.waa.onlineShopping.domain.enums;

public enum OrderStatus {
	CANCELED(0),
    SHIPPED(1),
    ON_THE_WAY(2),
    DELIVERED(3),
    ORDERED(4);
    
    
    int status;
    OrderStatus(int s){
    	status = s;
    }
    int getStatus() {
    	return status;
    }
    
}
