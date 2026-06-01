package model;

public class Servico {

    private int id;
    private String nome;
    private double preco;
    private int duracaoMin;

    public Servico(int id, String nome, double preco, int duracaoMin) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMin = duracaoMin;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getDuracaoMin() { return duracaoMin; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setDuracaoMin(int duracaoMin) { this.duracaoMin = duracaoMin; }

    @Override
    public String toString() {
        return id + " - " + nome + " | R$ " + String.format("%.2f", preco) + " | " + duracaoMin + "min";
    }
}
