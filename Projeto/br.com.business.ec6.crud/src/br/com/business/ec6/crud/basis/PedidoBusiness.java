/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business.ec6.crud.basis;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import br.com.comuns.crud.ec6.vos.conteudo.Pedido;
import br.com.comuns.crud.ec6.vos.conteudo.Produto;
import br.com.dao.ec6.crud.produto.ClienteTxTDao;
import br.com.dao.ec6.crud.produto.FuncionarioTxTDao;
import br.com.dao.ec6.crud.produto.PedidoTxTDao;
import br.com.dao.ec6.crud.produto.ProdutoTxTDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author italo
 */
public class PedidoBusiness {
    
    
    public Pedido Preenche_Entidade (String array []) throws ParseException
       {
           Pedido pedido = new Pedido();
           
            pedido.setCliente(Integer.parseInt(array[0]));
           
            pedido.setProdutos(Integer.parseInt(array[1]));           
           
           Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(array[2]);
           pedido.setData(date1);
           
           pedido.setFuncionario(Integer.parseInt(array[3]));
           
           
           return pedido;
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
    
     private boolean tryParseDate(String value)  {  
     try {  
         Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(value); 
         return true;  
      } catch (ParseException e) {  
         return false;  
      }  
}
     
     public  boolean valida_data (String data) throws ParseException{
         
         if(tryParseDate(data))
             return true;
         else 
             return false;
     
     }
    
    
    public boolean valida_cliente(String codigo){
    
    PedidoTxTDao dao = new PedidoTxTDao();
    return dao.valida_codigo_exclusão("Cliente.txt",codigo);
    
    }
    
    public boolean valida_Funcionario(String codigo){
    
    PedidoTxTDao dao = new PedidoTxTDao();
    return dao.valida_codigo_exclusão("Funcionario.txt",codigo);
    
    }
    
    public boolean valida_Produto(String codigo){
    
    PedidoTxTDao dao = new PedidoTxTDao();
    return dao.valida_codigo_exclusão("Produto.txt",codigo);
    
    }
    public void Exibe_Pedido () throws Exception
    {

        PedidoTxTDao t= new PedidoTxTDao ();
        ClienteTxTDao c = new ClienteTxTDao();
        FuncionarioTxTDao f = new FuncionarioTxTDao();
        ProdutoTxTDao p = new ProdutoTxTDao(); 
        Pedido ped= t.Busca_Pedido(t.ReadFile("Pedido.txt"));
        
         SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
         String dataPedido = formatador.format(ped.getData());
         
        System.out.println("**** INFORMAÇÕES DO PEDIDO****"); 
         System.out.println("Código Pedido: "+ped.getId()); 
        System.out.println("Cliente: "+c.Busca_Cliente(ped.getCliente()).getNome()); 
        System.out.println("Data: "+ dataPedido); 
        System.out.println("Descrição: "+p.Busca_Produto(ped.getProdutos()).getDescricao());
        System.out.println("Funcionário: "+f.Busca_Funcionario(ped.getFuncionario()).getNome());
    }
    
}
