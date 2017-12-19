package leonardo.unibratec.com.br.appmaker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import leonardo.unibratec.com.br.appmaker.R;
import leonardo.unibratec.com.br.appmaker.basics.Dados;
import leonardo.unibratec.com.br.appmaker.dao.DadosDAO;

public class MainActivity extends AppCompatActivity {

    private ListView listaDados;
    private ArrayList<Dados> dadosArray;
    private ArrayAdapter<Dados> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fba = (FloatingActionButton) findViewById(R.id.addFba);

        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DadosActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        carregarDados();
        super.onResume();
    }

    private void carregarDados() {
        listaDados = (ListView) findViewById(R.id.listaApp);
        DadosDAO dao = new DadosDAO(this);
        dadosArray = (ArrayList<Dados>) dao.buscarDados();

        adapter = new ArrayAdapter<Dados>(this, android.R.layout.simple_list_item_1, dadosArray);
        listaDados.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
