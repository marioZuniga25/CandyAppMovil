package org.utl.candyapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.utl.candyapp.model.Producto;

import java.util.List;

public class myAdapterProducto extends RecyclerView.Adapter <myAdapterProducto.MyProductoViewHolder>{

        Context context;
        List<Producto> listaProductos;


        public myAdapterProducto(Context context, List<Producto> listaProductos) {
            this.context = context;
            this.listaProductos = listaProductos;
        }

        @NonNull
        @Override
        public MyProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_view_producto, parent, false);
            return new MyProductoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyProductoViewHolder holder, int position) {
            Producto p = listaProductos.get(position);
            holder.txtNombreProducto.setText(p.getNombreProducto());
            holder.txtPrecioVenta.setText(String.valueOf(p.getPrecioVenta()));

            byte [] bytes = Base64.decode(p.getFotografia(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            holder.imgEmpleado.setImageBitmap(bitmap);
        }

    /*private void decodeImage(){
        byte [] bytes = Base64.decode(, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        //imgFoto.setImageBitmap(bitmap);
    }*/

        @Override
        public int getItemCount() {
            if (listaProductos.size() == 0){
                return 0;
            }else {
                return listaProductos.size();
            }

        }

        public static class MyProductoViewHolder extends RecyclerView.ViewHolder {

            TextView txtNombreProducto;
            TextView txtPrecioVenta;
            ImageView imgEmpleado;


            public MyProductoViewHolder(@NonNull View itemView) {
                super(itemView);

                txtNombreProducto = itemView.findViewById(R.id.lblNombreProducto);
                txtPrecioVenta = itemView.findViewById(R.id.lblPrecioProducto);
                imgEmpleado = itemView.findViewById(R.id.imgProducto);

            }
        }

    }
