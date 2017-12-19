package leonardo.unibratec.com.br.appmaker.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import leonardo.unibratec.com.br.appmaker.basics.Dados;

/**
 * Created by leonardo on 19/12/17.
 */

public class DadosDAO extends SQLiteOpenHelper {
    public DadosDAO(Context context) {
        super(context, "Dados", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table dados (id integer primary key, nome TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table dados;";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert(Dados dados) {
        ContentValues cv = getContentValues(dados);
        SQLiteDatabase db = getWritableDatabase();
        if (dados.getId() == null) {
            db.insert("dados", null, cv);
        } else {
            update(dados);
        }
    }

    public Dados update(Dados dados) {
        ContentValues cv = getContentValues(dados);
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {dados.getId().toString()};

        int retorno = db.update("dados", cv, "id = ?", params);
        if (retorno > 0) {
            return dados;
        }
        return null;
    }

    public List<Dados> buscarDados() {
        String sql = "select * from dados;";

        List<Dados> listaDados = new ArrayList<Dados>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Dados dados = new Dados();
            dados.setId(cursor.getInt(cursor.getColumnIndex("id")));
            dados.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            listaDados.add(dados);

        }
        return listaDados;
    }

    private ContentValues getContentValues(Dados dados) {
        ContentValues cv = new ContentValues();
        cv.put("nome", dados.getNome());
        return cv;
    }
}


