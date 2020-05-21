/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.acesso;

import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import br.com.comuns.ec6.crud.basis.Entidade;
import br.com.dao.ec6.crud.basis.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*; 
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author gabriell
 */
public class UsuarioTextoDAO extends DAO {

    private final ConcurrentHashMap<String, Funcionario> usuarios = new ConcurrentHashMap<>();
    
    public UsuarioTextoDAO()
    { 
        super(Funcionario.class);
//        Funcionario masterUser = new Funcionario();
//        masterUser.setLogin("master");
//        masterUser.setSenha("master");        
//        usuarios.put("master", masterUser);
    }
    @Override
    public Entidade seleciona(int id) {
        // Não há retorno por id
        return null;
    }
    
   
    public String[] ReadFile(String Usuario) throws Exception {

        File arquivo = new File("Acesso.txt");
        if (arquivo.exists()) {
            List<String[]> dadosConta = new ArrayList<>();
            try {
                BufferedReader in = new BufferedReader(new FileReader(arquivo));
                String dados;
                while (in.ready()) {
                    dados = in.readLine(); // linha a linha do txt
                    String[] teste = dados.split(Pattern.quote ("|"));
                    String usuario = teste[1];
                    if(usuario.equals(Usuario)){                        
//                        dadosConta.add(dados.split(Pattern.quote ("|")));
                        return teste;
                    }
                }
                in.close();
                
                return null;

//                return dadosConta;
            } catch (FileNotFoundException  e) {
                System.out.println("Erro: "+ e);
                throw e;
            }
        } else {
            System.out.println("Erro na leitura do arquivo.");
            throw new Exception();
        }
    }
    
       public String RetornaPerfilLogado() throws Exception {

        File arquivo = new File("PerfilLogado.txt");
        if (arquivo.exists()) {
            String dados= "";
            try {
                BufferedReader in = new BufferedReader(new FileReader(arquivo));
               
                while (in.ready()) {
                    dados = in.readLine(); // linha a linha do txt                   
                }           
                in.close();
                
                return dados;

//                return dadosConta;
            } catch (FileNotFoundException  e) {
                System.out.println("Erro: "+ e);
                throw e;
            }
        } else {
            System.out.println("Erro na leitura do arquivo.");
            throw new Exception();
        }
    }
    
    public void CadastraPerfilLogado(String t)
    {
        String fileNameAcesso = "PerfilLogado.txt";
         try // armazena no arquivo acesso
        {
            FileWriter fileAcesso = new FileWriter(fileNameAcesso);
            BufferedWriter WriterAcesso =new BufferedWriter(fileAcesso); 
            WriterAcesso.write(t);
            WriterAcesso.close();
                    }
                catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileNameAcesso + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    

    @Override
    public Funcionario localiza(String codigo) throws SQLException  {
//        Entidade entidade = usuarios.getOrDefault(codigo, null);
          Funcionario masterUser = new Funcionario();   
        try {
            String[] teste = new String[5];
            teste=ReadFile(codigo);
            if(teste!= null){
                        
              masterUser.setLogin(teste[1]);
              masterUser.setSenha(teste[2]); 
              masterUser.setPerfil(Integer.parseInt(teste[3]));
              usuarios.put("master", masterUser);
            }             
//      
        } catch (Exception ex) {
            Logger.getLogger(UsuarioTextoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return masterUser;
    }
    
    /* Opção 1 
    */
    @Override
    public ArrayList<Entidade> lista() throws SQLException {
        ArrayList<Entidade> entidades;
        entidades = new ArrayList();
        for (Funcionario usuario : usuarios.values())
        {
            entidades.add(usuario);
        }
        return entidades;
    }
    
}
