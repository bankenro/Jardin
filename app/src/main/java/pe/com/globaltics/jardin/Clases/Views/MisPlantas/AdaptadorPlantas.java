package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.com.globaltics.jardin.Clases.ActualizarPlanta.ActualizarPlanta;
import pe.com.globaltics.jardin.Clases.Views.ItemClickListener;
import pe.com.globaltics.jardin.R;

import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;


public class AdaptadorPlantas extends RecyclerView.Adapter<CuerpoPlantas>{
    private Context c;
    private ArrayList<Plantas> plantas;
    private Integer i;
    AdaptadorPlantas(Context c, ArrayList<Plantas> plantas, Integer i) {
        this.c = c;
        this.plantas = plantas;
        this.i = i;
    }

    @NonNull
    @Override
    public CuerpoPlantas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_plantas, parent, false);
        return new CuerpoPlantas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CuerpoPlantas holder, final int position) {
        final Plantas plantas1 = plantas.get(position);
        holder.nombre.setText(plantas1.getNombre());
        String hecho = "Realizado";
        String nohecho = "Aun no realizado";
        if (plantas1.getEstado()==1){
            holder.estado.setText(hecho);
            holder.estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
        }else {
            holder.estado.setText(nohecho);
            holder.estado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
        }
        String imagen = plantas1.getFoto();
        byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        holder.foto.setImageBitmap(bitmap);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (i){
                    case 1:
                        DialogDescripcion();
                        break;
                    case 2:
                        DialogRegar();
                        break;
                    case 3:
                        DialogAbonar();
                        break;
                    case 4:
                        DialogPodar();
                        break;
                    case 5:
                        DialogTrasplanar();
                        break;
                }
            }

            private void DialogTrasplanar() {
                final Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_trasplanar);
                ImageView foto = d.findViewById(R.id.foto);
                TextView nombre = d.findViewById(R.id.nombre);
                TextView estado = d.findViewById(R.id.estado);
                Button hacer = d.findViewById(R.id.hacer);

                String hecho = "Realizado";
                String nohecho = "Aun no realizado";
                if (plantas1.getEstado()==1){
                    estado.setText(hecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
                }else {
                    estado.setText(nohecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
                }
                nombre.setText(plantas1.getNombre());
                String imagen = plantas1.getFoto();
                byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                foto.setImageBitmap(bitmap);

                hacer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActualizarPlanta(c,urla,"acttras",plantas1.getCodigo(),plantas1.getNombre(),plantas1.getAltura(),plantas1.getColor(), holder.estado, d).execute();
                        }
                });

                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();
            }

            private void DialogPodar() {
                final Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_podar);
                ImageView foto = d.findViewById(R.id.foto);
                TextView nombre = d.findViewById(R.id.nombre);
                TextView estado = d.findViewById(R.id.estado);
                Button hacer = d.findViewById(R.id.hacer);

                String hecho = "Realizado";
                String nohecho = "Aun no realizado";
                if (plantas1.getEstado()==1){
                    estado.setText(hecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
                }else {
                    estado.setText(nohecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
                }
                nombre.setText(plantas1.getNombre());
                String imagen = plantas1.getFoto();
                byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                foto.setImageBitmap(bitmap);

                hacer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActualizarPlanta(c,urla,"actpodar",plantas1.getCodigo(),plantas1.getNombre(),plantas1.getAltura(),plantas1.getColor(), holder.estado, d).execute();

                    }
                });

                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();
            }

            private void DialogAbonar() {
                final Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_abonar);
                ImageView foto = d.findViewById(R.id.foto);
                TextView nombre = d.findViewById(R.id.nombre);
                TextView estado = d.findViewById(R.id.estado);
                Button hacer = d.findViewById(R.id.hacer);

                String hecho = "Realizado";
                String nohecho = "Aun no realizado";
                if (plantas1.getEstado()==1){
                    estado.setText(hecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
                }else {
                    estado.setText(nohecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
                }                nombre.setText(plantas1.getNombre());
                String imagen = plantas1.getFoto();
                byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                foto.setImageBitmap(bitmap);

                hacer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActualizarPlanta(c,urla,"actabonar",plantas1.getCodigo(),plantas1.getNombre(),plantas1.getAltura(),plantas1.getColor(), holder.estado, d).execute();
                    }
                });

                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();
            }

            private void DialogRegar() {
                final Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_regar);
                ImageView foto = d.findViewById(R.id.foto);
                TextView nombre = d.findViewById(R.id.nombre);
                TextView estado = d.findViewById(R.id.estado);
                Button hacer = d.findViewById(R.id.hacer);

                String hecho = "Realizado";
                String nohecho = "Aun no realizado";
                if (plantas1.getEstado()==1){
                    estado.setText(hecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
                }else {
                    estado.setText(nohecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.inactivo));
                }                nombre.setText(plantas1.getNombre());
                String imagen = plantas1.getFoto();
                byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                foto.setImageBitmap(bitmap);

                hacer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActualizarPlanta(c,urla,"actregar",plantas1.getCodigo(),plantas1.getNombre(),plantas1.getAltura(),plantas1.getColor(), holder.estado, d).execute();
                    }
                });

                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();
            }

            private void DialogActualizar() {
                final Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_actualizar);
                ImageView foto = d.findViewById(R.id.foto);
                final EditText nombre = d.findViewById(R.id.nombre);
                final EditText altura = d.findViewById(R.id.altura);
                final EditText color = d.findViewById(R.id.color);
                Button actualizar = d.findViewById(R.id.actualizar);
                Button eliminar = d.findViewById(R.id.eliminar);

                nombre.setText(plantas1.getNombre());
                altura.setText(plantas1.getAltura());
                color.setText(plantas1.getColor());
                String imagen = plantas1.getFoto();
                byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                foto.setImageBitmap(bitmap);

                actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActualizarPlanta(c,urla,"actplanta",plantas1.getCodigo(),nombre.getText().toString().trim(),
                                altura.getText().toString().trim(),color.getText().toString().trim(), holder.estado, d).execute();
                    }
                });
                eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActualizarPlanta(c,urla,"eliplanta",plantas1.getCodigo(),nombre.getText().toString().trim(),
                                altura.getText().toString().trim(),color.getText().toString().trim(), holder.estado, d).execute();
                        int newPosition = holder.getAdapterPosition();
                        plantas.remove(newPosition);
                        notifyItemRemoved(newPosition);
                        notifyItemRangeChanged(newPosition, plantas.size());
                    }
                });
                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();
            }
            private void DialogDescripcion() {
                final Dialog d = new Dialog(c);
                d.setContentView(R.layout.dialog_descripcion);
                ImageView foto = d.findViewById(R.id.foto);
                ImageView edit = d.findViewById(R.id.edit);
                final TextView nombre = d.findViewById(R.id.nombre);
                final TextView altura = d.findViewById(R.id.altura);
                final TextView color = d.findViewById(R.id.color);
                final TextView estado = d.findViewById(R.id.estado);

                nombre.setText(plantas1.getNombre());
                altura.setText(plantas1.getAltura());
                color.setText(plantas1.getColor());
                String no = "Informacion no cargada CLICK-ME";
                if (plantas1.getEstado().equals(0)){
                    estado.setText(no);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
                }
                String imagen = plantas1.getFoto();
                byte[] byteImage = Base64.decode(imagen, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                foto.setImageBitmap(bitmap);

                estado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(c,"Utilize el menu para ver el estado de sus plantas",Toast.LENGTH_SHORT).show();
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogActualizar();
                    }
                });

                Window window = d.getWindow();
                assert window != null;
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                d.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return plantas.size();
    }
}
