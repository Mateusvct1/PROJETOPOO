package model;

public class Profissional extends Usuario {

    private String especialidade;
    private String telefone;

    public Profissional(int id, String nome, String email, String senha,
                        String especialidade, String telefone) {
        super(id, nome, email, senha);
        this.especialidade = especialidade;
        this.telefone = telefone;
    }

    public String getEspecialidade() { return especialidade; }
    public String getTelefone() { return telefone; }

    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " | " + especialidade;
    }
}
