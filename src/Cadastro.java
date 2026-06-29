import java.util.ArrayList;
import java.util.Scanner;

public class Cadastro {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        ArrayList<String> cadastro = new ArrayList<>();
        int opcao = 0;

        System.out.println("Bem vindo ao Sistema de Cadastro do Banco Itau");

        while (opcao != 4){
            System.out.println("\n===============================");
            System.out.println("Digite 1 para adicionar novo Pessoa");
            System.out.println("Digite 2 para Listar Pessoa");
            System.out.println("Digite 3 para Remover as pessoas");
            System.out.println("Digite 4 para sair do Sistema");
            System.out.println("\n===============================");


            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Digite o nome do Pessoa");
                    String Novocadastro = scanner.nextLine();
                    cadastro.add(Novocadastro);
                    System.out.println("Pessoa adicionada com sucesso");
                    break;

                case 2:
                    if(cadastro.isEmpty()){
                        System.out.println("Sem nenhum cadastro ainda");
                    }else {
                        System.out.println("-------Lista de Pessoas-------");
                        for (int i = 0; i <cadastro.size() ; i++) {
                            System.out.println((i+1) + ". " + cadastro.get(i));
                        }
                    }
                    break;
                case 3:
                    if(cadastro.isEmpty()){
                        System.out.println("Sem cadastros para excluir");
                    }else  {
                        System.out.println("Digite o indice da pessoa a ser excluida");
                        int indiceRemover = scanner.nextInt();

                        //validando numero
                        if (indiceRemover > 0 && indiceRemover <= cadastro.size()) {
                            String cadastroRemovido = cadastro.remove(indiceRemover-1);
                            System.out.println("Pessoa ("+cadastroRemovido+")removida com sucesso");

                        }else {
                            System.out.println("Indice invalido ");
                        }
                    }
                    break;
                case 4:
                        System.out.println("Saindo do sistema ....");
                        break;
                default:
                    System.out.println("Opção Invalida");
                    break;
            }

        }
        scanner.close();

    }
}
