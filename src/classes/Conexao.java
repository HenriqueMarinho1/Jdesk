package classes;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {
    
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    private final String servidor = "jdbc:mysql://bneccbgbtpw7iarkufmx-mysql.services.clever-cloud.com:3306/bneccbgbtpw7iarkufmx";
    
    private final String usuario = "u4ruaplgks1bv8yf";
    private final String senha = "NaZpWt4gNJJLjYw2dC4H";
    private final String driver = "com.mysql.jdbc.Driver";
    
    public Connection AbrirConexao(){
        try {
            Class.forName(driver);
            
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement();
            
            System.out.println("Conexao aberta com sucesso");
        } catch (Exception e) {
            
            System.out.println("Erro ao acessar o banco de dados, verifique");
        }
        return con;
    }
    public void FecharConexao(){
        try {
            con.close();
            System.out.println("Conexao finalizada com sucesso");
        }catch (Exception e) {
            System.out.println("Erro ao encerrar conexao" + e.getMessage());
        }
    }
}