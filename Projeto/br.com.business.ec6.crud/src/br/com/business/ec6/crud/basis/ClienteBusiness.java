/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business.ec6.crud.basis;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.conteudo.Produto;

/**
 *
 * @author italo
 */
public class ClienteBusiness {
    public Cliente Preenche_Entidade (String array [])
       {
           Cliente cliente = new Cliente();
           cliente.setNome(array[0]);
           return cliente;
       }
       
       public boolean Valida_Nome(String descricao){
           boolean valida = true;
           if(descricao.isEmpty())
               valida = false;
       
           return valida;
       }
}
