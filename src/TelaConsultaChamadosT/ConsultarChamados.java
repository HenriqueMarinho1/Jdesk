package TelaConsultaChamadosT;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import classes.Conexao;
import ChamadosAceitos.ChamadosAceitos;
import telaChamadoTec.telaChamadoTec;
import classes.Usuario;

public class ConsultarChamados {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConsultarChamados::abrirTela);
    }

    public static void abrirTela() {
        Conexao banco = new Conexao();
        JFrame frame = new JFrame("Consultar Chamados");
        frame.getContentPane().setBackground(Color.WHITE); 

        try {
            banco.AbrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.resultset = banco.stmt.executeQuery(
                    "SELECT chamados.estatus, chamados.patri_equipamento, chamados.desc_problema " +
                    "FROM chamados WHERE chamados.estatus = 'aberto';"
            );

            ArrayList<String[]> dadosList = new ArrayList<>();

            while (banco.resultset.next()) {
                String patrimonio = banco.resultset.getString("patri_equipamento");
                String descricao = banco.resultset.getString("desc_problema");
                dadosList.add(new String[]{patrimonio, descricao});
            }

            String[][] dados = new String[dadosList.size()][2];
            dadosList.toArray(dados);

            String[] colunas = {"Patrimônio", "Descrição", "Ação"};

            DefaultTableModel model = new DefaultTableModel(dados, colunas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2;
                }
            };

            JTable tabela = new JTable(model);
            tabela.setBackground(Color.WHITE); 
            tabela.setFillsViewportHeight(true); 
            tabela.getColumn("Ação").setCellRenderer(new ButtonRenderer());
            tabela.getColumn("Ação").setCellEditor(new ButtonEditor(new JCheckBox(), tabela));

            JScrollPane scrollPane = new JScrollPane(tabela);
            scrollPane.getViewport().setBackground(Color.WHITE);

            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
            btnVoltar.setBackground(new Color(173, 216, 230));
            btnVoltar.setOpaque(true);
            btnVoltar.setBorderPainted(false);
            btnVoltar.addActionListener(e -> {
                telaChamadoTec mainpage = new telaChamadoTec();
                mainpage.abrirTela();
                frame.dispose();
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.WHITE);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(btnVoltar, BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setSize(480, 400);
            frame.setResizable(true);
            frame.setVisible(true);

            banco.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setText("Aceitar");
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(new Color(144, 238, 144));
        setOpaque(true);
        setBorderPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Aceitar");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(144, 238, 144)); 
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int selectedRow = table.getSelectedRow();
            String patrimonio = table.getValueAt(selectedRow, 0).toString();
            aceitarChamado(patrimonio);
            JOptionPane.showMessageDialog(button, "Chamado de patrimônio " + patrimonio + " foi aceito!");
            SwingUtilities.getWindowAncestor(button).dispose();
            ChamadosAceitos.abrirTela();
        }
        isPushed = false;
        return "Aceitar";
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    private void aceitarChamado(String patrimonio) {
        Conexao banco = new Conexao();
        try {
            banco.AbrirConexao();
            banco.stmt = banco.con.createStatement();
            String sql = "UPDATE chamados SET estatus = 'Aceito', tecnico_id = '" + Usuario.idTecnico + "' WHERE patri_equipamento = '" + patrimonio + "'";
            banco.stmt.executeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(button, "Erro ao aceitar o chamado: " + e.getMessage());
        } finally {
            banco.FecharConexao();
        }
    }
}
