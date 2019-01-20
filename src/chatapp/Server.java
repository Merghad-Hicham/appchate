package chatapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server extends javax.swing.JFrame {
    
   ArrayList clientOutputStreams;
   ArrayList<String> users;
   int index=0;
  


    
    //***********************************************************
           public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;       
       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception e) 
            {
                areaServer.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
           String[] data;
           try {
             
             String message=reader.readLine();
             data=message.split(":");
             
             areaServer.append(message+"\n");
          
             if(data[2].equals("login") && verfiUser(data[0],data[1])==1){
                  userAdd(data[0]);
                  areaServer.append("gooood \n");
             }
        
              if(data[3].equals("newlogin")){
                  
                  if(userExist(data[1])==0){
                       users.add(data[1]);
                  }
                  else{
                     userRemove(data[1]);  
                  }
                  if(!users.isEmpty()){
                       String s=users.get(0);
             for(int i=1;i<users.size();i++)
              s= s+":"+users.get(i);
             s=s+":addconnect";
                toClient(s); 
                areaServer.append(s+"\n");
                  }else{
                      toClient("NoOne:addconnect"); 
                  }
                  
              }
              
              if(data[2].equals("login") && verfiUser(data[0],data[1])!=1)
             {
                 areaServer.append("password or username is incorect");
                 toClient("false:blabla");
             }
              if(data[2].equals("newaccount")){
                  int x=verfiUser(data[0]);
                 int t=  creatAccount(data[0],data[1]);
                 if(t!=0 && x==0)
                   toClient("newaccount:account has created");
                 else
                 toClient("newaccount:echec create new account"); 
              }
             if(data[3].equals("chat")){
                  toClient(data[0]+":"+data[1]+":"+data[2]+":"+"chat");
              } 
            
             
              
            
  } catch (IOException e) {
                   areaServer.append("sameone left server.....\n");
           }
             
           
	} 
    }
           
           public int creatAccount(String username,String password){
             int t= Acces_donnee.mise_ajour("INSERT INTO `chatapp`.`client` "
                     + "(`id`, `nom`, `prenom`) VALUES (NULL, '"+username+"', '"+password+"')");
               return t;
           }
           
           public int userExist(String user){
               for(int i=0;i<users.size();i++){
                   if(user.equals(users.get(i)))
                       return 1;
               }
               return 0;
           }
           public int verfiUser(String username, String password){
                try {
             ResultSet rs=Acces_donnee.selection("SELECT * FROM client");
             while(rs.next()){
                 if((rs.getString(2)).equals(username) && (rs.getString(3)).equals(password)){
                      return 1; 
                   }
                                }
                     } catch (SQLException ex) {
                         
         }
               return 0;
           }
           
            public int verfiUser(String username){
                try {
             ResultSet rs=Acces_donnee.selection("SELECT * FROM client");
             while(rs.next()){
                 if((rs.getString(2)).equals(username)){
                      return 1; 
                   }
                                }
                     } catch (SQLException ex) {
                     }
               return 0;
           }
         
   
  public void userAdd(String user){

      toClient("true:"+user+" has connected \n");
           
      
  }
  public void userRemove(String user){
      int size=users.size();
       for(int i=0;i<size;i++){
           if(user.equals(users.get(i))){
                users.remove(i);
                size=size-1;
           }
              
       }   
  }

  
  public void toClient(String message){
       Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
                writer.flush();

            } 
            catch (Exception e) 
            {
		
            }
        } 
    }
    public void show(String message){
        areaServer.append("user :"+message+"\n");
    }
    public class ServerStart implements Runnable{
      BufferedReader reader;
       Socket socket;
       PrintWriter writer;
            String[] msgclient;
        @Override
        public void run() {
            
                    clientOutputStreams = new ArrayList();
                     users = new ArrayList();  
         try 
            {
                ServerSocket serverSock = new ServerSocket(2222);
                while (true) 
                {
				Socket clientSock = serverSock.accept();
				 writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);
				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				
                }
            }
            catch (Exception e)
            {
                areaServer.append("Error making a connection. \n");
            }
            
        }
        
    }
    
   //************************************* 
    
    
    
    
    public Server() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaServer = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SERVER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("STAT SERVER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton1)
                .addContainerGap(309, Short.MAX_VALUE))
        );

        areaServer.setColumns(20);
        areaServer.setRows(5);
        jScrollPane1.setViewportView(areaServer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 171, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

                 Thread starter = new Thread(new ServerStart());
                 starter.start();
                 
               //  areaServer.append(list.get(0).username+" "+list.get(0).password);
                 areaServer.append("start server....\n");


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaServer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
