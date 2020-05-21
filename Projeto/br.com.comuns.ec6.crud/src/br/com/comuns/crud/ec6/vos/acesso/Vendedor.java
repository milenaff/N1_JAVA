/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comuns.crud.ec6.vos.acesso;

import br.com.comuns.crud.ec6.vos.conteudo.Pedido;
import br.com.comuns.crud.ec6.vos.conteudo.Produto;

/**
 *
 * @author gabriell
 */
public class Vendedor  extends Funcionario {
    
    private boolean Cadastra_Produto(Produto produto){
        
        return true;
    }
    
     private boolean Cadastra_Pedido(Pedido pedido){
        
        return true;
    }
     
     private boolean Cadastra_Cliente(Cliente cliente){
        
        return true;
    }
    
}
