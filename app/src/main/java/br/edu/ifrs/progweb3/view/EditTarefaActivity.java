package br.edu.ifrs.progweb3.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import java.util.Calendar;
import br.edu.ifrs.progweb3.R;
import br.edu.ifrs.progweb3.dao.AppDatabase;
import br.edu.ifrs.progweb3.dao.TarefaDAO;
import br.edu.ifrs.progweb3.entity.Tarefa;


public class EditTarefaActivity extends AppCompatActivity {
    AppCompatEditText editTextNome ;
    AppCompatEditText editTextDesc;
    AppCompatEditText editTextData;
    AppCompatButton botaoEditar ;
    DatePickerDialog datePickerDialogData;
    Tarefa tarefa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tarefa);
        editTextNome = this.findViewById(R.id.editTextNomeEdit);
        editTextDesc =  this.findViewById(R.id.editTextDescricaoEdit);
        editTextData =  this.findViewById(R.id.editTextDataEdit);
        botaoEditar =  this.findViewById(R.id.buttonEditar);

        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual   = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual   = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual   = calendarDataAtual.get(Calendar.DAY_OF_MONTH);

        datePickerDialogData = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
                //formata o mês com dois digitos
                String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ? "0" + (mesSelecionado + 1 ): String.valueOf(mesSelecionado));
                editTextData.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);
            }}, anoAtual, mesAtual, diaAtual);


             editTextData.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                datePickerDialogData.show();
            }
        });

        //cria evento para o botão editar
        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterarTarefa();
            }
        });
        //Pega o objeto que foi passado como parâmetro
        Bundle extra =  this.getIntent().getExtras();
        tarefa = (Tarefa) getIntent().getSerializableExtra("tarefa");
        editTextNome.setText(tarefa.getNome());
        editTextDesc.setText(tarefa.getDescricao());
        editTextData.setText(tarefa.getData());
    }
    private void alterarTarefa() {
        if (editTextNome.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Nome é obrigatório!", Toast.LENGTH_LONG).show();
            editTextNome.requestFocus();
        } else if (editTextDesc.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Descrição é obrigatório!", Toast.LENGTH_LONG).show();
            editTextDesc.requestFocus();
        } else if (editTextData.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Data é obrigatório!", Toast.LENGTH_LONG).show();
            editTextData.requestFocus();
        } else {
            //modifica os dados do objeto que foi passado como parâmetro
            tarefa.setNome(editTextNome.getText().toString().trim());
            tarefa.setDescricao(editTextDesc.getText().toString().trim());
            tarefa.setData(editTextData.getText().toString().trim());
            TarefaDAO tarefaDAO = AppDatabase.getInstance(getApplicationContext()).createTarefaDAO();
            tarefaDAO.update(tarefa);
            mostraMensagem();
        }
    }
    public void mostraMensagem(){

            String msg = "Registro alterado com sucesso! ";
            //mostrando caixa de diálogo de sucesso
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.app_name);
            alertDialog.setMessage(msg);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // Forçando que o código retorne para a tela de consulta
                    Intent intent = new Intent(getApplicationContext(), ListTarefasActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.show();
    }
}
