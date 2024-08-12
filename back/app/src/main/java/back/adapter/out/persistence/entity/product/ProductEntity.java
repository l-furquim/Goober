package back.adapter.out.persistence.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "productID")
    private UUID productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private BigDecimal productPrice;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productImage")
    private String productImages;

    public ProductEntity(){

    }

    public ProductEntity(UUID productId, String productName, BigDecimal productPrice, String productDescription, String productImages) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
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
}

