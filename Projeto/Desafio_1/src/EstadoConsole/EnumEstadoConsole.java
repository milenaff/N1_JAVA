/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoConsole;

/**
 *
 * @author 082170012
 */
public enum EnumEstadoConsole {
    
    LOGIN(new EstadoConsoleLogin()),
    
    MENU_PRINCIPAL(new EstadoConsoleMenuPrincipal()),
    
    MENU_SECUNDARIO(new EstadoConsoleMenuSecundario()),
    
    CLIENTE(new EstadoConsoleCliente()),
    
    FUNCIONARIO(new EstadoConsoleFuncionario()),
    
    PEDIDO(new EstadoConsolePedido()),
    
    PRODUTO(new EstadoConsoleProduto());
    
    private final MaquinaEstadoConsole estadomaquina;
    
    EnumEstadoConsole(MaquinaEstadoConsole estadomaquina){
        this.estadomaquina = estadomaquina;
    } 
    
    public MaquinaEstadoConsole getEstadoMaquina() {
        return estadomaquina;
    }
    
}
