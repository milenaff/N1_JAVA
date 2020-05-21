/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.ec6.crud.basis;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author italo
 */
public class TXTDao {
    
    public int gera_codigo(String caminho_arquivo) throws Exception{
    
     int codigo = ReadFile(caminho_arquivo);     
     
     return ++codigo;
    
    }
    
      public int ReadFile(String arquivo) throws Exception {

     	String ultimo = "";
		try {
                       File file = new File(arquivo);
                       if(!file.exists())
                           return 0;
			InputStream is = new FileInputStream(arquivo);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					ultimo = line;
				}
			}
                        ultimo = ultimo.substring(0, ultimo.indexOf("|"));                                
			br.close();                        
                     
		} catch (IOException e) {
			e.printStackTrace();                       
		}
          return Integer.parseInt(ultimo);
                
    }
          private boolean tryParseInt(String value) {  
            try {  
                    Integer.parseInt(value);  
                    return true;  
                } 
            catch (NumberFormatException e) {  
                return false;  
      }
          }
           public boolean valida_codigo_exclusão(String nameFile, String codigo){
               int cod=0;
               if(!tryParseInt(codigo))  
                   return false;
               else
                   cod=Integer.parseInt(codigo);
               List<Integer> codigos = new ArrayList<Integer>();              
  
    		try {
                       File file = new File(nameFile);                      
                           
			InputStream is = new FileInputStream(nameFile);                        
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
                        line = br.readLine();                        
			while (line != null) {
                             int codigoEntidade =  Integer.parseInt(line.substring(0,line.indexOf("|")));
                             codigos.add(codigoEntidade);
				line = br.readLine();                            			
			}                        
			br.close(); 

                     
		} catch (IOException e) {
			e.printStackTrace();                       
		}
                        if(codigos.contains(cod))
                            return true;
                        else return false;
          
           }
 
}
