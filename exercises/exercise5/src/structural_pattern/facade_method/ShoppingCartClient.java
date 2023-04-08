package structural_pattern.facade_method;

/*
  Created a client class that demonstrates the use of the Facade design pattern
  by creating an instance of the "ShoppingCartFacade" class and calling its methods.
 */

public class ShoppingCartClient {
    public static void main(String[] args){
        // Create an instance of the "ShoppingCartFacade" class.
        ShoppingCartFacade shoppingCartFacade = new ShoppingCartFacade();
        shoppingCartFacade.placeOrder();
        shoppingCartFacade.makePayment();
    }
}
