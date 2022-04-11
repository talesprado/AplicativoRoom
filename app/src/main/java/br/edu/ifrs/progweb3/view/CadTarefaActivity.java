package br.edu.ifrs.progweb3.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import br.edu.ifrs.progweb3.R;
import br.edu.ifrs.progweb3.dao.AppDatabase;
import br.edu.ifrs.progweb3.entity.Tarefa;


public class CadTarefaActivity extends AppCompatActivity {
    AppCompatEditText nome;
    AppCompatEditText descricao;
    AppCompatEditText data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tarefa);
        Button botao = findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = findViewById(R.id.editTextNome);
                descricao =  findViewById((R.id.editTextDescricao));
                data =  findViewById(R.id.editTextData);
                Tarefa tarefa = new Tarefa();
                tarefa.setNome(nome.getText().toString());
                tarefa.setDescricao(descricao.getText().toString());
                tarefa.setData(data.getText().toString());
                AppDatabase.getInstance(getApplicationContext()).createTarefaDAO().insert(tarefa);
                Toast.makeText(getApplicationContext(), "Tarefa foi cadastrada!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void limparCampos(){
        nome = findViewById(R.id.editTextNome);
        descricao = findViewById((R.id.editTextDescricao));
        data = findViewById(R.id.editTextData);
        nome.setText("");
        descricao.setText("");
        data.setText("");
    }
}
