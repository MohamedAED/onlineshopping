$(document).ready(function() {
	
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
			url: 'http://localhost:8888/rest/shoppingCart/delete/'+ productId + "?buyerId=" + buyerId,
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				location.reload(true);
			},
			error: function(){						
				alert('Error while request..');
			} 
		});
	});
	
	$('.placeOrder-print-btn').click(function(event){
		event.preventDefault();
		var orderId = $(this).attr("data");
		$.ajax({
			url: 'http://localhost:8888/order/generateInvoice/'+ orderId,
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				location.reload(true);
			},
			error: function(){						
				alert('Error while request..');
			} 
		});
	});
	
	$('.product-review-btn').click(function(event){
		event.preventDefault();
		var productId = $(this).attr("data");
		$.ajax({
			url: 'http://localhost:8888/order/writeReview/'+ productId,
			type: 'PUT',
			dataType: "json",
			success: function (response) {
				alert("Product Review Successfully submitted!");
			},
			error: function(){						
				alert('Error while request..');
			} 
		});
	});
});

 