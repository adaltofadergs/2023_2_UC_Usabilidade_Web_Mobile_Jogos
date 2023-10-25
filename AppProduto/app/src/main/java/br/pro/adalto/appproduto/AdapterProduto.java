package br.pro.adalto.appproduto;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.pro.adalto.appproduto.model.Produto;

public class AdapterProduto extends BaseAdapter {

    private List<Produto> lista;

    public AdapterProduto(Context context, List<Produto> produtos){

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
        return null;
    }

}
