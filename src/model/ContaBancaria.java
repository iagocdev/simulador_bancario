package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ContaBancaria {
    private Pessoa titular;
    private int numeroConta;
    private BigDecimal saldo;
    private ArrayList<String> extrato;

    public ContaBancaria(Pessoa titular, int numeroConta) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = BigDecimal.ZERO; // precisão em transações
        this.extrato = new ArrayList<>();
        this.extrato.add("Contra Criada com sucesso");
    }

    // NOVO CONSTRUTOR: Recebe apenas 3 parâmetros (Titular, Numero e Saldo)
    public ContaBancaria(Pessoa titular, int numeroConta, BigDecimal saldo) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.extrato = new ArrayList<>(); // O extrato nasce limpo aqui dentro!
        this.extrato.add("Conta carregada do banco de dados com saldo de R$ " + saldo);
    }


    public void depositar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
            this.extrato.add("+ Deposito " + valor + "| saldo: " + this.saldo);
            System.out.println("Deposito de R$ " + valor + "realizado com sucesso");
        } else {
            System.out.println("Valor invalido");
        }
    }

    public boolean sacar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0 && this.saldo.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
            this.extrato.add("Saque " + valor + "| saldo: " + this.saldo);
            System.out.println("Saque de R$ : " + valor + " realizado com sucesso");
            return true;

        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }

    }
    public boolean transferir(BigDecimal valor, ContaBancaria contaDestino) {

        // 1. Verifica se o valor é válido
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0 && this.saldo.compareTo(valor) >= 0) {

            // 2. Tira o dinheiro do saldo atual (Conta de Origem)
            this.saldo = this.saldo.subtract(valor);

            // 3. Adiciona o dinheiro no saldo da conta de Destino
            contaDestino.saldo = contaDestino.saldo.add(valor);


            //extrato de quem enviou
            this.extrato.add(" PIX Enviado: R$ " + valor + " para " + contaDestino.getTitular().getNome() + " | Saldo: R$ " + this.saldo);

            //  extrato de QUEM RECEBEU
            contaDestino.extrato.add(" PIX Recebido: R$ " + valor + " de " + this.getTitular().getNome() + " | Saldo: R$ " + contaDestino.saldo);

            System.out.println(" Transferência de R$ " + valor + " para " + contaDestino.getTitular().getNome() + " realizada com sucesso!");
            return true;

        } else {
            System.out.println(" Transferência falhou: Saldo insuficiente ou valor inválido.");
            return false;
        }
    }

    public void exibirExtrato() {
        System.out.println("\n === EXTRATO BANCÁRIO DE " + this.titular.getNome().toUpperCase() + " ===");
        for (String movimentacao : extrato) {
            System.out.println(movimentacao);
        }
        System.out.println("=========================================");
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

