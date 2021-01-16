package com.mavc.parkingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    //CREACION DE BASE DE DATOS
    public Database(Context context) { super(context,"parking.bd", null,1); }


    @Override public void onCreate(SQLiteDatabase database)
    {
        //creacion de tablas
        database.execSQL("create table zona(id_zona integer primary key autoincrement,nom_zona text);");
        database.execSQL("create table tipo_bahias(id_tipbahia integer primary key autoincrement,nom_tipbahia text);");
        database.execSQL("create table marca(id_marca integer primary key autoincrement,nom_marca text)");
        database.execSQL("create table tipo_vehiculo(id_tipvehiculo integer primary key autoincrement,nom_tipvehiculo text,tarifa_hora text,tarifa_dia text);");

        database.execSQL("create table bahias(id_bahia integer primary key autoincrement,nom_bahia text,id_tipbahia integer,id_zona integer,estado_bahia text);");

        database.execSQL("create table cliente(id_cliente integer primary key autoincrement,cedula text,nombre text,celular text,direccion text,email text);");


        database.execSQL("create table vehiculo(chapa text primary key ,color text,id_tipvehiculo integer,id_marca integer,id_cliente integer)");

        database.execSQL("create table entrada_salida(id_entradasalida integer primary key autoincrement,chapa text, fecha_entrada text,hora_entrada text, fecha_salida text,hora_salida text,id_bahia integer,monto integer,tiempo_totaL text,observaciones text,estado integer,id_usuario integer)");

        database.execSQL("create table usuario(id_usuario integer primary key autoincrement,nick text,nombre text,contrasena text,estado integer)");
        database.execSQL("insert into usuario values('admin','Miguel Villalba','123', 1)");
    }


    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
