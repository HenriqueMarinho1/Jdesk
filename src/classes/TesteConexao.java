package classes;

public class TesteConexao {
    public static void main(String[] args) {
          Conexao c = new Conexao();
          c.AbrirConexao();
        
        try {
            Thread.sleep(4000);
            c.FecharConexao();
        } catch (InterruptedException ex){
            System.out.println("Azedou fazer o projeto dormir, verifique");
        }
    }
    
}
