package back.adapter.out.persistence.entity.product;

import back.adapter.out.persistence.entity.announcement.AnnouncementEntity;
import back.domain.enums.ProductCategories;
import back.domain.model.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_categorie")
    @Enumerated(value = EnumType.STRING)
    private ProductCategories productCategories;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image")
    private String productImages;

    public ProductEntity(){

    }

    public ProductEntity(UUID productId, String productName, BigDecimal productPrice, ProductCategories productCategories, String productDescription, String productImages) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategories = productCategories;
        this.productDescription = productDescription;
        this.productImages = productImages;
    }

    public UUID getProductEntityId() {
        return productId;
    }

    public void setProductEntityId(UUID productId) {
        this.productId = productId;
    }

    public String getProductEntityName() {
        return productName;
    }

    public void setProductEntityName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductEntityPrice() {
        return productPrice;
    }

    public void setProductEntityPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductEntityImages() {
        return productImages;
    }

    public void setProductEntityImages(String productImages) {
        this.productImages = productImages;
    }

    public String getProductEntityDescription() {
        return productDescription;
    }

    public void setProductEntityDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductCategories getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(ProductCategories productCategories) {
        this.productCategories = productCategories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategorie='" + productCategories + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productImages='" + productImages + '\'' +
                '}';
    }

}

