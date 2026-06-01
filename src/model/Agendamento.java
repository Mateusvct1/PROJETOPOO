package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Agendamento {

    private int id;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private Profissional profissional;
    private Servico servico;
    private Status status;

    public Agendamento(int id, LocalDateTime dataHora, Cliente cliente,
                       Profissional profissional, Servico servico) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.profissional = profissional;
        this.servico = servico;
        this.status = Status.AGENDADO;
    }

    public void cancelar() {
        if (this.status == Status.CONCLUIDO) {
            System.out.println("Nao e possivel cancelar um agendamento ja concluido.");
            return;
        }
        this.status = Status.CANCELADO;
    }

    public void concluir() {
        this.status = Status.CONCLUIDO;
    }

    public void iniciar() {
        this.status = Status.EM_ANDAMENTO;
    }

    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Cliente getCliente() { return cliente; }
    public Profissional getProfissional() { return profissional; }
    public Servico getServico() { return servico; }
    public Status getStatus() { return status; }

    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return id + " | " + dataHora.format(fmt)
             + " | " + cliente.getNome()
             + " | " + profissional.getNome()
             + " | " + servico.getNome()
             + " | " + status;
    }
}
