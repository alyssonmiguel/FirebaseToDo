package com.example.firebase_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.firebase_todo.adapter.TarefaAdapter;
import com.example.firebase_todo.modelo.Tarefa;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ListView listView;
    private List<Tarefa> tarefas = new ArrayList<>();
    private ArrayAdapter<Tarefa> arrayAdapter;
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);

        conectarBanco();
        leituraBanco();
        salvarTarefa();
    }

    public void leituraBanco(){
        databaseReference.child("tarefa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Construir o listView com dados do Banco

                tarefas.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Tarefa tarefa = snapshot.getValue(Tarefa.class);
                    tarefas.add(tarefa);
                }
                arrayAdapter = new TarefaAdapter(MainActivity.this,
                        (ArrayList<Tarefa>) tarefas);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void conectarBanco(){

        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarTarefa(){

        Tarefa tarefa = new Tarefa();
        tarefa.setUid(UUID.randomUUID().toString());
        tarefa.setNome("Estudar Android");
        tarefa.setImageSrc("");
        tarefa.setStatus(false);

        databaseReference
                .child("tarefa")
                .child(tarefa.getUid())
                .setValue(tarefa);
    }
}
