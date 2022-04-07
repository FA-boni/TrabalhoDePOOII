
package factory;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {
    
   public Connection getConnection(){
        
        try{
         
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/transporte?useTimezone=true&serverTimezone=UTC","root","00Gugu00");

    
} catch(SQLException excecao){
    
    throw new RuntimeException(excecao);
    
}
    }
}
