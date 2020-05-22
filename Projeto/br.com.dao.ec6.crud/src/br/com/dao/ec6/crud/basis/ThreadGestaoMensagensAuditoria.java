/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.basis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class ThreadGestaoMensagensAuditoria extends Thread {

    boolean Status = true;
    @Override
    public void run() {                
        try {
            while (Status) {
                if (!GerenciadorAuditoriaSingleton.getInstancia().FilaMensagens.isEmpty()) {
                    Mensagem_Seguranca(GerenciadorAuditoriaSingleton.getInstancia().FilaMensagens.element());
                    GerenciadorAuditoriaSingleton.getInstancia().Retira_Msg_Auditoria();                    
                }
            }
            Thread.sleep(10000000);

        } catch (Exception ex) {
            Logger.getLogger(ThreadGestaoMensagensAuditoria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Mensagem_Seguranca(String cadastro) throws Exception {
        // The name of the file to open.
        String fileName = "Sistema_Controle_Seguranca.txt";
        TXTDao dao = new TXTDao();
        int codigo = dao.gera_codigo(fileName);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String resposta = "Um " + cadastro + " foi cadastrado com sucesso no sistema no dia " + dtf.format(now);

        try {

            FileWriter fileWriter
                    = new FileWriter(fileName, true);

            BufferedWriter bufferedWriter
                    = new BufferedWriter(fileWriter);

            bufferedWriter.append(codigo + "|" + resposta + "\n");

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                    + fileName + "'");

        }
    }

    
}
