package id.uchidd.tugasdiskon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dataNamaBarang;
    private ArrayList<Integer> dataHargaBarang;
    private ArrayList<Integer> dataGambarBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItemView();
    }

    private void initItemView() {
        final RecyclerView rvListData = (RecyclerView)findViewById(R.id.listMain);
        rvListData.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvListData.setLayoutManager(layoutManager);

        dataNamaBarang = new ArrayList<>();
        dataNamaBarang.add("Adidas Ultra Boost");
        dataNamaBarang.add("Adidas NMD Boost");
        dataNamaBarang.add("Adidas EQT");
        dataNamaBarang.add("Adidas Stan Smith");
        dataNamaBarang.add("Adidas ZX Flux");

        dataHargaBarang = new ArrayList<>();
        dataHargaBarang.add(3200000);
        dataHargaBarang.add(2700000);
        dataHargaBarang.add(2200000);
        dataHargaBarang.add(1700000);
        dataHargaBarang.add(1200000);

        dataGambarBarang = new ArrayList<>();
        dataGambarBarang.add(R.drawable.ultraboost);
        dataGambarBarang.add(R.drawable.nmdboost);
        dataGambarBarang.add(R.drawable.eqt);
        dataGambarBarang.add(R.drawable.stansmith);
        dataGambarBarang.add(R.drawable.zxflux);

        RecyclerView.Adapter adapter = new AdapterList(dataNamaBarang, dataHargaBarang, dataGambarBarang);
        rvListData.setAdapter(adapter);

        final GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        rvListData.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent( RecyclerView recyclerView,  MotionEvent motionEvent) {
                View child = rvListData.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && gestureDetector.onTouchEvent(motionEvent)) {
                    int position = rvListData.getChildAdapterPosition(child);

                    String nama;
                    Integer harga, gambar;

                    nama = dataNamaBarang.get(position);
                    harga = dataHargaBarang.get(position);
                    gambar = dataGambarBarang.get(position);

                    prosesKirimData(nama, harga, gambar);
                }
                return false;
            }

            @Override
            public void onTouchEvent( RecyclerView recyclerView,  MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }

    public void prosesKirimData(String pushNamaBarang, Integer pushHargaBarang, Integer pushGambarBarang){

        Bundle data = new Bundle();

        data.putString("NAMA BARANG", pushNamaBarang);
        data.putInt("HARGA BARANG", pushHargaBarang);
        data.putInt("GAMBAR BARANG", pushGambarBarang);

        Intent prosesKirim = new Intent(MainActivity.this, TransactionActivity.class);
        prosesKirim.putExtras(data);
        startActivity(prosesKirim);
        finish();
        return;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
