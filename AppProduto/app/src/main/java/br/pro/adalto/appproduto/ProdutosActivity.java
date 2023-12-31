package br.pro.adalto.appproduto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.pro.adalto.appproduto.dao.ProdutoDAO;
import br.pro.adalto.appproduto.model.Produto;

public class ProdutosActivity extends AppCompatActivity {

    private ListView lvProdutos;
    private Button btnAdicionar;

    private List<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        lvProdutos = findViewById(R.id.lvProdutos);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listaProdutos = new ArrayList<>();

        carregarProdutos();

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProdutosActivity.this, MainActivity.class);
                i.putExtra("acao", "inserir" );
                startActivity( i );
            }
        });
        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProdutosActivity.this, MainActivity.class);
                Produto p = listaProdutos.get( i );
                int id = p.id;
                intent.putExtra("acao", "editar" );
                intent.putExtra("idProduto", id );
                startActivity( intent );
            }
        });


        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( i );
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Sobre");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.toString().equals("Sobre")){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setMessage("App desenvolvido por Adalto Selau Sparremberger");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void excluir(int posicao){
        Produto prod = this.listaProdutos.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENÇÃO!");
        alerta.setIcon( android.R.drawable.ic_dialog_alert  );
        alerta.setMessage("Confirma a exclusão do produto "+ prod.nome+"?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setNegativeButton("Não", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutoDAO.excluir(ProdutosActivity.this, prod.id);
                carregarProdutos();
            }
        });
        alerta.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarProdutos();
    }

    private void carregarProdutos(){
        this.listaProdutos = ProdutoDAO.getProdutos(this);
    //    ArrayAdapter adapter = new ArrayAdapter(this,
    //            android.R.layout.simple_list_item_1, lista);
        AdapterProduto adapter = new  AdapterProduto(this, this.listaProdutos);
        lvProdutos.setAdapter( adapter );

    }

}