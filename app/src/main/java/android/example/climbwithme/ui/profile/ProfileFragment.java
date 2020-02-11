package android.example.climbwithme.ui.profile;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.example.climbwithme.MyModel;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.example.climbwithme.R;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment implements NumberPicker.OnValueChangeListener{
    private TextView textView;
    private ImageView imageview;
    private String foto;
    private static final String IMAGE_DIRECTORY = "/encoded_image";
    private static final int GALLERY = 1, CAMERA = 2;
    private Context context;
    Uri myPicture = null;
    ImageButton selectImage;
    View view;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        selectImage = (ImageButton) view.findViewById(R.id.selectphoto);
        imageview =  (ImageView)view.findViewById(R.id.foto);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

       ImageButton button = (ImageButton) view.findViewById(R.id.imageButton3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog newFragment = new NumberPickerDialog();
                newFragment.setValueChangeListener(ProfileFragment.this);
                newFragment.show(getFragmentManager(), "time picker");

            }
        });
        return view;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        TextView maxlead = (TextView) view.findViewById(R.id.addpb);
        maxlead.setText(matchLivello(numberPicker.getValue()+1));
        MyModel.utente.setLivelloMaxLead(numberPicker.getValue());

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TextView textView = view.findViewById(R.id.text_profile);
        //textView.setText("Siamo nel profile fragment");

        String nomecognome = MyModel.utente.getNome().trim()+" "+ MyModel.utente.getCognome().trim();
        String dataDiNascita = MyModel.utente.getDataDiNascita();
        TextView textView1 = view.findViewById(R.id.addnome);
        textView1.setText(nomecognome);
        TextView textView2 = view.findViewById(R.id.adddataNascita);
        textView2.setText(dataDiNascita);
        TextView textView3 = view.findViewById(R.id.addtelefono);
        textView3.setText("+39 "+ MyModel.utente.getNumeroTelefono());
        TextView textView4 = view.findViewById(R.id.addminliv);
        textView4.setText(""+ matchLivello(MyModel.utente.getMinLiv())+"   /   "+ matchLivello(MyModel.utente.getMaxLiv()));
        TextView textView5 = view.findViewById(R.id.addpb);
        textView5.setText( matchLivello(MyModel.utente.getMaxLiv()));

        if (MyModel.utente.getFoto() != null){
            ImageView addfoto= view.findViewById(R.id.foto);
            String b64img =MyModel.utente.getFoto();
            byte[] decodedString = Base64.decode(b64img, Base64.DEFAULT); //la converto in bytestream
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); //converto il bytestream in Bitmap
            imageview.setImageBitmap(decodedByte);
        }else{
            imageview.setImageResource(R.drawable.ic_profile_black_24dp);
        }

    }




    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context=activity;
    }



    public String matchLivello(int s){
        String livello="";
        if (s==1){ livello="4a"; }
        if (s==2){ livello="4a+"; }
        if (s==3){ livello="4b"; }
        if (s==4){ livello="4b+"; }
        if (s==5){ livello="4c"; }
        if (s==6){ livello="4c+"; }
        if (s==7){ livello="5a"; }
        if (s==8){ livello="5a+"; }
        if (s==9){ livello="5b"; }
        if (s==10){ livello="5b+"; }
        if (s==11){ livello="5c"; }
        if (s==12){ livello="5c+"; }
        if (s==13){ livello="6a"; }
        if (s==14){ livello="6a+"; }
        if (s==15){ livello="6b"; }
        if (s==16){ livello="6b+"; }
        if (s==17){ livello="6c"; }
        if (s==18){ livello="6c+"; }
        if (s==19){ livello="7a"; }
        if (s==20){ livello="7a+"; }
        if (s==21){ livello="7b"; }
        if (s==22){ livello="7b+"; }
        if (s==23){ livello="7c"; }
        if (s==24){ livello="7c+"; }
        if (s==25){ livello="8a"; }
        if (s==26){ livello="8a+"; }
        if (s==27){ livello="8b"; }
        if (s==28){ livello="8b+"; }
        if (s==29){ livello="8c"; }
        if (s==30){ livello="8c+"; }
        if (s==31){ livello="9a"; }
        if (s==32){ livello="9a+"; }
        if (s==33){ livello="9b"; }
        if (s==34){ livello="9b+"; }
        if (s==35){ livello="9c"; }
        if (s==36){ livello="9c+"; }
        return livello;
    }




    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
        pictureDialog.setTitle("AGGIUNGI IMMAGINE PROFILO ");
        String[] pictureDialogItems = {
                "Seleziona dalla galleria",
                "Scatta una foto" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), contentURI);
                    Toast.makeText(context, "Immagine salvata!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);
                    saveImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(context, "Immagine salvata!", Toast.LENGTH_SHORT).show();
        }
    }


    public void saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        // converto la bitmap in un byteArray
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        //codifico il bytearray in una stringa in formato base64, per essere caricato sul server
        MyModel.utente.setFoto(encodedImage);
        Log.d("encoded",encodedImage.toString());
        Log.d("fotonelmodel", MyModel.utente.getFoto());

        //chiamata volley per inserire la foto nel DB
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        String url = "https://climbwithme.herokuapp.com/iserisciutente.php";
        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("Foto", encodedImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                datiDaPassare,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("VolleySaveFoto", "Correct: " + response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.toString());
            }
        });
        Log.d("Volley", "Sending request");
        mRequestQueue.add(request);

    }

}


