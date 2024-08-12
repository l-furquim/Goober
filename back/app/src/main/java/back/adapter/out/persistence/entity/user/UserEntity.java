package back.adapter.out.persistence.entity.user;

import back.domain.enums.AccountStatus;
import back.domain.exception.UserException;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name= "user")
public class UserEntity {

    @Id
    @Column(name = "userId")
    private UUID userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "userImage")
    private String userImage;

    @Enumerated(EnumType.STRING)
    @Column(name="status" ,nullable=false)
    private AccountStatus status;

    public UserEntity(UUID userId, String userName, String userEmail, String userPassword, String userImage, AccountStatus accountStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.status = accountStatus;
    }

    public UserEntity(){

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

    public static void validate(UserEntity user){
        if(user.getUserEmail().isEmpty() || user.getUserPassword().isEmpty() || user.getUserName().isEmpty()){
            throw new UserException("Usuario nao pode conter senha , nome ou email vazios");
        }
    }

}

