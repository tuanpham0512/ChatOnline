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
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLEditorKit;
import model.*;

/**
 *
 * @author Valdez
 */
public class ChatBox extends javax.swing.JFrame {

    private final String upload_domain = "http://uploads.chatonline.com";

    private MessageController messageController = null;
    private ClientMainView clientMainView;
    private Conversation conversation;

    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public Message getConversationID() {
        Message model = new Message();
        model.setConversation_id(conversation.getId());
        return model;
    }

    /**
     * Creates new form ChatBox
     */
    public ChatBox(Conversation conversation, ClientMainView clientMainView, ObjectInputStream ois, ObjectOutputStream oos) {

        System.out.println("start ois oos");
        this.oos = oos;
        this.ois = ois;
        System.out.println("end ois oos");
        this.messageController = new MessageController(this, this.ois, this.oos);
        System.out.println("end ChatBox Contrustor");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        setConversation(conversation);
        this.clientMainView = clientMainView;
        this.setTitle(conversation.getFriendDisplayName());
        initComponents();
        messageController.getHistoryMessages();
        displayAvatars(conversation);

    }

    public void returnMessage(Message message) {
        if (message.getUser_avatar() == null) {
            message.setUser_avatar("/default/default_avatar.png");
        }
        append("<table style='background: #f2f2f2; margin-bottom: 3px;'>"
                + "<tr>"
                + "<td valign=top>"
                + "<img src='" + upload_domain + message.getUser_avatar() + "' height='40' width='40' style='border-radius: 50%;'>"
                + "</td>"
                + "<td valign=top style='width:220px; word-wrap:break-word;'>"
                + "<b style='color:blue'>" + message.getNick_name() + ": </b>"
                + "<br>"
                + message.getContent()
                + "</td>"
                + "</tr>"
                + "</table>");
    }

    public Message getSendingMessage() {
        Message message = new Message();
        message.setConversation_id(conversation.getId());
        message.setUser_id(conversation.getMainUserId());
        message.setContent(JTextAreaNewMessage.getText().replace("\n", "<br>"));
        JTextAreaNewMessage.setText("");
        return message;
    }

    public void returnHistoryMessages(ArrayList<Message> messages) {
        for (Message message : messages) {
            if (message.getUser_avatar() == null) {
                message.setUser_avatar("/default/default_avatar.png");
            }
            append("<table style='background: #f2f2f2; margin-bottom: 3px;'>"
                    + "<tr>"
                    + "<td valign=top>"
                    + "<img src='" + upload_domain + message.getUser_avatar() + "' height='40' width='40' style='border-radius: 50%;'>"
                    + "</td>"
                    + "<td valign=top style='width:220px; word-wrap:break-word;'>"
                    + "<b style='color:blue'>" + message.getNick_name() + ": </b>"
                    + "<br>"
                    + message.getContent()
                    + "</td>"
                    + "</tr>"
                    + "</table>");
        }
    }

    public void append(String data) {
        HTMLEditorKit editor = (HTMLEditorKit) JEditorPaneChatHistory.getEditorKit();
        StringReader reader = new StringReader(data);
        try {
            editor.read(reader, JEditorPaneChatHistory.getDocument(), JEditorPaneChatHistory.getDocument().getLength());
            JScrollPaneChatHistory.getVerticalScrollBar().setValue(JScrollPaneChatHistory.getVerticalScrollBar().getMaximum());
        } catch (Exception ex) {
        }
    }

    public void displayAvatars(Conversation conversation) {
        try {
            lbl_MyAvatar.setText("");
            lbl_FriendAvatar.setText("");
            //------------------------------------------------------------------
            BufferedImage myAvatar = ImageIO.read(new URL(upload_domain + "/default/default_avatar.png"));
            if (conversation.getMainUserAvatarPath() != null) {
                URL url = new URL(upload_domain + conversation.getMainUserAvatarPath());
                myAvatar = ImageIO.read(url);
            }
            Image myAvatar_rs = myAvatar.getScaledInstance(78, 78, Image.SCALE_SMOOTH);
            ImageIcon myAvatar_img = new ImageIcon(myAvatar_rs);
            lbl_MyAvatar.setIcon(myAvatar_img);
            //------------------------------------------------------------------
            BufferedImage friendAvatar = ImageIO.read(new URL(upload_domain + "/default/default_avatar.png"));
            if (conversation.getFriendAvatarPath() != null) {
                URL url = new URL(upload_domain + conversation.getFriendAvatarPath());
                friendAvatar = ImageIO.read(url);
            }
            Image friendAvatar_rs = friendAvatar.getScaledInstance(78, 78, Image.SCALE_SMOOTH);
            ImageIcon friendAvatar_img = new ImageIcon(friendAvatar_rs);
            lbl_FriendAvatar.setIcon(friendAvatar_img);
        } catch (IOException ex) {
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

        JP_Main = new javax.swing.JPanel();
        JP_Chat = new javax.swing.JPanel() {
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
        JPanelChatHistory = new javax.swing.JPanel();
        JScrollPaneChatHistory = new javax.swing.JScrollPane();
        JEditorPaneChatHistory = new javax.swing.JEditorPane();
        JPanelNewMessage = new javax.swing.JPanel();
        jp_Send = new javax.swing.JPanel();
        btn_Send = new javax.swing.JLabel();
        JScrollPaneNewMessage = new javax.swing.JScrollPane();
        JTextAreaNewMessage = new javax.swing.JTextArea();
        JP_Avatar = new javax.swing.JPanel() {
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
        lbl_FriendAvatar = new javax.swing.JLabel();
        lbl_MyAvatar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JP_Chat.setPreferredSize(new java.awt.Dimension(369, 404));

        JPanelChatHistory.setBackground(new java.awt.Color(255, 255, 255));
        JPanelChatHistory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));

        JScrollPaneChatHistory.setBorder(null);

        JEditorPaneChatHistory.setEditable(false);
        JEditorPaneChatHistory.setContentType("text/html"); // NOI18N
        JEditorPaneChatHistory.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        JEditorPaneChatHistory.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    \n  </body>\n</html>\n");
        JScrollPaneChatHistory.setViewportView(JEditorPaneChatHistory);

        javax.swing.GroupLayout JPanelChatHistoryLayout = new javax.swing.GroupLayout(JPanelChatHistory);
        JPanelChatHistory.setLayout(JPanelChatHistoryLayout);
        JPanelChatHistoryLayout.setHorizontalGroup(
            JPanelChatHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelChatHistoryLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(JScrollPaneChatHistory)
                .addContainerGap())
        );
        JPanelChatHistoryLayout.setVerticalGroup(
            JPanelChatHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelChatHistoryLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(JScrollPaneChatHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        JPanelNewMessage.setBackground(new java.awt.Color(255, 255, 255));
        JPanelNewMessage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));

        jp_Send.setBackground(new java.awt.Color(163, 73, 164));
        jp_Send.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));

        btn_Send.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_Send.setForeground(new java.awt.Color(255, 255, 255));
        btn_Send.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Send.setText("Send");
        btn_Send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_SendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_SendMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_SendMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_SendMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jp_SendLayout = new javax.swing.GroupLayout(jp_Send);
        jp_Send.setLayout(jp_SendLayout);
        jp_SendLayout.setHorizontalGroup(
            jp_SendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Send, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jp_SendLayout.setVerticalGroup(
            jp_SendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JScrollPaneNewMessage.setBorder(null);
        JScrollPaneNewMessage.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPaneNewMessage.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        JTextAreaNewMessage.setColumns(20);
        JTextAreaNewMessage.setLineWrap(true);
        JTextAreaNewMessage.setRows(5);
        JTextAreaNewMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTextAreaNewMessageKeyReleased(evt);
            }
        });
        JScrollPaneNewMessage.setViewportView(JTextAreaNewMessage);

        javax.swing.GroupLayout JPanelNewMessageLayout = new javax.swing.GroupLayout(JPanelNewMessage);
        JPanelNewMessage.setLayout(JPanelNewMessageLayout);
        JPanelNewMessageLayout.setHorizontalGroup(
            JPanelNewMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelNewMessageLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(JScrollPaneNewMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_Send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        JPanelNewMessageLayout.setVerticalGroup(
            JPanelNewMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelNewMessageLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jp_Send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(JPanelNewMessageLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(JScrollPaneNewMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout JP_ChatLayout = new javax.swing.GroupLayout(JP_Chat);
        JP_Chat.setLayout(JP_ChatLayout);
        JP_ChatLayout.setHorizontalGroup(
            JP_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPanelNewMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelChatHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        JP_ChatLayout.setVerticalGroup(
            JP_ChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanelChatHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JPanelNewMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbl_FriendAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_FriendAvatar.setText("Avatar");
        lbl_FriendAvatar.setToolTipText("");
        lbl_FriendAvatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        lbl_FriendAvatar.setMaximumSize(new java.awt.Dimension(78, 78));
        lbl_FriendAvatar.setMinimumSize(new java.awt.Dimension(78, 78));
        lbl_FriendAvatar.setOpaque(true);
        lbl_FriendAvatar.setPreferredSize(new java.awt.Dimension(78, 78));
        lbl_FriendAvatar.setRequestFocusEnabled(false);

        lbl_MyAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_MyAvatar.setText("Avatar");
        lbl_MyAvatar.setToolTipText("");
        lbl_MyAvatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        lbl_MyAvatar.setMaximumSize(new java.awt.Dimension(78, 78));
        lbl_MyAvatar.setMinimumSize(new java.awt.Dimension(78, 78));
        lbl_MyAvatar.setOpaque(true);
        lbl_MyAvatar.setPreferredSize(new java.awt.Dimension(78, 78));
        lbl_MyAvatar.setRequestFocusEnabled(false);

        javax.swing.GroupLayout JP_AvatarLayout = new javax.swing.GroupLayout(JP_Avatar);
        JP_Avatar.setLayout(JP_AvatarLayout);
        JP_AvatarLayout.setHorizontalGroup(
            JP_AvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_AvatarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_AvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_FriendAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_MyAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_AvatarLayout.setVerticalGroup(
            JP_AvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_AvatarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_FriendAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(lbl_MyAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout JP_MainLayout = new javax.swing.GroupLayout(JP_Main);
        JP_Main.setLayout(JP_MainLayout);
        JP_MainLayout.setHorizontalGroup(
            JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MainLayout.createSequentialGroup()
                .addComponent(JP_Chat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(JP_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JP_MainLayout.setVerticalGroup(
            JP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Chat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JP_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SendMouseEntered
        // TODO add your handling code here:
        jp_Send.setBackground(jp_Send.getBackground().brighter());
    }//GEN-LAST:event_btn_SendMouseEntered

    private void btn_SendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SendMouseExited
        // TODO add your handling code here:
        jp_Send.setBackground(new Color(163, 73, 164));
    }//GEN-LAST:event_btn_SendMouseExited

    private void btn_SendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SendMouseClicked
        // TODO add your handling code here:
        if (!JTextAreaNewMessage.getText().equals("")) {
            messageController.sendMessage();
        }
    }//GEN-LAST:event_btn_SendMouseClicked

    private void JTextAreaNewMessageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextAreaNewMessageKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (evt.isShiftDown()) {
                JTextAreaNewMessage.append("\n");
            } else {
                messageController.sendMessage();
            }
        }
    }//GEN-LAST:event_JTextAreaNewMessageKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try {
            clientMainView.closeFriendChatBox(conversation);
        } catch (ConcurrentModificationException ex) {
        }
    }//GEN-LAST:event_formWindowClosed

    private void btn_SendMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SendMousePressed
        // TODO add your handling code here:
        jp_Send.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));
    }//GEN-LAST:event_btn_SendMousePressed

    private void btn_SendMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SendMouseReleased
        // TODO add your handling code here:
        jp_Send.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));
    }//GEN-LAST:event_btn_SendMouseReleased

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
//            java.util.logging.Logger.getLogger(ChatBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChatBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChatBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChatBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChatBox().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane JEditorPaneChatHistory;
    private javax.swing.JPanel JP_Avatar;
    private javax.swing.JPanel JP_Chat;
    private javax.swing.JPanel JP_Main;
    private javax.swing.JPanel JPanelChatHistory;
    private javax.swing.JPanel JPanelNewMessage;
    private javax.swing.JScrollPane JScrollPaneChatHistory;
    private javax.swing.JScrollPane JScrollPaneNewMessage;
    private javax.swing.JTextArea JTextAreaNewMessage;
    private javax.swing.JLabel btn_Send;
    private javax.swing.JPanel jp_Send;
    private javax.swing.JLabel lbl_FriendAvatar;
    private javax.swing.JLabel lbl_MyAvatar;
    // End of variables declaration//GEN-END:variables
}
