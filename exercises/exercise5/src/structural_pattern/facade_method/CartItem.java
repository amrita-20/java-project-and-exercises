package structural_pattern.facade_method;

public class CartItem {
    private Product product;
    int quantity;

    CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    double getTotalProductPrice(){
        double totalPrice = this.quantity * product.getPrice();
        return totalPrice;
    }
}
