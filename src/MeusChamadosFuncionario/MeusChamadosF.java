package MeusChamadosFuncionario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
// import javax.swing.table.DefaultCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import classes.Conexao;
import classes.Usuario;
import telaChamado.telaChamado;


public class MeusChamadosF extends JFrame {

    public MeusChamadosF(){
        setTitle("Chamados Aceitos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocation(680, 280);
        JPanel panel = new JPanel(new BorderLayout());
        JTable tabelaChamados = criarTabelaMeusChamados();
        JScrollPane scrollPane = new JScrollPane(tabelaChamados);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            telaChamado telaChamado = new telaChamado();
            telaChamado.abrirTela();
            dispose();
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnVoltar, BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
    }

    public JTable criarTabelaMeusChamados() {
        String[] colunas = {"Patrimônio", "Problema", "Ação"};
        String[][] dados = obterDadosMeusChamados();
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);

        // Renderizador para o botão de deletar
        tabela.getColumn("Ação").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton button = new JButton("Deletar");
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
                return button;
            }
        });

        // Adiciona o ActionListener ao botão
        tabela.getColumn("Ação").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton button = new JButton("Deletar");
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);

                button.addActionListener(e -> {
                    String patrimonio = (String) table.getValueAt(row, 0);
                    deletarChamado(patrimonio);
                    ((DefaultTableModel) table.getModel()).removeRow(row);
                });
                return button;
            }
        });

        return tabela;
    }

    private void deletarChamado(String patrimonio) {
        Conexao banco = new Conexao();
        try {
            banco.AbrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.executeUpdate("DELETE FROM chamados WHERE patri_equipamento = '" + patrimonio + "'");
            JOptionPane.showMessageDialog(null, "Chamado deletado com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar chamado: " + e.getMessage());
        } finally {
            banco.FecharConexao();
        }
    }

    private String[][] obterDadosMeusChamados() {
        Conexao banco = new Conexao();
        ArrayList<String[]> dadosList = new ArrayList<>();
        try {
            int id = Usuario.idTecnico;
            banco.AbrirConexao();
            banco.stmt = banco.con.createStatement();
            ResultSet resultSet = banco.stmt.executeQuery("SELECT patri_equipamento, desc_problema FROM chamados WHERE id_usuario = '" + id + "'");
            while (resultSet.next()) {
                String patrimonio = resultSet.getString("patri_equipamento");
                String problema = resultSet.getString("desc_problema");
                dadosList.add(new String[]{patrimonio, problema, "Deletar"});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar chamados aceitos: " + e.getMessage());
        } finally {
            banco.FecharConexao();
        }
        String[][] dados = new String[dadosList.size()][3];
        dadosList.toArray(dados);
        return dados;
    }

    public static void abrirTela() {
        new MeusChamadosF();
    }
}
