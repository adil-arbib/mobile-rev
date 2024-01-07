package com.arbib.test003.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Toast;

import com.arbib.test003.R;
import com.arbib.test003.models.Note;
import com.arbib.test003.repositories.MyDatabase;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {


    private ListView listView;

    private Button btnAdd;

    private MyDatabase database;

    private static final int REQUEST_CODE = 1;
    private Spinner spinner;

    private List<Note> notes;

    private ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.sbinner);
//        listView = findViewById(R.id.lvNotes);
//        btnAdd = findViewById(R.id.btnAdd);
//
//        database = new MyDatabase(this);
//
//
//        notes = database.allNotes();
//
//        System.out.println(notes);
//
//        arrayAdapter =
//                new ArrayAdapter<>(this,
//                        android.R.layout.simple_list_item_1,
//                        notes.stream().map(note -> note.getTitle()).collect(Collectors.toList())
//                        );
//
//        listView.setAdapter(arrayAdapter);
//
//
//        btnAdd.setOnClickListener(v -> {
//            Intent intent = new Intent(this, AddNoteActivity.class);
//            startActivityForResult(intent, REQUEST_CODE);
//        });


        String[] items = {"item1","item2","item3"};

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items
        );

        spinner.setAdapter(arrayAdapter1);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String title = data.getStringExtra("TITLE");
            String content = data.getStringExtra("CONTENT");
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
            Note note = new Note(title,content);
            database.addNote(note);
        }
    }
}