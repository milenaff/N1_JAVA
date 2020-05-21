/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;
import java.util.Scanner;
import br.com.business.ec6.crud.acesso.Acesso;
import br.com.business.ec6.crud.basis.ProdutoBusiness;
import br.com.dao.ec6.crud.produto.ProdutoTxTDao;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Pattern;
/**
 *
 * @author italo
 */
public class EstadoConsoleProduto extends MaquinaEstadoConsole {
       private  void mensagem(){
        System.out.println("**** PRODUTO ****");
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
        String [] vetor = new String[3];
          while(!valida){
          mensagem();  
          resposta = scanner.nextLine();
          Acesso validacao = new Acesso();
          if(validacao.valida_resposta_usuario(resposta, 4))
              valida = true;
          else
              System.out.println("Resposta inválida ! ");
          
        }
          
        int answer = Integer.parseInt(resposta);
        
        switch(answer){
        
            case 1:{
                ProdutoBusiness p = new ProdutoBusiness();                
                System.out.println("**** CADASTRO PRODUTO ****");
                String resp="";
                valida = false;
                
                while(!valida){
                
                    System.out.println("Digite a descrição");
                    resp = scanner.nextLine();
                
                    if(p.Valida_Descricao(resp)){
                        vetor[0]=resp;
                        valida = true;
                    }                    
                    else 
                        System.out.println("Descrição Inválida");         
                
                }
                
                valida= false;
                while(!valida){
                    
                    System.out.println("Digite o preço");
                    resp = scanner.nextLine();
                
                    if(p.valida_preco(resp)){
                        vetor[1]=resp;
                        valida = true;
                    }                        
                    else 
                        System.out.println("Preço Inválido");                
                }
              
                
                ProdutoTxTDao dao = new ProdutoTxTDao();                
              try {
                  dao.Cadastrar( p.Preenche_Entidade(vetor));
              } catch (Exception ex) {
                  Logger.getLogger(EstadoConsoleProduto.class.getName()).log(Level.SEVERE, null, ex);
              }
                
                
                break;
            }
            case 2:{
                
               ProdutoTxTDao dao = new ProdutoTxTDao(); 
            try {
                dao.Lista_Produtos();
            } catch (Exception ex) {
                Logger.getLogger(EstadoConsoleProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                break;
            }
            case 3:{
                   ProdutoTxTDao dao = new ProdutoTxTDao();
                   System.out.println("Para excluir digite o código de um dos produtos abaixo:");         
                   
                 try {   
                        valida = false;
                        while(!valida){                            
                        dao.Lista_Produtos(); 
                        resposta = scanner.nextLine();      
                        if(dao.valida_codigo_exclusão("Produto.txt", resposta)){
                            valida = true;
                            int codigo = Integer.parseInt(resposta);
                            dao.Deleta_Produto(codigo);
                        }                         
                        else
                            System.out.println("não existe esse código de produto! ");
          
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
            }       
          
         
          return false;
    }
    
    
}
