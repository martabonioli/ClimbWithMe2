package android.example.climbwithme.ui.profile;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.example.climbwithme.MyModel;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.example.climbwithme.R;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
    private TextView textView;
    private ImageView imageview;
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TextView textView = view.findViewById(R.id.text_profile);
        //textView.setText("Siamo nel profile fragment");

        String nomecognome = MyModel.utente.getNome().trim()+" "+ MyModel.utente.getCognome().trim();
        String dataDiNascita = MyModel.utente.getDataDiNascita();
            Log.d("nomecognoeModel",nomecognome);



        TextView textView1 = view.findViewById(R.id.addnome);
        textView1.setText(nomecognome);
        TextView textView2 = view.findViewById(R.id.adddataNascita);
        textView2.setText(dataDiNascita);
        TextView textView3 = view.findViewById(R.id.addtelefono);
        textView3.setText(MyModel.utente.getNumeroTelefono());
        TextView textView4 = view.findViewById(R.id.addminliv);
        textView4.setText(""+ matchLivello(MyModel.utente.getMinLiv())+"   ----   "+ matchLivello(MyModel.utente.getMaxLiv()));


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
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
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
                    String path = saveImage(bitmap);
                    Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }


    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(context,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

}


