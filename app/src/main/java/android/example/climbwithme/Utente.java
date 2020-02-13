package android.example.climbwithme;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class Utente {
    private String codiceSessione;
    private String foto;
    private String nome;
    private String cognome;
    private String numeroTelefono;
    private String dataDiNascita;
    private Integer livelloMaxLead;
    private Integer minLiv;
    private Integer maxLiv;




    public  Utente(String codiceSessione, String foto, String nome, String cognome, String dataDiNascita, Integer livelloMaxLead,
                   Integer minLiv, Integer maxLiv, String numeroTelefono){

        this.codiceSessione = codiceSessione;
        this.foto =foto;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.livelloMaxLead = livelloMaxLead;
        this.minLiv = minLiv;
        this.maxLiv = maxLiv;
        this.numeroTelefono = numeroTelefono;
    }

    public Utente(JSONObject utenteJSON) {

        try {
            this.codiceSessione = utenteJSON.getString("codicesessione");
            this.nome = utenteJSON.getString("nome");
            this.cognome = utenteJSON.getString("cognome");
            this.dataDiNascita = utenteJSON.getString("datadinascita");
            this.numeroTelefono = utenteJSON.getString("numerotelefono");
            this.minLiv = utenteJSON.getInt("minliv");
            this.maxLiv = utenteJSON.getInt("maxliv");

            this.livelloMaxLead = utenteJSON.getInt("livellomaxlead");

                //modifica per crash


            this.foto = utenteJSON.getString("foto");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public String getCodiceSessione() {
        return codiceSessione;
    }

    public void setCodiceSessione(String codiceSessione) {
        this.codiceSessione = codiceSessione;
    }
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
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




}
