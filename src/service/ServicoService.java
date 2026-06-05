package service;

import model.Servico;
import repository.ServicoRepository;
import java.util.List;

public class ServicoService {

    private ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico cadastrar(String nome, double preco, int duracaoMin) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do servico nao pode ser vazio.");
        if (preco < 0)
            throw new IllegalArgumentException("Preco nao pode ser negativo.");
        int id = servicoRepository.gerarId();
        return servicoRepository.salvar(new Servico(id, nome, preco, duracaoMin));
    }

    public List<Servico> listarTodos() { return servicoRepository.listarTodos(); }

    public Servico buscarPorId(int id) {
        return servicoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Servico nao encontrado."));
    }

    public boolean remover(int id) { return servicoRepository.remover(id); }
}
