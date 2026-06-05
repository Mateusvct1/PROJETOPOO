package repository;

import model.Gerente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenteRepository {

    private static final String ARQUIVO = "dados/gerentes.txt";
    private List<Gerente> gerentes = new ArrayList<>();
    private int proximoId = 1;

    public GerenteRepository() {
        new File("dados").mkdirs();
        carregarDoArquivo();
    }

    public Gerente salvar(Gerente gerente) {
        gerentes.add(gerente);
        salvarNoArquivo();
        return gerente;
    }

    public List<Gerente> listarTodos() {
        return new ArrayList<>(gerentes);
    }

    public Optional<Gerente> buscarPorId(int id) {
        return gerentes.stream().filter(g -> g.getId() == id).findFirst();
    }

    public Optional<Gerente> buscarPorEmail(String email) {
        return gerentes.stream().filter(g -> g.getEmail().equals(email)).findFirst();
    }

    public boolean remover(int id) {
        boolean removido = gerentes.removeIf(g -> g.getId() == id);
        if (removido) salvarNoArquivo();
        return removido;
    }

    public int gerarId() { return proximoId++; }

    private void salvarNoArquivo() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ARQUIVO))) {
            w.write(String.valueOf(proximoId)); w.newLine();
            for (Gerente g : gerentes) {
                w.write(g.getId() + ";" + g.getNome() + ";" + g.getEmail() + ";"
                        + g.getSenha() + ";" + g.getEspecialidade() + ";"
                        + g.getTelefone() + ";" + g.getCargo());
                w.newLine();
            }
        } catch (IOException e) { System.out.println("Erro ao salvar gerentes: " + e.getMessage()); }
    }

    private void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha = r.readLine();
            if (linha != null) proximoId = Integer.parseInt(linha.trim());
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", -1);
                if (p.length == 7)
                    gerentes.add(new Gerente(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4], p[5], p[6]));
            }
        } catch (IOException e) { System.out.println("Erro ao carregar gerentes: " + e.getMessage()); }
    }
}
