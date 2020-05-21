/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comuns.crud.ec6.vos.conteudo;

import br.com.comuns.crud.ec6.vos.acesso.Cliente;
import br.com.comuns.crud.ec6.vos.acesso.Funcionario;
import br.com.comuns.ec6.crud.basis.Entidade;
import java.util.Date;
import java.util.List;


/**
 *
 * @author gabriell
 */
public class Pedido extends Entidade  {
    private int produtos; 
    
    private Date data;
    
    private double valor_total;
    
    private int cliente;
    
    private int funcionario;

    /**
     * @return the pedidos
     */
    public int getProdutos() {
        return produtos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setProdutos(int produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the valor_total
     */
    public double getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the cliente
     */
    public int getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the funcionario
     */
    public int getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }
    
}
