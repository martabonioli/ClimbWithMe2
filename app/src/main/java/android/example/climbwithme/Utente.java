package android.example.climbwithme;

import java.sql.Date;

public class Utente {
    private String codiceSessione;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private Integer livelloMaxLead;
    private Integer minLiv;
    private Integer maxLiv;

    public  Utente(String codiceSessione, String nome, String cognome, Date dataDiNascita, Integer livelloMaxLead,
                   Integer minLiv, Integer maxLiv){

        this.codiceSessione = codiceSessione;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.livelloMaxLead = livelloMaxLead;
        this.minLiv = minLiv;
        this.maxLiv = maxLiv;
    }

    public String getCodiceSessione() {
        return codiceSessione;
    }

    public void setCodiceSessione(String codiceSessione) {
        this.codiceSessione = codiceSessione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public Integer getLivelloMaxLead() {
        return livelloMaxLead;
    }

    public void setLivelloMaxLead(Integer livelloMaxLead) {
        this.livelloMaxLead = livelloMaxLead;
    }

    public Integer getMinLiv() {
        return minLiv;
    }

    public void setMinLiv(Integer minLiv) {
        this.minLiv = minLiv;
    }

    public Integer getMaxLiv() {
        return maxLiv;
    }

    public void setMaxLiv(Integer maxLiv) {
        this.maxLiv = maxLiv;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    private String numeroTelefono;
}
