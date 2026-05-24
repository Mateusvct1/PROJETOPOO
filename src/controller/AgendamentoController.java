package controller;

import model.Agendamento;
import service.AgendamentoService;
import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoController {

    private AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    public Agendamento realizarAgendamento(int clienteId, int profissionalId,
                                           int servicoId, LocalDateTime dataHora) {
        try { return agendamentoService.realizarAgendamento(clienteId, profissionalId, servicoId, dataHora); }
        catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); return null; }
    }

    public void iniciarAgendamento(int id) {
        try { agendamentoService.iniciarAgendamento(id); System.out.println("Agendamento iniciado."); }
        catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void cancelarAgendamento(int id) {
        try { agendamentoService.cancelarAgendamento(id); System.out.println("Agendamento cancelado."); }
        catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void concluirAgendamento(int id) {
        try { agendamentoService.concluirAgendamento(id); System.out.println("Agendamento concluido."); }
        catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public List<Agendamento> listarTodos() { return agendamentoService.listarTodos(); }
    public List<Agendamento> buscarPorCliente(int id) { return agendamentoService.buscarPorCliente(id); }
    public List<Agendamento> buscarPorProfissional(int id) { return agendamentoService.buscarPorProfissional(id); }
}
