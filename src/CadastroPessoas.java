import java.util.ArrayList;
import java.util.Scanner;

public class CadastroPessoas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pessoa> cadastro = new ArrayList<>();
        int opcao = 0;

        System.out.println("Bem vindo ao Sistema de Cadastro Do Banco Itaú ");

        while(opcao != 4){
            System.out.println("\n======================");
            System.out.println(" 1 - Cadastrar Pessoa");
            System.out.println(" 2 - Excluir Pessoas");
            System.out.println(" 3 - Consultar Pessoa");
            System.out.println(" 4 - Sair");
            System.out.println("\n======================");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Digite o nome do Pessoa: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o CPF do Pessoa: ");
                    String cpf = scanner.nextLine();

                    System.out.println("Digite o telefone: ");
                    Long telefone = scanner.nextLong();
                    scanner.nextLine();

                    ///objetos
                    Pessoa novaPessoa = new Pessoa(nome, cpf, telefone);
                    cadastro.add(novaPessoa);

                    System.out.println("Pessoa (" +nome+") cadastrada com sucesso!");

                    break;

                case 2:
                    if (cadastro.isEmpty()){
                        System.out.println("Sem cadastros para excluir");
                    }else {
                        System.out.println("Digite o índice da pessoa a ser excluida: ");
                        int indiceRemover = scanner.nextInt();

                        if (indiceRemover > 0 && indiceRemover <= cadastro.size()){
                            Pessoa pessoaremovida = cadastro.remove(indiceRemover -1 );
                            System.out.println("Cliente ("+pessoaremovida.getNome() +") removido com sucesso!");
                        }else {
                            System.out.println("Índice inválido");
                        }

                    }
                    break;
                case 3:
                    if (cadastro.isEmpty()){
                        System.out.println("Sem cadastros ainda");
                    }else {
                        System.out.println("----------Lista de CLientes Itaú---------");
                        for (int i = 0; i < cadastro.size() ; i++) {
                            Pessoa p = cadastro.get(i);
                            System.out.println((i + 1) + ". Nome: " + p.getNome() +
                                    "| CPF: " + p.getCpf() +
                                    "|Telefone: " + p.getTelefone());

                        }
                    }
                    break;

                case 4:
                    System.out.println("Saindo do Sistema.... ");
                    break;
                default:
                    System.out.println("Opção inválida. ");
                    break;
            }

        }
        scanner.close();
    }
}
