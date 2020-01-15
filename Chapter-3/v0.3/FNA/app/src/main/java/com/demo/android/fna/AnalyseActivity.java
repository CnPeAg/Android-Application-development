package com.demo.android.fna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AnalyseActivity extends Activity {

	private TextView  m_tvTitle;
	private TextView  m_analyseCol;
	private Button    m_Btnback;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyse_main);
       
        m_Btnback  = (Button) findViewById(R.id.btnBack);	
        m_Btnback.setOnClickListener(returnDataCalc);
        findViews();  
        getIntentData();
    }
	private OnClickListener returnDataCalc = new OnClickListener()
    {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("RETURN_DATA", "Return Data To MainActivity");
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			finish();
		} 	
    };
    @Override
    public void onBackPressed() {
    	Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("RETURN_DATA", "Return Data To MainActivity");
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
    }

	private void findViews(){
		m_tvTitle     = (TextView) findViewById(R.id.tvTitle);
		m_analyseCol  = (TextView) findViewById(R.id.analyseCol);		
	}
	private void  getIntentData(){
		 Bundle bunde = this.getIntent().getExtras();
         long itemId = bunde.getLong("FNA_FRUITS"); 
         disposeAnalysis((int)itemId); 
	}
	protected void disposeAnalysis(int position){
		String content = null;
		String title = null;
		switch(position){
			case 0:
				title = "苹果成分 （g/100g）";
				content = "水分（%）" + "84.6;"
						+"蛋白质 " +"0.3;"
						+"脂肪" + "0.6;"
						+"总糖" + "1.4;"
						+"粗纤维" + "0.12;"
						+"热量（千卡）"+"58;";

				break;
			case 1:
				title = "香蕉成分 （g/100g）";
				content = "水分（%）" + "76;"
						+"蛋白质 " +"1.2;"
						+"脂肪" + "0.5;"
						+"总糖" + "1.8;"
						+"粗纤维" + "0.9;"
						+"热量（千卡）"+"67;";
				break;
			case 2:
				title = "菠萝成分 （g/100g）";
				content = "水分（%）" + "89;"
						+"蛋白质 " +"0.5;"
						+"脂肪" + "0.4;"
						+"总糖" + "15;"
						+"粗纤维" + "0.4;"
						+"热量（千卡）"+"60;";
				break;
			case 3:
				title = "西瓜成分 （g/100g）";
				content = "水分（%）" + "92;"
						+"蛋白质 " +"0.6;"
						+"脂肪" + "0.4;"
						+"总糖" + "17;"
						+"粗纤维" + "0.9;"
						+"热量（千卡）"+"54;";
				break;
		}
		showAnalyse(content, title);
	}
	private void showAnalyse(String content, String title){
		m_tvTitle.setText(title);
		m_analyseCol.setText(content);
	}
}
