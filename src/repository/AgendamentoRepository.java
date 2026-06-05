package repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.*;

public class AgendamentoRepository {

    private static final String ARQUIVO = "dados/agendamentos.txt";
    private List<Agendamento> agendamentos = new ArrayList<>();
    private int proximoId = 1;

    private ClienteRepository clienteRepository;
    private ProfissionalRepository profissionalRepository;
    private ServicoRepository servicoRepository;

    public AgendamentoRepository(ClienteRepository clienteRepository,
                                  ProfissionalRepository profissionalRepository,
                                  ServicoRepository servicoRepository) {
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
        new File("dados").mkdirs();
        carregarDoArquivo();
    }

    public Agendamento salvar(Agendamento agendamento) {
        agendamentos.add(agendamento);
        salvarNoArquivo();
        return agendamento;
    }

    public List<Agendamento> listarTodos() { return new ArrayList<>(agendamentos); }

    public Optional<Agendamento> buscarPorId(int id) {
        return agendamentos.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Agendamento> buscarPorProfissional(int profissionalId) {
        return agendamentos.stream()
                .filter(a -> a.getProfissional().getId() == profissionalId)
                .collect(Collectors.toList());
    }

    public List<Agendamento> buscarPorCliente(int clienteId) {
        return agendamentos.stream()
                .filter(a -> a.getCliente().getId() == clienteId)
                .collect(Collectors.toList());
    }

    public boolean existeConflito(int profissionalId, LocalDateTime dataHora) {
        return agendamentos.stream()
                .filter(a -> a.getProfissional().getId() == profissionalId)
                .filter(a -> a.getStatus() != Status.CANCELADO)
                .anyMatch(a -> a.getDataHora().equals(dataHora));
    }

    public void atualizarNoArquivo() { salvarNoArquivo(); }

    public int gerarId() { return proximoId++; }

    private void salvarNoArquivo() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ARQUIVO))) {
            w.write(String.valueOf(proximoId)); w.newLine();
            for (Agendamento a : agendamentos) {
                w.write(a.getId() + ";" + a.getDataHora() + ";"
                        + a.getCliente().getId() + ";" + a.getProfissional().getId() + ";"
                        + a.getServico().getId() + ";" + a.getStatus().name());
                w.newLine();
            }
        } catch (IOException e) { System.out.println("Erro ao salvar agendamentos: " + e.getMessage()); }
    }

    private void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha = r.readLine();
            if (linha != null) proximoId = Integer.parseInt(linha.trim());
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", -1);
                if (p.length == 6) {
                    Optional<Cliente> c = clienteRepository.buscarPorId(Integer.parseInt(p[2]));
                    Optional<Profissional> pr = profissionalRepository.buscarPorId(Integer.parseInt(p[3]));
                    Optional<Servico> s = servicoRepository.buscarPorId(Integer.parseInt(p[4]));
                    if (c.isPresent() && pr.isPresent() && s.isPresent()) {
                        Agendamento a = new Agendamento(Integer.parseInt(p[0]),
                                LocalDateTime.parse(p[1]), c.get(), pr.get(), s.get());
                        a.setStatus(Status.valueOf(p[5]));
                        agendamentos.add(a);
                    }
                }
            }
        } catch (IOException e) { System.out.println("Erro ao carregar agendamentos: " + e.getMessage()); }
    }
}
