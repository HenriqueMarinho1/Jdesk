package classes;
import javax.swing.JOptionPane;

public class Chamados {
    private String estatus;
    private String patrimonio;
    private String problema;
    private String desc_Acao;
    private int id = Usuario.idTecnico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getDesc_Acao() {
        return desc_Acao;
    }

    public void setDesc_Acao(String desc_Acao) {
        this.desc_Acao = desc_Acao;
    }
    
    public Boolean CriarChamados (int patrimonio, String problema, int id){
        Conexao banco = new Conexao();
        Boolean resultChamados;
        try{
            banco.AbrirConexao();
            banco.stmt = banco.con.createStatement();
            String sql = "INSERT INTO chamados (estatus, patri_equipamento, desc_problema, desc_acao, id_usuario) " + "VALUES ('"+ "Aberto" + "','" + patrimonio + "','" + problema + "','" + "sem desc" + "','" + id + "')";            
            banco.stmt.execute(sql);
            resultChamados = true;
            banco.FecharConexao();
        }catch(Exception ec){
            resultChamados = false;
            System.out.println("erro ao realizar o chamado " + ec.getMessage());
        }
        return resultChamados;
    }
    
    public void deletarChamado(String patrimonio) {
        Conexao banco = new Conexao();
        try {
            banco.AbrirConexao();
            String query = "UPDATE chamados SET estatus = 'D' WHERE patri_equipamento = '" + patrimonio + "'";
            banco.stmt = banco.con.createStatement();
            banco.stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Chamado deletado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar chamado: " + e.getMessage());
        } finally {
            banco.FecharConexao();
        }
    }
   
}
