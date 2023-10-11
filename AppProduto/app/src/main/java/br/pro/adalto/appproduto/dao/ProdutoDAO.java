package br.pro.adalto.appproduto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pro.adalto.appproduto.model.Produto;


public class ProdutoDAO {

    public static long inserir(Context context, Produto prod){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        //db.execSQL( "INSERT INTO produtos (nome, preco) VALUES " +
        //            " ( '" + prod.nome + "' ,  " + prod.preco + " ) " );
        ContentValues valores = new ContentValues();
        valores.put("nome", prod.nome);
        valores.put("preco", prod.preco);

        long id = db.insert("produtos" , null, valores);
        return id;
    }

    public static void editar(Context context, Produto prod){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", prod.nome);
        valores.put("preco", prod.preco);

        db.update("produtos" ,  valores, " id = " + prod.id, null);
    }

    public static void excluir(Context context, int idProd){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("produtos" , " id = " + idProd, null);
    }

    public static List<Produto> getProdutos(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        List<Produto> lista = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome", null);
            if ( cursor.getCount() > 0 ){
                cursor.moveToFirst();
                do {
                    Produto prod = new Produto();
                    prod.id = cursor.getInt( 0);
                    prod.nome = cursor.getString( 1 );
                    prod.preco = cursor.getDouble( 2 );
                    lista.add( prod );
                }while ( cursor.moveToNext() );
            }
        }catch (Exception e){

        }
        return lista;
    }

    public static Produto getProdutoById(Context context, long idProd){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM produtos WHERE id = " + idProd, null);
            if ( cursor.getCount() > 0 ){
                cursor.moveToFirst();

                Produto prod = new Produto();
                prod.id = cursor.getInt( 0);
                prod.nome = cursor.getString( 1 );
                prod.preco = cursor.getDouble( 2 );

                return prod;
            }
        }catch (Exception e){

        }
        return null;
    }


}
