package com.example.myapplicationhorapp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.content.Intent;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {

    //Objeto para instanciar el boton
    ImageView openCamBtn;

    //Objeto para instanciar el boton
    ImageView openDocsBtn;

    //Objeto para instanciar el boton
    ImageView sentBtn;

    //Objeto para instanciar el mensaje de error
    TextView alertTxt;

    TextView newMessage;
    EditText type;

    //Objeto para instanciar la miniatura desde la vista
    ImageView cameraPreview;

    //Variable de control para consultar si el sistema operativo guardo el permiso del usuario solicitado en el Manifest
    private static final int MY_CAMERA_REQUEST_CODE = 2;

    //Variable de control para contralar que la camara si tomó la fotografia
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    //Variable de control para contralar que se abrió un directorio
    private static final int REQUEST_OPEN_DOCUMENTS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        //Instanciar el Boton Camera desde el XL a Java
        openCamBtn = findViewById(R.id.imageView37);

        //Instanciar el Boton Documents desde el XL a Java
        openDocsBtn = findViewById(R.id.imageView16);

        //Instanciar el Boton Documents desde el XL a Java
        sentBtn = findViewById(R.id.imageView17);

        //Instanciar el TextView desde el XL a Java
        alertTxt = findViewById(R.id.alertText);

        //Instanciar el TextView desde el XL a Java
        newMessage = findViewById(R.id.textView30);

        //Instanciar el TextView desde el XL a Java
        type = findViewById(R.id.editTextTextPersonName);

        //Instenciar el Imageview desde el XL a Java
        cameraPreview = findViewById(R.id.imageView3);

        //Consultar si en el manifest fue o no otorgado el permiso para usar la camara
        if(checkSelfPermission (Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            //Como no ha sido otorgado el permiso, se solicita el permiso sobre la camara
            requestPermissions (new String[] {Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        //Asignar el TAP al boton
        configurarOpenCamaraBtn();
        //Asignar el TAP al boton
        configurarOpenDocsBtn();
        //Asignar el TAP al boton
        enviarBtn();
    }

    //Metodo para asignar el eventListener al boton
    private void configurarOpenCamaraBtn(){
        openCamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCamaraActiva(getApplicationContext())){
                    lanzarIntentTomarFoto();
                } else {
                    alertTxt.setText("El dispositivo no tiene cámara o no está disponible");
                }
            }
        });
    }

    //Metodo para asignar el eventListener al boton
    private void configurarOpenDocsBtn(){
        openDocsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                openDirectory(intent.getData());
            }
        });
    }

    //Metodo para asignar el eventListener al boton
    private void enviarBtn(){
        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMessage.setText(type.getText());
            }
        });
    }

    private boolean validarCamaraActiva(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private void lanzarIntentTomarFoto(){
        // Crear el intent solicitandole al sistema operativo que tone una foto
        // Recuerde: El Sistema operativo no sabe cual es la app de fotografias
        Intent tomarUnaFoto = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

        try{
            //Lanzar el intent para abrir la app generica de fotos justamente en la pantalla de cpatura de fotografias.
            // Se usa el codigo como un "Comodin" ue valida si la fotografia fue tomada
            startActivityForResult(tomarUnaFoto, REQUEST_IMAGE_CAPTURE);
        }
        catch (ActivityNotFoundException e) {
            alertTxt.setText("Se presentó un error al tomar la fotografía");
        }
    }

    /*private void lanzarIntentOpenDocs(){
        // Crear el intent solicitandole al sistema operativo que tone una foto
        // Recuerde: El Sistema operativo no sabe cual es la app de fotografias
        Intent abrirDocs = new Intent (DocumentsContract.Document.COLUMN_DISPLAY_NAME);

        try{
            //Lanzar el intent para abrir la app generica de fotos justamente en la pantalla de cpatura de fotografias.
            // Se usa el codigo como un "Comodin" ue valida si la fotografia fue tomada
            startActivityForResult(abrirDocs, REQUEST_OPEN_DOCUMENTS);
        }
        catch (ActivityNotFoundException e) {
            alertTxt.setText("Se presentó un error al abrir el directorio");
        }
    }*/

    //Mostrar en pantalla la miniatura de la imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent fotografia) {
        //Usa el conodin devuelto por el Intent y lo compara contra el valor entregado inicialmente al Intent
        //Luego evalua el exito de la operacion ejecutada por el intent
        super.onActivityResult(requestCode, resultCode, fotografia);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            //Parsear el resultado del Intent para poder trabajarlo
            Bundle extras = fotografia.getExtras();

            //Obtener los bits de color de la imagen para convertirlo en una miniatura de baja resolucion
            Bitmap imagen = (Bitmap) extras.get("data");

            cameraPreview.setImageBitmap(imagen);
        } else
        if (requestCode == REQUEST_OPEN_DOCUMENTS && resultCode == RESULT_OK){
            // The result data contains a URI for the document or directory that
            // the user selected.
            Uri uri = null;
            if (fotografia != null) {
                uri = fotografia.getData();
                // Perform operations on the document using its URI.
            }
        }
    }

    public void openDirectory(Uri uriToLoad) {
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        // Provide read access to files and sub-directories in the user-selected
        // directory.
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when it loads.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);

        startActivityForResult(intent, REQUEST_OPEN_DOCUMENTS);
    }

    public void IrCamera(View view) {
        Intent Camera = new Intent(this, Camera.class);
        startActivity(Camera);
    }

    public void irDocuments(View view) {
        Intent Documents = new Intent(this, Documents.class);
        startActivity(Documents);
    }
}
