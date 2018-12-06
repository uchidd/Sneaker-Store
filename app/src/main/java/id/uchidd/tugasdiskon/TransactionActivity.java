package id.uchidd.tugasdiskon;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionActivity extends AppCompatActivity {

    @BindView(R.id.gambar)
    ImageView gambar;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.harga)
    TextView harga;
    @BindView(R.id.spinnerUkuran)
    Spinner spinnerUkuran;
    @BindView(R.id.spinnerJumlah)
    Spinner spinnerJumlah;
    @BindView(R.id.cvBeli)
    CardView cvBeli;

    private Integer[] ukuran = {
            37,
            38,
            39,
            40,
            41,
            42,
            43,
            44,
            45
    };

    private Integer[] jumlah = {
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        Bundle getData = getIntent().getExtras();

        final String getNamaBarang = getData.getString("NAMA BARANG");
        final Integer getHargaBarang = getData.getInt("HARGA BARANG");
        Integer getGambarBarang = getData.getInt("GAMBAR BARANG");

        Picasso.get().load(getGambarBarang).into(gambar);
        nama.setText(getNamaBarang);
        harga.setText("Rp " + getHargaBarang);

        ArrayAdapter<Integer> adapterUkuran = new ArrayAdapter<Integer>(TransactionActivity.this, android.R.layout.simple_list_item_1, ukuran);
        adapterUkuran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUkuran.setAdapter(adapterUkuran);

        ArrayAdapter<Integer> adapterJumlah = new ArrayAdapter<Integer>(TransactionActivity.this, android.R.layout.simple_list_item_1, jumlah);
        adapterUkuran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJumlah.setAdapter(adapterJumlah);

        cvBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ukuran = spinnerUkuran.getSelectedItem().hashCode();
                int jumlah = spinnerJumlah.getSelectedItem().hashCode();
                int totalHarga = getHargaBarang * jumlah;
                AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivity.this);

                if (totalHarga >= 2000000) {
                    int diskon = totalHarga*30/100;
                    int bayar = totalHarga - diskon;

                    builder.setTitle("Detail Pembelian")
                            .setMessage("Barang: " + getNamaBarang
                            + "\nUkuran: " + ukuran
                            + "\nHarga: Rp" + getHargaBarang
                            + "\nJumlah: " + jumlah
                            + "\nTotal Harga: Rp" + totalHarga
                            + "\nPotongan Harga: Rp" + diskon
                            + "\nYang Harus Dibayar: Rp" + bayar)
                            .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    builder.setTitle("Detail Pembelian")
                            .setMessage("Barang: " + getNamaBarang
                                    + "\nUkuran: " + ukuran
                                    + "\nHarga: Rp" + getHargaBarang
                                    + "\nJumlah: " + jumlah
                                    + "\nTotal Harga: Rp" + totalHarga
                                    + "\nYang Harus Dibayar: Rp" + totalHarga)
                            .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
                }

                }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TransactionActivity.this, MainActivity.class));
        finish();
    }
}
