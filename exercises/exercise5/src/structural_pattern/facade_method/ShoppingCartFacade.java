package structural_pattern.facade_method;

//Facade pattern provides a simplified interface to a larger body of code, making it easier to use and understand.
public class ShoppingCartFacade {
    private Order order;
    private Cart cart;

    /*
       creates a new instance of the "Cart" class, adds items to it,
       and creates a new instance of the "Order" class using the
       cart and an address.
     */
   ShoppingCartFacade() {
        Address address1 = new Address("Peter", "Daniel", "Mansion grove", "Santa Clara", "California", "89203");
        Product product1 = new Product("Phone", 39502, 1000);
        Product product2 = new Product("laptop", 65502, 1500);
        CartItem cartItem1 = new CartItem(product1, 2);
        CartItem cartItem2 = new CartItem(product2, 3);
        this.cart = new Cart();
        this.cart.addToCart(cartItem1);
        this.cart.addToCart(cartItem2);
        this.order = new Order(address1, 1220, this.cart);
    }
    public void placeOrder(){
        this.order.checkout();
    }
    public void makePayment(){
        this.order.paymentMethod();
    }
}

