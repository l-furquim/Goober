package back.adapter.out.persistence.entity.cart;

import back.adapter.out.persistence.entity.product.ProductEntity;
import back.domain.model.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @Column(name = "cart_id")
    private UUID cartId;

    @Column(name = "items_quantity")
    private Integer itemsQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


    @Column(name = "user_id")
    private UUID userId;

    @OneToMany
    @JoinTable(name = "cart_products",
    joinColumns = @JoinColumn(name = "cart_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> cartProducts;

    public CartEntity(){

    }

    public CartEntity(UUID cartId, Integer itemsQuantity, BigDecimal totalPrice, UUID userId, List<ProductEntity> cartProducts) {
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

    public List<ProductEntity> getCartEntityProducts(){
        return this.cartProducts;
    }

    public void addToCartEntity(ProductEntity product){
        this.cartProducts.add(product);
    }

    public UUID getCartId(){
        return this.cartId;
    }


}