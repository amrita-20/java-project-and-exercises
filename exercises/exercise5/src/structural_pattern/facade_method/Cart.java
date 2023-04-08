package structural_pattern.facade_method;

import java.util.ArrayList;

public class Cart {
    ArrayList <CartItem> items = new ArrayList<>();

    void addToCart(CartItem item){
        items.add(item);
    }

    double getTotalItemsPrice(){
        double totalPrice = 0.0;
        for(CartItem item: items){
            totalPrice +=item.getTotalProductPrice();
        }
        return totalPrice;
    }

}
