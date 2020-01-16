package com.demo.android.fna;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Analyse extends Activity {

	private TextView  m_tvTitle;
	private TextView  m_analyseCol;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyse_main);
       
        findViews();  
        logicDispose();
    }
	private void findViews(){
		m_tvTitle     = (TextView) findViewById(R.id.tvTitle);
		m_analyseCol  = (TextView) findViewById(R.id.analyseCol);		
	}
	private void  logicDispose(){
		 Bundle bunde = this.getIntent().getExtras();
         long itemId = bunde.getLong("FNA_FRUITS"); 
         disposeAnalysis((int)itemId); 
	}
	protected void disposeAnalysis(int position){
		String content = null;
		String title = null;
		switch(position){
		case 0:
			title = "ƻ���ɷ� ��g/100g��";
			content = "ˮ�֣�%��" + "84.6;"
				    +"������ " +"0.3;"
					+"֬��" + "0.6;"
					+"����" + "1.4;"
					+"����ά" + "0.12;"
					+"������ǧ����"+"58;";
								
			break;
		case 1:
			title = "�㽶�ɷ� ��g/100g��";
			content = "ˮ�֣�%��" + "76;"
				    +"������ " +"1.2;"
					+"֬��" + "0.5;"
					+"����" + "1.8;"
					+"����ά" + "0.9;"
					+"������ǧ����"+"67;";
			break;
		case 2:
			title = "���ܳɷ� ��g/100g��";
			content = "ˮ�֣�%��" + "89;"
				    +"������ " +"0.5;"
					+"֬��" + "0.4;"
					+"����" + "15;"
					+"����ά" + "0.4;"
					+"������ǧ����"+"60;";
			break;
		case 3:
			title = "���ϳɷ� ��g/100g��";
			content = "ˮ�֣�%��" + "92;"
				    +"������ " +"0.6;"
					+"֬��" + "0.4;"
					+"����" + "17;"
					+"����ά" + "0.9;"
					+"������ǧ����"+"54;";
			break;
		}
		showAnalyse(content, title);
	}
	private void showAnalyse(String content, String title){
		m_tvTitle.setText(title);
		m_analyseCol.setText(content);
	}
}
