package com.example.myapplicationhorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget. ImageView;
import android.widget.TextView;

public class Camera extends AppCompatActivity {
    //Objeto para instanciar el boton
    Button openCamBtn;
    //Objeto para instanciar el mensaje de error
    TextView alertTxt;
    //Objeto para instanciar la miniatura desde la vista
    ImageView cameraPreview;

    //Variable de control para consultar si el sistema operativo guardo el permiso del usuario solicitado en el Manifest
    private static final int MY_CAMERA_REQUEST_CODE = 2;

    //Variable de control para contralar que la camara si tomó la fotografia
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        //Instanciar el Boton devde el XL a Java
        openCamBtn = findViewById(R.id.openCamBtn);

        //Instanciar el TextView desde el XL a Java
        alertTxt = findViewById(R.id.TextAlertCam);

        //Instenciar el Imageview desde el XL a Java
        cameraPreview = findViewById(R.id.CamPreview);

        //Consultar si en el manifest fue o no otorgado el permiso para usar la camara
        if(checkSelfPermission (Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            //Como no ha sido otorgado el permiso, se solicita el permiso sobre la camara
            requestPermissions (new String[] {Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        //Asignar el TAP al boton
        configurarOpenCamaraBtn();
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
        }
    }
}