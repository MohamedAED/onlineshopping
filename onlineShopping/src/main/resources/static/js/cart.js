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
				location.reload(true);
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
	
});

 