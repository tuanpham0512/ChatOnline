/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.*;
import view.*;

/**
 *
 * @author Valdez
 */
public class UserController {

    private ClientStartView clientStartView;
    private ClientMainView clientMainView;
    private AccountInfoView accountInfoView;
    private ChangePasswordView changePasswordView;
    private ComboAddNewFriendKeyHandler comboAddNewFriendKeyHandler;
    private ComboAddParticipantKeyHandler comboAddParticipantKeyHandler;
    private String serverHost = "localhost";
    private int serverPort = 8888;

    public UserController(ClientStartView clientStartView) {
        this.clientStartView = clientStartView;
    }

    public UserController(ClientMainView clientMainView) {
        this.clientMainView = clientMainView;
    }

    public UserController(AccountInfoView accountInfoView) {
        this.accountInfoView = accountInfoView;
    }

    public UserController(ChangePasswordView changePasswordView) {
        this.changePasswordView = changePasswordView;
    }

    public UserController(ComboAddNewFriendKeyHandler comboAddNewFriendKeyHandler) {
        this.comboAddNewFriendKeyHandler = comboAddNewFriendKeyHandler;
    }

    public UserController(ComboAddParticipantKeyHandler comboAddParticipantKeyHandler) {
        this.comboAddParticipantKeyHandler = comboAddParticipantKeyHandler;
    }

    public void login() {
        try {
            User user = clientStartView.getUserLogin();
            user.setAction("login");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            User result = (User) o;
            if (result == null) {
                clientStartView.showMessage("Invalid Username and/or Password!");
            } else {
                clientStartView.toMainView(result);
            }
            mySocket.close();
        } catch (Exception ex) {
            clientStartView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void signUp() {
        try {
            User user = clientStartView.getUserSignUp();
            user.setAction("signUp");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            Boolean result = (Boolean) o;
            if (!result) {
                clientStartView.showMessage("Username and/or Email already exist!");
            } else {
                clientStartView.switchToLogin();
                clientStartView.showMessage("Register successfully. Try login now!");
            }
            mySocket.close();
        } catch (Exception ex) {
            clientStartView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void changeInfo() {
        try {
            User user = accountInfoView.getUserChangedInfo();
            user.setAction("changeInfo");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            User result = (User) o;
            if (result == null) {
                accountInfoView.showMessage("Some error occurred. Please try again!");
            } else {
                accountInfoView.returnInfo(result);
            }
            mySocket.close();
        } catch (Exception ex) {
            accountInfoView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void changeAvatar() {
        try {
            User user = accountInfoView.getUserChangedAvatar();
            user.setAction("changeAvatar");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            User result = (User) o;
            if (result == null) {
                accountInfoView.showMessage("Some error occurred. Please try again!");
            } else {
                accountInfoView.returnAvatar(result);
            }
            mySocket.close();
        } catch (Exception ex) {
            accountInfoView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void changeStatus() {
        try {
            User user = clientMainView.getUserChangedStatus();
            user.setAction("changeStatus");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            User result = (User) o;
            if (result == null) {
                clientMainView.showMessage("Some error occurred. Please try again!");
            } else {
                clientMainView.returnStatus(result);
            }
            mySocket.close();
        } catch (Exception ex) {
            clientMainView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void changePassword() {
        try {
            User user = changePasswordView.getUserNewPassword();
            user.setAction("changePassword");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            User result = (User) o;
            if (result == null) {
                changePasswordView.showMessage("Your password is incorrect. Please try again!");
            } else {
                changePasswordView.returnNewPassword(result);
            }
            mySocket.close();
        } catch (Exception ex) {
            changePasswordView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void changeDescription() {
        try {
            User user = clientMainView.getUserChangedDescription();
            user.setAction("changeDescription");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            User result = (User) o;
            if (result == null) {
                clientMainView.showMessage("Some error occurred. Please try again!");
            } else {
                clientMainView.returnDescription(result);
            }
            mySocket.close();
        } catch (Exception ex) {
            clientMainView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void getFriendList() {
        try {
            User user = clientMainView.getUserID();
            user.setAction("getFriendList");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            ArrayList<User> result = (ArrayList<User>) o;
            clientMainView.returnFriendList(result);
            mySocket.close();
        } catch (Exception ex) {
            clientMainView.showMessage(ex.getStackTrace().toString());
        }
    }

    public void getSuggestedNewFriends() {
        try {
            User user = comboAddNewFriendKeyHandler.getCbNewFriendsText();
            user.setAction("getSuggestedNewFriends");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            ArrayList<User> result = (ArrayList<User>) o;
            comboAddNewFriendKeyHandler.returnSuggestedNewFriends(result);
            mySocket.close();
        } catch (Exception ex) {
        }
    }

    public void getSuggestedParticipantFriends() {
        try {
            User user = comboAddParticipantKeyHandler.getCbParticipantFriendsText();
            user.setAction("getSuggestedParticipantFriends");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(user);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            ArrayList<User> result = (ArrayList<User>) o;
            comboAddParticipantKeyHandler.returnSuggestedParticipantFriends(result);
            mySocket.close();
        } catch (Exception ex) {
        }
    }

}
