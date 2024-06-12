package com.example.lista_menuapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText usuario, password;
    Button iniciar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        iniciar = findViewById(R.id.iniciar);
        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("Mis preferencias", Context.MODE_PRIVATE);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = password.getText().toString();

                usuario.setText("");
                password.setText("");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", user);
                editor.putString("password", pass);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Lista_tareas.class);
                startActivity(intent);
            }
        });
    }
}