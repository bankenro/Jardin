package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pe.com.globaltics.jardin.Clases.Views.ItemClickListener;
import pe.com.globaltics.jardin.R;

class CuerpoPlantas extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView foto;
    TextView nombre,estado;
    private ItemClickListener itemClickListener;
    CuerpoPlantas(View itemView) {
        super(itemView);
        foto = itemView.findViewById(R.id.foto);
        nombre = itemView.findViewById(R.id.nombre);
        estado = itemView.findViewById(R.id.estado);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
