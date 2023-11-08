package br.pro.adalto.appbuscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvCEP;
    private EditText etCEP;
    private Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCEP = findViewById(R.id.tvCEP);
        etCEP = findViewById(R.id.etCEP);
        btnConsultar = findViewById(R.id.btnConsultar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });


    }

    private void consultar(){
        String cep = etCEP.getText().toString();
        if( cep.isEmpty() ){
            Toast.makeText(this, "Preencha o CEP",
                    Toast.LENGTH_LONG).show();
        }else {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            RequestQueue queue = Volley.newRequestQueue(this);
            tvCEP.setText("Carregando...");

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String texto = response.getString("logradouro") +
                                                "\n" + response.getString("complemento")+
                                                "\n" + response.getString("bairro");
                                        tvCEP.setText( texto );

                                    }catch (JSONException e){

                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );


            queue.add( jsonRequest );
        }
    }

}