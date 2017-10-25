/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UserController;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.User;

/**
 *
 * @author Valdez
 */
public class ComboAddNewFriendKeyHandler extends KeyAdapter {

    private final JComboBox<String> comboBox;
    private final ArrayList<String> list = new ArrayList<String>();

    private UserController userController = new UserController(this);
    private ClientMainView clientMainView;

    public ComboAddNewFriendKeyHandler(JComboBox<String> combo, ClientMainView clientMainView) {
        super();
        this.comboBox = combo;
        this.clientMainView = clientMainView;
    }

    public void returnSuggestedNewFriends(ArrayList<User> users) {
        list.removeAll(list);
        for (User user : users) {
            list.add(user.getUsername());
        }
    }

    public User getCbNewFriendsText() {
        JTextField text = (JTextField) comboBox.getEditor().getEditorComponent();
        User user = clientMainView.getUserID();
        user.setUsername(text.getText().trim());
        return user;
    }

    private void setSuggestionModel(JComboBox<String> comboBox, ComboBoxModel<String> model, String str) {
        comboBox.setModel(model);
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(str);
    }

    private ComboBoxModel<String> getSuggestedModel(List<String> list, String text) {
        userController.getSuggestedNewFriends();
        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        for (String s : list) {
            m.addElement(s);
        }
        return m;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String text = ((JTextField) e.getComponent()).getText();
                ComboBoxModel<String> m;
                if (text.trim().isEmpty()) {
                    comboBox.hidePopup();
                } else {
                    m = getSuggestedModel(list, text.trim());
                    if (m.getSize() == 0) {
                        comboBox.hidePopup();
                    } else {
                        setSuggestionModel(comboBox, m, text);
                        comboBox.showPopup();
                    }
                }
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField) e.getComponent();
        String text = textField.getText();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (!list.isEmpty()) {
                    if (comboBox.getSelectedIndex() == -1) {
                    textField.setText(comboBox.getItemAt(0));
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!list.isEmpty()) {
                    clientMainView.getLbl_Avatar().requestFocus();
                    if (comboBox.getSelectedIndex() != -1) {
                        clientMainView.showMessage(comboBox.getSelectedItem().toString());
                    } else {
                        clientMainView.showMessage(comboBox.getItemAt(0));
                    }
                }
                break;
//            case KeyEvent.VK_UP:
//                e.consume();
//                break;
//            case KeyEvent.VK_DOWN:
//                e.consume();
//                break;
            default:
                break;
        }
    }

}
