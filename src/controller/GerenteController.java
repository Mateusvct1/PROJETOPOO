package controller;

import model.Gerente;
import service.GerenteService;
import java.util.List;

public class GerenteController {

    private GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    public Gerente cadastrar(String nome, String email, String senha,
                              String especialidade, String telefone, String cargo) {
        try {
            Gerente g = gerenteService.cadastrar(nome, email, senha, especialidade, telefone, cargo);
            System.out.println("Gerente cadastrado: " + g);
            return g;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public Gerente login(String email, String senha) {
        try {
            Gerente g = gerenteService.login(email, senha);
            System.out.println("Bem-vindo, " + g.getNome() + "!");
            return g;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public List<Gerente> listarTodos() { return gerenteService.listarTodos(); }

    public boolean remover(int id) {
        boolean r = gerenteService.remover(id);
        System.out.println(r ? "Gerente removido." : "Gerente nao encontrado.");
        return r;
    }
}
