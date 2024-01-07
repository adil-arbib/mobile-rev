package com.arbib.test003.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arbib.test003.R;
import com.arbib.test003.models.Note;
import com.arbib.test003.repositories.MyDatabase;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private Button btnAdd;

    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnAdd = findViewById(R.id.btnAdd);

        database = new MyDatabase(this);

        Intent intent = getIntent();

        btnAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            if(!validate(title,content)){
                Toast.makeText(this, "Enter tnawi", Toast.LENGTH_SHORT).show();
                return;
            }
            Note note = new Note(title, content);
            intent.putExtra("TITLE", note.getTitle());
            intent.putExtra("CONTENT", note.getContent());
            setResult(RESULT_OK, intent);
            finish();
//            database.addNote(note);
        });

    }

    private boolean validate(String title, String content) {
        return !title.isEmpty() && !content.isEmpty();
    }

}