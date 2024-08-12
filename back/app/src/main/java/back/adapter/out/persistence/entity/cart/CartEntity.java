package back.adapter.out.persistence.entity.cart;

import back.domain.model.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @Column(name = "cartId")
    private UUID cartId;

    @Column(name = "itemsQuantity")
    private Integer itemsQuantity;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;


    @Column(name = "userId")
    private UUID userId;

    private List<Product> cartProducts;

    public CartEntity(){

    }

    public CartEntity(UUID cartId, Integer itemsQuantity, BigDecimal totalPrice, UUID userId, List<Product> cartProducts) {
        this.cartId = cartId;
        this.itemsQuantity = itemsQuantity;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.cartProducts = cartProducts;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Product> getCartEntityProducts(){
        return this.cartProducts;
    }

    public void addToCartEntity(Product product){
        this.cartProducts.add(product);
    }

    public UUID getCartId(){
        return this.cartId;
    }


}