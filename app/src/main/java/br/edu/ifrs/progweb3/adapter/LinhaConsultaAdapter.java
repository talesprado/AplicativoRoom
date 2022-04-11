package br.edu.ifrs.progweb3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.progweb3.R;
import br.edu.ifrs.progweb3.dao.AppDatabase;
import br.edu.ifrs.progweb3.dao.TarefaDAO;
import br.edu.ifrs.progweb3.entity.Tarefa;
import br.edu.ifrs.progweb3.view.EditTarefaActivity;
import br.edu.ifrs.progweb3.view.ListTarefasActivity;

public class LinhaConsultaAdapter extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;
    List<Tarefa> tarefas =  new ArrayList<>();

    //Cria objeto do tipo que lista as tarefas
    private ListTarefasActivity listarAtividades;

    //Construtor que recebe a ativida como parametro e a lista de tarefas que vai retornar do BD
    public LinhaConsultaAdapter(ListTarefasActivity listarAtividades, List<Tarefa> tarefas ) {
        this.tarefas       =  tarefas;
        this.listarAtividades  =  listarAtividades;
        this.layoutInflater     = (LayoutInflater) this.listarAtividades.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //Retorna a quantidade de objetos que esta na lista
    @Override
    public int getCount(){
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Método converte os valores de um item  da lista de Tarefas para uma linha do ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Cria um objeto para acessar o layout activity_linha.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha,null);

        //vincula os campos do arquivo de layout aos objetos cadastrados
        AppCompatTextView textViewCodigo = viewLinhaLista.findViewById(R.id.textViewCodigo);
        AppCompatTextView textViewNome  =  viewLinhaLista.findViewById(R.id.textViewNome);
        AppCompatTextView textViewDescricao = viewLinhaLista.findViewById(R.id.textViewDescricao);
        AppCompatTextView textViewData = viewLinhaLista.findViewById(R.id.textViewData);
        AppCompatButton buttonExcluir = viewLinhaLista.findViewById(R.id.buttonExcluir);
        AppCompatButton buttonEditar = viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(tarefas.get(position).getId()));
        textViewNome.setText(tarefas.get(position).getNome());
        textViewDescricao.setText(tarefas.get(position).getDescricao());
        textViewData.setText(tarefas.get(position).getData());

        //Criando evento para excluir um registro do BD
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefaDAO tarefaDAO = AppDatabase.getInstance(listarAtividades.getApplicationContext()).createTarefaDAO();
                List<Tarefa> tarefas = tarefaDAO.getAllTarefas();
                tarefaDAO.delete(tarefas.get(position));
                atualizaLista(position);
             }
        });

        //Criando evento para editar um registro do BD
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarAtividades, EditTarefaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("tarefa",tarefas.get(position));
                listarAtividades.startActivity(intent);
            }
        });
        return viewLinhaLista;
    }

    //atualizando a lista após excluir registro
    public void atualizaLista(int position){
        String mensagem = "Registro excluído com sucesso!";
        Toast.makeText(listarAtividades, mensagem, Toast.LENGTH_LONG).show();
        this.tarefas.remove(position);
        this.notifyDataSetChanged();
    }
}