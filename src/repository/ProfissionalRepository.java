package repository;

import model.Profissional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfissionalRepository {

    private static final String ARQUIVO = "dados/profissionais.txt";
    private List<Profissional> profissionais = new ArrayList<>();
    private int proximoId = 1;

    public ProfissionalRepository() {
        new File("dados").mkdirs();
        carregarDoArquivo();
    }

    public Profissional salvar(Profissional profissional) {
        profissionais.add(profissional);
        salvarNoArquivo();
        return profissional;
    }

    public List<Profissional> listarTodos() {
        return new ArrayList<>(profissionais);
    }

    public Optional<Profissional> buscarPorId(int id) {
        return profissionais.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Profissional> buscarPorEmail(String email) {
        return profissionais.stream().filter(p -> p.getEmail().equals(email)).findFirst();
    }

    public boolean remover(int id) {
        boolean removido = profissionais.removeIf(p -> p.getId() == id);
        if (removido) salvarNoArquivo();
        return removido;
    }

    public int gerarId() { return proximoId++; }

    private void salvarNoArquivo() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ARQUIVO))) {
            w.write(String.valueOf(proximoId)); w.newLine();
            for (Profissional p : profissionais) {
                w.write(p.getId() + ";" + p.getNome() + ";" + p.getEmail() + ";"
                        + p.getSenha() + ";" + p.getEspecialidade() + ";" + p.getTelefone());
                w.newLine();
            }
        } catch (IOException e) { System.out.println("Erro ao salvar profissionais: " + e.getMessage()); }
    }

    private void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha = r.readLine();
            if (linha != null) proximoId = Integer.parseInt(linha.trim());
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", -1);
                if (p.length == 6)
                    profissionais.add(new Profissional(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4], p[5]));
            }
        } catch (IOException e) { System.out.println("Erro ao carregar profissionais: " + e.getMessage()); }
    }
}
