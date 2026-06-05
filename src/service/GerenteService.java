package service;

import model.Gerente;
import repository.GerenteRepository;
import java.util.List;

public class GerenteService {

    private GerenteRepository gerenteRepository;

    public GerenteService(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }

    public Gerente cadastrar(String nome, String email, String senha,
                              String especialidade, String telefone, String cargo) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do gerente nao pode ser vazio.");
        int id = gerenteRepository.gerarId();
        return gerenteRepository.salvar(new Gerente(id, nome, email, senha, especialidade, telefone, cargo));
    }

    public Gerente login(String email, String senha) {
        return gerenteRepository.buscarPorEmail(email)
                .filter(g -> g.login(email, senha))
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalidos."));
    }

    public List<Gerente> listarTodos() { return gerenteRepository.listarTodos(); }

    public Gerente buscarPorId(int id) {
        return gerenteRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Gerente nao encontrado."));
    }

    public boolean remover(int id) { return gerenteRepository.remover(id); }
}
