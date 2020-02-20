package android.example.climbwithme.ui.bacheca;

import android.app.Activity;
import android.content.Intent;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SingolaUscita extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView fotoUtente;
    private TextView nomeUtente;
    private TextView dataUscita;
    private TextView partenza;
    private TextView arrivo;
    private Activity parentActivity;
    private String uscita;


    public SingolaUscita(@NonNull View itemView,  Activity parentActivity) {
        super(itemView);
        this.parentActivity = parentActivity;
        fotoUtente = itemView.findViewById(R.id.fotoUtente);
        nomeUtente = itemView.findViewById(R.id.nomeUtente);
        dataUscita = itemView.findViewById(R.id.dataUscita);
        partenza = itemView.findViewById(R.id.partenza);
        arrivo = itemView.findViewById(R.id.arrivo);
        itemView.setOnClickListener(this);
    }

    public void setUscita(Uscita uscita) {
        this.uscita = uscita.getId();
        fotoUtente.setImageBitmap(convert(uscita.getFoto()));
        nomeUtente.setText (uscita.getNome());
        dataUscita.setText(uscita.getDataUscita());
        partenza.setText(getCompleteAddressString(uscita.getLatLuogoPartenza(),uscita.getLonLuogoPartenza()));
        arrivo.setText(getCompleteAddressString(uscita.getLatLuogoArrivo(),uscita.getLonLuogoArrivo()));

    }

    private Bitmap convert(String foto) {
        String b64img = foto; //prendo l'immagine sotto forma di stringa
        byte[] decodedString = Base64.decode(b64img, Base64.DEFAULT); //la converto in bytestream
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); //converto il bytestream in Bitmap
       return decodedByte;
    }


    public void onClick(View v) {
        Intent openDetail = new Intent(parentActivity, DettagliUscita.class);
        openDetail.putExtra(DettagliUscita.USCITA_EXTRA,uscita);
        parentActivity.startActivity(openDetail);
    }
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(parentActivity, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);

                StringBuilder strReturnedAddress = new StringBuilder("");

                /*for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }*/
                strReturnedAddress.append(returnedAddress.getLocality());
                strAdd = strReturnedAddress.toString();

                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
}
