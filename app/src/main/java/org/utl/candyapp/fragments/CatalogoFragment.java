package org.utl.candyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.utl.candyapp.R;
import org.utl.candyapp.apis.ProductoApi;
import org.utl.candyapp.model.Producto;
import org.utl.candyapp.myAdapterProducto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogoFragment extends Fragment {


    private static String URL_BASE = "http://10.16.17.147:8080/heelpdeck_Desk/api/";
    public List<Producto> listaProductos = new ArrayList<>();
    RecyclerView recyclerViewProducto;
    myAdapterProducto myAdapter;



    public CatalogoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogoFragment newInstance(String param1, String param2) {
        CatalogoFragment fragment = new CatalogoFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewProducto = view.findViewById(R.id.rvProducto);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getActivity()));
        cargarEmpleados();
    }

    public void cargarEmpleados(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductoApi pro = retrofit.create(ProductoApi.class);
        Call<List<Producto>> listadoProductos = pro.getAll();
        listadoProductos.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                System.out.println("Codigo: "+ response.code());
                if (response.code() == 200){
                    for (Producto p: response.body())
                    {
                        listaProductos.add(p);
                        System.out.println(p.getNombreProducto());
                        System.out.println(p.getPrecioVenta());
                        System.out.println(p.getFotografia());
                    }
                    myAdapter = new myAdapterProducto(getActivity(), listaProductos);
                    recyclerViewProducto.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    System.out.println(listaProductos.size());
                }else if (response.code() == 404){
                    Toast.makeText(getContext(), "No hay nada en la lista", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                System.out.println("Fallo "+ t.toString());
            }
        });
    }

}