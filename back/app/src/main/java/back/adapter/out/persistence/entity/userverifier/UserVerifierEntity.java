package back.adapter.out.persistence.entity.userverifier;

import back.domain.enums.AccountStatus;
import back.domain.model.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "userVerifier")
public class UserVerifierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userVerifierId")
    private Long userVerifierId;

    @Column(name = "userVerifierEmail")
    private String userVerifierEmail;

    @Column(name = "userVerifierPassword")
    private String userVerifierPassword;

    @Column(name = "expiresAt")
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

    @Column(name = "code")
    private String code;

    public UserVerifierEntity(){

    }

    public UserVerifierEntity(String userVerifierEmail, String userVerifierPassword, Instant expiresAt, AccountStatus status, String code) {
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
        return new UserVerifierEntity(user.getUserEmail(),
                user.getUserPassword(),
                Instant.now().plusMillis(900000),
                user.getStatus(),
                UUID.randomUUID().toString());
    }
}
