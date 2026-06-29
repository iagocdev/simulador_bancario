public class Pessoa {
    private String nome;
    private String cpf;
    private Long telefone;
    public Pessoa(String nome, String cpf, Long telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public Long getTelefone() {
        return telefone;
    }
}
