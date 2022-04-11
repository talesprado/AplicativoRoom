package br.edu.ifrs.progweb3.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.ifrs.progweb3.entity.Tarefa;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface TarefaDAO {

    @Query("SELECT * FROM Tarefa")
    public List<Tarefa> getAllTarefas();

    @Query("SELECT * FROM Tarefa WHERE nome = :name")
    public List<Tarefa> getTarefaByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Tarefa tarefa);

    @Update
    public void update(Tarefa tarefa);

    @Delete
    public void delete(Tarefa tarefa);

}