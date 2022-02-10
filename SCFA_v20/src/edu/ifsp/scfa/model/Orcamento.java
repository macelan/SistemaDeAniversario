/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author macel
 */
@Entity
@Table(name = "Orcamento")
public class Orcamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrcamento", nullable = false)
    private Integer idOrcamento;
    @Column(name = "dataOrcamento") //Gravará data pelo sistema
    @Temporal(TemporalType.DATE)
    private Date dataOrcamento;
    @Column(name = "valorTotal")
    private double valorTotal;
    @Column(name = "valorTotalMaoDeObra")
    private double valorTotalMaoDeObra;
    @Column(name = "quantidadeAlimento", length = 4)
    private Integer quantidadeAlimento;
    @Column(name = "quantidadeBebida",length = 4)
    private Integer quantidadeBebida;
    @Column(name = "quantidadeUtensilio",length = 4)
    private Integer quantidadeUtensilio;
    @Column(name = "status",length = 70)
    private String status;
    @Column(name = "nomeDoAniversariante", length = 70)
    private String nomeDoAniversariante;
    @Column(name = "dataEvento" )
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @Column(name = "horaEvento")
    @Temporal(TemporalType.TIME)
    private Date horaEvento;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "orcamento")
    private List<ItemOrcamentoAlimento> alimentos;
    
    @OneToMany(mappedBy = "orcamento")
    private List<ItemOrcamentoBebida> bebidas;
    
    @OneToMany(mappedBy = "orcamento")
    private List<ItemOrcamentoUtensilio> utensilios;
    
//    @OneToMany
//    private List<Bebida> bebidas = new ArrayList<>();
    
//    @ManyToMany
//    private List<Alimento> alimentos = new ArrayList<>();

    public Orcamento() {
        this.alimentos = new ArrayList();
        this.bebidas = new ArrayList();
    }

    public Integer getQuantidadeUtensilio() {
        return quantidadeUtensilio;
    }

    public void setQuantidadeUtensilio(Integer quantidadeUtensilio) {
        this.quantidadeUtensilio = quantidadeUtensilio;
    }

    public List<ItemOrcamentoUtensilio> getUtensilios() {
        return utensilios;
    }

    public void setUtensilios(List<ItemOrcamentoUtensilio> utensilios) {
        this.utensilios = utensilios;
    }
    
    

    public Integer getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Integer idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Date getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(Date dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeAlimento() {
        return quantidadeAlimento;
    }

    public void setQuantidadeAlimento(Integer quantidadeAlimento) {
        this.quantidadeAlimento = quantidadeAlimento;
    }

    public Integer getQuantidadeBebida() {
        return quantidadeBebida;
    }

    public void setQuantidadeBebida(Integer quantidadeBebida) {
        this.quantidadeBebida = quantidadeBebida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeDoAniversariante() {
        return nomeDoAniversariante;
    }

    public void setNomeDoAniversariante(String nomeDoAniversariante) {
        this.nomeDoAniversariante = nomeDoAniversariante;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Date getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Date horaEvento) {
        this.horaEvento = horaEvento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemOrcamentoAlimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<ItemOrcamentoAlimento> alimentos) {
        this.alimentos = alimentos;
    }

    public List<ItemOrcamentoBebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<ItemOrcamentoBebida> bebidas) {
        this.bebidas = bebidas;
    }

    public double getValorTotalMaoDeObra() {
        return valorTotalMaoDeObra;
    }

    public void setValorTotalMaoDeObra(double valorTotalMaoDeObra) {
        this.valorTotalMaoDeObra = valorTotalMaoDeObra;
    }
   
    @Override
    public String toString() {
        return "Orcamento{" + "idOrcamento=" + idOrcamento + ", dataOrcamento=" + dataOrcamento + ", valorTotal=" + valorTotal + ", quantidadeAlimento=" + quantidadeAlimento + ", quantidadeBebida=" + quantidadeBebida + ", quantidadeUtensilio=" + quantidadeUtensilio + ", status=" + status + ", nomeDoAniversariante=" + nomeDoAniversariante + ", dataEvento=" + dataEvento + ", horaEvento=" + horaEvento + ", cliente=" + cliente + ", alimentos=" + alimentos + ", bebidas=" + bebidas + ", utensilios=" + utensilios + '}';
    }

    

    
    
}
