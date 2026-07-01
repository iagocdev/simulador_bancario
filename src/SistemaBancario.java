import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBancario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ContaBancaria> contas = new ArrayList<>();
        int opcao = 0;
        int proximoNumeroConta = 1001;

        System.out.println("Bem vindo ao Itaú Bank net (Simulador");

        while(opcao != 6){
            System.out.println("\n==================================");
            System.out.println(" 1 - Abrir Conta (Cadastrar Cliente)");
            System.out.println(" 2 - Consultar Contas e Saldos");
            System.out.println(" 3 - Depositar Dinheiro");
            System.out.println(" 4 - Sacar Dinheiro");
            System.out.println(" 5 - Fechar Conta (Excluir)");
            System.out.println(" 6 - Sair");
            System.out.println("\n==================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do Cliente: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o CPF do Cliente: ");
                    String cpf = scanner.nextLine();

                    System.out.println("Digite o telefone do Cliente: ");
                    Long telefone = scanner.nextLong();
                    scanner.nextLine();

                    //Obejtos com Composição
                    Pessoa titular = new Pessoa(nome, cpf, telefone);
                    ContaBancaria novaConta = new ContaBancaria(titular,proximoNumeroConta);
                    contas.add(novaConta);

                    System.out.println("Conta nº "+proximoNumeroConta+"criada com sucesso!");
                    proximoNumeroConta ++;
                    break;

                case 2:
                    if (contas.isEmpty()){
                        System.out.println("Nenhuma conta ativa no sistema.");
                    }else {
                        System.out.println("\n------LISTA DE CONTAS ITAÚ-------");
                        for (int i = 0; i < contas.size(); i++) {
                            ContaBancaria c = contas.get(i);
                            System.out.println((i + 1) + ". Conta " + c.getNumeroConta() +
                                    "|Titular: " + c.getTitular().getNome() +
                                    "| Saldo: " + c.getSaldo());
                        }
                    }
                    break;
                case 3:
                    if (contas.isEmpty()){
                        System.out.println("Nenhuma conta ativa para receber depositos.");
                    } else {
                        System.out.println("Digite a conta para deposito.");
                        int numConta = scanner.nextInt();
                        System.out.println("Digite o valor: ");
                        BigDecimal valorDeposito = scanner.nextBigDecimal();

                        boolean encontrada = false;
                        for (ContaBancaria c : contas) {
                            if (c.getNumeroConta() == numConta) {
                                c.depositar(valorDeposito);
                                encontrada = true;
                                break;
                            }
                        }
                        if (!encontrada){
                            System.out.println("Conta não encontrada.");
                        }
                    } break;
                case 4:
                    if (contas.isEmpty()){
                        System.out.println("Nenhuma conta ativa para sacar.");
                    }else {
                        System.out.println("Digite a conta para sacar.");
                        int numConta = scanner.nextInt();
                        System.out.println("Digite o valor: ");
                        BigDecimal valorSaque = scanner.nextBigDecimal();

                        boolean encontrada = false;
                        for (ContaBancaria c : contas) {
                            if (c.getNumeroConta() == numConta) {
                                c.sacar(valorSaque);
                                encontrada = true;
                                break;
                            }
                        }
                        if (!encontrada)System.out.println("Conta nao encontrada.");
                    }
                    break;
                case 5:
                    if (contas.isEmpty()) {
                        System.out.println("📭 Sem contas registradas para exclusão.");
                    } else {
                        System.out.print("Digite o índice (da lista) da conta a ser excluída: ");
                        int indice = scanner.nextInt();

                        if (indice > 0 && indice <= contas.size()) {
                            ContaBancaria removida = contas.remove(indice - 1);
                            System.out.println("🗑️ Conta Nº " + removida.getNumeroConta() + " de " + removida.getTitular().getNome() + " foi encerrada.");
                        } else {
                            System.out.println("⚠️ Índice inválido.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Saindo do sistema do Banco Itaú... Até logo!");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
            }
        }

