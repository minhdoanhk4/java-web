/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minhda.registration;

import java.io.Serializable;

/**
 *
 * @author msi2k
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLenghtErr;
    private String passwordLenghtErr;
    private String confirmNotMatched;
    private String fullNameLenghtErr;
    private String usernameIsExitErr;

    public RegistrationCreateError() {
    }
    
    

    /**
     * @return the usernameLenghtErr
     */
    public String getUsernameLenghtErr() {
        return usernameLenghtErr;
    }

    /**
     * @param usernameLenghtErr the usernameLenghtErr to set
     */
    public void setUsernameLenghtErr(String usernameLenghtErr) {
        this.usernameLenghtErr = usernameLenghtErr;
    }

    /**
     * @return the passwordLenghtErr
     */
    public String getPasswordLenghtErr() {
        return passwordLenghtErr;
    }

    /**
     * @param passwordLenghtErr the passwordLenghtErr to set
     */
    public void setPasswordLenghtErr(String passwordLenghtErr) {
        this.passwordLenghtErr = passwordLenghtErr;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    /**
     * @return the fullNameLenghtErr
     */
    public String getFullNameLenghtErr() {
        return fullNameLenghtErr;
    }

    /**
     * @param fullNameLenghtErr the fullNameLenghtErr to set
     */
    public void setFullNameLenghtErr(String fullNameLenghtErr) {
        this.fullNameLenghtErr = fullNameLenghtErr;
    }

    /**
     * @return the usernameIsExitErr
     */
    public String getUsernameIsExitErr() {
        return usernameIsExitErr;
    }

    /**
     * @param usernameIsExitErr the usernameIsExitErr to set
     */
    public void setUsernameIsExitErr(String usernameIsExitErr) {
        this.usernameIsExitErr = usernameIsExitErr;
    }
    
    
}
