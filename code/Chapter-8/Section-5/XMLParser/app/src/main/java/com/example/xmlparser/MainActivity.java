package com.example.xmlparser;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private static final String TAG = "XML";
	
	private XMLParser parser;
	private List<Product> products;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button readBtn = (Button) findViewById(R.id.readBtn);
        Button writeBtn = (Button) findViewById(R.id.writeBtn);
        
        readBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onFileItemClick("products.xml");
			}
		});
        
        
        writeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String xml = parser.serialize(products);  //���л�
					FileOutputStream fos = openFileOutput("products.xml", Context.MODE_PRIVATE);
					fos.write(xml.getBytes("UTF-8"));
				} catch (Exception e) {
					Log.e(TAG, e.getMessage());
				}
			}
		});
    }
    public void onFileItemClick(String filename){
    	try {
			InputStream is = getAssets().open(filename);
        	parser = new SaxXMLParser();  //����SaxBookParserʵ��
        	products = parser.parse(is);  //����������
        	String msg = "��" +"3"+ "����Ʒ\n";
			for (Product product : products) {
					msg += "id:"+product.getId() +"��Ʒ����"+product.getName()+"�۸�"+product.getPrice()+"\n";
			}
			
			Dialog alertDialog = new AlertDialog.Builder(this).setTitle("��Ʒ��Ϣ")
					 .setMessage(msg).setPositiveButton("�ر�", null).create();
			alertDialog.show();
			 
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
    }
    

}
