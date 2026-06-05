package service;

import model.Profissional;
import repository.ProfissionalRepository;
import java.util.List;

public class ProfissionalService {

    private ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public Profissional cadastrar(String nome, String email, String senha,
                                  String especialidade, String telefone) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do profissional nao pode ser vazio.");
        int id = profissionalRepository.gerarId();
        return profissionalRepository.salvar(new Profissional(id, nome, email, senha, especialidade, telefone));
    }

    public Profissional login(String email, String senha) {
        return profissionalRepository.buscarPorEmail(email)
                .filter(p -> p.login(email, senha))
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalidos."));
    }

    public List<Profissional> listarTodos() { return profissionalRepository.listarTodos(); }

    public Profissional buscarPorId(int id) {
        return profissionalRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional nao encontrado."));
    }

    public boolean remover(int id) { return profissionalRepository.remover(id); }
}
