/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.*;

/**
 *
 * @author Valdez
 */
public class CreateGroupView extends javax.swing.JFrame {

    private final String file_path = "file/icon/";

    private ConversationController conversationController = null;
    private ClientMainView clientMainView;
    private User user;
    private ArrayList<User> users = new ArrayList<User>();
    
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    
    public User getUserID() {
        return clientMainView.getUserID();
    }

    /**
     * Creates new form CreateGroupView
     */
    public CreateGroupView(User user, ClientMainView clientMainView, ObjectInputStream ois, ObjectOutputStream oos) {
        this.oos = oos;
        this.ois = ois;
        conversationController = new ConversationController(this,ois,oos);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        this.user = user;
        this.clientMainView = clientMainView;
        initComponents();

        lbl_AddParticipantIcon.requestFocusInWindow();
    }

    public void openCreatedGroupChat(Conversation conversation) {
        clientMainView.getConversationController().getGroupList();
        clientMainView.getConversationController().openGroupConversation(conversation.getId());
        this.dispose();
    }

    public Conversation getConversationParticipants() {
        Conversation conversation = new Conversation();
        conversation.setName(txt_GroupName.getText());
        conversation.setMainUserId(getUserID().getId());
        ArrayList<Integer> participantId = new ArrayList<Integer>();
        ArrayList<String> participantDisplayName = new ArrayList<String>();
        participantId.add(user.getId());
        if (user.getDisplay_name().isEmpty()) {
            participantDisplayName.add(user.getUsername());
        } else {
            participantDisplayName.add(user.getDisplay_name());
        }
        for (User user : users) {
            participantId.add(user.getId());
            if (user.getDisplay_name().isEmpty()) {
                participantDisplayName.add(user.getUsername());
            } else {
                participantDisplayName.add(user.getDisplay_name());
            }
        }
        conversation.setParticipantId(participantId);
        conversation.setParticipantDisplayName(participantDisplayName);
        return conversation;
    }

    public void addParticipant(User user) {
        Boolean added = false;
        for (User u : users) {
            if (u.getId() == user.getId()) {
                added = true;
            }
        }
        if (!added) {
            users.add(user);
        }
        loadParticipantList();
    }

    private void loadParticipantList() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) JTableParticipantList.getModel();
        defaultTableModel.getDataVector().removeAllElements();
        String groupName = "";
        if (user.getDisplay_name().isEmpty()) {
            defaultTableModel.addRow(new Object[]{user.getUsername(), user.getUsername()});
            groupName += user.getUsername() + ", ";
        } else {
            defaultTableModel.addRow(new Object[]{user.getUsername(), user.getDisplay_name()});
            groupName += user.getDisplay_name() + ", ";
        }
        for (User user : users) {
            if (user.getDisplay_name().isEmpty()) {
                defaultTableModel.addRow(new Object[]{user.getUsername(), user.getUsername()});
                groupName += user.getUsername() + ", ";
            } else {
                defaultTableModel.addRow(new Object[]{user.getUsername(), user.getDisplay_name()});
                groupName += user.getDisplay_name() + ", ";
            }
        }
        if (users.size() > 0) {
            txt_GroupName.setText(groupName.substring(0, groupName.length() - 2));
        } else {
            txt_GroupName.setText("");
        }
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JP_Main = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color color = new Color(120, 36, 111);
                GradientPaint gp = new GradientPaint(0, 0, color.darker(), 0, getHeight(), color.brighter());
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        lbl_AddParticipantIcon = new javax.swing.JLabel();
        cb_AddParticipant = new javax.swing.JComboBox<>();
        JP_ParticipantList = new javax.swing.JPanel();
        JScrollPaneParticipantList = new javax.swing.JScrollPane();
        JTableParticipantList = new javax.swing.JTable();
        jp_Remove = new javax.swing.JPanel();
        btn_Remove = new javax.swing.JLabel();
        lbl_GroupName = new javax.swing.JLabel();
        txt_GroupName = new javax.swing.JTextField();
        jp_CreateGroup = new javax.swing.JPanel();
        btn_CreateGroup = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Group");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lbl_AddParticipantIcon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_AddParticipantIcon.setForeground(java.awt.Color.yellow);
        lbl_AddParticipantIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_AddParticipantIcon.setIcon(new javax.swing.ImageIcon(file_path + "add-contact-icon.png"));
        lbl_AddParticipantIcon.setText("Add Participants:");

        cb_AddParticipant.setEditable(true);
        cb_AddParticipant.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));
        cb_AddParticipant.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTextField text = (JTextField) cb_AddParticipant.getEditor().getEditorComponent();
        text.setText(" Type friend's username here...");
        text.setFont(new java.awt.Font("Tahoma", 2, 14));
        text.setForeground(java.awt.Color.gray);
        text.addKeyListener(new ComboAddParticipantKeyHandler(cb_AddParticipant, this));
        text.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (text.getText().equals(" Type friend's username here...")) {
                    text.setText(" ");
                    text.setForeground(Color.DARK_GRAY);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                text.setText(" Type friend's username here...");
                text.setForeground(Color.GRAY);
            }
        });

        JP_ParticipantList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));

        JTableParticipantList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Display name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JScrollPaneParticipantList.setViewportView(JTableParticipantList);

        javax.swing.GroupLayout JP_ParticipantListLayout = new javax.swing.GroupLayout(JP_ParticipantList);
        JP_ParticipantList.setLayout(JP_ParticipantListLayout);
        JP_ParticipantListLayout.setHorizontalGroup(
            JP_ParticipantListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPaneParticipantList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        JP_ParticipantListLayout.setVerticalGroup(
            JP_ParticipantListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPaneParticipantList, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        jp_Remove.setBackground(new java.awt.Color(93, 53, 176));
        jp_Remove.setBorder(new javax.swing.border.MatteBorder(null));
        jp_Remove.setMaximumSize(new java.awt.Dimension(75, 28));
        jp_Remove.setMinimumSize(new java.awt.Dimension(75, 28));

        btn_Remove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Remove.setForeground(new java.awt.Color(255, 255, 255));
        btn_Remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Remove.setText("Remove");
        btn_Remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        btn_Remove.setMaximumSize(new java.awt.Dimension(75, 25));
        btn_Remove.setMinimumSize(new java.awt.Dimension(75, 25));
        btn_Remove.setPreferredSize(new java.awt.Dimension(75, 25));
        btn_Remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_RemoveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_RemoveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_RemoveMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_RemoveMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_RemoveMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jp_RemoveLayout = new javax.swing.GroupLayout(jp_Remove);
        jp_Remove.setLayout(jp_RemoveLayout);
        jp_RemoveLayout.setHorizontalGroup(
            jp_RemoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jp_RemoveLayout.setVerticalGroup(
            jp_RemoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lbl_GroupName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_GroupName.setForeground(java.awt.Color.yellow);
        lbl_GroupName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_GroupName.setText("Group name:");

        txt_GroupName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_GroupName.setForeground(java.awt.Color.darkGray);
        txt_GroupName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        txt_GroupName.setCaretColor(java.awt.Color.darkGray);

        jp_CreateGroup.setBackground(new java.awt.Color(93, 53, 176));
        jp_CreateGroup.setBorder(new javax.swing.border.MatteBorder(null));
        jp_CreateGroup.setMaximumSize(new java.awt.Dimension(75, 28));
        jp_CreateGroup.setMinimumSize(new java.awt.Dimension(75, 28));

        btn_CreateGroup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_CreateGroup.setForeground(new java.awt.Color(255, 255, 255));
        btn_CreateGroup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_CreateGroup.setText("Create Group");
        btn_CreateGroup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        btn_CreateGroup.setMaximumSize(new java.awt.Dimension(75, 25));
        btn_CreateGroup.setMinimumSize(new java.awt.Dimension(75, 25));
        btn_CreateGroup.setPreferredSize(new java.awt.Dimension(75, 25));
        btn_CreateGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CreateGroupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_CreateGroupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_CreateGroupMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_CreateGroupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_CreateGroupMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jp_CreateGroupLayout = new javax.swing.GroupLayout(jp_CreateGroup);
        jp_CreateGroup.setLayout(jp_CreateGroupLayout);
        jp_CreateGroupLayout.setHorizontalGroup(
            jp_CreateGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_CreateGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jp_CreateGroupLayout.setVerticalGroup(
            jp_CreateGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_CreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout JP_MainLayout = new javax.swing.GroupLayout(JP_Main);
        JP_Main.setLayout(JP_MainLayout);
        JP_MainLayout.setHorizontalGroup(
            JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MainLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jp_CreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JP_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_MainLayout.createSequentialGroup()
                        .addComponent(lbl_AddParticipantIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_AddParticipant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(JP_ParticipantList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_MainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jp_Remove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_MainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_GroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_GroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_MainLayout.setVerticalGroup(
            JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_AddParticipantIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(cb_AddParticipant))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_ParticipantList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_GroupName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_GroupName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_CreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RemoveMouseClicked
        // TODO add your handling code here:
        int r = JTableParticipantList.getSelectedRow();
        if (r >= 0 && r < users.size()) {
            users.remove(r);
            if (users.size() == 0 || JTableParticipantList.getSelectedRow() == 0) {
                JTableParticipantList.clearSelection();
            }
            loadParticipantList();
        }
    }//GEN-LAST:event_btn_RemoveMouseClicked

    private void btn_RemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RemoveMouseEntered
        // TODO add your handling code here:
        jp_Remove.setBackground(jp_Remove.getBackground().brighter());
    }//GEN-LAST:event_btn_RemoveMouseEntered

    private void btn_RemoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RemoveMouseExited
        // TODO add your handling code here:
        jp_Remove.setBackground(new Color(93, 53, 176));
    }//GEN-LAST:event_btn_RemoveMouseExited

    private void btn_CreateGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CreateGroupMouseClicked
        // TODO add your handling code here:
        if (users.size() < 2) {
            showMessage("A group requied at least 3 participants.");
        } else {
            conversationController.createGroupConversation();
        }
    }//GEN-LAST:event_btn_CreateGroupMouseClicked

    private void btn_CreateGroupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CreateGroupMouseEntered
        // TODO add your handling code here:
        jp_CreateGroup.setBackground(jp_CreateGroup.getBackground().brighter());
    }//GEN-LAST:event_btn_CreateGroupMouseEntered

    private void btn_CreateGroupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CreateGroupMouseExited
        // TODO add your handling code here:
        jp_CreateGroup.setBackground(new Color(93, 53, 176));
    }//GEN-LAST:event_btn_CreateGroupMouseExited

    private void btn_RemoveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RemoveMousePressed
        // TODO add your handling code here:                
        int r = JTableParticipantList.getSelectedRow();
        if (r >= 0 && r < users.size()) {
            btn_Remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        }
    }//GEN-LAST:event_btn_RemoveMousePressed

    private void btn_RemoveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RemoveMouseReleased
        // TODO add your handling code here:
        int r = JTableParticipantList.getSelectedRow();
        if (r >= 0 && r < users.size()) {
            btn_Remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        }
    }//GEN-LAST:event_btn_RemoveMouseReleased

    private void btn_CreateGroupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CreateGroupMousePressed
        // TODO add your handling code here:
        btn_CreateGroup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
    }//GEN-LAST:event_btn_CreateGroupMousePressed

    private void btn_CreateGroupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CreateGroupMouseReleased
        // TODO add your handling code here:
        btn_CreateGroup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
    }//GEN-LAST:event_btn_CreateGroupMouseReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        clientMainView.setCheckCreateGroupView(false);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CreateGroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateGroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateGroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateGroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateGroupView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JP_Main;
    private javax.swing.JPanel JP_ParticipantList;
    private javax.swing.JScrollPane JScrollPaneParticipantList;
    private javax.swing.JTable JTableParticipantList;
    private javax.swing.JLabel btn_CreateGroup;
    private javax.swing.JLabel btn_Remove;
    private javax.swing.JComboBox<String> cb_AddParticipant;
    private javax.swing.JPanel jp_CreateGroup;
    private javax.swing.JPanel jp_Remove;
    private javax.swing.JLabel lbl_AddParticipantIcon;
    private javax.swing.JLabel lbl_GroupName;
    private javax.swing.JTextField txt_GroupName;
    // End of variables declaration//GEN-END:variables
}
