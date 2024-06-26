/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import client.ClientApp;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class HomeCandidato extends javax.swing.JFrame {

    private ClientApp clientApp;
    private String usuario;
    private String token;
    private String email;


    public HomeCandidato(ClientApp clientApp, String usuario, String email, String token) {
        initComponents();
        this.clientApp = clientApp;
        this.usuario = usuario;
        this.token = token;
        this.email = email;
        this.visualizarUsusario(usuario, email);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnCandidato = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inpNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inpSenha = new javax.swing.JTextField();
        bttnAtualizar = new javax.swing.JToggleButton();
        bttnLogout = new javax.swing.JToggleButton();
        bttnApagar = new javax.swing.JToggleButton();
        btnCompetencias = new javax.swing.JToggleButton();
        btnVagas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bem vindo(a)");

        pnCandidato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nome");

        inpNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpNomeActionPerformed(evt);
            }
        });

        jLabel2.setText("E-mail");

        inpEmail.setEditable(false);
        inpEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpEmailActionPerformed(evt);
            }
        });

        jLabel4.setText("Senha");

        inpSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCandidatoLayout = new javax.swing.GroupLayout(pnCandidato);
        pnCandidato.setLayout(pnCandidatoLayout);
        pnCandidatoLayout.setHorizontalGroup(
            pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnCandidatoLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel3)
                    .addContainerGap(292, Short.MAX_VALUE)))
        );
        pnCandidatoLayout.setVerticalGroup(
            pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(pnCandidatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnCandidatoLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jLabel3)
                    .addContainerGap(246, Short.MAX_VALUE)))
        );

        bttnAtualizar.setText("Atualizar dados");
        bttnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAtualizarActionPerformed(evt);
            }
        });

        bttnLogout.setText("Sair");
        bttnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnLogoutActionPerformed(evt);
            }
        });

        bttnApagar.setText("Deletar conta");
        bttnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnApagarActionPerformed(evt);
            }
        });

        btnCompetencias.setText("Competências");
        btnCompetencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompetenciasActionPerformed(evt);
            }
        });

        btnVagas.setText("Vagas");
        btnVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVagasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(pnCandidato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bttnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCompetencias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bttnAtualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCompetencias)
                        .addGap(18, 18, 18)
                        .addComponent(btnVagas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttnLogout)
                        .addGap(18, 18, 18)
                        .addComponent(bttnApagar))
                    .addComponent(pnCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAtualizarActionPerformed
        String nome = this.inpNome.getText();
        String senha = this.inpSenha.getText();

        JSONObject request = new JSONObject();
        request.put("operacao", "atualizar" + this.usuario);
        request.put("email", this.email);
        request.put("senha", senha);
        request.put("nome", nome);
        request.put("token", token);

        String response = this.clientApp.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 201) {
            JOptionPane.showMessageDialog(HomeCandidato.this, "Usuario atualizado com sucesso!", "Sucesso Atualizar Usuario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(HomeCandidato.this, responseJson.getString("mensagem"), "Erro Atualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bttnAtualizarActionPerformed

    private void bttnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnLogoutActionPerformed
        JSONObject request = new JSONObject();
        request.put("operacao", "logout");
        request.put("token", this.token);

        String response = this.clientApp.callServer(request);

        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 204) {
            new Login(this.clientApp).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(HomeCandidato.this, responseJson.getString("mensagem"), "Erro Logout", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnLogoutActionPerformed

    private void inpNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpNomeActionPerformed

    private void inpEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpEmailActionPerformed

    private void inpSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpSenhaActionPerformed

    private void bttnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnApagarActionPerformed
        JSONObject request = new JSONObject();

        request.put("operacao", "apagar" + this.usuario);
        request.put("email", this.inpEmail.getText());
        request.put("token", this.token);

        String response = this.clientApp.callServer(request);
        JSONObject responseJson = new JSONObject(response);

        if (responseJson.getInt("status") == 201) {
            new Login(this.clientApp).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(HomeCandidato.this, responseJson.getString("mensagem"), "Erro Apagar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnApagarActionPerformed

    private void btnCompetenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompetenciasActionPerformed
        new CompetenciaExperiencia(this.clientApp, this.token, this.email).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCompetenciasActionPerformed

    private void btnVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVagasActionPerformed
        new Vaga(clientApp, usuario, email, token).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVagasActionPerformed

    private void visualizarUsusario(String usuario, String email) {
        JSONObject request = new JSONObject();

        request.put("operacao", "visualizar" + usuario);
        request.put("email", email);
        request.put("token", token);

        String response = this.clientApp.callServer(request);

        JSONObject responseJson = new JSONObject(response);
        if (responseJson.getInt("status") == 201) {
            this.inpNome.setText(responseJson.getString("nome"));
            this.inpEmail.setText(email);
            this.inpSenha.setText(responseJson.getString("senha"));
        } else {
            JOptionPane.showMessageDialog(HomeCandidato.this, responseJson.getString("mensagem"), "Erro Visualizar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCompetencias;
    private javax.swing.JButton btnVagas;
    private javax.swing.JToggleButton bttnApagar;
    private javax.swing.JToggleButton bttnAtualizar;
    private javax.swing.JToggleButton bttnLogout;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpNome;
    private javax.swing.JTextField inpSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnCandidato;
    // End of variables declaration//GEN-END:variables
}
