package br.pro.adalto.appproduto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.pro.adalto.appproduto.dao.ProdutoDAO;
import br.pro.adalto.appproduto.model.Produto;

public class MainActivity extends AppCompatActivity {

    private EditText etNome, etPreco;
    private Button botaoSalvar;

    private String acao;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNome = (EditText) findViewById(R.id.etNome);
        etPreco = findViewById(R.id.etPreco);
        botaoSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMenuLimpar){
            etNome.setText("");
            etPreco.setText("");
        }

        if (item.getItemId() == R.id.itemMenuSite){
            Uri uri = Uri.parse("https://fadergs.edu.br");
            Intent intent= new Intent(Intent.ACTION_VIEW, uri );
            startActivity( intent );
        }

        if (item.getItemId() == R.id.itemMenuLigar){
            Uri uri = Uri.parse("tel: 51987654321");
            Intent intent= new Intent(Intent.ACTION_CALL, uri );
            startActivity( intent );
        }
        if (item.getItemId() == R.id.itemMenuTelefone){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Fazer chamada...");
            EditText etNumero = new EditText(this);
            etNumero.setHint("Digite o numero do telefone:");
            etNumero.setInputType(InputType.TYPE_CLASS_PHONE);
            alerta.setView( etNumero );
            alerta.setNeutralButton("Cancelar", null);
            alerta.setPositiveButton("Ligar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String numero = etNumero.getText().toString();
                    if( !numero.isEmpty() ){
                        Uri uri = Uri.parse("tel: " + numero);
                        Intent intent= new Intent(Intent.ACTION_DIAL, uri );
                        startActivity( intent );
                    }
                }
            });
            alerta.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregarFormulario(){
        int id = getIntent().getIntExtra("idProduto" , 0);
        produto = ProdutoDAO.getProdutoById(this, id );
        if ( produto == null )
            Log.i("erroProd", "Prod é null" );

        etNome.setText( produto.nome );
        etPreco.setText( String.valueOf(produto.preco) );
    }
    private void salvar(){
        String nome = etNome.getText().toString();
        String sPreco = etPreco.getText().toString();
        if( sPreco.isEmpty() ){
            Toast.makeText( this, "Preço inválido!", Toast.LENGTH_LONG).show();
        }else if ( nome.isEmpty() ){
            Toast.makeText( this,"Campo Nome é obrigatório!", Toast.LENGTH_LONG).show();
        }else{
            double preco = Double.parseDouble( sPreco );

            if( acao.equals("inserir")) {
                produto = new Produto(nome, preco);
                long id = ProdutoDAO.inserir(this, produto);
                String mensagem = "Produto: " + id + " - " + nome + "\nPreço: "+ preco +
                        "\n\nCadastrado com sucesso!";
                Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
            }else{
                produto.nome = nome;
                produto.preco = preco;
                ProdutoDAO.editar(this, produto);
                finish();
            }

            etNome.setText("");
            etPreco.setText("");
        }
    }


}








