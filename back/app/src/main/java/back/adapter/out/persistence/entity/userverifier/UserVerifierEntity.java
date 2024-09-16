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
    private UUID userVerifierId;

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

    @Column(name = "userImagePath")
    private String userImagePath;

    public UserVerifierEntity(){

    }

    public UserVerifierEntity(UUID userVerifierId,String userVerifierName, String userVerifierEmail, String userVerifierPassword
            , Instant expiresAt, AccountStatus status, String code, String userImagePath) {

        this.userVerifierId = userVerifierId;
        this.userVerifierName = userVerifierName;
        this.userVerifierEmail = userVerifierEmail;
        this.userVerifierPassword = userVerifierPassword;
        this.expiresAt = expiresAt;
        this.status = status;
        this.code = code;
        this.userImagePath = userImagePath;
    }

    public UUID getUserVerifierEntityId() {
        return userVerifierId;
    }

    public void setUserVerifierEntityId(UUID userVerifierId) {
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

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public static UserVerifierEntity build(User user){
        return new UserVerifierEntity(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                Instant.now().plusMillis(900000),
                user.getStatus(),
                UUID.randomUUID().toString(),
                user.getUserImage());
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

    public UUID getUserVerifierId() {
        return userVerifierId;
    }

    public void setUserVerifierId(UUID userVerifierId) {
        this.userVerifierId = userVerifierId;
    }
}
