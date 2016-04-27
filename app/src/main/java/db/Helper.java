package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import entity.Casamento;
import entity.Convidado;
import entity.Usuario;

/**
 * Created by Deyvison on 08/04/2016.
 */
public class Helper extends OrmLiteSqliteOpenHelper {

    private static final String NOME = "ayty";
    private static final int VERSAO = 5;


    public Helper(Context context) {
        super(context, NOME, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, Casamento.class);
            TableUtils.createTable(connectionSource, Convidado.class);
            TableUtils.createTable(connectionSource, Usuario.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Casamento.class, true);
            TableUtils.dropTable(connectionSource, Convidado.class, true);
            TableUtils.dropTable(connectionSource, Usuario.class, true);

            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
