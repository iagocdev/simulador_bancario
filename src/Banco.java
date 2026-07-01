import java.util.ArrayList;

public class Banco {
    private ArrayList<ContaBancaria> contas;
    private int proximoNumeroConta;

    public Banco() {
        this.contas = new ArrayList<>();
        this.proximoNumeroConta = 1001;
    }
    //TODO cria e armazena a conta
    public ContaBancaria abrirConta(Pessoa titular) {
        ContaBancaria novaConta = new ContaBancaria(titular, proximoNumeroConta);
        contas.add(novaConta);
        proximoNumeroConta++;
        return novaConta;
    }

    //login
    public ContaBancaria fazerLogin(int numeroConta , String cpf ) {
        for (ContaBancaria c : contas) {
            if (c.getNumeroConta() == numeroConta && c.getTitular().getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
    //pix CPF
    public ContaBancaria buscarContaPorCpf(String cpf) {
        for (ContaBancaria c : contas) {
            if (c.getTitular().getCpf().equals(cpf)) return c;
        }
        return null;
    }
    public ContaBancaria buscaContaPorTelefone(Long telefone) {
        for (ContaBancaria c : contas) {
            if (c.getTitular().getTelefone().equals(telefone)) return c;
        }
        return null;
    }
    public ContaBancaria buscaContaPorNumero(Long numero) {
        for (ContaBancaria c : contas) {
            if (c.getNumeroConta() == numero) return c;
        }
        return null;
    }
    public boolean isVazio() {
        return contas.isEmpty();
    }

}
