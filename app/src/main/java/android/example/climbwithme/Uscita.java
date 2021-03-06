package android.example.climbwithme;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Uscita {
    private String dataUscita;
    private Double latLuogoPartenza;
    private Double lonLuogoPartenza;
    private Double latLuogoArrivo;
    private Double lonLuogoArrivo;
    private String tipoArrampicata;
    private String mezzoTrasporto;
    private String attrezzatura;
    private String codiceSessione;
    private String fotoUtente;
    private String nome;
    private Integer livelloMaxLead;



    public Uscita(String dataUscita, Double latLuogoPartenza,
                  Double lonLuogoPartenza, Double latLuogoArrivo, Double lonLuogoArrivo,
                  String tipoArrampicata, String mezzoTrasporto, String attrezzatura,String codiceSessione, String fotoUtente, String nome, Integer livelloMaxLead ) {
        this.dataUscita = dataUscita;
        this.latLuogoPartenza = latLuogoPartenza;
        this.lonLuogoPartenza = lonLuogoPartenza;
        this.latLuogoArrivo = latLuogoArrivo;
        this.lonLuogoArrivo = lonLuogoArrivo;
        this.tipoArrampicata = tipoArrampicata;
        this.mezzoTrasporto = mezzoTrasporto;
        this.attrezzatura = attrezzatura;
        this.codiceSessione = codiceSessione;
        this.fotoUtente =fotoUtente;
        this.nome = nome;
        this.livelloMaxLead = livelloMaxLead;
    }



    public Uscita(JSONObject uscitaJSON) {
        try {
            this.nome = uscitaJSON.getString("nome");
            this.fotoUtente = uscitaJSON.getString("foto");
            this.dataUscita = uscitaJSON.getString("datauscita");
            this.latLuogoArrivo = uscitaJSON.getDouble("latluogoarrivo");
            this.lonLuogoArrivo = uscitaJSON.getDouble("lonluogoarrivo");
            this.lonLuogoPartenza = uscitaJSON.getDouble("lonluogopartenza");
            this.latLuogoPartenza = uscitaJSON.getDouble("latluogopartenza");
            this.codiceSessione = uscitaJSON.getString("codicesessione");
            this.attrezzatura = uscitaJSON.getString("attrezzatura");
            this.mezzoTrasporto = uscitaJSON.getString("mezzotrasporto");
            this.tipoArrampicata=uscitaJSON.getString("tipoarrampicata");

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

    public Double getLatLuogoPartenza() {
        return latLuogoPartenza;
    }

    public void setLatLuogoPartenza(Double latLuogoPartenza) {
        this.latLuogoPartenza = latLuogoPartenza;
    }

    public Double getLonLuogoPartenza() {
        return lonLuogoPartenza;
    }

    public void setLonLuogoPartenza(Double lonLuogoPartenza) {
        this.lonLuogoPartenza = lonLuogoPartenza;
    }

    public Double getLatLuogoArrivo() {
        return latLuogoArrivo;
    }

    public void setLatLuogoArrivo(Double latLuogoArrivo) {
        this.latLuogoArrivo = latLuogoArrivo;
    }

    public Double getLonLuogoArrivo() {
        return lonLuogoArrivo;
    }

    public void setLonLuogoArrivo(Double lonLuogoArrivo) {
        this.lonLuogoArrivo = lonLuogoArrivo;
    }

    public String getTipoArrampicata() {
        return tipoArrampicata;
    }

    public void setTipoArrampicata(String tipoArrampicata) {
        this.tipoArrampicata = tipoArrampicata;
    }

    public String getMezzoTrasporto() {
        return mezzoTrasporto;
    }

    public void setMezzoTrasporto(String mezzoTrasporto) {
        this.mezzoTrasporto = mezzoTrasporto;
    }

    public String getAttrezzatura() {
        return attrezzatura;
    }

    public void setAttrezzatura(String attrezzatura) {
        this.attrezzatura = attrezzatura;
    }

    public  String getId() {
        return "" + getDataUscita() + " " + getCodiceSessione();
    }


    public String getFoto() {
        return fotoUtente;
    }

    public void setFoto(String foto) {
        this.fotoUtente = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer getLivelloMaxLead() {
        return livelloMaxLead;
    }

    public void setLivelloMaxLead(Integer livelloMaxLead) {
        this.livelloMaxLead = livelloMaxLead;
    }


    public String getDataUscita() {
        return dataUscita;
    }

    public void setDataUscita(String dataUscita) {
        this.dataUscita = dataUscita;
    }


}
