/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business.ec6.crud.acesso;

import br.com.business.ec6.crud.basis.FabricaRepositorio;
import br.com.comuns.crud.ec6.enums.EntidadesDisponiveis;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import br.com.dao.ec6.crud.repositorio.basis.Repositorio;
import br.com.dao.ec6.crud.acesso.UsuarioTextoDAO;

/**
 *
 * @author gabriell
 */
public class Acesso {
    
    private boolean validaSenha(String senhaRepositorio, String senhaDigitada){
        return (senhaRepositorio.equals(senhaDigitada)); 
    }
    
    public boolean validaUsuario(Funcionario user) {
        boolean retorno = false;
        if(user.getLogin().equals("admin") && user.getSenha().equals("admin"))
        {
            return true;
        }
        Repositorio repositorio = FabricaRepositorio.Fabrica();        
        Funcionario usuario = (Funcionario)repositorio.localiza(user.getLogin(), EntidadesDisponiveis.USUARIO);
        if (usuario.getLogin() != null)
        {
            retorno = validaSenha(usuario.getSenha(), user.getSenha());
        }
        return retorno;
    }
    private boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
}
    
    public boolean valida_resposta_usuario(String resposta,int limite_resposta){
        
           if(tryParseInt(resposta)){
               int valor = Integer.parseInt(resposta);
               if(valor<= limite_resposta)
                    return true;
               else 
                   return false;
           }
           else
                   return false;
    }
    public int RetornaPerfilUser (Funcionario f)
    {
        int perfil=0;
        Repositorio repositorio = FabricaRepositorio.Fabrica();        
        Funcionario usuario = (Funcionario)repositorio.localiza(f.getLogin(), EntidadesDisponiveis.USUARIO);
        if (usuario != null)
        {
            perfil = usuario.getPerfil();
            return perfil;
        }
        return 0;
    }
    
    public String PerfilLogado() throws Exception{
        
        UsuarioTextoDAO usuario= new UsuarioTextoDAO ();      
        
        return usuario.RetornaPerfilLogado();
    }
    
    public void CadastraPerfilLogin( String t)
    {
        UsuarioTextoDAO usuario= new UsuarioTextoDAO ();        
        
        usuario.CadastraPerfilLogado(t);
    }
}
