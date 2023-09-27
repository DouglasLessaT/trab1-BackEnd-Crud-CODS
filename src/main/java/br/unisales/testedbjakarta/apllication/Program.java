package br.unisales.testedbjakarta.apllication;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import br.unisales.testedbjakarta.model.Cliente;
import br.unisales.testedbjakarta.model.OrdemServico;


import java.util.List;
import java.util.Scanner;



public class Program {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Scanner scanner;

    public Program() {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
        scanner = new Scanner(System.in);
    }


    // final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ControleDeOrdemDeServiço");

    // Métodos do CRUD para Cliente

    public void adicionarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarClientePorId(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> listarClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public void atualizarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public void removerCliente(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
    }

    // Métodos do CRUD para Ordem de Serviço

    public void adicionarOrdemServico(OrdemServico ordemServico) {
        em.getTransaction().begin();
        em.persist(ordemServico);
        em.getTransaction().commit();
    }

    public OrdemServico buscarOrdemServicoPorId(Long id) {
        return em.find(OrdemServico.class, id);
    }

    public List<OrdemServico> listarOrdensServico() {
        return em.createQuery("SELECT o FROM OrdemServico o", OrdemServico.class).getResultList();
    }

    public void atualizarOrdemServico(OrdemServico ordemServico) {
        em.getTransaction().begin();
        em.merge(ordemServico);
        em.getTransaction().commit();
    }

    public void removerOrdemServico(Long id) {
        OrdemServico ordemServico = em.find(OrdemServico.class, id);
        if (ordemServico != null) {
            em.getTransaction().begin();
            em.remove(ordemServico);
            em.getTransaction().commit();
        }
    }

    
    public void fechar() {
        em.close();
        emf.close();
        scanner.close();
    }
    
    public Cliente leituraDadosCliente() {
        Cliente cliente = new Cliente();
    
        scanner.nextLine(); // Limpa o buffer do teclado
    
        System.out.print("Digite o nome do Cliente: ");
        String nome = scanner.nextLine();
        cliente.setNome(nome);
    
        // Aqui, você pode ler e definir outros atributos do Cliente, como endereço, telefone, etc., se necessário.
    
        return cliente;
    }
    
    public OrdemServico leituraDadosOrdemServico() {
        OrdemServico ordemServico = new OrdemServico();
    
        scanner.nextLine(); // Limpa o buffer do teclado
    
        System.out.print("Digite a descrição da Ordem de Serviço: ");
        String descricao = scanner.nextLine();
        ordemServico.setDescricao(descricao);
        List<Cliente> clientes = listarClientes();
        System.out.println("\n ===Lista de Clientes===");
        for (Cliente c : clientes) {
            System.out.println(c.getIdCliente() + " - " + c.getNome());
        }
        System.out.println("Digite o codigo do Cliente: ");
        Long id = scanner.nextLong();
        Cliente cliente = buscarClientePorId(id);
        ordemServico.setCliente(cliente);
        // Aqui, você pode ler e definir outros atributos da Ordem de Serviço, se necessário.
    
        return ordemServico;
    }



    public void exibirMenuCliente() {
        int opcao;
        do {
            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Buscar Cliente por ID");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
    
            opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1:
                    adicionarCliente(leituraDadosCliente());
                    break;
                case 2:
                    System.out.print("Digite o ID do Cliente: ");
                    long id = scanner.nextLong();
                    Cliente cliente = buscarClientePorId(id);
                    if (cliente != null) {
                        System.out.println("Cliente encontrado: " + cliente.getNome());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    List<Cliente> clientes = listarClientes();
                    System.out.println("\n ===Lista de Clientes===");
                    for (Cliente c : clientes) {
                        System.out.println(c.getIdCliente() + " - " + c.getNome());
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do Cliente que deseja atualizar: ");
                    long idAtualizar = scanner.nextLong();
                    Cliente clienteAtualizar = buscarClientePorId(idAtualizar);
                    if (clienteAtualizar != null) {
                        Cliente clienteAtualizado = leituraDadosCliente();
                        clienteAtualizado.setIdCliente(idAtualizar);
                        atualizarCliente(clienteAtualizado);
                        System.out.println("Cliente atualizado com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o ID do Cliente que deseja remover: ");
                    long idRemover = scanner.nextLong();
                    removerCliente(idRemover);
                    System.out.println("Cliente removido com sucesso!");
                    break;
                case 0:
                    // Voltar ao Menu Principal
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
    
    public void exibirMenuOrdemServico() {
        int opcao;
        do {
            System.out.println("\n=== MENU ORDEM DE SERVIÇO ===");
            System.out.println("1. Adicionar Ordem de Serviço");
            System.out.println("2. Buscar Ordem de Serviço por ID");
            System.out.println("3. Listar Ordens de Serviço");
            System.out.println("4. Atualizar Ordem de Serviço");
            System.out.println("5. Remover Ordem de Serviço");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
    
            opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1:
                    adicionarOrdemServico(leituraDadosOrdemServico());
                    break;
                case 2:
                    System.out.print("Digite o ID da Ordem de Serviço: ");
                    long id = scanner.nextLong();
                    OrdemServico ordemServico = buscarOrdemServicoPorId(id);
                    if (ordemServico != null) {
                        System.out.println("Ordem de Serviço encontrada: " + ordemServico.getDescricao());
                    } else {
                        System.out.println("Ordem de Serviço não encontrada.");
                    }
                    break;
                case 3:
                    List<OrdemServico> ordensServico = listarOrdensServico();
                    System.out.println("\n ===Lista de Ordens de Serviço===");
                    for (OrdemServico os : ordensServico) {
                        System.out.println(os.getId() + " - " + os.getDescricao() + " - " + os.getCliente());
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID da Ordem de Serviço que deseja atualizar: ");
                    long idAtualizar = scanner.nextLong();
                    OrdemServico ordemServicoAtualizar = buscarOrdemServicoPorId(idAtualizar);
                    if (ordemServicoAtualizar != null) {
                        OrdemServico ordemServicoAtualizada = leituraDadosOrdemServico();
                        ordemServicoAtualizada.setId(idAtualizar);
                        atualizarOrdemServico(ordemServicoAtualizada);
                        System.out.println("Ordem de Serviço atualizada com sucesso!");
                    } else {
                        System.out.println("Ordem de Serviço não encontrada.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o ID da Ordem de Serviço que deseja remover: ");
                    long idRemover = scanner.nextLong();
                    removerOrdemServico(idRemover);
                    System.out.println("Ordem de Serviço removida com sucesso!");
                    break;
                case 0:
                    // Voltar ao Menu Principal
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {
        Program programa = new Program();

        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Menu Cliente");
            System.out.println("2. Menu Ordem de Serviço");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = programa.scanner.nextInt();

            switch (opcao) {
                case 1:
                    programa.exibirMenuCliente();
                    break;
                case 2:
                    programa.exibirMenuOrdemServico();
                    break;
                case 0:
                    programa.fechar();
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
}