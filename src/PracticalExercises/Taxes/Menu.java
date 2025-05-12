package PracticalExercises.Taxes;

import PracticalExercises.Taxes.model.Declaracao;
import PracticalExercises.Taxes.model.DeclaracaoCompleta;
import PracticalExercises.Taxes.model.DeclaracaoSimplificada;
import PracticalExercises.Taxes.persistence.DeclaracaoRepositoy;
import PracticalExercises.Taxes.persistence.InMemoryDeclaracaoRepository;

import java.util.Scanner;

public class Menu {
    private final DeclaracaoRepositoy repository = new InMemoryDeclaracaoRepository();

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n*** MENU ***");
            System.out.println("1. Adicionar Declaração");
            System.out.println("2. Editar Declaração");
            System.out.println("3. Remover Declaração");
            System.out.println("4. Listar Declarações");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");

            int opc = scanner.nextInt();
            scanner.nextLine();
            switch(opc){
                case 1 -> adicionarDeclaracao(scanner);
                case 2 -> editarDeclaracao(scanner);
                case 3 -> removerDeclaracao(scanner);
                case 4 -> listarDeclaracoes();
                case 5 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }

        System.out.println("Programa encerrado.");
    }

    private void adicionarDeclaracao(Scanner scanner){
        System.out.println("\n=== Adicionar Declaração ===");
        System.out.println("Ganho Tributável: ");
        double ganhoTributavel = scanner.nextDouble();
        System.out.println("Valor Pago: ");
        double valorPago = scanner.nextDouble();

        scanner.nextLine();
        System.out.println("Escolha o tipo de declaração:");
        System.out.println("1 - Simplificada");
        System.out.println("2 - Completa");
        int tipo = scanner.nextInt();

        Declaracao declaracao = switch (tipo) {
            case 1 -> new DeclaracaoSimplificada(ganhoTributavel, valorPago);
            case 2 -> new DeclaracaoCompleta(ganhoTributavel, valorPago);
            default -> null;
        };

        if (declaracao != null) {
            repository.save(declaracao);
            System.out.println("Declaração adicionada com sucesso.");
        } else {
            System.out.println("Tipo inválido! Declaração não criada.");
        }
    }

    private void editarDeclaracao(Scanner scanner){
        System.out.println("\n=== Editar Declaração ===");
        System.out.print("ID da declaração: ");
        Long id = scanner.nextLong();

        repository.findById(id).ifPresentOrElse(declaracao -> {
            System.out.print("Novo ganho tributável: ");
            double novoGanho = scanner.nextDouble();
            System.out.print("Novo valor pago: ");
            double novoValorPago = scanner.nextDouble();

            if (declaracao instanceof DeclaracaoSimplificada) {
                repository.update(new DeclaracaoSimplificada(novoGanho, novoValorPago));
            } else if (declaracao instanceof DeclaracaoCompleta completa) {
                repository.update(new DeclaracaoCompleta(novoGanho, novoValorPago));
            }

            System.out.println("Declaração atualizada com sucesso.");
        }, () -> System.out.println("Declaração não encontrada para o ID fornecido."));
    }

    private void removerDeclaracao(Scanner scanner) {
        System.out.println("\n=== Remover Declaração ===");
        System.out.print("ID da declaração: ");
        Long id = scanner.nextLong();

        repository.findById(id).ifPresentOrElse(declaracao -> {
            repository.delete(declaracao);
            System.out.println("Declaração removida com sucesso.");
        }, () -> System.out.println("Declaração não encontrada."));
    }

    private void listarDeclaracoes() {
        System.out.println("\n=== Lista de Declarações ===");
        repository.findAll().forEach(System.out::println);
    }
}

