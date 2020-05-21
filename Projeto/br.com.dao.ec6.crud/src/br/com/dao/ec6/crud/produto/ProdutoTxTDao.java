/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.produto;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.conteudo.Produto;
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
public class ProdutoTxTDao extends TXTDao {
    
    public void Cadastrar(Produto produto) throws Exception{
        
        
        // The name of the file to open.
        String fileName = "Produto.txt";
        int codigo = gera_codigo(fileName);       
       

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName, true);
            
          
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            //bufferedWriter.newLine();
            bufferedWriter.append(codigo + "|" + produto.getDescricao() + "|" + produto.getPreco()+"\n"); 
            
                             
            // Always close files.
            bufferedWriter.close();
//            Mensagem_Seguranca("Produto");
            GerenciadorAuditoriaSingleton.getInstancia().Adiciona_Msg_Auditoria("Produto");
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }          

    }
    
    public void Rescreve_Produto(List<String> produtos){
    
        // The name of the file to open.
        String fileName = "Produto.txt";
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
            // Or we could just do this:
            // ex.printStackTrace();
        }
    
    }
    
    
    
    public void Deleta_Produto(int codigo){
        
       List<String> produtos = new ArrayList<String>();
       String arquivo= "Produto.txt";
       String ultimo = "";
		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {				
                            int codigo_produto =  Integer.parseInt(line.substring(0,line.indexOf("|")));
                            
                            if(codigo != codigo_produto)
                                produtos.add(line);
                            
                            line = br.readLine();
			}
                        
			br.close();                        
                     
                        Rescreve_Produto(produtos);
		} catch (IOException e) {
			e.printStackTrace();                       
		}  
    
    }
    
     public int Lista_Produtos() throws Exception {
         
       String arquivo= "Produto.txt";
     	String ultimo = "";
        int n =0;
		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
			while (line != null) {
				line = br.readLine();
                                System.out.println(line);	
                                n++;
			}
                        
			br.close();                        
                     
		} catch (IOException e) {
			e.printStackTrace();                       
		}         
             return n;   
    }
     
     public Produto Busca_Produto(int codigo)
    {
        Produto cl = new Produto ();
       String arquivo= "Produto.txt";
		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {	
                            String[] teste = line.split(Pattern.quote ("|"));
                            int codigo_prod =  Integer.parseInt(teste[0]);
                            
                            if(codigo_prod==codigo)
                            {
                               cl.setId(codigo_prod);
                               cl.setDescricao(teste[1]);
                               cl.setPreco(Double.parseDouble(teste[2]));
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
