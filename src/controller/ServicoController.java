package controller;

import java.util.List;
import model.Servico;
import service.ServicoService;

public class ServicoController {

    private ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    public Servico cadastrar(String nome, double preco, int duracaoMin) {
        try {
            Servico s = servicoService.cadastrar(nome, preco, duracaoMin);
            System.out.println("Servico cadastrado: " + s);
            return s;
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public List<Servico> listarTodos() { return servicoService.listarTodos(); }

    public boolean remover(int id) {
        boolean r = servicoService.remover(id);
        System.out.println(r ? "Servico removido." : "Servico nao encontrado.");
        return r;
    }
}
