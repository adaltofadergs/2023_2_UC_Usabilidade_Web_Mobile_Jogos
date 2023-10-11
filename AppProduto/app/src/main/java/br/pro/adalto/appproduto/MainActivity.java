package br.pro.adalto.appproduto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }
        etNome = (EditText) findViewById(R.id.etNome);
        etPreco = findViewById(R.id.etPreco);
        botaoSalvar = findViewById(R.id.btnSalvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }
    private void carregarFormulario(){
        long id = getIntent().getLongExtra("idProduto" , 0);
        produto = ProdutoDAO.getProdutoById(this, id );
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








