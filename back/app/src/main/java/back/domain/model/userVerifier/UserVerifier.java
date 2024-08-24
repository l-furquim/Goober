package back.domain.model.userVerifier;

import back.domain.enums.AccountStatus;
import back.domain.model.user.User;


import java.time.Instant;
import java.util.UUID;

public class UserVerifier {



    private UUID userVerifierId;

    private String userVerifierName;

    private String userVerifierEmail;


    private String userVerifierPassword;


    private Instant expiresAt;



    private AccountStatus status;


    private String code;

    public UserVerifier(){

    }

    public UserVerifier(UUID userVerifierid ,String userVerifierName, String userVerifierEmail, String userVerifierPassword, Instant expiresAt, AccountStatus status, String code) {
        this.userVerifierId = userVerifierid;
        this.userVerifierName = userVerifierName;
        this.userVerifierEmail = userVerifierEmail;
        this.userVerifierPassword = userVerifierPassword;
        this.expiresAt = expiresAt;
        this.status = status;
        this.code = code;
    }

    public UUID getUserVerifierId() {
        return userVerifierId;
    }

    public void setUserVerifierId(UUID userVerifierId) {
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
        return new UserVerifier(
                user.getUserId(),
                user.getUserName(),user.getUserEmail(),
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
}
