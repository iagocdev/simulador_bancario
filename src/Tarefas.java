import java.util.ArrayList;
import java.util.Scanner;

public class Tarefas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        ArrayList<String> tarefas = new ArrayList<>();
        int opcao = 0 ;

        System.out.println("Bem vindo ao Gerenciador de Tarefas");

        //while mantem o programa rodando
        while (opcao != 4) {
            System.out.println("\n =================");
            System.out.println("1. Adcionar tarefas");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Remover Tarefas");
            System.out.println("4. Sair");
            System.out.println("Digite sua escolha");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a tarefa: ");
                    String Novatarefa = scanner.nextLine();
                    tarefas.add(Novatarefa);
                    System.out.println("Tarefas adicionada com sucesso");
                    break;
                case 2:
                    if (tarefas.isEmpty()) {
                        System.out.println("Sua lista está vazia");
                    }else  {
                        System.out.println("\n ------Tarefas ------");
                        //laço para percorrer a lista e imprimir
                        for (int i = 0 ; i < tarefas.size(); i++) {
                            System.out.println(i + 1 + " . " + tarefas.get(i));
                        }
                    }
                    break;

                case 3:
                    if (tarefas.isEmpty()) {
                        System.out.println("Sem tarefas para remover");
                    }else {
                        System.out.println("Digite a tarefa a ser deletada: ");
                        int indiceRemover = scanner.nextInt();
                        // validando numero
                        if (indiceRemover > 0 && indiceRemover <= tarefas.size()) {
                            String tarefaRemovida = tarefas.remove(indiceRemover-1);
                            System.out.println("Tarefas ( "+tarefaRemovida + ") removida com sucesso");
                        }else {
                            System.out.println("Numero invalido");
                        }

                    }
                    break;

                case 4:
                    System.out.println("Encerrando ...");
                    break;

                default:
                    System.out.println("Opcao invalida");
                    break;
            }


        }
        scanner.close();
    }
}
