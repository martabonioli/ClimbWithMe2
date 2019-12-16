package android.example.climbwithme;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Uscita {
    private SimpleDateFormat dataUscita;
    private String codiceSessione;
    private Double latLuogoPartenza;
    private Double lonLuogoPartenza;
    private Double latLuogoArrivo;
    private Double lonLuogoArrivo;
    private String tipoArrampicata;
    private String mezzoTrasporto;
    private String attrezzatura;

    public Uscita(SimpleDateFormat dataUscita, String codiceSessione, Double latLuogoPartenza,
                  Double lonLuogoPartenza, Double latLuogoArrivo, Double lonLuogoArrivo,
                  String tipoArrampicata, String mezzoTrasporto, String attrezzatura ) {
        this.dataUscita = dataUscita;
        this.codiceSessione = codiceSessione;
        this.latLuogoPartenza = latLuogoPartenza;
        this.lonLuogoPartenza = lonLuogoPartenza;
        this.latLuogoArrivo = latLuogoArrivo;
        this.lonLuogoArrivo = lonLuogoArrivo;
        this.tipoArrampicata = tipoArrampicata;
        this.mezzoTrasporto = mezzoTrasporto;
        this.attrezzatura = attrezzatura;
    }

    public SimpleDateFormat getData() {
        return dataUscita;
    }

    public void setData(SimpleDateFormat data) {
        this.dataUscita = data;
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
        return "" + getData() + "" + getCodiceSessione();
    }




}
