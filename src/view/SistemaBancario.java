package view;

import model.ContaBancaria;
import model.Pessoa;
import repository.Banco;

import java.math.BigDecimal;
import java.util.Scanner;

public class SistemaBancario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciando nosso gerenciador de dados
        Banco bancoItau = new Banco();

        int opcaoPrincipal = 0;

        System.out.println("🏦 Bem-vindo ao Itaú Bank net (SOLID & PIX API)");

        // ==========================================
        // MENU 1: TELA INICIAL (DESLOGADO)
        // ==========================================
        while (opcaoPrincipal != 3) {
            System.out.println("\n--- TELA INICIAL ---");
            System.out.println("1 - Abrir Nova Conta");
            System.out.println("2 - Acessar Conta (Login)");
            System.out.println("3 - Sair do repository.Banco");
            System.out.print("Escolha: ");

            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF (Será sua chave PIX 1): ");
                    String cpf = scanner.nextLine();
                    System.out.print("Telefone (Será sua chave PIX 2 - Apenas números): ");
                    long telefone = scanner.nextLong();
                    scanner.nextLine();

                    Pessoa titular = new Pessoa(nome, cpf, telefone);
                    ContaBancaria novaConta = bancoItau.abrirConta(titular);
                    System.out.println("✅ Conta criada! Seu número é: " + novaConta.getNumeroConta());
                    break;

                case 2:
                    if (bancoItau.isVazio()) {
                        System.out.println("📭 Nenhuma conta registrada no banco.");
                        break;
                    }

                    System.out.print("Digite o Número da Conta: ");
                    int numConta = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite seu CPF (Senha): ");
                    String cpfLogin = scanner.nextLine();

                    // O sistema tenta fazer o login
                    ContaBancaria contaLogada = bancoItau.fazerLogin(numConta, cpfLogin);

                    if (contaLogada != null) {
                        System.out.println("\n🔓 Login realizado com sucesso! Olá, " + contaLogada.getTitular().getNome());
                        menuLogado(scanner, bancoItau, contaLogada); // Chama o menu interno
                    } else {
                        System.out.println(" Credenciais inválidas.");
                    }
                    break;

                case 3:
                    System.out.println("Desligando os servidores... Até logo!");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }

    // ==========================================
    // MENU 2: ÁREA PIX (LOGADO)
    // ==========================================
    private static void menuLogado(Scanner scanner, Banco banco, ContaBancaria contaAtiva) {
        int opcaoSecundaria = 0;

        while (opcaoSecundaria != 4) {
            System.out.println("\n--- SUA CONTA | Saldo: R$ " + contaAtiva.getSaldo() + " ---");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir (PIX)");
            System.out.println("4 - Extrato");
            System.out.println("5 - Deslogar (Sair da Conta)");
            System.out.print("Escolha: ");

            opcaoSecundaria = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoSecundaria) {
                case 1:
                    System.out.print("Valor do Depósito: R$ ");
                    contaAtiva.depositar(scanner.nextBigDecimal());
                    break;

                case 2:
                    System.out.print("Valor do Saque: R$ ");
                    contaAtiva.sacar(scanner.nextBigDecimal());
                    break;

                case 3:
                    System.out.println("\n-----Tranferência PIX");
                    System.out.println("Qual tipo de chave ?");
                    System.out.println("1 - CPF");
                    System.out.println("2 - Telefone");
                    System.out.println("3 - Numero Conta");
                    System.out.println("Opção: ");

                    int tipoChave = scanner.nextInt();
                    scanner.nextLine();

                    ContaBancaria contaDestino = null;
                    if (tipoChave == 1) {
                        System.out.println("Digite o CPF: ");
                        String cpfBusca = scanner.nextLine();
                        contaDestino = banco.buscarContaPorCpf(cpfBusca);
                    } else if (tipoChave == 2 ) {
                        System.out.println("Digite o Telefone: ");
                        Long telefoneBusca = scanner.nextLong();
                        scanner.nextLine();
                        contaDestino = banco.buscaContaPorTelefone(telefoneBusca);
                    } else if (tipoChave == 3) {
                        System.out.println("Digite o Numero Conta: ");
                        Long  numeroContaBusca = scanner.nextLong();
                        scanner.nextLine();
                        contaDestino = banco.buscaContaPorNumero(numeroContaBusca);
                    }else {
                        System.out.println("Tipo invalido");
                        break;
                    }
                    if (contaDestino == null) {
                        System.out.println(" Nenhuma conta encontrada com essa chave PIX.");
                    } else if (contaDestino.getNumeroConta() == contaAtiva.getNumeroConta()) {
                        System.out.println("⚠️ Operação bloqueada: Você não pode fazer um PIX para a própria conta logada!");
                    } else {
                        // 3. Se passou pelas validações, executa a transferência
                        System.out.println(" Destinatário encontrado: " + contaDestino.getTitular().getNome());
                        System.out.print("Digite o valor do PIX: R$ ");
                        BigDecimal valorPix = scanner.nextBigDecimal();


                        contaAtiva.transferir(valorPix, contaDestino);
                    }
                    break;
                case 4:
                    contaAtiva.exibirExtrato();
                    break;
                case 5:
                    System.out.println(" Deslogando... Voltando à tela inicial.");
                    break;

                default:
                    System.out.println(" Opção inválida.");
                    break;
            }
        }
    }
}