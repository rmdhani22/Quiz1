package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText namaPelanggan, kodeBarang, jumlahBarang;
    RadioGroup memberGroup;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaPelanggan = findViewById(R.id.namapelanggan);
        kodeBarang = findViewById(R.id.kodebrg);
        jumlahBarang = findViewById(R.id.jmlhbrg);
        memberGroup = findViewById(R.id.membergrup);
        btnOrder = findViewById(R.id.btnorder);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid()) {
                    String nama = namaPelanggan.getText().toString();
                    String kode = kodeBarang.getText().toString();
                    String jumlah = jumlahBarang.getText().toString();
                    int selectedId = memberGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String tipeMember = selectedRadioButton.getText().toString();

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("kode", kode);
                    intent.putExtra("jumlah", jumlah);
                    intent.putExtra("tipeMember", tipeMember);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Mohon lengkapi semua field!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isInputValid() {
        return !namaPelanggan.getText().toString().isEmpty() &&
                !kodeBarang.getText().toString().isEmpty() &&
                !jumlahBarang.getText().toString().isEmpty() &&
                memberGroup.getCheckedRadioButtonId() != -1;
    }
}
