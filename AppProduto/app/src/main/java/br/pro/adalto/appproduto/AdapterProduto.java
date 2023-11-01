package br.pro.adalto.appproduto;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.pro.adalto.appproduto.model.Produto;

public class AdapterProduto extends BaseAdapter {

    private List<Produto> lista;
    private Context context;
    private LayoutInflater inflater;

    public AdapterProduto(Context context, List<Produto> produtos){
        this.context = context;
        this.inflater = LayoutInflater.from( context );
        this.lista = produtos;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get( i );
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemSuporte item;
        if( view == null ){
            view = inflater.inflate(R.layout.item_produto, null);
            item = new ItemSuporte();
            item.tvNome = view.findViewById(R.id.tvNomeProduto);
            item.tvPreco = view.findViewById(R.id.tvPrecoProduto);
            item.layout = view.findViewById(R.id.llItemProduto);
        //    item.cbComprar = view.findViewById(R.id.cbItemProduto);
            view.setTag( item );
        }else{
            item = (ItemSuporte) view.getTag();
        }
        Produto prod = lista.get( i );
        item.tvNome.setText( prod.nome );
        item.tvPreco.setText( String.valueOf( prod.preco ) );
        if ( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(240, 240, 240));
        }else {
            item.layout.setBackgroundResource( R.color.vermelhoFadergs );
            //item.layout.setBackgroundColor( Color.WHITE );
        }

//        item.cbComprar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if ( b ){
//                    Toast.makeText(context, prod.nome + " selecionado.",
//                            Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(context, prod.nome + " desmarcado.",
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        return view;
    }

    private class ItemSuporte{
        TextView tvNome, tvPreco;
        LinearLayout layout;
    //    CheckBox cbComprar;
    }

}
