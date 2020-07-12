$(document).ready(function() {
	
	$('.order-btn').click(function(event){

		event.preventDefault();
		var productId = $(this).attr("data");

		$.ajax({
			url: 'http://localhost:8888/rest/shoppingCart/add/' + productId,
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
		let buyerId = $('#buyerId').val();//document.getElementById("buyerId").value(); //$("buyerId");
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

 