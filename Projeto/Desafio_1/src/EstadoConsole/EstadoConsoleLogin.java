/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;

import br.com.business.ec6.crud.acesso.Acesso;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import java.util.Scanner;
import desafio_1.Desafio_1;

/**
 *
 * @author 082170012
 */
public class EstadoConsoleLogin extends MaquinaEstadoConsole{

    @Override
    public boolean Executa() {
        boolean sair = false;
        Funcionario use = new Funcionario();
        Scanner scan = new Scanner(System.in);
        System.out.println("****LOGIN*****");
        System.out.println("Digite o seu usuário");
        use.setLogin(scan.nextLine());
        System.out.println("Digite a sua senha");
        use.setSenha(scan.nextLine());
        
        Acesso acesso = new Acesso();
        boolean senhavalida = acesso.validaUsuario(use);        
        
        /* Perfil 1 = Gerente
           Perfil 2 = Vendedor
        */
        
        if(senhavalida)        
        {
            int perfil=0;
            if(use.getLogin().equals("admin") && use.getSenha().equals("admin"))
                perfil=1;
            else
            {
             perfil= acesso.RetornaPerfilUser(use);
            }
            if(perfil == 1)
            { 
                acesso.CadastraPerfilLogin("um");
                desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.MENU_PRINCIPAL.getEstadoMaquina();
            }
            else
            {
                acesso.CadastraPerfilLogin("dois");
                desafio_1.Desafio_1.estadoconsole = EnumEstadoConsole.MENU_SECUNDARIO.getEstadoMaquina();
            }
        }
        else{
            System.out.println("Dados Inválidos");
        }
        return sair;
    }
    
}
