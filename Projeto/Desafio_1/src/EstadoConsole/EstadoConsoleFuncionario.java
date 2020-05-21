/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;

import br.com.business.ec6.crud.acesso.Acesso;
import br.com.business.ec6.crud.basis.ClienteBusiness;
import br.com.business.ec6.crud.basis.FuncionarioBusiness;
import br.com.dao.ec6.crud.produto.ClienteTxTDao;
import br.com.dao.ec6.crud.produto.FuncionarioTxTDao;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class EstadoConsoleFuncionario extends MaquinaEstadoConsole {
 private  void mensagem(){
        System.out.println("**** Funcionario ****");
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
        Acesso valid = new Acesso();
        
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
                FuncionarioBusiness c = new FuncionarioBusiness();                
                System.out.println("**** CADASTRO FUNCIONARIO ****");
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
                
                 while(!valida){
                
                    System.out.println("Digite o perfil (1-Gerente 2-Vendedor) ");
                    resp = scanner.nextLine();
                
                    if(c.valida_resposta_perfil(resp)){
                        vetor[1]=resp;
                        valida = true;
                    }                    
                    else 
                        System.out.println("Perfil Inválido");         
                
                }
                valida= false;    
                    while(!valida){
                
                    System.out.println("Digite o login ");
                    resp = scanner.nextLine();
                
                    if(c.Valida_Nome(resp)){
                        vetor[2]=resp;
                        vetor[3]="teste";
                        if(valid.validaUsuario(c.Preenche_Entidade(vetor)))
                        {
                            valida=false;
                             System.out.println("Login já cadastrado");
                         }                       
                        else
                            valida = true;
                    }                    
                    else 
                        System.out.println("Login Inválido");         
                
                }
                valida= false;
                while(!valida){
                
                    System.out.println("Digite a senha ");
                    resp = scanner.nextLine();
                
                    if(c.Valida_Nome(resp)){
                        vetor[3]=resp;
                        valida = true;
                    }                    
                    else 
                        System.out.println("Senha Inválido");         
                
                }
                
                FuncionarioTxTDao dao = new FuncionarioTxTDao();                
              try {
                  dao.Cadastrar( c.Preenche_Entidade(vetor));
              } catch (Exception ex) {
                  Logger.getLogger(EstadoConsoleFuncionario.class.getName()).log(Level.SEVERE, null, ex);
              }
                
                
                break;
            }
            case 2:{
                
               FuncionarioTxTDao dao = new FuncionarioTxTDao(); 
            try {
                dao.Lista_Funcionario();
            } catch (Exception ex) {
                Logger.getLogger(EstadoConsoleCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                break;
            }
            case 3:{
                
                  FuncionarioTxTDao dao = new FuncionarioTxTDao();
                  System.out.println("Para excluir digite o código de um dos funciinários abaixo:");         
                   
                 try {   
                        valida = false;
                        while(!valida){                            
                        dao.Lista_Funcionario(); 
                        resposta = scanner.nextLine();      
                        if(dao.valida_codigo_exclusão("Funcionario.txt", resposta)){
                            valida = true;
                            int codigo = Integer.parseInt(resposta);
                            dao.Deleta_Funcionario(codigo);
                        }                         
                        else
                            System.out.println("não existe esse código de funcionario! ");
          
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

//
    
}
