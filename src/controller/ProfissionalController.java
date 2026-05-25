package controller;

import model.Profissional;
import service.ProfissionalService;
import java.util.List;

public class ProfissionalController {

    private ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    public Profissional cadastrar(String nome, String email, String senha,
                                  String especialidade, String telefone) {
        try {
            Profissional p = profissionalService.cadastrar(nome, email, senha, especialidade, telefone);
            System.out.println("Profissional cadastrado: " + p);
            return p;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public Profissional login(String email, String senha) {
        try {
            Profissional p = profissionalService.login(email, senha);
            System.out.println("Bem-vindo, " + p.getNome() + "!");
            return p;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public List<Profissional> listarTodos() { return profissionalService.listarTodos(); }

    public boolean remover(int id) {
        boolean r = profissionalService.remover(id);
        System.out.println(r ? "Profissional removido." : "Profissional nao encontrado.");
        return r;
    }
}
