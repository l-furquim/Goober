package back.adapter.out.persistence.entity.userverifier;

import back.domain.enums.AccountStatus;
import back.domain.model.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "userverifier")
public class UserVerifierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userverifier_id")
    private Long userVerifierId;

    @Column(name = "userverifier_name")
    private String userVerifierName;

    @Column(name = "userverifier_email")
    private String userVerifierEmail;

    @Column(name = "userverifier_password")
    private String userVerifierPassword;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

    @Column(name = "code")
    private String code;

    public UserVerifierEntity(){

    }

    public UserVerifierEntity(String userVerifierName, String userVerifierEmail, String userVerifierPassword, Instant expiresAt, AccountStatus status, String code) {
        this.userVerifierName = userVerifierName;
        this.userVerifierEmail = userVerifierEmail;
        this.userVerifierPassword = userVerifierPassword;
        this.expiresAt = expiresAt;
        this.status = status;
        this.code = code;
    }

    public Long getUserVerifierEntityId() {
        return userVerifierId;
    }

    public void setUserVerifierEntityId(Long userVerifierId) {
        this.userVerifierId = userVerifierId;
    }

    public String getUserVerifierEntityEmail() {
        return userVerifierEmail;
    }

    public void setUserVerifierEntityEmail(String userVerifierEmail) {
        this.userVerifierEmail = userVerifierEmail;
    }

    public String getUserVerifierEntityPassword() {
        return userVerifierPassword;
    }

    public void setUserVerifierEntityPassword(String userVerifierPassword) {
        this.userVerifierPassword = userVerifierPassword;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static UserVerifierEntity build(User user){
        return new UserVerifierEntity(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                Instant.now().plusMillis(900000),
                user.getStatus(),
                UUID.randomUUID().toString());
    }

    public String getUserVerifierName() {
        return userVerifierName;
    }

    public void setUserVerifierName(String userVerifierName) {
        this.userVerifierName = userVerifierName;
    }

    public String getUserVerifierEmail() {
        return userVerifierEmail;
    }

    public void setUserVerifierEmail(String userVerifierEmail) {
        this.userVerifierEmail = userVerifierEmail;
    }

    public String getUserVerifierPassword() {
        return userVerifierPassword;
    }

    public void setUserVerifierPassword(String userVerifierPassword) {
        this.userVerifierPassword = userVerifierPassword;
    }

    public Long getUserVerifierId() {
        return userVerifierId;
    }

    public void setUserVerifierId(Long userVerifierId) {
        this.userVerifierId = userVerifierId;
    }
}
