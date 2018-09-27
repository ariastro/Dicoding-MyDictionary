package com.example.astronout.mydictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astronout.mydictionary.DetailActivity;
import com.example.astronout.mydictionary.R;
import com.example.astronout.mydictionary.model.Kamus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusHolder> {

    private ArrayList<Kamus> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public KamusAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public KamusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamus, parent, false);
        return new KamusHolder(view);
    }
    public void addItem(ArrayList<Kamus> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(KamusHolder holder, int position) {
        holder.textViewKata.setText(mData.get(position).getKata());
        holder.textViewTranslate.setText(mData.get(position).getTranslate());
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void replaceAll(ArrayList<Kamus> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public class KamusHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_kata)
        TextView textViewKata;
        @BindView(R.id.tv_translate)
        TextView textViewTranslate;

        public KamusHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("extra_kata", mData.get(pos).getKata());
                        intent.putExtra("extra_translate", mData.get(pos).getKata());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
