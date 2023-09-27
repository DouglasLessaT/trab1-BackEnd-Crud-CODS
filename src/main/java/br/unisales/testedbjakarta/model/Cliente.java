package br.unisales.testedbjakarta.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrdemServico> ordensDeServico;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long id) {
        this.idCliente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<OrdemServico> getOrdensDeServico() {
        return ordensDeServico;
    }

    public void setOrdensDeServico(List<OrdemServico> ordensDeServico) {
        this.ordensDeServico = ordensDeServico;
    }

    @Override
    public String toString(){
        return this.nome;
    }

}   