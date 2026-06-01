package controller;

import model.Cliente;
import service.ClienteService;
import java.util.List;

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente cadastrar(String nome, String email, String senha, String telefone) {
        try {
            Cliente c = clienteService.cadastrar(nome, email, senha, telefone);
            System.out.println("Cliente cadastrado: " + c);
            return c;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public Cliente login(String email, String senha) {
        try {
            Cliente c = clienteService.login(email, senha);
            System.out.println("Bem-vindo, " + c.getNome() + "!");
            return c;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public List<Cliente> listarTodos() { return clienteService.listarTodos(); }

    public boolean remover(int id) {
        boolean r = clienteService.remover(id);
        System.out.println(r ? "Cliente removido." : "Cliente nao encontrado.");
        return r;
    }
}
