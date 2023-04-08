package structural_pattern.facade_method;

public class Order {
    private Address address;
    private int id;
    private Cart cart;

    Order(Address address, int id, Cart cart){
        this.address = address;
        this.id = id;
        this.cart = cart;
    }

    void checkout(){
        System.out.println("Ready for checkout!");
    }

    void paymentMethod(){
        System.out.println("Total cart value is " + cart.getTotalItemsPrice());
        System.out.println("Please select payment method");
    }
}
