package back.domain.model.userVerifier;

import back.domain.enums.AccountStatus;
import back.domain.model.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "userVerifier")
public class UserVerifier {

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

    public UserVerifier(){

    }

    public UserVerifier(String userVerifierEmail, String userVerifierPassword, Instant expiresAt, AccountStatus status, String code) {
        this.userVerifierEmail = userVerifierEmail;
        this.userVerifierPassword = userVerifierPassword;
        this.expiresAt = expiresAt;
        this.status = status;
        this.code = code;
    }

    public Long getUserVerifierId() {
        return userVerifierId;
    }

    public void setUserVerifierId(Long userVerifierId) {
        this.userVerifierId = userVerifierId;
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

    public static UserVerifier build(User user){
        return new UserVerifier(user.getUserEmail(),
                user.getUserPassword(),
                Instant.now().plusMillis(900000),
                user.getStatus(),
                UUID.randomUUID().toString());
    }
}
