package com.example.lista_menuapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Lista_tareas extends AppCompatActivity {
    private EditText etTarea;
    private Button btnAdd, btndelete;
    private TextView txtLista;
    private List<String> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_tareas);

        etTarea = findViewById(R.id.etTarea);
        btnAdd = findViewById(R.id.btnAdd);
        btndelete = findViewById(R.id.btndelete);
        txtLista = findViewById(R.id.txtLista);

        listaTareas = new ArrayList<>();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarea = etTarea.getText().toString();
                if (!tarea.isEmpty()) {
                    listaTareas.add(tarea);
                    mostrarTareas();
                    etTarea.setText("");
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listaTareas.isEmpty()) {
                    mostrarDialogoOpciones();
                }
            }
        });
    }

    private void mostrarTareas() {
        StringBuilder builder = new StringBuilder();
        for (String tarea : listaTareas) {
            builder.append(tarea).append("\n");
        }
        txtLista.setText(builder.toString());
    }

    private void mostrarDialogoOpciones() {
        final CharSequence[] tareas = listaTareas.toArray(new CharSequence[listaTareas.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione la tarea");
        builder.setItems(tareas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarDialogoAccion(which);
            }
        });
        builder.show();
    }

    private void mostrarDialogoAccion(final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione una acción");
        builder.setItems(new CharSequence[]{"Modificar", "Eliminar"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    mostrarDialogoModificar(index);
                } else if (which == 1) {
                    listaTareas.remove(index);
                    mostrarTareas();
                }
            }
        });
        builder.show();
    }

    private void mostrarDialogoModificar(final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modificar tarea");
        final EditText input = new EditText(this);
        input.setText(listaTareas.get(index));
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nuevaTarea = input.getText().toString();
                if (!nuevaTarea.isEmpty()) {
                    listaTareas.set(index, nuevaTarea);
                    mostrarTareas();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
