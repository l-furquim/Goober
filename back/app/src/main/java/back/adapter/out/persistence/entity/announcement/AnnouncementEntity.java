package back.adapter.out.persistence.entity.announcement;

import back.adapter.out.persistence.entity.product.ProductEntity;
import back.adapter.out.persistence.entity.question.QuestionEntity;
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
    @Column(name = "announcement_id")
    private UUID announcementId;

    @Column(name = "announcement_name")
    private String announcementName;

    @Column(name = "announcement_price")
    private BigDecimal announcementPrice;

    @Column(name = "announcement_likes")
    private Integer announcementLikes;

    @Column(name = "announcement_questions")
    private Integer announcementQuestions;

    @Column(name = "announcer_name")
    private String announcerName;

    @Column(name = "product_images")
    private String productImages;

    @OneToMany
    @JoinTable(
            name = "announcement_products",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products;

    @OneToMany
    @JoinTable(
            name = "announcement_questions",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<QuestionEntity> productQuestions;

    public AnnouncementEntity(){

    }

    public AnnouncementEntity(BigDecimal announcementPrice,String announcementName, Integer announcementLikes, Integer announcementQuestions, String announcerName, String productImages, List<ProductEntity> products) {
        this.announcementPrice = announcementPrice;
        this.announcementLikes = announcementLikes;
        this.announcementName = announcementName;
        this.announcementQuestions = announcementQuestions;
        this.announcerName = announcerName;
        this.productImages = productImages;
        this.products = products;
    }

    public UUID getAnnouncementEntityId() {
        return announcementId;
    }

    public void setAnnouncementEntityId(UUID announcementId) {
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
    public List<QuestionEntity> getProductQuestions(){
        return this.productQuestions;
    }

    public String getAnnouncerName() {
        return announcerName;
    }

    public void setAnnouncerName(String announcerName) {
        this.announcerName = announcerName;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void addProducts(ProductEntity product){
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

    public void setProductQuestions(List<QuestionEntity> productQuestions) {
        this.productQuestions = productQuestions;
    }

    public String getAnnouncementEntityName() {
        return announcementName;
    }

    public void setAnnouncementEntityName(String announcementName) {
        this.announcementName = announcementName;
    }




}