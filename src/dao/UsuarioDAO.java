
package dao;

import factory.Conexao;
import model.Usuario;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class UsuarioDAO {
    
    private Connection connection;
    int id;
    String username;
    String senha;
    String perfil;
    
    public UsuarioDAO(){
       
        this.connection = new Conexao().getConnection();
        
    }
    
    public void inserir(Usuario usuario){
    
        String sql = "INSERT INTO medicos(nome, senha, perfil) "
                + "VALUES(?,?,?)";
        
       try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, usuario.getUsername());
           stmt.setString(2, usuario.getSenha());
           stmt.setString(3, usuario.getPerfil());
           stmt.execute();
           stmt.close();
           
           
           
       }
       catch(SQLException u){
           throw  new RuntimeException(u);
       } 
          
    }
    
    
    public Usuario consultar(Usuario usuario){
        
        Usuario consulta = new Usuario();
        String sql = "select nome, senha, perfil = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,String.valueOf(usuario.getId()));
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                consulta.setUsername(rs.getString(1));
                consulta.setSenha(rs.getString(2));
                consulta.setPerfil(rs.getString(3));
            }
            else{
                consulta = null;
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return(consulta);
    }
        
    
    } 