package br.edu.ifrs.progweb3.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.edu.ifrs.progweb3.entity.Tarefa;

@Database(entities = {Tarefa.class}, version = 1,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract TarefaDAO createTarefaDAO();

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"driver_database").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}