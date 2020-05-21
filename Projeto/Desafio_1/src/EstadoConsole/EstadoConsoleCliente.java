/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;

import br.com.business.ec6.crud.acesso.Acesso;
import br.com.business.ec6.crud.basis.ClienteBusiness;
import br.com.dao.ec6.crud.produto.ClienteTxTDao;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class EstadoConsoleCliente extends MaquinaEstadoConsole{
        private  void mensagem(){
        System.out.println("**** Cliente ****");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Voltar");
        
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
                ClienteBusiness c = new ClienteBusiness();                
                System.out.println("**** CADASTRO CLIENTE ****");
                String resp="";
                valida = false;
                
                while(!valida){
                
                    System.out.println("Digite o nome ");
                    resp = scanner.nextLine();
                
                    if(c.Valida_Nome(resp)){
                        vetor[0]=resp;
                        valida = true;
                    }                    
                    else 
                        System.out.println("Nome Inválido");         
                
                }
                
                valida= false;
                            
                
                ClienteTxTDao dao = new ClienteTxTDao();                
              try {
                  dao.Cadastrar( c.Preenche_Entidade(vetor));
              } catch (Exception ex) {
                  Logger.getLogger(EstadoConsoleCliente.class.getName()).log(Level.SEVERE, null, ex);
              }
                
                
                break;
            }
            case 2:{
                
               ClienteTxTDao dao = new ClienteTxTDao(); 
            try {
                dao.Lista_Clientes();
            } catch (Exception ex) {
                Logger.getLogger(EstadoConsoleCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                break;
            }
            case 3:{
                  ClienteTxTDao dao = new ClienteTxTDao();
                   System.out.println("Para excluir digite o código de um dos clientes abaixo:");         
                   
                 try {   
                        valida = false;
                        while(!valida){                            
                        dao.Lista_Clientes(); 
                        resposta = scanner.nextLine();      
                        if(dao.valida_codigo_exclusão("Cliente.txt", resposta)){
                            valida = true;
                            int codigo = Integer.parseInt(resposta);
                            dao.Deleta_Cliente(codigo);
                        }                         
                        else
                            System.out.println("não existe esse código de cliente! ");
          
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
