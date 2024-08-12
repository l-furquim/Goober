package back.adapter.out.persistence.entity.announcement;

import back.domain.model.product.Product;
import back.domain.model.question.Question;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "announcement")
public class AnnouncementEntity {

    @Id
    @Column(name = "announcementId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announcementId;

    @Column(name = "announcementName")
    private String announcementName;

    @Column(name = "announcementPrice")
    private BigDecimal announcementPrice;

    @Column(name = "announcementLikes")
    private Integer announcementLikes;

    @Column(name = "announcementQuestions")
    private Integer announcementQuestions;

    @Column(name = "announcerName")
    private String announcerName;

    @Column(name = "productImages")
    private String productImages;

    private List<Product> products;

    private List<Question> productQuestions;

    public AnnouncementEntity(){

    }

    public AnnouncementEntity(BigDecimal announcementPrice,String announcementName, Integer announcementLikes, Integer announcementQuestions, String announcerName, String productImages, List<Product> products, List<Question> productQuestions) {
        this.announcementPrice = announcementPrice;
        this.announcementLikes = announcementLikes;
        this.announcementName = announcementName;
        this.announcementQuestions = announcementQuestions;
        this.announcerName = announcerName;
        this.productImages = productImages;
        this.products = products;
        this.productQuestions = productQuestions;
    }

    public Long getAnnouncementEntityId() {
        return announcementId;
    }

    public void setAnnouncementEntityId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public Integer getAnnouncementEntityLikes() {
        return announcementLikes;
    }

    public void setAnnouncementEntityLikes(Integer announcementLikes) {
        this.announcementLikes = announcementLikes;
    }

    public Integer getAnnouncementEntityQuestions() {
        return announcementQuestions;
    }

    public void setAnnouncementEntityQuestions(Integer announcementQuestions) {
        this.announcementQuestions = announcementQuestions;
    }

    public String getProductId() {
        return announcerName;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }
    public List<Question> getProductQuestions(){
        return this.productQuestions;
    }

    public String getAnnouncerName() {
        return announcerName;
    }

    public void setAnnouncerName(String announcerName) {
        this.announcerName = announcerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProducts(Product product){
        this.products.add(product);
    }

    public BigDecimal getAnnouncementEntityPrice() {
        return announcementPrice;
    }

    public void setAnnouncementEntityPrice(BigDecimal announcementPrice) {
        this.announcementPrice = announcementPrice;
    }

    public String getAnnouncerId() {
        return announcerName;
    }

    public void setAnnouncerId(String announcerName) {
        this.announcerName = announcerName;
    }

    public void setProductQuestions(List<Question> productQuestions) {
        this.productQuestions = productQuestions;
    }

    public String getAnnouncementEntityName() {
        return announcementName;
    }

    public void setAnnouncementEntityName(String announcementName) {
        this.announcementName = announcementName;
    }
}