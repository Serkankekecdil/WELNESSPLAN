package com.my.diyetisyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.diyetisyen.R;
import com.my.diyetisyen.modeller.BesinBilgisiModel;

import java.util.List;

public class BesinBilgisiAdapter extends RecyclerView.Adapter<BesinBilgisiAdapter.CardViewTasarimNesneleriniTutucu> {

    private Context context;
    private List<BesinBilgisiModel> besinBilgisiListesi;

    public BesinBilgisiAdapter(Context mContext, List<BesinBilgisiModel> besinler) {
        this.context = mContext;
        this.besinBilgisiListesi = besinler;
    }

    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_besin_ve_kalorisi, parent, false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position) {
        final BesinBilgisiModel besin = besinBilgisiListesi.get(position);

        holder.tvBesinAdi.setText(besin.getAd());
        holder.tvKalori.setText(besin.getKalori());
    }

    @Override
    public int getItemCount() {
        return besinBilgisiListesi.size();
    }

    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public TextView tvBesinAdi;
        public TextView tvKalori;

        public CardViewTasarimNesneleriniTutucu(View view) {
            super(view);
            tvBesinAdi = view.findViewById(R.id.tvBesinAdi);
            tvKalori = view.findViewById(R.id.tvKalori);
        }
    }
}
