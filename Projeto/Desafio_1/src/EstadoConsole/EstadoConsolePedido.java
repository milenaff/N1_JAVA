/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;

import br.com.business.ec6.crud.acesso.Acesso;
import br.com.business.ec6.crud.basis.PedidoBusiness;
import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import br.com.dao.ec6.crud.produto.ClienteTxTDao;
import br.com.dao.ec6.crud.produto.FuncionarioTxTDao;
import br.com.dao.ec6.crud.produto.PedidoTxTDao;
import br.com.dao.ec6.crud.produto.ProdutoTxTDao;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class EstadoConsolePedido extends MaquinaEstadoConsole {
  private  void mensagem(){
        System.out.println("**** PEDIDO ****");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Excluir"); 
        System.out.println("4 - Sair");         
       }  
    @Override
    public boolean Executa() {
        
        Scanner scanner = new Scanner(System.in);
        boolean valida= false;  
        String resposta = ""; 
        String [] vetor = new String[4];
        PedidoBusiness ped = new PedidoBusiness();
        
          while(!valida){
          mensagem();  
          resposta = scanner.nextLine();
          Acesso validacao = new Acesso();
          if(validacao.valida_resposta_usuario(resposta, 5))
              valida = true;
          else
              System.out.println("Resposta inválida ! ");
          
        }
          
        int answer = Integer.parseInt(resposta);
        
        switch(answer){
        
            case 1:{
                
                PedidoBusiness p = new PedidoBusiness();                
                System.out.println("**** CADASTRO PEDIDO ****");
                String resp="";
                valida = false;
                ClienteTxTDao cliente = new ClienteTxTDao();
                while(!valida){
                
                    System.out.println("Digite o código do Cliente");
                    try {
                        cliente.Lista_Clientes();
                    } catch (Exception ex) {
                        Logger.getLogger(EstadoConsolePedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    resp = scanner.nextLine();
                
                    if(p.valida_cliente(resp)){
                        vetor[0]=resp;
                        valida = true;
                    }                    
                    else 
                        System.out.println("Código inválido");         
                
                }
                ProdutoTxTDao produto = new ProdutoTxTDao();
                valida= false;
                while(!valida){
                    
                    System.out.println("Digite o código do Produto");
                    try {
                        produto.Lista_Produtos();
                    } catch (Exception ex) {
                        Logger.getLogger(EstadoConsolePedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    resp = scanner.nextLine();
                
                    if(p.valida_Produto(resp)){
                        vetor[1]=resp;
                        valida = true;
                    }                        
                    else 
                        System.out.println("Código inválido");                
                }
                
                valida= false;
                while(!valida){
                    
                    System.out.println("Digite a Data");
                    resp = scanner.nextLine();
                
                    try {
                        if(p.valida_data(resp)){
                            vetor[2]=resp;
                            valida = true;
                        }
                        else                
                            System.out.println("Data Inválida");
                    } catch (ParseException ex) {
                        Logger.getLogger(EstadoConsolePedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              
                FuncionarioTxTDao funcionario = new FuncionarioTxTDao();
                valida= false;
                while(!valida){
                    
                    System.out.println("Digite o código do Funcionário");
                    try {
                        funcionario.Lista_Funcionario();
                    } catch (Exception ex) {
                        Logger.getLogger(EstadoConsolePedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    resp = scanner.nextLine();                
                   
                    if(p.valida_Funcionario(resp)){
                       vetor[3]=resp;
                       valida = true;
                    }
                    else                
                      System.out.println("código inválido");
                   
                }
                
                PedidoTxTDao dao = new PedidoTxTDao();         
                
              try {
                  dao.Cadastrar( p.Preenche_Entidade(vetor));
                  ped.Exibe_Pedido();
              } catch (Exception ex) {
                  Logger.getLogger(EstadoConsoleProduto.class.getName()).log(Level.SEVERE, null, ex);
              }
                
                
                break;
            }
            case 2:{
                
               PedidoTxTDao dao = new PedidoTxTDao(); 
            try {
                dao.Lista_Pedidos();
            } catch (Exception ex) {
                Logger.getLogger(EstadoConsoleProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                break;
            }
            case 3:{
                   PedidoTxTDao dao = new PedidoTxTDao();
                   System.out.println("Para excluir digite o código de um dos pedidos abaixo:");         
                   
                 try {   
                        valida = false;
                        while(!valida){                            
                        dao.Lista_Pedidos(); 
                        resposta = scanner.nextLine();      
                        if(dao.valida_codigo_exclusão("Pedido.txt", resposta)){
                            valida = true;
                            int codigo = Integer.parseInt(resposta);
                            dao.Deleta_Pedido(codigo);
                        }                         
                        else
                            System.out.println("não existe esse código de pedido! ");
          
                        }
                        
                    } catch (Exception ex) {
                        Logger.getLogger(EstadoConsoleProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                break;
            } 
            case 4:{
                
                
                 Acesso validacao = new Acesso();
                 try {
                    String perfil = validacao.PerfilLogado();
                    if(perfil.equals("um"))
                        desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.MENU_PRINCIPAL.getEstadoMaquina();
                    else
                        desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.MENU_SECUNDARIO.getEstadoMaquina();
                 
                 } catch (Exception ex) {
                        Logger.getLogger(EstadoConsoleCliente.class.getName()).log(Level.SEVERE, null, ex);
                  }
                
                
                break;
            }
            case 5:{
                FuncionarioTxTDao f= new FuncionarioTxTDao();
                Funcionario cli= f.Busca_Funcionario(2);
                System.out.println(cli.getNome());
            }
            }       
          
         
          return false;
    }
    
}
