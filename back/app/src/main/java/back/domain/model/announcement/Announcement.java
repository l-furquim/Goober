package back.domain.model.announcement;


import back.domain.model.product.Product;
import back.domain.model.question.Question;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;



public class Announcement implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID announcementId;


    private String announcementName;

    private BigDecimal announcementPrice;


    private Integer announcementLikes;


    private Integer announcementQuestions;


    private String announcerId;


    private String productImages;

    private List<Product> products;

    private List<Question> productQuestions;

    private String announcementStreet;

    private Integer announcementNumber;

    private String announcementState;

    private String announcementDistrict;

    public Announcement(){

    }

    public UUID getAnnouncementId() {
        return announcementId;
    }

    public Announcement(
            UUID announcementId, BigDecimal announcementPrice,String announcementName, Integer announcementLikes,
            Integer announcementQuestions, String announcerId, String productImages, List<Product> products,
            String announcementStreet, Integer announcementNumber, String announcementState, String announcementDistrict
    ) {

        this.announcementId = announcementId;
        this.announcementPrice = announcementPrice;
        this.announcementLikes = announcementLikes;
        this.announcementName = announcementName;
        this.announcementQuestions = announcementQuestions;
        this.products = products;
        this.announcerId = announcerId;
        this.productImages = productImages;
        this.announcementStreet = announcementStreet;
        this.announcementNumber = announcementNumber;
        this.announcementState = announcementState;
        this.announcementDistrict = announcementDistrict;
    }

    public void setAnnouncementId(UUID announcementId) {
        this.announcementId = announcementId;
    }

    public Integer getAnnouncementLikes() {
        return announcementLikes;
    }

    public void setAnnouncementLikes(Integer announcementLikes) {
        this.announcementLikes = announcementLikes;
    }

    public Integer getAnnouncementQuestions() {
        return announcementQuestions;
    }

    public void setAnnouncementQuestions(Integer announcementQuestions) {
        this.announcementQuestions = announcementQuestions;
    }

    public String getProductId() {
        return announcerId;
    }

    public void setProductId(UUID announcerId) {
        this.announcerId = Announcement.this.announcerId;
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
        return announcerId;
    }

    public void setAnnouncerName(String announcerId) {
        this.announcerId = announcerId;
    }

    public List<Product> getProducts() {
        return products;
    }


    public void addProducts(Product product){
        this.products.add(product);
    }

    public BigDecimal getAnnouncementPrice() {
        return announcementPrice;
    }

    public void setAnnouncementPrice(BigDecimal announcementPrice) {
        this.announcementPrice = announcementPrice;
    }

    public String getAnnouncerId() {
        return announcerId;
    }

    public void setAnnouncerId(String announcerId) {
        this.announcerId = announcerId;
    }

    public void setProductQuestions(List<Question> productQuestions) {
        this.productQuestions = productQuestions;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public void setAnnouncementName(String announcementName) {
        this.announcementName = announcementName;
    }

    public String getAnnouncementStreet() {
        return announcementStreet;
    }

    public void setAnnouncementStreet(String announcementStreet) {
        this.announcementStreet = announcementStreet;
    }

    public Integer getAnnouncementNumber() {
        return announcementNumber;
    }

    public void setAnnouncementNumber(Integer announcementNumber) {
        this.announcementNumber = announcementNumber;
    }

    public String getAnnouncementState() {
        return announcementState;
    }

    public void setAnnouncementState(String announcementState) {
        this.announcementState = announcementState;
    }

    public String getAnnouncementDistrict() {
        return announcementDistrict;
    }

    public void setAnnouncementDistrict(String announcementDistrict) {
        this.announcementDistrict = announcementDistrict;
    }
}
