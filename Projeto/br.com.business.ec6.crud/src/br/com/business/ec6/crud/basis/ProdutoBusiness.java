/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business.ec6.crud.basis;
import br.com.comuns.crud.ec6.vos.conteudo.Produto;
import br.com.dao.ec6.crud.produto.PedidoTxTDao;

/**
 *
 * @author 082170022
 */
public class ProdutoBusiness {
    
       public Produto Preenche_Entidade (String array [])
       {
           Produto prod = new Produto();
           prod.setDescricao(array[0]);
           prod.setPreco(Double.parseDouble(array[1]));
           return prod;
       }
       
       public boolean Valida_Descricao(String descricao){
           boolean valida = true;
           if(descricao.isEmpty())
               valida = false;
       
           return valida;
       }
       
       
    private boolean tryParseDouble(String value) {  
     try {  
         Double.parseDouble(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
}
    
    public boolean valida_preco(String resposta){
        
           if(tryParseDouble(resposta))            
               return true;
           else
               return false;          
    }   
  
       
       
    
}
