$(document).ready(function() {
	
	$('.product-add-btn').click(function(event){
		event.preventDefault();
		var productId = $(this).attr("data");
		let buyerId = $('#buyerId').val();
		$.ajax({
			url: 'http://localhost:8888/rest/shoppingCart/add/' + productId + "?buyerId=" + buyerId,
			type: 'PUT',
			dataType: "json",
			success: function(response){
				location.reload(true);
			},
			error: function(){						
				alert('Error while request..');
			}
		});
		
	});
	
	$('.product-order-btn').click(function(event){
		event.preventDefault();
		var productId = $(this).attr("data");
		let buyerId = $('#buyerId').val();
		$.ajax({
			url: 'http://localhost:8888/rest/shoppingCart/add/' + productId + "?buyerId=" + buyerId,
			type: 'PUT',
			dataType: "json",
			success: function(response){
				alert("Product Successfully added to the Shopping Cart!");
			},
			error: function(){						
				alert('Error while request..');
			}
		});
		
	});
	
	$('.product-remove-btn').click(function(event){
		event.preventDefault();
		var productId = $(this).attr("data");
		let buyerId = $('#buyerId').val();
		$.ajax({
			url: 'http://localhost:8888/rest/shoppingCart/delete/' + productId + "?buyerId=" + buyerId,
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				location.reload(true);
			},
			error: function(){						
				location.reload(true);
			} 
		});
	});
	
	$('.placeOrder-print-btn').click(function(event){
		event.preventDefault();
		var orderId = $(this).attr("data");
		let buyerId = $('#buyerId').val();
		$.ajax({
			url: 'http://localhost:8888/order/generateInvoice/' + orderId + "?buyerId=" + buyerId,
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				alert("Order Invoice Successfully downloaded!");
			},
			error: function(){						
				alert('Error while request..');
			} 
		});
	});
	
	$('.product-review-btn').click(function(event){
		event.preventDefault();
		var productId = $(this).attr("data");
		let buyerId = $('#buyerId').val();
		$.ajax({
			url: 'http://localhost:8888/order/writeReview/' + productId + "?buyerId=" + buyerId + "&productReview=" + $("#textReview" + productId).val(),
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				alert("Product Review Successfully submitted!");
				location.reload(true);
			},
			error: function(){						
				location.reload(true);
			} 
		});
	});
	
	$('.placeOrder-cancel-btn').click(function(event){
		event.preventDefault();
		var orderId = $(this).attr("data");
		let buyerId = $('#buyerId').val();
		$.ajax({
			url: 'http://localhost:8888/order/cancelOrder/' + orderId + "?buyerId=" + buyerId,
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				location.reload(true);
			},
			error: function(){						
				location.reload(true);
			} 
		});
	});
});

 