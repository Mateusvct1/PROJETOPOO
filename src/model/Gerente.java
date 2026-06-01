package model;

public class Gerente extends Profissional {

    private String cargo;

    public Gerente(int id, String nome, String email, String senha,
                   String especialidade, String telefone, String cargo) {
        super(id, nome, email, senha, especialidade, telefone);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " | " + cargo;
    }
}
