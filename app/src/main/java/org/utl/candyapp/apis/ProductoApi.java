package org.utl.candyapp.apis;

import org.utl.candyapp.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {

    @GET("producto/getAll")
    Call<List<Producto>> getAll();

}
