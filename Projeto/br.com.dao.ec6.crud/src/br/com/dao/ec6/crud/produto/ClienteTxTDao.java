/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.produto;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
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
public class ClienteTxTDao extends TXTDao {
    
      public void Cadastrar(Cliente cliente) throws Exception{
        
        
        // The name of the file to open.
        String fileName = "Cliente.txt";
        int codigo = gera_codigo(fileName);       
       

        try {
        
            FileWriter fileWriter =
                new FileWriter(fileName, true);            
          
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
        
            bufferedWriter.append(codigo + "|" + cliente.getNome()+"\n"); 
            
                             
            // Always close files.
            bufferedWriter.close();
//            Mensagem_Seguranca("Cliente");
            GerenciadorAuditoriaSingleton.getInstancia().Adiciona_Msg_Auditoria("Cliente");

        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }          

    }
    
     public void Lista_Clientes() throws Exception {
        
         
       String arquivo= "Cliente.txt";
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
     
       public void Rescreve_Cliente(List<String> produtos){
    
        // The name of the file to open.
        String fileName = "Cliente.txt";
        File file = new File(fileName);      
           if(file.exists())
                file.delete();
   
             
       

        try {            
             
            FileWriter fileWriter = new FileWriter(fileName, true);          
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);           
         
            
            for(String item : produtos){             
           

            // Note that write() does not automatically
            // append a newline character.
            //bufferedWriter.newLine();
            bufferedWriter.append(item+"\n");                         
             
            
            }
            // Always close files.
            bufferedWriter.close();      
            
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    
    }
    
    
    
    public void Deleta_Cliente(int codigo){
        
       List<String> clientes = new ArrayList<String>();
       String arquivo= "Cliente.txt";
       String ultimo = "";
		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {				
                            int codigo_cliente =  Integer.parseInt(line.substring(0,line.indexOf("|")));
                            
                            if(codigo != codigo_cliente)
                                clientes.add(line);
                            
                            line = br.readLine();
			}
                        
			br.close();                        
                     
                        Rescreve_Cliente(clientes);
		} catch (IOException e) {
			e.printStackTrace();                       
		}  
    
    }
    public Cliente Busca_Cliente (int codigo)
    {
        Cliente cl = new Cliente ();
       String arquivo= "Cliente.txt";
		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {	
                            String[] teste = line.split(Pattern.quote ("|"));
                            int codigo_cliente =  Integer.parseInt(teste[0]);
                            
                            if(codigo_cliente==codigo)
                            {
                               cl.setId(codigo_cliente);
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
