package repository;

import model.Cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {

    private static final String ARQUIVO = "dados/clientes.txt";
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public ClienteRepository() {
        new File("dados").mkdirs();
        carregarDoArquivo();
    }

    public Cliente salvar(Cliente cliente) {
        clientes.add(cliente);
        salvarNoArquivo();
        return cliente;
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return clientes.stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }

    public boolean remover(int id) {
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) salvarNoArquivo();
        return removido;
    }

    public int gerarId() { return proximoId++; }

    private void salvarNoArquivo() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ARQUIVO))) {
            w.write(String.valueOf(proximoId)); w.newLine();
            for (Cliente c : clientes) {
                w.write(c.getId() + ";" + c.getNome() + ";" + c.getEmail() + ";"
                        + c.getSenha() + ";" + c.getTelefone());
                w.newLine();
            }
        } catch (IOException e) { System.out.println("Erro ao salvar clientes: " + e.getMessage()); }
    }

    private void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha = r.readLine();
            if (linha != null) proximoId = Integer.parseInt(linha.trim());
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", -1);
                if (p.length == 5)
                    clientes.add(new Cliente(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4]));
            }
        } catch (IOException e) { System.out.println("Erro ao carregar clientes: " + e.getMessage()); }
    }
}
