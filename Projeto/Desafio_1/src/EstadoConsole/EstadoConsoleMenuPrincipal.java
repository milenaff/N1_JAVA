/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;

import java.util.Scanner;
import br.com.business.ec6.crud.acesso.Acesso;
import desafio_1.Desafio_1;

/**
 *
 * menu para gerente @author 082170012
 */
public class EstadoConsoleMenuPrincipal extends MaquinaEstadoConsole {
   private  void mensagem(){
        System.out.println("**** MENU ****");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Produtos");
        System.out.println("2 - Pedidos");
        System.out.println("3 - Funcionario");
        System.out.println("4 - Cliente"); 
        
       }
   
   
    @Override
    public boolean Executa() {
        
        Scanner scanner = new Scanner(System.in);
        boolean valida= false;  
        String resposta = "";
        
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
                desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.PRODUTO.getEstadoMaquina();    
                break;
            }
            case 2:{
                desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.PEDIDO.getEstadoMaquina();    
                break;
            }
            case 3:{
                desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.FUNCIONARIO.getEstadoMaquina();    
                break;
            }
            case 4:{
                desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.CLIENTE.getEstadoMaquina();    
                break;
            }
        
        
        }
       
        
     
        
        
        return false;
    }
    
}
