package service;

import model.*;
import repository.*;
import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;
    private ClienteRepository clienteRepository;
    private ProfissionalRepository profissionalRepository;
    private ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                               ClienteRepository clienteRepository,
                               ProfissionalRepository profissionalRepository,
                               ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }

    public Agendamento realizarAgendamento(int clienteId, int profissionalId,
                                           int servicoId, LocalDateTime dataHora) {
        if (dataHora.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("A data do agendamento deve ser futura.");
        if (agendamentoRepository.existeConflito(profissionalId, dataHora))
            throw new IllegalArgumentException("Profissional ja possui agendamento nesse horario.");
        Cliente cliente = clienteRepository.buscarPorId(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente nao encontrado."));
        Profissional profissional = profissionalRepository.buscarPorId(profissionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profissional nao encontrado."));
        Servico servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Servico nao encontrado."));
        int id = agendamentoRepository.gerarId();
        return agendamentoRepository.salvar(new Agendamento(id, dataHora, cliente, profissional, servico));
    }

    public void iniciarAgendamento(int id) {
        Agendamento a = agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento nao encontrado."));
        a.iniciar();
        agendamentoRepository.atualizarNoArquivo();
    }

    public void cancelarAgendamento(int id) {
        Agendamento a = agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento nao encontrado."));
        a.cancelar();
        agendamentoRepository.atualizarNoArquivo();
    }

    public void concluirAgendamento(int id) {
        Agendamento a = agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento nao encontrado."));
        a.concluir();
        agendamentoRepository.atualizarNoArquivo();
    }

    public List<Agendamento> listarTodos() { return agendamentoRepository.listarTodos(); }
    public List<Agendamento> buscarPorCliente(int id) { return agendamentoRepository.buscarPorCliente(id); }
    public List<Agendamento> buscarPorProfissional(int id) { return agendamentoRepository.buscarPorProfissional(id); }
}
