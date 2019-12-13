package android.example.climbwithme;

import java.util.Date;

public class Uscita {
    private Date data;
    private String codiceSessione;
    private Double latLuogoPartenza;
    private Double lonLuogoPartenza;
    private Double latLuogoArrivo;
    private Double lonLuogoArrivo;
    private String tipoArrampicata;
    private String mezzoTrasporto;
    private String attrezzatura;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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


}
