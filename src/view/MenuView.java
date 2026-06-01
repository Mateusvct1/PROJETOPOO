package view;


import controller.*;
import model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuView {

    private Scanner scanner = new Scanner(System.in);
    private ClienteController clienteController;
    private ProfissionalController profissionalController;
    private ServicoController servicoController;
    private AgendamentoController agendamentoController;
    private GerenteController gerenteController;

    public MenuView(ClienteController clienteController,
            ProfissionalController profissionalController,
            ServicoController servicoController,
            AgendamentoController agendamentoController,
            GerenteController gerenteController) {
        this.clienteController = clienteController;
        this.profissionalController = profissionalController;
        this.servicoController = servicoController;
        this.agendamentoController = agendamentoController;
        this.gerenteController = gerenteController;
    }

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInt();
            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProfissionais();
                case 3 -> menuGerentes();
                case 4 -> menuServicos();
                case 5 -> menuAgendamentos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n=== CORTEAGIL ===");
        System.out.println("1 - Clientes");
        System.out.println("2 - Profissionais");
        System.out.println("3 - Gerentes");
        System.out.println("4 - Servicos");
        System.out.println("5 - Agendamentos");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void menuClientes() {
        System.out.println("\n--- CLIENTES ---");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Login cliente");
        System.out.println("3 - Listar clientes");
        System.out.println("4 - Remover cliente");
        System.out.print("Escolha: ");
        switch (lerInt()) {
            case 1 -> cadastrarCliente();
            case 2 -> loginCliente();
            case 3 -> listarClientes();
            case 4 -> removerCliente();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        clienteController.cadastrar(nome, email, senha, telefone);
    }

    private void loginCliente() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        clienteController.login(email, senha);
    }

    private void listarClientes() {
        List<Cliente> lista = clienteController.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : lista) {
                System.out.println(c);
            }
        }
    }

    private void removerCliente() {
        listarClientes();
        System.out.print("ID do cliente a remover: ");
        clienteController.remover(lerInt());
    }

    private void menuProfissionais() {
        System.out.println("\n--- PROFISSIONAIS ---");
        System.out.println("1 - Cadastrar profissional");
        System.out.println("2 - Login profissional");
        System.out.println("3 - Listar profissionais");
        System.out.println("4 - Ver agenda");
        System.out.println("5 - Remover profissional");
        System.out.print("Escolha: ");
        switch (lerInt()) {
            case 1 -> cadastrarProfissional();
            case 2 -> loginProfissional();
            case 3 -> listarProfissionais();
            case 4 -> agendaPorProfissional();
            case 5 -> removerProfissional();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void cadastrarProfissional() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        profissionalController.cadastrar(nome, email, senha, especialidade, telefone);
    }

    private void loginProfissional() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        profissionalController.login(email, senha);
    }

    private void listarProfissionais() {
        List<Profissional> lista = profissionalController.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
        } else {
            for (Profissional p : lista) {
                System.out.println(p);
            }
        }

    }

    private void agendaPorProfissional() {
        listarProfissionais();
        System.out.print("ID do profissional: ");
        List<Agendamento> lista = agendamentoController.buscarPorProfissional(lerInt());
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
        } else {
            for (Agendamento a : lista) {
                System.out.println(a);
            }
        }
    }

    private void removerProfissional() {
        listarProfissionais();
        System.out.print("ID do profissional a remover: ");
        profissionalController.remover(lerInt());
    }

    private void menuGerentes() {
        System.out.println("\n--- GERENTES ---");
        System.out.println("1 - Cadastrar gerente");
        System.out.println("2 - Login gerente");
        System.out.println("3 - Listar gerentes");
        System.out.println("4 - Remover gerente");
        System.out.print("Escolha: ");
        switch (lerInt()) {
            case 1 -> cadastrarGerente();
            case 2 -> loginGerente();
            case 3 -> {
                gerenteController.listarTodos().forEach(System.out::println);
            }
            case 4 -> removerGerente();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void cadastrarGerente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        gerenteController.cadastrar(nome, email, senha, especialidade, telefone, cargo);
    }

    private void loginGerente() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        gerenteController.login(email, senha);
    }

    private void removerGerente() {
        List<Gerente> lista = gerenteController.listarTodos();
        for (Gerente g : lista) {
            System.out.println(g);
        }
        System.out.print("ID do gerente a remover: ");
        gerenteController.remover(lerInt());
    }

    private void menuServicos() {
        System.out.println("\n--- SERVICOS ---");
        System.out.println("1 - Cadastrar servico");
        System.out.println("2 - Listar servicos");
        System.out.println("3 - Remover servico");
        System.out.print("Escolha: ");
        switch (lerInt()) {
            case 1 -> cadastrarServico();
            case 2 -> listarServicos();
            case 3 -> removerServico();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void cadastrarServico() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preco: ");
        double preco = lerDouble();
        System.out.print("Duracao (minutos): ");
        int duracao = lerInt();
        servicoController.cadastrar(nome, preco, duracao);
    }

    private void listarServicos() {
        List<Servico> lista = servicoController.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum servico cadastrado.");
        } else {
            for (Servico s : lista) {
                System.out.println(s);
            }
        }
    }

    private void removerServico() {
        listarServicos();
        System.out.print("ID do servico a remover: ");
        servicoController.remover(lerInt());
    }

    private void menuAgendamentos() {
        System.out.println("\n--- AGENDAMENTOS ---");
        System.out.println("1 - Novo agendamento");
        System.out.println("2 - Listar agendamentos");
        System.out.println("3 - Iniciar agendamento");
        System.out.println("4 - Cancelar agendamento");
        System.out.println("5 - Concluir agendamento");
        System.out.println("6 - Historico por cliente");
        System.out.print("Escolha: ");
        switch (lerInt()) {
            case 1 -> novoAgendamento();
            case 2 -> listarAgendamentos();
            case 3 -> iniciarAgendamento();
            case 4 -> cancelarAgendamento();
            case 5 -> concluirAgendamento();
            case 6 -> historicoPorCliente();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void novoAgendamento() {
        System.out.println("\n--- Clientes cadastrados ---");
        listarClientes();
        System.out.print("ID do cliente: ");
        int clienteId = lerInt();

        System.out.println("\n--- Profissionais disponiveis ---");
        listarProfissionais();
        System.out.print("ID do profissional: ");
        int profissionalId = lerInt();

        System.out.println("\n--- Servicos disponiveis ---");
        listarServicos();
        System.out.print("ID do servico: ");
        int servicoId = lerInt();

        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataStr, fmt);
            Agendamento a = agendamentoController.realizarAgendamento(clienteId, profissionalId, servicoId, dataHora);
            if (a != null)
                System.out.println("Agendamento realizado: " + a);
        } catch (DateTimeParseException e) {
            System.out.println("Formato invalido. Use dd/MM/yyyy HH:mm");
        }
    }

    private void listarAgendamentos() {
        List<Agendamento> lista = agendamentoController.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
        } else {
            for (Agendamento a : lista) {
                System.out.println(a);
            }
        }
    }

    private void iniciarAgendamento() {
        listarAgendamentos();
        System.out.print("ID do agendamento: ");
        agendamentoController.iniciarAgendamento(lerInt());
    }

    private void cancelarAgendamento() {
        listarAgendamentos();
        System.out.print("ID do agendamento: ");
        agendamentoController.cancelarAgendamento(lerInt());
    }

    private void concluirAgendamento() {
        listarAgendamentos();
        System.out.print("ID do agendamento: ");
        agendamentoController.concluirAgendamento(lerInt());
    }

    private void historicoPorCliente() {
        listarClientes();
        System.out.print("ID do cliente: ");
        List<Agendamento> lista = agendamentoController.buscarPorCliente(lerInt());
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
        } else {
            for (Agendamento a : lista) {
                System.out.println(a);
            }
        }
    }

    private int lerInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

