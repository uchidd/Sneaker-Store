package id.uchidd.tugasdiskon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private ArrayList<String> dataNamaBarang;
    private ArrayList<Integer> dataHargaBarang;
    private ArrayList<Integer> dataGambarBarang;

    public AdapterList(
            ArrayList<String> namaBarang, ArrayList<Integer> hargaBarang, ArrayList<Integer> gambarBarang){

        this.dataNamaBarang = namaBarang;
        this.dataHargaBarang = hargaBarang;
        this.dataGambarBarang = gambarBarang;

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {

        viewHolder.namaBarang.setText(dataNamaBarang.get(i));
        viewHolder.hargaBarang.setText("Rp " + dataHargaBarang.get(i));
        Picasso.get().load(dataGambarBarang.get(i)).into(viewHolder.gambarBarang);
    }

    @Override
    public int getItemCount() {
        return dataNamaBarang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView namaBarang, hargaBarang;
        private ImageView gambarBarang;

        public ViewHolder(View view) {
            super(view);
            namaBarang = (TextView)itemView.findViewById(R.id.namaBarang);
            hargaBarang = (TextView)itemView.findViewById(R.id.hargaBarang);
            gambarBarang = (ImageView) itemView.findViewById(R.id.gambarBarang);
        }
    }
}
