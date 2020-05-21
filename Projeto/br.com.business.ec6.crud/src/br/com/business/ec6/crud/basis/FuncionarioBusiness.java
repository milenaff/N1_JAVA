/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business.ec6.crud.basis;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;

/**
 *
 * @author italo
 */
public class FuncionarioBusiness {
    public Funcionario Preenche_Entidade (String array [])
       {
           Funcionario func = new Funcionario();
           func.setNome(array[0]);
           func.setPerfil(Integer.parseInt(array[1]));
           func.setLogin(array[2]);
           func.setSenha(array[3]);
           return func;
       }
       
       public boolean Valida_Nome(String descricao){
           boolean valida = true;
           if(descricao.isEmpty())
               valida = false;
       
           return valida;
       }
       private boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
}
    
    public boolean valida_resposta_perfil(String resposta){
        
           if(tryParseInt(resposta)){
               int valor = Integer.parseInt(resposta);
               if(valor<= 2)
                    return true;
               else 
                   return false;
           }
           else
                   return false;
    }
    
}
