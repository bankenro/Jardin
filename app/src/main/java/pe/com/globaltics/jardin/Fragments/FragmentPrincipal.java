package pe.com.globaltics.jardin.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.Objects;

import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;


import pe.com.globaltics.jardin.Clases.Views.MisPlantas.LlenarPlantas;
import pe.com.globaltics.jardin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPrincipal extends Fragment implements SearchView.OnQueryTextListener,View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{


    public FragmentPrincipal() {
        // Required empty public constructor
    }
    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String accion;
    private Integer codigo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_principal, container, false);

        rv = view.findViewById(R.id.rv);
        FloatingActionButton fb = view.findViewById(R.id.add);
        swipeRefreshLayout = view.findViewById(R.id.srl);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        SearchView searchView =  view.findViewById(R.id.sv);
        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("jardin", Context.MODE_PRIVATE);
        codigo = preferences.getInt("codigo",0);

        if (getArguments() != null) {
            accion = getArguments().getString("accion", "");
        }

        fb.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                Llenar("");
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                Bundle bundle = new Bundle();
                Fragment fragment = new AgregarPlantas();
                bundle.putString("accion","addplantas");
                fragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().
                        beginTransaction().replace(R.id.contenedor,fragment).commit();
                break;
        }
    }

    @Override
    public void onRefresh() {
        rv.setAdapter(null);
        Llenar("");
    }

    private void Llenar(String s) {
        swipeRefreshLayout.setRefreshing(true);
        new LlenarPlantas(getActivity(),urla,accion,codigo,1,s,rv,swipeRefreshLayout).execute();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Llenar(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Llenar(newText);
        return false;
    }
}
