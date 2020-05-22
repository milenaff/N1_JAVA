/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio_1;
import EstadoConsole.MaquinaEstadoConsole;
import EstadoConsole.EnumEstadoConsole;
import br.com.business.ec6.crud.config.Config;
import br.com.comuns.crud.ec6.enums.TipoRepositorio;
import br.com.dao.ec6.crud.acesso.UsuarioTextoDAO;
import br.com.dao.ec6.crud.basis.GerenciadorAuditoriaSingleton;
import br.com.dao.ec6.crud.basis.ThreadGestaoMensagensAuditoria;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author 082170022
 */
public class Desafio_1 {

    public static MaquinaEstadoConsole estadoconsole;
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        //teste
        // TODO code application logic here
        Config.getInstance().setDatabase(TipoRepositorio.TEXTO);

        estadoconsole = EnumEstadoConsole.LOGIN.getEstadoMaquina();
        Boolean saida = false;
        GerenciadorAuditoriaSingleton.getInstancia().Ativa();

        
        while (!saida) {
            saida = estadoconsole.Executa();                
        }
        
        GerenciadorAuditoriaSingleton.getInstancia().Desativa();
        
    
        
    }
    
}
