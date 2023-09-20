package br.pro.adalto.appproduto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNome, etPreco;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    private void salvar(){
        String nome = etNome.getText().toString();
        String sPreco = etPreco.getText().toString();
        if( sPreco.isEmpty() ){
            Toast.makeText( this, "Preço inválido!", Toast.LENGTH_LONG).show();
        }else if ( nome.isEmpty() ){
            Toast.makeText( this,"Campo Nome é obrigatório!", Toast.LENGTH_LONG).show();
        }else{
            double preco = Double.parseDouble( sPreco );
            String mensagem = "Produto: " + nome + "\nPreço: "+ preco +
                    "\n\nCadastrado com sucesso!";
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
        }
    }


}








