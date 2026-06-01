package model;

public class Cliente extends Usuario {

    private String telefone;

    public Cliente(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha);
        this.telefone = telefone;
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " | " + getTelefone();
    }
}
