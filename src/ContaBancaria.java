import java.math.BigDecimal;

public class ContaBancaria {
    private Pessoa titular;
    private int numeroConta;
    private BigDecimal saldo;

    public ContaBancaria(Pessoa titular, int numeroConta) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = BigDecimal.ZERO; // precisão em transações
    }

    public void depositar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
            System.out.println("Deposito de R$ " + valor + "realizado com sucesso");
        } else {
            System.out.println("Valor invalido");
        }
    }

    public boolean sacar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0 && this.saldo.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
            System.out.println("Saque de R$ : " + valor + " realizado com sucesso");
            return true;

        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }

    }

    public Pessoa getTitular() {
        return titular;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}

