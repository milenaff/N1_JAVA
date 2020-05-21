/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.produto;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import br.com.dao.ec6.crud.basis.GerenciadorAuditoriaSingleton;
import br.com.dao.ec6.crud.basis.TXTDao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author italo
 */
public class FuncionarioTxTDao extends TXTDao{
    public void Cadastrar(Funcionario f) throws Exception{
        
        
        // The name of the file to open.
        String fileName = "Funcionario.txt";
        int codigo = gera_codigo(fileName);       
       

        try { // armazena no arquivo funcionario 
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName, true);           
          
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            //bufferedWriter.newLine();
            bufferedWriter.append(codigo + "|" + f.getNome()+"|"+f.getPerfil()+"|"+f.getLogin()+"|"+f.getSenha()+"\n"); 
            bufferedWriter.close();
//            Mensagem_Seguranca("Funcionario");
            GerenciadorAuditoriaSingleton.getInstancia().Adiciona_Msg_Auditoria("Funcionario");
                        

         
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
             String fileNameAcesso = "Acesso.txt";

        try // armazena no arquivo acesso
        {
            FileWriter fileAcesso = new FileWriter(fileNameAcesso,true);
            BufferedWriter WriterAcesso =new BufferedWriter(fileAcesso); 
            WriterAcesso.append(codigo + "|" +f.getLogin()+"|"+f.getSenha()+"|"+f.getPerfil()+"\n");
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
    
     public void Lista_Funcionario() throws Exception {
         
       String arquivo= "Funcionario.txt";
    		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
			while (line != null) {
				line = br.readLine();
                                System.out.println(line);			
			}                        
			br.close();                        
                     
		} catch (IOException e) {
			e.printStackTrace();                       
		}         
                
    }
     public void Rescreve_Funcionario(List<String> produtos){
    
        // The name of the file to open.
        String fileName = "Funcionario.txt";
        File file = new File(fileName);      
           if(file.exists())
                file.delete();    
       

        try {            
             
            FileWriter fileWriter = new FileWriter(fileName, true);          
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);           
         
            
            for(String item : produtos){             
        
            bufferedWriter.append(item+"\n");                         
             
            
            }
         
            bufferedWriter.close();      
            
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
    
    }
    
    
    
    public void Deleta_Funcionario(int codigo){
        
       List<String> funcionarios = new ArrayList<String>();
       String arquivo= "Funcionario.txt";

		try {
                                          
                        InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {				
                            int codigo_func =  Integer.parseInt(line.substring(0,line.indexOf("|")));
                            
                            if(codigo != codigo_func)
                                funcionarios.add(line);
                            
                            line = br.readLine();
			}
                        
			br.close();                        
                     
                        Rescreve_Funcionario(funcionarios);
                        Deleta_Acesso(codigo);
		} catch (IOException e) {
			e.printStackTrace();                       
		}  
    
    }
    
    
    
    
      public void Rescreve_Acesso(List<String> produtos){
    
        // The name of the file to open.
        String fileName = "Acesso.txt";
        File file = new File(fileName);      
           if(file.exists())
                file.delete();    
       

        try {            
             
            FileWriter fileWriter = new FileWriter(fileName, true);          
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);           
         
            
            for(String item : produtos){             
        
            bufferedWriter.append(item+"\n");                         
             
            
            }
         
            bufferedWriter.close();      
            
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
    
    }
    
    
    
    public void Deleta_Acesso(int codigo){
        
       List<String> funcionarios = new ArrayList<String>();
       String arquivo= "Acesso.txt";

		try {
                                          
                        InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {				
                            int codigo_func =  Integer.parseInt(line.substring(0,line.indexOf("|")));
                            
                            if(codigo != codigo_func)
                                funcionarios.add(line);
                            
                            line = br.readLine();
			}
                        
			br.close();                        
                     
                        Rescreve_Acesso(funcionarios);
		} catch (IOException e) {
			e.printStackTrace();                       
		}  
    
    }
    
    public Funcionario Busca_Funcionario (int codigo)
    {
        Funcionario cl = new Funcionario ();
       String arquivo= "Funcionario.txt";
		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {	
                            String[] teste = line.split(Pattern.quote ("|"));
                            int codigo_func =  Integer.parseInt(teste[0]);
                            
                            if(codigo_func==codigo)
                            {
                               cl.setId(codigo_func);
                               cl.setNome(teste[1]);                               
                            }
                            
                            line = br.readLine();
			}
                        
			br.close();                      
                } catch (IOException e) {
			e.printStackTrace();                       
		} 
                return cl;
    }
    
    
    
    
}
