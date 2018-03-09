package com.rubylich.exmple;

import android.support.annotation.Nullable;

/**
 * Created by rl98880 on 06/03/2018.
 */

public class PresentationEitherJava {
    static class InvalidEmail extends Exception {
    }

    static class InvalidPassword extends Exception {
    }

    static class EmailNotExist extends Exception {
    }

    static class WrongPassword extends Exception {
    }

    private AccessToken login(String email, String password)
            throws InvalidEmail, InvalidPassword, EmailNotExist, WrongPassword {
        return null;
    }

    static class AccessToken {
        public String token;
    }

    private void loginPresenter() {
        try {
            AccessToken accessToken = login("me@gmail.com", "my_secret");
            showLoginSuccess(accessToken.token);
        } catch (InvalidEmail invalidEmail) {
            showErrorMessageInvalidEmail();
        } catch (InvalidPassword invalidPassword) {
            showErrorMessageInvalidPassword();
        } catch (EmailNotExist emailNotExist) {
            showErrorMessageEmailNotExist();
        } catch (WrongPassword wrongPassword) {
            showErrorMessageWrongPassword();
        }
    }

    private void showErrorMessageEmailNotExist() {

    }

    private void showErrorMessageWrongPassword() {

    }

    private void showErrorMessageInvalidPassword() {

    }

    private void showErrorMessageInvalidEmail() {

    }

    private void showLoginSuccess(String token) {

    }

    @Nullable
    private String getTokenOrNull() {

        String tokenOrNull = null;

        try {
            AccessToken accessToken = login("email@gmail.com", "password");
            tokenOrNull = accessToken.token;
        } catch (Exception e) {
            //swallowed
        }

        return tokenOrNull;
    }

    private void printLoginResult() {
        String toPrint;

        try {
            AccessToken accessToken = login("email@gmail.com", "password");
            toPrint = "Token: " + accessToken.token;
        } catch (InvalidEmail invalidEmail) {
            toPrint = "Invalid email";
        } catch (InvalidPassword invalidPassword) {
            toPrint = "Invalid password";
        } catch (EmailNotExist emailNotExist) {
            toPrint = "Email not exist";
        } catch (WrongPassword wrongPassword) {
            toPrint = "Wrong password";
        }

        System.out.println(toPrint);
    }
}
