$(document).ready(function() {

    $('#product-order-btn').click(function(event){
        event.preventDefault();
        alert("Button Clicked");
        let productId = $(this).attr("data");
        alert("ProductID =>" + productId);
        let buyerId = $('#buyerId').val();
        alert("BuyId =>" + buyerId);
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
});