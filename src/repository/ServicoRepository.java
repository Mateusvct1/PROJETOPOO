package repository;

import model.Servico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoRepository {

    private static final String ARQUIVO = "dados/servicos.txt";
    private List<Servico> servicos = new ArrayList<>();
    private int proximoId = 1;

    public ServicoRepository() {
        new File("dados").mkdirs();
        carregarDoArquivo();
    }

    public Servico salvar(Servico servico) {
        servicos.add(servico);
        salvarNoArquivo();
        return servico;
    }

    public List<Servico> listarTodos() {
        return new ArrayList<>(servicos);
    }

    public Optional<Servico> buscarPorId(int id) {
        return servicos.stream().filter(s -> s.getId() == id).findFirst();
    }

    public boolean remover(int id) {
        boolean removido = servicos.removeIf(s -> s.getId() == id);
        if (removido) salvarNoArquivo();
        return removido;
    }

    public int gerarId() { return proximoId++; }

    private void salvarNoArquivo() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ARQUIVO))) {
            w.write(String.valueOf(proximoId)); w.newLine();
            for (Servico s : servicos) {
                w.write(s.getId() + ";" + s.getNome() + ";" + s.getPreco() + ";" + s.getDuracaoMin());
                w.newLine();
            }
        } catch (IOException e) { System.out.println("Erro ao salvar servicos: " + e.getMessage()); }
    }

    private void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha = r.readLine();
            if (linha != null) proximoId = Integer.parseInt(linha.trim());
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", -1);
                if (p.length == 4)
                    servicos.add(new Servico(Integer.parseInt(p[0]), p[1], Double.parseDouble(p[2]), Integer.parseInt(p[3])));
            }
        } catch (IOException e) { System.out.println("Erro ao carregar servicos: " + e.getMessage()); }
    }
}
