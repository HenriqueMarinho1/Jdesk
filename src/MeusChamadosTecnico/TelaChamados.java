package MeusChamadosTecnico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaChamados extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;

    public TelaChamados() {
        setTitle("Gestão de Chamados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do modelo da tabela
        model = new DefaultTableModel(new String[]{"ID", "Patrimônio", "Problema", "Status", "Ação"}, 0);
        tabela = new JTable(model);
        tabela.setFillsViewportHeight(true);
        tabela.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tabela.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String text = (value == null) ? "" : value.toString();
            setText(text);
            if (text.contains("Editar")) {
                setBackground(new Color(173, 216, 230)); // Azul claro
            } else if (text.contains("Concluir")) {
                setBackground(new Color(255, 255, 224)); // Amarelo claro
            } else {
                setBackground(Color.LIGHT_GRAY);
            }
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String text;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    if (text.equals("Editar")) {
                        // Lógica para editar o chamado
                        System.out.println("Editar chamado na linha: " + row);
                    } else if (text.equals("Concluir")) {
                        // Lógica para concluir o chamado
                        System.out.println("Concluir chamado na linha: " + row);
                    }
                }
            });
        }

       
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row) {
            this.row = row;
            text = (value == null) ? "" : value.toString();
            button.setText(text);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return text;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaChamados tela = new TelaChamados();
            tela.setVisible(true);
        });
    }
}
