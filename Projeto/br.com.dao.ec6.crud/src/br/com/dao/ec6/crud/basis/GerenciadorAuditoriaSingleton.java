/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.basis;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author italo
 */
public class GerenciadorAuditoriaSingleton {
    
    private static Object objeto = new Object();
    public static GerenciadorAuditoriaSingleton _instance;

    public static GerenciadorAuditoriaSingleton getInstancia() {
        if (_instance == null) {
            _instance = new GerenciadorAuditoriaSingleton();
        }
        return _instance;
    }
    
    //fila de mensagens
    public static ConcurrentLinkedQueue<String> FilaMensagens = new ConcurrentLinkedQueue<String>();

    ThreadGestaoMensagensAuditoria Thread = new ThreadGestaoMensagensAuditoria();

    public void Adiciona_Msg_Auditoria(String mensagem) {
        FilaMensagens.add(mensagem);
    }

    public void Retira_Msg_Auditoria() {
        if (!FilaMensagens.isEmpty()) {
            FilaMensagens.remove();
        }
    }

    public void Ativa() {
        Thread.start();
    }

    public void Run() {
        Thread.run();
    }

    public void Desativa() throws InterruptedException {
        Thread.join();
    }

    public boolean IsAlive() {
        return Thread.isAlive();
    }


}
