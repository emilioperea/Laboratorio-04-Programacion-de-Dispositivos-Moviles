package com.aditusstudios.laboratorio04;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtDe, edtPara, edtCC, edtCCo, edtAsunto, edtMensaje;
    private Button btnAceptar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDe = findViewById(R.id.edtDe);
        edtPara = findViewById(R.id.edtPara);
        edtCC = findViewById(R.id.edtCC);
        edtCCo = findViewById(R.id.edtCCo);
        edtAsunto = findViewById(R.id.edtAsunto);
        edtMensaje = findViewById(R.id.edtMensaje);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String para = edtPara.getText().toString();
                String cc = edtCC.getText().toString();
                String cco = edtCCo.getText().toString();
                String asunto = edtAsunto.getText().toString();
                String mensaje = edtMensaje.getText().toString();

                if (para.isEmpty() || asunto.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Los campos Para y Asunto son obligatorios",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{para});
                    if (!cc.isEmpty()) {
                        intent.putExtra(Intent.EXTRA_CC, new String[]{cc});
                    }
                    if (!cco.isEmpty()) {
                        intent.putExtra(Intent.EXTRA_BCC, new String[]{cco});
                    }
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje);

                    try {
                        startActivity(Intent.createChooser(intent, "Enviar correo via..."));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MainActivity.this,
                                "No hay aplicaciones de correo instaladas",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDe.setText("");
                edtPara.setText("");
                edtCC.setText("");
                edtCCo.setText("");
                edtAsunto.setText("");
                edtMensaje.setText("");
                Toast.makeText(MainActivity.this, "Formulario limpiado", Toast.LENGTH_LONG).show();
            }
        });
    }
}