package chatapp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
public static String username;
String password;
int start=0;
int port =2222;
Socket socket;
String cond;
BufferedReader reader;
PrintWriter writer;
 Thread T = new Thread(new LoginRun());

//********************

public void sendToServer(String username,String pass,String key){
    try{
            socket = new Socket("localhost",port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
             System.out.println("send....");
             writer.println(username+":"+pass+":"+key+":"+"data");
             writer.flush();
    }  catch(Exception e){
           JOptionPane.showMessageDialog(null,"server has not started");
    }

}

public class LoginRun implements Runnable{
String[] data;
        @Override
        public void run() {
            String[] data;
            try{
                String result;
                 
                String message=reader.readLine();
                
                data=message.split(":");
                result=data[0];
                  message=data[1];
                  System.out.println(data[0]+"\n");
                  cond=result;
                  System.out.println(data[0]+"-"+data[1]);
               if(result.equals("true")){
                   
              start_client_frame(username);
                   setVisible(false);
               }if(result.equals("false")){
                   JOptionPane.showMessageDialog(null,"password or username is incorect");
                   jPasswordField1.setText(null);
               }
                    
                if(result.equals("login"))
                    echec.setText(message);
                 if(result.equals("newaccount")){
                     JOptionPane.showMessageDialog(null,data[1]);
                    // T.destroy();
                 }
                    
              
               
                
            }catch(Exception e){
                
            }
        }
}
 public static void start_client_frame(String msg){
                        Client cf=new Client();
                        cf.setTitle("Chat Interface");
                         cf.jLabel1.setText(msg);
                        cf.setVisible(true);
  }
public void ThreadListner(){
   
    T.start();
}
//********************
    public Login() {
        initComponents();
    }



  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Ccpassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        Cusername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Cpassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        echec = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        create = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(815, 485));
        getContentPane().setLayout(null);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setText("S'inscrire");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(360, 400, 170, 30);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 250, 230, 30);
        getContentPane().add(Ccpassword);
        Ccpassword.setBounds(310, 350, 280, 30);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 300, 200, 0);
        getContentPane().add(Cusername);
        Cusername.setBounds(310, 250, 280, 30);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 350, 230, 30);

        Cpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CpasswordActionPerformed(evt);
            }
        });
        getContentPane().add(Cpassword);
        Cpassword.setBounds(310, 300, 280, 30);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 90, 170, 25);

        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(160, 90, 170, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setText("S'identifier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(660, 90, 115, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(360, 86, 110, 0);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(478, 90, 150, 30);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(470, 1065, 170, 0);
        getContentPane().add(echec);
        echec.setBounds(71, 468, 340, 0);

        jSeparator2.setMinimumSize(new java.awt.Dimension(760, 20));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(20, 148, 760, 2);
        getContentPane().add(create);
        create.setBounds(75, 1071, 290, 0);

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\chatapp project\\images\\ba1.png")); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(-10, -10, 810, 470);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(60, 160, 70, 2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(!Cusername.getText().equals("") && !Cpassword.getText().equals("")){
           if(Cpassword.getText().equals(Ccpassword.getText())){
               sendToServer(Cusername.getText(),Cpassword.getText(),"newaccount");
               
             username=Cusername.getText();
               start_client_frame(username);
               setVisible(false);
                ThreadListner();
           }else{
               JOptionPane.showMessageDialog(null,"the password should be identique");
           }
           
       }else
            JOptionPane.showMessageDialog(null,"username and password should be....");
           
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CpasswordActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        username=jTextField1.getText();
              password=jPasswordField1.getText();
           sendToServer(username,password,"login");
           ThreadListner();
   
    }//GEN-LAST:event_jButton1ActionPerformed

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Ccpassword;
    private javax.swing.JPasswordField Cpassword;
    private javax.swing.JTextField Cusername;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel create;
    private javax.swing.JLabel echec;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
