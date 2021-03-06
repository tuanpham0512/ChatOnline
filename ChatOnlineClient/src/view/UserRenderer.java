/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import model.User;

/**
 *
 * @author Valdez
 */
public class UserRenderer extends javax.swing.JPanel implements ListCellRenderer<User> {

    private final String file_path = "file/icon/";
    private final String upload_domain = "http://uploads.chatonline.com";

    /**
     * Creates new form UserRenderer
     */
    public UserRenderer() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        initComponents();
    }

    public void displayAvatar(User user) {
        try {
            BufferedImage avatar = ImageIO.read(new URL(upload_domain + "/default/default_avatar.png"));
            if (user.getAvatar_path() != null) {
                URL url = new URL(upload_domain + user.getAvatar_path());
                avatar = ImageIO.read(url);
            }
            Image avatar_rs = avatar.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon avatar_img = new ImageIcon(avatar_rs);
            lbl_Avatar.setText("");
            lbl_Avatar.setIcon(avatar_img);
        } catch (IOException ex) {
        }
    }

    public void displayStatus(User user) {
        try {
            String status_path = file_path;
            int temp = user.getStatus();
            switch (temp) {
                case 0:
                    status_path += "online-icon.png";
                    break;
                case 1:
                    status_path += "away-icon.png";
                    break;
                case 2:
                    status_path += "busy-icon.png";
                    break;
                case 3:
                    status_path += "invisible-icon.png";
                    break;
                case 4:
                    status_path += "offline-icon.png";
                    break;
            }
            BufferedImage status = ImageIO.read(new File(status_path));
            ImageIcon status_img = new ImageIcon(status);
            lbl_DisplayName.setIcon(status_img);
        } catch (IOException ex) {
        }
    }

    public void displayName(User user) {
        if (user.getDisplay_name() != null && !user.getDisplay_name().equals("")) {
            lbl_DisplayName.setText(user.getDisplay_name());
        } else {
            lbl_DisplayName.setText(user.getUsername());
        }
    }

    public void displayDescription(User user) {
        lbl_Description.setText(user.getDescription());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_Avatar = new javax.swing.JLabel();
        lbl_DisplayName = new javax.swing.JLabel();
        lbl_Description = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        lbl_Avatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Avatar.setText("Avatar");
        lbl_Avatar.setToolTipText("");
        lbl_Avatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        lbl_Avatar.setMaximumSize(new java.awt.Dimension(50, 50));
        lbl_Avatar.setMinimumSize(new java.awt.Dimension(50, 50));
        lbl_Avatar.setOpaque(true);
        lbl_Avatar.setPreferredSize(new java.awt.Dimension(50, 50));
        lbl_Avatar.setRequestFocusEnabled(false);

        lbl_DisplayName.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lbl_DisplayName.setText("Name");

        lbl_Description.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_Description.setForeground(new java.awt.Color(153, 153, 153));
        lbl_Description.setText("Description");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_DisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lbl_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_DisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Description)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            displayAvatar(value);
            displayStatus(value);
            displayName(value);
            displayDescription(value);
            lbl_Avatar.setOpaque(true);
            lbl_DisplayName.setOpaque(true);
            lbl_Description.setOpaque(true);
            if (isSelected) {
                lbl_Avatar.setBackground(list.getSelectionBackground());
                lbl_DisplayName.setBackground(list.getSelectionBackground());
                lbl_Description.setBackground(list.getSelectionBackground());
                this.setBackground(list.getSelectionBackground());
            } else {
                lbl_Avatar.setBackground(list.getBackground());
                lbl_DisplayName.setBackground(list.getBackground());
                lbl_Description.setBackground(list.getBackground());
                this.setBackground(list.getBackground());
            }
            return this;
        } catch (Exception ex) {
            return null;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_Avatar;
    private javax.swing.JLabel lbl_Description;
    private javax.swing.JLabel lbl_DisplayName;
    // End of variables declaration//GEN-END:variables
}
