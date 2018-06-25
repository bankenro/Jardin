package pe.com.globaltics.jardin.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import pe.com.globaltics.jardin.Clases.Views.MisPlantas.LlenarPlantas;
import pe.com.globaltics.jardin.R;

import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegarFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    public RegarFragment() {
        // Required empty public constructor
    }
    private RecyclerView rv;
    private String accion;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Integer codigo;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regar, container, false);
        rv = view.findViewById(R.id.rv);
        swipeRefreshLayout = view.findViewById(R.id.srl);
        if (getArguments()!=null){
            accion = getArguments().getString("accion","");
        }
        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("jardin", Context.MODE_PRIVATE);
        codigo = preferences.getInt("codigo",0);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                Llenar();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }
    @Override
    public void onRefresh() {
        rv.setAdapter(null);
        Llenar();
    }

    private void Llenar() {
        swipeRefreshLayout.setRefreshing(true);
        new LlenarPlantas(getActivity(),urla,accion,codigo,2,rv,swipeRefreshLayout).execute();
    }
}
