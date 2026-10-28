package com.example.sqllite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    TextView t1;
    Button b1, b2;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.e1);;
        t1=findViewById(R.id.t1);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        dbHelper = new DatabaseHelper(this);


    }

    public void insert(View view) {
        String name = e1.getText().toString();
        if (dbHelper.addName(name)) {
            Toast.makeText(this, "Name added successfully", Toast.LENGTH_SHORT).show();
            e1.setText("");

        }
        else {
            Toast.makeText(this, "Failed to add name", Toast.LENGTH_SHORT).show();
        }
    }

    public void show(View view) {
        String names = dbHelper.getAllNames();
        t1.setText(names);
    }
    }
}