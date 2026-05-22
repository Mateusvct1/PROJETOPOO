import controller.*;
import repository.*;
import service.*;
import view.MenuView;

public class App {

    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();
        ProfissionalRepository profissionalRepository = new ProfissionalRepository();
        GerenteRepository gerenteRepository = new GerenteRepository();
        ServicoRepository servicoRepository = new ServicoRepository();
        AgendamentoRepository agendamentoRepository = new AgendamentoRepository(
                clienteRepository, profissionalRepository, servicoRepository);

        ClienteService clienteService = new ClienteService(clienteRepository);
        ProfissionalService profissionalService = new ProfissionalService(profissionalRepository);
        GerenteService gerenteService = new GerenteService(gerenteRepository);
        ServicoService servicoService = new ServicoService(servicoRepository);
        AgendamentoService agendamentoService = new AgendamentoService(
                agendamentoRepository, clienteRepository,
                profissionalRepository, servicoRepository);

        ClienteController clienteController = new ClienteController(clienteService);
        ProfissionalController profissionalController = new ProfissionalController(profissionalService);
        GerenteController gerenteController = new GerenteController(gerenteService);
        ServicoController servicoController = new ServicoController(servicoService);
        AgendamentoController agendamentoController = new AgendamentoController(agendamentoService);

        MenuView menu = new MenuView(clienteController, profissionalController,
                servicoController, agendamentoController, gerenteController);
        menu.iniciar();
    }
}