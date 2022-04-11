package br.edu.ifrs.progweb3.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifrs.progweb3.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listaOpcoes = this.findViewById(R.id.lista);
        String[] itens = {"Cadastrar", "Listar Todos"};
        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itens);
        listaOpcoes.setAdapter(arrayItens);
        listaOpcoes.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> listView, View v, int position, long id){
            if(position ==0) {//cadastrar
                Intent intencao = new Intent(this, CadTarefaActivity.class);
                startActivity(intencao);
            }
            else {
                Intent intencao = new Intent(this, ListTarefasActivity.class);
                startActivity(intencao);
            }
        }
}
