package service;

import model.Cliente;
import repository.ClienteRepository;
import java.util.List;

public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(String nome, String email, String senha, String telefone) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do cliente nao pode ser vazio.");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email do cliente nao pode ser vazio.");
        int id = clienteRepository.gerarId();
        return clienteRepository.salvar(new Cliente(id, nome, email, senha, telefone));
    }

    public Cliente login(String email, String senha) {
        return clienteRepository.buscarPorEmail(email)
                .filter(c -> c.login(email, senha))
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalidos."));
    }

    public List<Cliente> listarTodos() { return clienteRepository.listarTodos(); }

    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente nao encontrado."));
    }

    public boolean remover(int id) { return clienteRepository.remover(id); }
}
