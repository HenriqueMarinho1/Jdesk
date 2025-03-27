package ChamadosAceitos;

import classes.Conexao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import telaChamadoTec.telaChamadoTec;
import classes.Usuario;
import serviçoRealizado.serviçoRealizado;

public class ChamadosAceitos extends JFrame {
    public static String patrimonio;
    private JTable currentTable;

    public ChamadosAceitos() {
        setTitle("Chamados Aceitos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocation(680, 280);
        // Fundo branco para o JFrame
        getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // Fundo branco do painel

        JTable tabelaChamados = criarTabelaChamadosAceitos();
        JScrollPane scrollPane = new JScrollPane(tabelaChamados);
        scrollPane.getViewport().setBackground(Color.WHITE); // Fundo do viewport

        // Botão "Voltar" com estilização
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(173, 216, 230));
        btnVoltar.setForeground(Color.BLACK);

        btnVoltar.addActionListener(e -> {
            telaChamadoTec mainpage = new telaChamadoTec();
            mainpage.abrirTela();
            dispose();
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnVoltar, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private JTable criarTabelaChamadosAceitos() {
        String[] colunas = {"Patrimônio", "Problema", "Ação"};
        String[][] dados = obterDadosChamadosAceitos();
        DefaultTableModel model = new DefaultTableModel(dados, colunas);

        currentTable = new JTable(model);

        // Configuração da JTable e cabeçalho
        currentTable.setBackground(Color.WHITE);
        currentTable.setGridColor(Color.LIGHT_GRAY);
        currentTable.setShowGrid(true);
        currentTable.getTableHeader().setBackground(Color.WHITE);
        currentTable.getTableHeader().setForeground(Color.BLACK);

        // Renderizador e editor de botões
        currentTable.getColumnModel().getColumn(2).setCellRenderer(new ActionButtonRenderer());
        currentTable.getColumnModel().getColumn(2).setCellEditor(new ActionButtonEditor(currentTable));

        return currentTable;
    }

    private String[][] obterDadosChamadosAceitos() {
        Conexao banco = new Conexao();
        ArrayList<String[]> dadosList = new ArrayList<>();

        try {
            banco.AbrirConexao();
            banco.stmt = banco.con.createStatement();
            String sql = "SELECT patri_equipamento, desc_problema, desc_acao FROM chamados WHERE estatus = 'aceito' AND tecnico_id = '" + Usuario.idTecnico + "'";
            ResultSet resultSet = banco.stmt.executeQuery(sql);

            while (resultSet.next()) {
                String patrimonio = resultSet.getString("patri_equipamento");
                String problema = resultSet.getString("desc_problema");
                String acao = resultSet.getString("desc_acao");
                dadosList.add(new String[]{patrimonio, problema, acao});
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
        new ChamadosAceitos();
    }

    private class ActionButtonRenderer extends JPanel implements TableCellRenderer {
        private final JButton btnConcluir = new JButton("Concluir");
        private final JButton btnCancelar = new JButton("Cancelar");

        public ActionButtonRenderer() {
            setLayout(new GridLayout(1, 2, 5, 5));
            configurarBotoes();
        }

        private void configurarBotoes() {
            btnConcluir.setFont(new Font("Arial", Font.BOLD, 12));
            btnConcluir.setBackground(Color.GREEN);
            btnConcluir.setForeground(Color.WHITE);

            btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
            btnCancelar.setBackground(Color.RED);
            btnCancelar.setForeground(Color.WHITE);

            add(btnConcluir);
            add(btnCancelar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    private class ActionButtonEditor extends AbstractCellEditor implements javax.swing.table.TableCellEditor {
        private final JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        private final JButton btnConcluir = new JButton("Concluir");
        private final JButton btnCancelar = new JButton("Cancelar");

        public ActionButtonEditor(JTable currentTable) {
            configurarBotoes();
        }

        private void configurarBotoes() {
            btnConcluir.setFont(new Font("Arial", Font.BOLD, 12));
            btnConcluir.setBackground(Color.GREEN);
            btnConcluir.setForeground(Color.WHITE);

            btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
            btnCancelar.setBackground(Color.RED);
            btnCancelar.setForeground(Color.WHITE);

            btnConcluir.addActionListener(e -> {
                int currentRow = currentTable.getSelectedRow();
                if (currentRow != -1) { 
                    patrimonio = currentTable.getValueAt(currentRow, 0).toString(); 
                    Conexao banco = new Conexao();
                    serviçoRealizado sr = new serviçoRealizado();
                    dispose();
                    sr.abrirTela();
                }
                fireEditingStopped();
            });

            btnCancelar.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Chamado Cancelado!");
                fireEditingStopped();
            });

            panel.add(btnConcluir);
            panel.add(btnCancelar);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }
}
