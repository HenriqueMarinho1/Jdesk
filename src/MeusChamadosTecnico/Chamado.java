
package MeusChamadosTecnico;


public class Chamado {
    
    
    
    private int id;
    private String patrimonio;
    private String problema;
    private String status;

    public Chamado(int id, String patrimonio, String problema, String status) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.problema = problema;
        this.status = status;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getPatrimonio() { return patrimonio; }
    public String getProblema() { return problema; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
