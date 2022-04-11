package br.edu.ifrs.progweb3.view;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import br.edu.ifrs.progweb3.R;
import br.edu.ifrs.progweb3.adapter.LinhaConsultaAdapter;
import br.edu.ifrs.progweb3.dao.AppDatabase;
import br.edu.ifrs.progweb3.dao.TarefaDAO;
import br.edu.ifrs.progweb3.entity.Tarefa;

public class ListTarefasActivity extends AppCompatActivity {
    private ListView listTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tarefas);
        listTarefas = this.findViewById(R.id.listViewTarefas);
        TarefaDAO tarefaDAO = AppDatabase.getInstance(getApplicationContext()).createTarefaDAO();
        getAll(tarefaDAO.getAllTarefas());
    }
     protected  void getAll(List<Tarefa> tarefas){
         listTarefas.setAdapter(new LinhaConsultaAdapter(this, tarefas));
     }
}
