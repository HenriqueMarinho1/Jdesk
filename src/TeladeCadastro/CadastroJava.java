/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package TeladeCadastro;

import javax.swing.text.MaskFormatter;
import classes.Usuario;
import javax.swing.JFormattedTextField;
import java.text.ParseException;
import javax.swing.JOptionPane; 
import javax.swing.JPanel;
import TeladeLogin.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AbstractDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class NumericDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && string.matches("[0-9]*")) { // Permite apenas números
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text != null && text.matches("[0-9]*")) { // Permite apenas números
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }
}

/**
 *
 * @author renan.losantos
 */
public class CadastroJava extends javax.swing.JFrame {

    /**
     * Creates new form telaCadastro
     */
    public CadastroJava() {
 setLocation(680, 280);        initComponents();
          setResizable(false);
    
        
        btnCadastrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean usuarioCadastrado; 
                Usuario usuario = new Usuario();
                
                
                usuario.setEmail(txtEmail.getText());
                usuario.setNome(textNome.getText());
                String validasenha = null;
                String Psenha = txtSenha.getText();
                String Csenha = txtConfirmarSenha.getText();
                usuario.setSenha(validasenha);
                usuario.setData_nasc(txtDate.getText());
                
                if(usuario.getNome().length() <3){
                    JOptionPane.showMessageDialog(null, "Nome Inválido, Mínimo 3 caracteres!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(usuario.getNome().length() >255){
                    JOptionPane.showMessageDialog(null, "Nome Iválido, Limite de Caracteres excedido!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
		else if(usuario.getEmail().length() <12){
                    JOptionPane.showMessageDialog(null, "Email Inválido,  Mínimo 12 caracteres!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(usuario.getEmail().length() >40){
                    JOptionPane.showMessageDialog(null, "Email, Limite de Caracteres excedido!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
		 else if(Psenha.length() <6){
                    JOptionPane.showMessageDialog(null, "Senha Inválida,  Mínimo 6 caracteres!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(Psenha.length() >12){
                    JOptionPane.showMessageDialog(null, "Senha Inválida, Limite de Caracteres excedido!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
		else if(CPF.getText().length() < 14){
                    JOptionPane.showMessageDialog(null, "Cpf Inválido,  Mínimo 14 caracteres!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
		 else if( usuario.getData_nasc().length() <10){
                    JOptionPane.showMessageDialog(null, "Data Inválida,  Mínimo 10 Caracteres!! ", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                else if( usuario.getData_nasc().length() > 10){
                    JOptionPane.showMessageDialog(null, "Data Inválida, Limite de Caracteres excedido!! ", "Atenção", JOptionPane.ERROR_MESSAGE);
                    return;
                }
             
                    
                    
                //usuario.setCpf(CPF.getText());
                usuario.setData_nasc(txtDate.getText());
                String tipo = "";
               if (btnSim.isSelected() || btnNao.isSelected()) {
                    if (btnSim.isSelected()) {
                        tipo = "T";
                    }
                    if (btnNao.isSelected()) {
                        tipo = "F";
                    }
                }
                usuario.setTipo(tipo);
                usuario.setEstatus("A");
        
                if("".equals(usuario.getEmail())){
                    JOptionPane.showMessageDialog(null, "Campo e-mail precisa ser informado!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    txtEmail.grabFocus();
                }
                else if("".equals(txtSenha.getText())){
                    JOptionPane.showMessageDialog(null, "Campo senha precisa ser informado!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    txtSenha.grabFocus();
                }
                else if("".equals(usuario.getSenha())){
                    JOptionPane.showMessageDialog(null, "Campo confirmar senha precisa ser informado!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    txtConfirmarSenha.grabFocus();
                }
                else if("".equals(usuario.getCpf())){
                    JOptionPane.showMessageDialog(null, "Campo cpf precisa ser informado!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    CPF.grabFocus();
                }
                else if("".equals(usuario.getData_nasc())){
                    JOptionPane.showMessageDialog(null, "Campo data de nascimento precisa ser informado!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    txtDate.grabFocus();
                }
                else if("".equals(usuario.getTipo())){
                    JOptionPane.showMessageDialog(null, "Campo tipo precisa ser informado!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                   
                }else{                    
                    
                    if(usuario.getEmail() == null || Psenha == null || CPF.getText() == null || 
                            usuario.getData_nasc() == null || usuario.getTipo() == null) {
                        System.out.println("Campo e-mail: " + usuario.getEmail());
                        System.out.println("Campo senha: " + Psenha);
                        System.out.println("Campo cpf: " + CPF.getText());
                        System.out.println("Campo data de nascimento: " + usuario.getData_nasc());
                        System.out.println("Campo status: " + usuario.getTipo());
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar, Campo deve ser preenchido!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    }
                     else {
                        
                        usuarioCadastrado = usuario.CadastrarUsuario(usuario.getNome(),usuario.getEmail(), Psenha, CPF.getText(), usuario.getData_nasc(),usuario.getEstatus() ,usuario.getTipo());
                        if(usuarioCadastrado == true){
                            TelaLogin telaLogin = new TelaLogin();
                            telaLogin.abrirTela();
                            dispose();
                            JOptionPane.showMessageDialog(null, "Usuario cadastrado!!!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        }
                         else{
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!!!", "Atenção", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                }
            });
        btnVoltar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               TelaLogin telaLogin = new TelaLogin();
               telaLogin.abrirTela();
                dispose();
            }
            });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CPF = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtConfirmarSenha = new javax.swing.JFormattedTextField();
        textNome = new javax.swing.JFormattedTextField();
        txtDate = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSim = new javax.swing.JRadioButton();
        btnNao = new javax.swing.JRadioButton();
        btnCadastrar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        logoJava = new javax.swing.JLabel();
        
       

    // Adicionar o campo CPF ao painel
    try {
    MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
    cpfFormatter.setPlaceholderCharacter('_');
    CPF = new JFormattedTextField(cpfFormatter);
    
    // Aplicar o DocumentFilter
    ((AbstractDocument) CPF.getDocument()).setDocumentFilter(new NumericDocumentFilter());
} catch (ParseException e) {
    e.printStackTrace();
}

        
        
        
         buttonGroup1.add(btnSim);
         buttonGroup1.add(btnNao);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(520, 450));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Nome Completo:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 130, 120, 16);
        jPanel1.add(CPF);
        CPF.setBounds(320, 150, 170, 22);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Confirmar Senha:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 320, 110, 16);
        jPanel1.add(txtConfirmarSenha);
        txtConfirmarSenha.setBounds(50, 340, 170, 22);
        jPanel1.add(textNome);
        textNome.setBounds(50, 150, 170, 22);
        jPanel1.add(txtDate);
        txtDate.setBounds(320, 220, 170, 22);
        jPanel1.add(txtEmail);
        txtEmail.setBounds(50, 220, 170, 22);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("CPF");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(320, 130, 20, 16);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Você é um técnico?");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(310, 300, 180, 16);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("E-mail:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(50, 200, 70, 16);

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(txtSenha);
        txtSenha.setBounds(50, 280, 170, 22);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Senha:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(50, 260, 60, 16);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Data de Nascimento:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(320, 200, 130, 16);

        btnSim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSim.setText("Sim");
        jPanel1.add(btnSim);
        btnSim.setBounds(320, 320, 50, 21);

        btnNao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNao.setText("Não");
        jPanel1.add(btnNao);
        btnNao.setBounds(400, 320, 45, 21);

        btnCadastrar.setBackground(new java.awt.Color(244,68,52));
        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCadastrar.setText("CADASTRAR");
        jPanel1.add(btnCadastrar);
        btnCadastrar.setBounds(320, 390, 130, 40);

        btnVoltar.setBackground(new java.awt.Color(37,150,190));
        btnVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoltar.setText("VOLTAR");
        jPanel1.add(btnVoltar);
        btnVoltar.setBounds(90, 390, 130, 40);

        logoJava.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-logo-java-coffee-cup-144.png"))); // NOI18N
        logoJava.setText("jLabel8");
        jPanel1.add(logoJava);
        logoJava.setBounds(200, 10, 120, 120);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }      
    
    public void abrirTela(){
        CadastroJava telaDeCadastro = new CadastroJava();
        telaDeCadastro.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroJava().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JFormattedTextField CPF;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JRadioButton btnNao;
    private javax.swing.JRadioButton btnSim;
    private javax.swing.JButton btnVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logoJava;
    private javax.swing.JFormattedTextField textNome;
    private javax.swing.JFormattedTextField txtConfirmarSenha;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JFormattedTextField txtEmail;
    private javax.swing.JFormattedTextField txtSenha;
    // End of variables declaration                   
}
