package back.domain.model.product;


import back.domain.enums.ProductCategories;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;


public class Product {


    private UUID productId;


    private String productName;


    private BigDecimal productPrice;

    private ProductCategories productCategorie;

    private String productDescription;


    private String productImages;

    public Product(){

    }

    public Product(UUID productId, String productName, BigDecimal productPrice, ProductCategories productCategorie, String productDescription, String productImages) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategorie = productCategorie;
        this.productDescription = productDescription;
        this.productImages = productImages;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductCategories getProductCategorie() {
        return productCategorie;
    }

    public void setProductCategorie(ProductCategories productCategorie) {
        this.productCategorie = productCategorie;
    }
}
