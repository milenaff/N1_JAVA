/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.produto;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.conteudo.Pedido;
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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author italo
 */
public class PedidoTxTDao  extends TXTDao {
    
     public void Cadastrar(Pedido pedido) throws Exception{
        
        
        // The name of the file to open.
        String fileName = "Pedido.txt";
        int codigo = gera_codigo(fileName);  
       

        try {
        
            FileWriter fileWriter =
                new FileWriter(fileName, true);            
          
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); 
      
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            String dataPedido = formatador.format(pedido.getData());
        
            
            bufferedWriter.append(codigo + "|" + pedido.getCliente() + "|"+ dataPedido + "|"+ pedido.getProdutos()+"|"+ pedido.getFuncionario()+"\n"); 
            
                             
            // Always close files.
            bufferedWriter.close();
//            Mensagem_Seguranca("Pedido");
            GerenciadorAuditoriaSingleton.getInstancia().Adiciona_Msg_Auditoria("Pedido");
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }          

    }
         public Pedido Busca_Pedido (int codigo) throws ParseException
    {
        Pedido cl = new Pedido ();
       String arquivo= "Pedido.txt";

		try {
                       File file = new File(arquivo);                      
                           
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();
			while (line != null) {	
                            String[] teste = line.split(Pattern.quote ("|"));
                            int codigo_pedido =  Integer.parseInt(teste[0]);
                            
                            if(codigo_pedido==codigo)
                            {
                               cl.setId(codigo_pedido);
                               cl.setCliente(Integer.parseInt(teste[1]));
                               java.util.Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(teste[2]);
                               cl.setData(date1);
                               cl.setProdutos(Integer.parseInt(teste[3]));
                               cl.setFuncionario(Integer.parseInt(teste[4]));
                            }
                            
                            line = br.readLine();
			}
                        
			br.close();                      
                } catch (IOException e) {
			e.printStackTrace();                       
		} 
                return cl;
    }
         
      public void Rescreve_Pedido(List<String> pedido){
    
        // The name of the file to open.
        String fileName = "Pedido.txt";
        File file = new File(fileName);      
           if(file.exists())
                file.delete();
        

        try {            
             
            FileWriter fileWriter = new FileWriter(fileName, true);          
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);           
         
            
            for(String item : pedido){             
      
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
    
    
    
    public void Deleta_Pedido(int codigo){
        
       List<String> pedidos = new ArrayList<String>();
       String arquivo= "Pedido.txt";
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
                                pedidos.add(line);
                            
                            line = br.readLine();
			}
                        
			br.close();                        
                     
                        Rescreve_Pedido(pedidos);
		} catch (IOException e) {
			e.printStackTrace();                       
		}  
    
    }
     public int Lista_Pedidos() throws Exception {
         
       String arquivo= "Pedido.txt";
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
}
