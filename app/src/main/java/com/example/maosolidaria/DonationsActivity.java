package com.example.maosolidaria;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DonationsActivity extends AppCompatActivity {
    private EditText donorName;
    private EditText donorContact;
    private EditText foodType;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);

        donorName = findViewById(R.id.donor_name);
        donorContact = findViewById(R.id.donor_contact);
        foodType = findViewById(R.id.food_type);
        Button submitButton = findViewById(R.id.submit_button);
        db = new DatabaseHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = donorName.getText().toString();
                String contact = donorContact.getText().toString();
                String food = foodType.getText().toString();

                if (name.isEmpty() || contact.isEmpty() || food.isEmpty()) {
                    Toast.makeText(DonationsActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    db.insertDonation(name, contact, food);
                    Toast.makeText(DonationsActivity.this, "Doação registrada com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
