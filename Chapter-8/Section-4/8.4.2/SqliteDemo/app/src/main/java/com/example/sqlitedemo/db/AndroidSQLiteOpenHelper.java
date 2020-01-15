package com.example.sqlitedemo.db;

import com.example.sqlitedemo.Books;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ����ͨ��SQLiteOpenHelper�������������������SQLiteDatabase�Ķ���
 * getReadableDatabase() �������ߴ�һ����ѯ���ݿ�
 * getWritableDatabase() �������ߴ�һ����д���ݿ� 
 */
public class AndroidSQLiteOpenHelper extends SQLiteOpenHelper {

	// ���ݿ�����
	public static final String DBNAME = "Books.db";
	// ���ݿ�汾
	public static final int VERSION = 2;
	// ������䣬��Сд������
	private static final String CREATETABLE = "create table if not exists "
			+ Books.TABLENAME
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name string, author string, reserve INTEGER)";

	/**
	 * ���캯��������ʵ��
	 * @param context ������·��
	 * @param name ���ݿ�����
	 * @param factory ��ѡ�α깤����ͨ��ΪNULL
	 * @param version ��ǰ���ݿ�汾��
	 */
	public AndroidSQLiteOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	//���ݿ��һ�δ���ʱ����ã�һ�������д������ݿ��
	@Override
	public void onCreate(SQLiteDatabase db) {
		//ʹ��execSQL()����ִ��DDL��䣬���û���쳣���������û�з���ֵ
		db.execSQL(CREATETABLE);
	}

	//�����ݿ���Ҫ�޸ĵ�ʱ��Androidϵͳ�������ĵ����������
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		this.deleteDB(db);
		this.onCreate(db);
	}

	// ɾ����
	private void deleteDB(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + Books.TABLENAME);
	}
	
	//�����ݿ�ʱ�Ļص�����
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	@Override
	public synchronized void close() {
		super.close();
	}

}
