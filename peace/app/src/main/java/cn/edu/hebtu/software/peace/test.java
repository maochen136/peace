package cn.edu.hebtu.software.peace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        String s=intent.getStringExtra("data");
        TextView textView=findViewById(R.id.test_text);
        textView.setText(s);
    }

}
