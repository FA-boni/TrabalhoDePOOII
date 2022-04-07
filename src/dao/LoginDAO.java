
package dao;

import factory.Conexao;
import gui.Login;
import gui.Menu_admin;
import gui.Menu_motorista;
import gui.Menu_usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;


public class LoginDAO {
    
    private Connection connection;
    private String perfil;
    private String username;
    private String senha;
    private final Login gui;
    
     public LoginDAO(Login gui){ 
        this.connection = new Conexao().getConnection();
        this.gui = gui;
    }
     
   
     
     public void logar(){
       
         
         String username = gui.getTxtUsername().getText();
         String senha = gui.getTxtSenha().getText();
         
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        try (Connection conn = (Connection) 
                
                DriverManager.getConnection("jdbc:mysql://localhost:3306/transporte?useTimezone=true&serverTimezone=UTC","root","00Gugu00"); 
                Statement stmt = (Statement) conn.createStatement()) {

            String query = "select username from usuarios where username = '" + username +"' and senha = '" + senha + "'";

                try (ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
       
                        if(perfil != "Administrador"){
                           JOptionPane.showMessageDialog(null,"Conectado com sucesso");
                           Menu_admin menu = new Menu_admin();
                           menu.setVisible(true);
                           this.gui.dispose();
                        }
        
                   
                    
                    if(perfil!= "Motorista"){
                           JOptionPane.showMessageDialog(null,"Conectado com sucesso");
                           Menu_motorista menu = new Menu_motorista();
                           menu.setVisible(true);
                           this.gui.dispose();
                        }
                     if(perfil != "Usuário"){
                           JOptionPane.showMessageDialog(null,"Conectado com sucesso");
                           Menu_usuario menu = new Menu_usuario();
                           menu.setVisible(true);
                           this.gui.dispose();
                        }
        
                    }
                    
                    
                    

                    else {
                        JOptionPane.showMessageDialog(null,"Usuário e/ou senha incorretos.");
                        gui.getTxtUsername().setText("");
                        gui.getTxtSenha().setText("");
                        
                    }   
                }

    
        }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        
   }
     
      
    
    
}