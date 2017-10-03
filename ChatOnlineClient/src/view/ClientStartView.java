/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UserController;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Valdez
 */
public class ClientStartView extends javax.swing.JFrame {

    UserController userController = new UserController(this);

    /**
     * Creates new form Start
     */
    public ClientStartView() {
        initComponents();
        this.setLocationRelativeTo(null);
        lbl_SignIn.requestFocusInWindow();
    }

    private String SHA_1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            showMessage(ex.getStackTrace().toString());
        }
        return null;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void toMainView(User user) {
        ClientMainView clientMainView = new ClientMainView(user);
        clientMainView.setVisible(true);
        this.dispose();
    }

    public User getUserLogin() {
        User model = new User();
        model.setUsername(txt_UsernameSI.getText().trim());
        model.setPassword_hash(SHA_1(new String(pwd_PasswordSI.getPassword()).trim()));
        txt_UsernameSI.requestFocusInWindow();
        return model;
    }

    public User getUserSignUp() {
        User model = new User();
        model.setUsername(txt_UsernameJU.getText().trim());
        model.setEmail(txt_EmailJU.getText().trim());
        model.setPassword_hash(SHA_1(new String(pwd_PasswordJU.getPassword()).trim()));
        txt_UsernameJU.requestFocusInWindow();
        return model;
    }

    public void switchToLogin() {
        JP_Main.removeAll();
        JP_Main.repaint();
        JP_Main.revalidate();
        JP_Main.add(JP_Login);
        JP_Main.repaint();
        JP_Main.revalidate();
        this.setTitle("Sign in");
    }

    public void switchToSignUp() {
        JP_Main.removeAll();
        JP_Main.repaint();
        JP_Main.revalidate();
        JP_Main.add(JP_SignUp);
        JP_Main.repaint();
        JP_Main.revalidate();
        this.setTitle("Join us");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pwd_PasswordSI = new javax.swing.JPasswordField();
        pwd_PasswordJU = new javax.swing.JPasswordField();
        JP_Main = new javax.swing.JPanel();
        JP_Login = new javax.swing.JPanel();
        lbl_SignIn = new javax.swing.JLabel();
        txt_UsernameSI = new javax.swing.JTextField();
        undl_UsernameSI = new javax.swing.JSeparator();
        txt_PasswordSI = new javax.swing.JTextField();
        undl_PasswordSI = new javax.swing.JSeparator();
        jp_Login = new javax.swing.JPanel();
        btn_Login = new javax.swing.JLabel();
        jp_ToSignUp = new javax.swing.JPanel();
        btn_ToSignUp = new javax.swing.JLabel();
        JP_SignUp = new javax.swing.JPanel();
        lbl_JoinUs = new javax.swing.JLabel();
        txt_UsernameJU = new javax.swing.JTextField();
        undl_UsernameJU = new javax.swing.JSeparator();
        txt_EmailJU = new javax.swing.JTextField();
        undl_EmailJU = new javax.swing.JSeparator();
        txt_PasswordJU = new javax.swing.JTextField();
        undl_PasswordJU = new javax.swing.JSeparator();
        jp_SignUp = new javax.swing.JPanel();
        btn_SignUp = new javax.swing.JLabel();
        jp_ToLogin = new javax.swing.JPanel();
        btn_ToLogin = new javax.swing.JLabel();

        pwd_PasswordSI.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pwd_PasswordSI.setForeground(new java.awt.Color(255, 255, 255));
        pwd_PasswordSI.setBorder(null);
        pwd_PasswordSI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pwd_PasswordSIFocusLost(evt);
            }
        });
        pwd_PasswordSI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pwd_PasswordSIKeyPressed(evt);
            }
        });

        pwd_PasswordJU.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pwd_PasswordJU.setForeground(new java.awt.Color(255, 255, 255));
        pwd_PasswordJU.setBorder(null);
        pwd_PasswordJU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pwd_PasswordJUFocusLost(evt);
            }
        });
        pwd_PasswordJU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pwd_PasswordJUKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign in");
        setResizable(false);

        JP_Main.setBackground(new java.awt.Color(38, 40, 55));
        JP_Main.setLayout(new java.awt.CardLayout());

        JP_Login.setBackground(JP_Main.getBackground());

        lbl_SignIn.setFont(new java.awt.Font("Ravie", 0, 36)); // NOI18N
        lbl_SignIn.setForeground(new java.awt.Color(255, 255, 255));
        lbl_SignIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_SignIn.setText("Sign in");

        txt_UsernameSI.setBackground(JP_Main.getBackground());
        txt_UsernameSI.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_UsernameSI.setForeground(java.awt.Color.lightGray);
        txt_UsernameSI.setText(" Username");
        txt_UsernameSI.setBorder(null);
        txt_UsernameSI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_UsernameSIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_UsernameSIFocusLost(evt);
            }
        });

        txt_PasswordSI.setBackground(JP_Main.getBackground());
        txt_PasswordSI.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_PasswordSI.setForeground(java.awt.Color.lightGray);
        txt_PasswordSI.setText(" Password");
        txt_PasswordSI.setBorder(null);
        txt_PasswordSI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_PasswordSIFocusGained(evt);
            }
        });

        jp_Login.setBackground(new java.awt.Color(58, 56, 77));
        jp_Login.setPreferredSize(new java.awt.Dimension(700, 70));

        btn_Login.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_Login.setForeground(new java.awt.Color(255, 255, 255));
        btn_Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Login.setText("Login");
        btn_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_LoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_LoginMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jp_LoginLayout = new javax.swing.GroupLayout(jp_Login);
        jp_Login.setLayout(jp_LoginLayout);
        jp_LoginLayout.setHorizontalGroup(
            jp_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jp_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btn_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );
        jp_LoginLayout.setVerticalGroup(
            jp_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jp_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btn_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        jp_ToSignUp.setBackground(new java.awt.Color(58, 56, 77));
        jp_ToSignUp.setPreferredSize(new java.awt.Dimension(700, 70));

        btn_ToSignUp.setFont(new java.awt.Font("Cooper Black", 2, 20)); // NOI18N
        btn_ToSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btn_ToSignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_ToSignUp.setText("Creat a new account...");
        btn_ToSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ToSignUpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ToSignUpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ToSignUpMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jp_ToSignUpLayout = new javax.swing.GroupLayout(jp_ToSignUp);
        jp_ToSignUp.setLayout(jp_ToSignUpLayout);
        jp_ToSignUpLayout.setHorizontalGroup(
            jp_ToSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_ToSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_ToSignUpLayout.setVerticalGroup(
            jp_ToSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_ToSignUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_LoginLayout = new javax.swing.GroupLayout(JP_Login);
        JP_Login.setLayout(JP_LoginLayout);
        JP_LoginLayout.setHorizontalGroup(
            JP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_ToSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_SignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JP_LoginLayout.createSequentialGroup()
                .addGroup(JP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_LoginLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addGroup(JP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(undl_PasswordSI)
                            .addComponent(undl_UsernameSI)
                            .addComponent(txt_UsernameSI, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txt_PasswordSI)))
                    .addGroup(JP_LoginLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jp_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        JP_LoginLayout.setVerticalGroup(
            JP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_LoginLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lbl_SignIn)
                .addGap(50, 50, 50)
                .addComponent(txt_UsernameSI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undl_UsernameSI, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_PasswordSI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undl_PasswordSI, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jp_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jp_ToSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JP_Main.add(JP_Login, "card2");

        JP_SignUp.setBackground(JP_Main.getBackground());

        lbl_JoinUs.setFont(new java.awt.Font("Ravie", 0, 36)); // NOI18N
        lbl_JoinUs.setForeground(new java.awt.Color(255, 255, 255));
        lbl_JoinUs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_JoinUs.setText("Join us");

        txt_UsernameJU.setBackground(JP_Main.getBackground());
        txt_UsernameJU.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_UsernameJU.setForeground(java.awt.Color.lightGray);
        txt_UsernameJU.setText(" Username");
        txt_UsernameJU.setBorder(null);
        txt_UsernameJU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_UsernameJUFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_UsernameJUFocusLost(evt);
            }
        });

        txt_EmailJU.setBackground(JP_Main.getBackground());
        txt_EmailJU.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_EmailJU.setForeground(java.awt.Color.lightGray);
        txt_EmailJU.setText(" Email");
        txt_EmailJU.setBorder(null);
        txt_EmailJU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_EmailJUFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_EmailJUFocusLost(evt);
            }
        });

        txt_PasswordJU.setBackground(JP_Main.getBackground());
        txt_PasswordJU.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_PasswordJU.setForeground(java.awt.Color.lightGray);
        txt_PasswordJU.setText(" Password");
        txt_PasswordJU.setBorder(null);
        txt_PasswordJU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_PasswordJUFocusGained(evt);
            }
        });

        jp_SignUp.setBackground(new java.awt.Color(58, 56, 77));
        jp_SignUp.setPreferredSize(new java.awt.Dimension(700, 70));

        btn_SignUp.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_SignUp.setForeground(new java.awt.Color(255, 255, 255));
        btn_SignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_SignUp.setText("Sign up");
        btn_SignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SignUpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_SignUpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_SignUpMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jp_SignUpLayout = new javax.swing.GroupLayout(jp_SignUp);
        jp_SignUp.setLayout(jp_SignUpLayout);
        jp_SignUpLayout.setHorizontalGroup(
            jp_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_SignUpLayout.createSequentialGroup()
                .addComponent(btn_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_SignUpLayout.setVerticalGroup(
            jp_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_SignUp, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jp_ToLogin.setBackground(new java.awt.Color(58, 56, 77));
        jp_ToLogin.setPreferredSize(new java.awt.Dimension(700, 70));

        btn_ToLogin.setFont(new java.awt.Font("Cooper Black", 2, 20)); // NOI18N
        btn_ToLogin.setForeground(new java.awt.Color(255, 255, 255));
        btn_ToLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_ToLogin.setText("Already have an account? Login here...");
        btn_ToLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ToLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ToLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ToLoginMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jp_ToLoginLayout = new javax.swing.GroupLayout(jp_ToLogin);
        jp_ToLogin.setLayout(jp_ToLoginLayout);
        jp_ToLoginLayout.setHorizontalGroup(
            jp_ToLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_ToLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jp_ToLoginLayout.setVerticalGroup(
            jp_ToLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_ToLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_SignUpLayout = new javax.swing.GroupLayout(JP_SignUp);
        JP_SignUp.setLayout(JP_SignUpLayout);
        JP_SignUpLayout.setHorizontalGroup(
            JP_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_ToLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_JoinUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_SignUpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(undl_PasswordJU)
                    .addComponent(undl_EmailJU)
                    .addComponent(undl_UsernameJU)
                    .addComponent(txt_UsernameJU, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txt_EmailJU, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_PasswordJU, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(225, 225, 225))
            .addGroup(JP_SignUpLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jp_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_SignUpLayout.setVerticalGroup(
            JP_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_SignUpLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lbl_JoinUs)
                .addGap(50, 50, 50)
                .addComponent(txt_UsernameJU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undl_UsernameJU, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_EmailJU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undl_EmailJU, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_PasswordJU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undl_PasswordJU, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jp_ToLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JP_Main.add(JP_SignUp, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UsernameSIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UsernameSIFocusGained
        // TODO add your handling code here:
        if (txt_UsernameSI.getText().equals(" Username")) {
            txt_UsernameSI.setText("");
            txt_UsernameSI.setForeground(Color.WHITE);
            txt_UsernameSI.setCaretColor(Color.WHITE);
        }
    }//GEN-LAST:event_txt_UsernameSIFocusGained

    private void txt_UsernameSIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UsernameSIFocusLost
        // TODO add your handling code here:
        if (txt_UsernameSI.getText().isEmpty()) {
            txt_UsernameSI.setText(" Username");
            txt_UsernameSI.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txt_UsernameSIFocusLost

    private void txt_PasswordSIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PasswordSIFocusGained
        // TODO add your handling code here:
        JP_Login.add(pwd_PasswordSI);
        pwd_PasswordSI.setBounds(txt_PasswordSI.getBounds());
        JP_Login.remove(txt_PasswordSI);
        pwd_PasswordSI.setBackground(JP_Login.getBackground());
        pwd_PasswordSI.requestFocusInWindow();
        pwd_PasswordSI.setCaretColor(Color.WHITE);
        Rectangle temp1 = undl_PasswordSI.getBounds();
        JP_Login.remove(undl_PasswordSI);
        JP_Login.add(undl_PasswordSI);
        undl_PasswordSI.setBounds(temp1);
        Rectangle temp2 = jp_Login.getBounds();
        JP_Login.remove(jp_Login);
        JP_Login.add(jp_Login);
        jp_Login.setBounds(temp2);
    }//GEN-LAST:event_txt_PasswordSIFocusGained

    private void pwd_PasswordSIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwd_PasswordSIFocusLost
        // TODO add your handling code here:
        if (pwd_PasswordSI.getPassword().length == 0) {
            JP_Login.add(txt_PasswordSI);
            txt_PasswordSI.setText(" Password");
            txt_PasswordSI.setBounds(txt_PasswordSI.getBounds());
            JP_Login.remove(pwd_PasswordSI);
            Rectangle temp1 = undl_PasswordSI.getBounds();
            JP_Login.remove(undl_PasswordSI);
            JP_Login.add(undl_PasswordSI);
            undl_PasswordSI.setBounds(temp1);
            Rectangle temp2 = jp_Login.getBounds();
            JP_Login.remove(jp_Login);
            JP_Login.add(jp_Login);
            jp_Login.setBounds(temp2);
        }
    }//GEN-LAST:event_pwd_PasswordSIFocusLost

    private void btn_LoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LoginMouseEntered
        // TODO add your handling code here:
        jp_Login.setBackground(new Color(100, 100, 135));
    }//GEN-LAST:event_btn_LoginMouseEntered

    private void btn_LoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LoginMouseExited
        // TODO add your handling code here:
        jp_Login.setBackground(new Color(58, 56, 77));
    }//GEN-LAST:event_btn_LoginMouseExited

    private void btn_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LoginMouseClicked
        // TODO add your handling code here:
        if (txt_UsernameSI.getText().equals("") || pwd_PasswordSI.getPassword().length == 0) {
            showMessage("please fill the required fields (username, password).");
        } else {
            userController.login();
        }
    }//GEN-LAST:event_btn_LoginMouseClicked

    private void pwd_PasswordSIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwd_PasswordSIKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_UsernameSI.getText().equals("") || pwd_PasswordSI.getPassword().length == 0) {
                showMessage("please fill the required fields (username, password).");
            } else {
                userController.login();
            }
        }
    }//GEN-LAST:event_pwd_PasswordSIKeyPressed

    private void txt_UsernameJUFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UsernameJUFocusGained
        // TODO add your handling code here:
        if (txt_UsernameJU.getText().equals(" Username")) {
            txt_UsernameJU.setText("");
            txt_UsernameJU.setForeground(Color.WHITE);
            txt_UsernameJU.setCaretColor(Color.WHITE);
        }
    }//GEN-LAST:event_txt_UsernameJUFocusGained

    private void txt_UsernameJUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UsernameJUFocusLost
        // TODO add your handling code here:
        if (txt_UsernameJU.getText().isEmpty()) {
            txt_UsernameJU.setText(" Username");
            txt_UsernameJU.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txt_UsernameJUFocusLost

    private void txt_EmailJUFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EmailJUFocusGained
        // TODO add your handling code here:
        if (txt_EmailJU.getText().equals(" Email")) {
            txt_EmailJU.setText("");
            txt_EmailJU.setForeground(Color.WHITE);
            txt_EmailJU.setCaretColor(Color.WHITE);
        }
    }//GEN-LAST:event_txt_EmailJUFocusGained

    private void txt_PasswordJUFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PasswordJUFocusGained
        // TODO add your handling code here:
        JP_SignUp.add(pwd_PasswordJU);
        pwd_PasswordJU.setBounds(txt_PasswordJU.getBounds());
        txt_PasswordJU.setText("");
        JP_SignUp.remove(txt_PasswordJU);
        pwd_PasswordJU.setBackground(JP_SignUp.getBackground());
        pwd_PasswordJU.requestFocusInWindow();
        pwd_PasswordJU.setCaretColor(Color.WHITE);
        Rectangle temp1 = undl_PasswordJU.getBounds();
        JP_SignUp.remove(undl_PasswordJU);
        JP_SignUp.add(undl_PasswordJU);
        undl_PasswordJU.setBounds(temp1);
        Rectangle temp2 = jp_SignUp.getBounds();
        JP_SignUp.remove(jp_SignUp);
        JP_SignUp.add(jp_SignUp);
        jp_SignUp.setBounds(temp2);
    }//GEN-LAST:event_txt_PasswordJUFocusGained

    private void btn_SignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SignUpMouseClicked
        // TODO add your handling code here:
        if (txt_UsernameJU.getText().equals("") || txt_EmailJU.getText().equals("") || pwd_PasswordJU.getPassword().length == 0) {
            showMessage("please fill the required fields (username, email, password).");
        } else if (!txt_UsernameJU.getText().matches("^[\\w-]{6,15}$")) {
            showMessage("Username must contain 6 to 15 characters. Only letter, number and underscore are allowed!");
        } else if (!txt_EmailJU.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            showMessage("Invalid email!");
        } else if (pwd_PasswordJU.getPassword().length < 6) {
            showMessage("Password must contain at least 6 characters!");
        } else {
            userController.signUp();
        }
    }//GEN-LAST:event_btn_SignUpMouseClicked

    private void btn_SignUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SignUpMouseEntered
        // TODO add your handling code here:
        jp_SignUp.setBackground(new Color(100, 100, 135));
    }//GEN-LAST:event_btn_SignUpMouseEntered

    private void btn_SignUpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SignUpMouseExited
        // TODO add your handling code here:
        jp_SignUp.setBackground(new Color(58, 56, 77));
    }//GEN-LAST:event_btn_SignUpMouseExited

    private void pwd_PasswordJUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwd_PasswordJUFocusLost
        // TODO add your handling code here:
        if (pwd_PasswordJU.getPassword().length == 0) {
            JP_SignUp.add(txt_PasswordJU);
            txt_PasswordJU.setText(" Password");
            txt_PasswordJU.setBounds(txt_PasswordJU.getBounds());
            JP_SignUp.remove(pwd_PasswordJU);
            Rectangle temp1 = undl_PasswordJU.getBounds();
            JP_SignUp.remove(undl_PasswordJU);
            JP_SignUp.add(undl_PasswordJU);
            undl_PasswordJU.setBounds(temp1);
            Rectangle temp2 = jp_SignUp.getBounds();
            JP_SignUp.remove(jp_SignUp);
            JP_SignUp.add(jp_SignUp);
            jp_SignUp.setBounds(temp2);
        }
    }//GEN-LAST:event_pwd_PasswordJUFocusLost

    private void pwd_PasswordJUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwd_PasswordJUKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_UsernameJU.getText().equals("") || txt_EmailJU.getText().equals("") || pwd_PasswordJU.getPassword().length == 0) {
                showMessage("please fill the required fields (username, email, password).");
            } else if (!txt_UsernameJU.getText().matches("^[\\w-]{6,15}$")) {
                showMessage("Username must contain 6 to 15 characters. Only letter, number and underscore are allowed!");
            } else if (!txt_EmailJU.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                showMessage("Invalid email!");
            } else if (pwd_PasswordJU.getPassword().length < 6) {
                showMessage("Password must contain at least 6 characters!");
            } else {
                userController.signUp();
            }
        }
    }//GEN-LAST:event_pwd_PasswordJUKeyPressed

    private void txt_EmailJUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EmailJUFocusLost
        // TODO add your handling code here:
        if (txt_EmailJU.getText().isEmpty()) {
            txt_EmailJU.setText(" Email");
            txt_EmailJU.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txt_EmailJUFocusLost

    private void btn_ToSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ToSignUpMouseClicked
        // TODO add your handling code here:
        switchToSignUp();
    }//GEN-LAST:event_btn_ToSignUpMouseClicked

    private void btn_ToSignUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ToSignUpMouseEntered
        // TODO add your handling code here:
        jp_ToSignUp.setBackground(new Color(100, 100, 135));
    }//GEN-LAST:event_btn_ToSignUpMouseEntered

    private void btn_ToSignUpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ToSignUpMouseExited
        // TODO add your handling code here:
        jp_ToSignUp.setBackground(new Color(58, 56, 77));
    }//GEN-LAST:event_btn_ToSignUpMouseExited

    private void btn_ToLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ToLoginMouseClicked
        // TODO add your handling code here:
        switchToLogin();
    }//GEN-LAST:event_btn_ToLoginMouseClicked

    private void btn_ToLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ToLoginMouseEntered
        // TODO add your handling code here:
        jp_ToLogin.setBackground(new Color(100, 100, 135));
    }//GEN-LAST:event_btn_ToLoginMouseEntered

    private void btn_ToLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ToLoginMouseExited
        // TODO add your handling code here:
        jp_ToLogin.setBackground(new Color(58, 56, 77));
    }//GEN-LAST:event_btn_ToLoginMouseExited

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
//
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ClientStartView.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ClientStartView.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ClientStartView.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ClientStartView.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ClientStartView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JP_Login;
    private javax.swing.JPanel JP_Main;
    private javax.swing.JPanel JP_SignUp;
    private javax.swing.JLabel btn_Login;
    private javax.swing.JLabel btn_SignUp;
    private javax.swing.JLabel btn_ToLogin;
    private javax.swing.JLabel btn_ToSignUp;
    private javax.swing.JPanel jp_Login;
    private javax.swing.JPanel jp_SignUp;
    private javax.swing.JPanel jp_ToLogin;
    private javax.swing.JPanel jp_ToSignUp;
    private javax.swing.JLabel lbl_JoinUs;
    private javax.swing.JLabel lbl_SignIn;
    private javax.swing.JPasswordField pwd_PasswordJU;
    private javax.swing.JPasswordField pwd_PasswordSI;
    private javax.swing.JTextField txt_EmailJU;
    private javax.swing.JTextField txt_PasswordJU;
    private javax.swing.JTextField txt_PasswordSI;
    private javax.swing.JTextField txt_UsernameJU;
    private javax.swing.JTextField txt_UsernameSI;
    private javax.swing.JSeparator undl_EmailJU;
    private javax.swing.JSeparator undl_PasswordJU;
    private javax.swing.JSeparator undl_PasswordSI;
    private javax.swing.JSeparator undl_UsernameJU;
    private javax.swing.JSeparator undl_UsernameSI;
    // End of variables declaration//GEN-END:variables
}
