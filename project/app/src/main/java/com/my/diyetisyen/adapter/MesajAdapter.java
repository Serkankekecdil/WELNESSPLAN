package com.my.diyetisyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.diyetisyen.R;
import com.my.diyetisyen.modeller.MesajModel;

import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter<MesajAdapter.CardViewTasarimNesneleriniTutucu> {

    private Context context;
    private List<MesajModel> mesajListesi;

    public MesajAdapter(Context mContext, List<MesajModel> mesajModel) {
        this.context = mContext;
        this.mesajListesi = mesajModel;
    }

    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public TextView tvText;
        public TextView tvTarih;

        public CardViewTasarimNesneleriniTutucu(View view) {
            super(view);
            tvText = view.findViewById(R.id.tvText);
            tvTarih = view.findViewById(R.id.tvTarih);
        }
    }

    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_motivasyon, parent, false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position) {
        final MesajModel mesaj = mesajListesi.get(position);

        holder.tvText.setText(mesaj.getYazi());
        holder.tvTarih.setText(mesaj.getTarih() + " tarihinde paylaşıldı");

    }

    @Override
    public int getItemCount() {
        return mesajListesi.size();
    }


}
