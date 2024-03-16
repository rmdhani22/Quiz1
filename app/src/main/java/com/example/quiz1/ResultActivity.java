package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    TextView namaPelangganText, kodeBarangText, namaBarangText, hargaBarangText, jumlahText, totalHargaText, diskonText, diskonMemberText, jumlahBayarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        namaPelangganText = findViewById(R.id.nama_pelanggan);
        kodeBarangText = findViewById(R.id.kode_barang);
        namaBarangText = findViewById(R.id.nama_barang);
        hargaBarangText = findViewById(R.id.harga_barang);
        jumlahText = findViewById(R.id.jumlah_barang);
        totalHargaText = findViewById(R.id.total_harga);
        diskonText = findViewById(R.id.diskon_harga);
        diskonMemberText = findViewById(R.id.diskon_member);
        jumlahBayarText = findViewById(R.id.jumlah_bayar);

        Button shareButton = findViewById(R.id.button_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareResult();
            }
        });

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String kode = intent.getStringExtra("kode");
        String jumlah = intent.getStringExtra("jumlah");
        String tipeMember = intent.getStringExtra("tipeMember");

        double hargaBarang = getHargaBarang(kode);
        String namaBarang = getNamaBarang(kode);
        double totalHarga = Double.parseDouble(jumlah) * hargaBarang;
        double diskonHarga = hitungDiskon(totalHarga);
        double diskonMember = hitungDiskonMember(totalHarga, tipeMember);
        double totalBayar = totalHarga - diskonHarga - diskonMember;

        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String totalHargaFormatted = rupiahFormat.format(totalHarga);
        String diskonHargaFormatted = rupiahFormat.format(diskonHarga);
        String diskonMemberFormatted = rupiahFormat.format(diskonMember);
        String totalBayarFormatted = rupiahFormat.format(totalBayar);
        String hargaBarangFormatted = rupiahFormat.format(hargaBarang);

        namaPelangganText.setText("Nama Pembeli: " + nama);
        kodeBarangText.setText("Kode Barang: " + kode);
        namaBarangText.setText("Nama Barang: " + namaBarang);
        hargaBarangText.setText("Harga Barang: " + hargaBarangFormatted);
        jumlahText.setText("Jumlah: " + jumlah);
        totalHargaText.setText("Total Harga: " + totalHargaFormatted);
        diskonText.setText("Diskon Harga: " + diskonHargaFormatted);
        diskonMemberText.setText("Diskon Member: " + diskonMemberFormatted);
        jumlahBayarText.setText("Jumlah Bayar: " + totalBayarFormatted);
    }

    private double getHargaBarang(String kode) {
        switch (kode) {
            case "IPX":
                return 5725300;
            case "PCO":
                return 2730551;
            case "LV3":
                return 6666666;

            default:
                return 0;
        }
    }

    private String getNamaBarang(String kode) {
        switch (kode) {

            case "IPX":
                return "Iphone X";
            case "PCO":
                return "POCO M3";
            case "LV3":
                return "Lenovo V14 Gen 3";
            default:
                return "Barang tidak ditemukan";
        }
    }

    private double hitungDiskon(double totalHarga) {
        if (totalHarga >= 100000) {
            return 0.1 * totalHarga;
        } else {
            return 0;
        }
    }

    private double hitungDiskonMember(double totalHarga, String tipeMember) {
        if (tipeMember.equals("Gold")) {
            return 0.05 * totalHarga;
        } else if (tipeMember.equals("Platinum")) {
            return 0.1 * totalHarga;
        } else {
            return 0;
        }
    }

    // Method untuk berbagi hasil pembelian
    private void shareResult() {
        String message = "Nama Pembeli: " + namaPelangganText.getText().toString() + "\n" +
                "Kode Barang: " + kodeBarangText.getText().toString() + "\n" +
                "Nama Barang: " + namaBarangText.getText().toString() + "\n" +
                "Harga Barang: " + hargaBarangText.getText().toString() + "\n" +
                "Jumlah: " + jumlahText.getText().toString() + "\n" +
                "Total Harga: " + totalHargaText.getText().toString() + "\n" +
                "Diskon Harga: " + diskonText.getText().toString() + "\n" +
                "Diskon Member: " + diskonMemberText.getText().toString() + "\n" +
                "Jumlah Bayar: " + jumlahBayarText.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Bagikan Pembelian Melalui"));
    }
}
