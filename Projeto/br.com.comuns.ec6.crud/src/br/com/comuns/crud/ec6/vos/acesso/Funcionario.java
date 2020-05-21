/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comuns.crud.ec6.vos.acesso;

import br.com.comuns.ec6.annotations.CampoNoBanco;
import br.com.comuns.ec6.crud.basis.Entidade;

/**
 *
 * @author gabriell
 */
public class Funcionario extends Entidade {    
    
    @CampoNoBanco(nome = "senha", chave = false)
    private String senha;
    
    
    @CampoNoBanco(nome = "login", chave = true)
    private String login;
    
    private String nome;
    
    private int perfil;

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return  login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the perfil
     */
    public int getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    
}
