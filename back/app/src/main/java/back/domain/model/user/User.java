package back.domain.model.user;

import back.domain.enums.AccountStatus;
import back.domain.exception.UserException;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    private UUID userId;


    private String userName;


    private String userEmail;


    private String userPassword;


    private String userImage;


    private AccountStatus status;

    public User(UUID userId, String userName, String userEmail, String userPassword, String userImage, AccountStatus accountStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.status = accountStatus;
    }

    public User(){

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public static void validate(User user){
        if(user.getUserEmail().isEmpty() || user.getUserPassword().isEmpty() || user.getUserName().isEmpty()){
            throw new UserException("Usuario nao pode conter senha , nome ou email vazios");
        }
    }

}
