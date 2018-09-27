package com.example.astronout.mydictionary;


import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.astronout.mydictionary.adapter.KamusAdapter;
import com.example.astronout.mydictionary.db.KamusHelper;
import com.example.astronout.mydictionary.model.Kamus;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaFragment extends Fragment implements MaterialSearchBar.OnSearchActionListener {

    KamusAdapter kamusAdapter;
    KamusHelper kamusHelper;

    @BindView(R.id.recyclerview_id)
    RecyclerView recyclerView;

    @BindView(R.id.search_bar_id)
    MaterialSearchBar materialSearchBar;

    ArrayList<Kamus> kamus = new ArrayList<>();
    boolean isEnglish = false;

    public IndonesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_indonesia, container, false);

        ButterKnife.bind(this, v);

        kamusHelper = new KamusHelper(getActivity());
        kamusAdapter = new KamusAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(kamusAdapter);

        materialSearchBar.setOnSearchActionListener(this);

        kamusHelper.open();

        // Ambil semua data mahasiswa di database
        loadData();

        kamusHelper.close();

        kamusAdapter.addItem(kamus);

        return v;
    }

    private void loadData(String search) {
        try {
            kamusHelper.open();
            if (search.isEmpty()) {
                kamus = kamusHelper.getAllData(isEnglish);
            } else {
                kamus = kamusHelper.getDataByKata(search, isEnglish);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusHelper.close();
        }
        kamusAdapter.replaceAll(kamus);
    }

    private void loadData() {
        loadData("");
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        loadData(String.valueOf(text));
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}
