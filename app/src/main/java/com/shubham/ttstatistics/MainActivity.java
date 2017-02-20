package com.shubham.ttstatistics;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText pl1,pl2;
    TextView pls1,pls2,score;
    Button pladd1,pladd2,newGame;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pl1 = (EditText) findViewById(R.id.editText1);
        pl2 = (EditText) findViewById(R.id.editText2);
        pls1= (TextView) findViewById(R.id.textView1);
        pls2= (TextView) findViewById(R.id.textView2);
        score= (TextView) findViewById(R.id.textView3);
        pladd1 = (Button)findViewById(R.id.button1);
        pladd2 = (Button)findViewById(R.id.button2);
        newGame = (Button)findViewById(R.id.button3);
        aSwitch = (Switch)findViewById(R.id.switch1);

        pl1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        pl2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.reset) {
            resetAll();
        }
        return super.onOptionsItemSelected(item);
    }

    public void resetAll(){
         pls1.setText("0");
         pls2.setText("0");
         pl1.setText("");
         pl2.setText("");
        aSwitch.setChecked(false);
        score.setText("Score : 0 / 0");
        pl1.setFocusable(true);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void increment(View view){
        int num1=Integer.parseInt(pls1.getText().toString());
        int num2=Integer.parseInt(pls2.getText().toString());

        if(view.getId()==R.id.button1){
            num1=num1+1;
            pls1.setText((num1)+"");
        }
        else if(view.getId()==R.id.button2){
            num2=num2+1;
            pls2.setText((num2)+"");
        }

        if((num1+num2)%2==0 && !(num1==0&&num2==0)){
            if (!aSwitch.isChecked()){
                aSwitch.setChecked(true);
            }
            else {
                aSwitch.setChecked(false);
            }
            Toast.makeText(this,"Service change",Toast.LENGTH_SHORT).show();

        }
    }

    public void doWork(View view){
        int player1=Integer.parseInt(pls1.getText().toString());
        int player2=Integer.parseInt(pls2.getText().toString());
        int num1 = Integer.parseInt(String.valueOf(score.getText().toString().charAt(8)));
        int num2 = Integer.parseInt(String.valueOf(score.getText().toString().charAt(12)));

        if(player1<player2){
            num2=num2+1;
            score.setText("Score : "+num1+" / "+num2);
            pls1.setText("0");
            pls2.setText("0");
            Toast.makeText(this,pl2.getText().toString()+" win",Toast.LENGTH_LONG).show();
        }
        else if(player2<player1){
            num1=num1+1;
            score.setText("Score : "+num1+" / "+num2);
            pls1.setText("0");
            pls2.setText("0");
            Toast.makeText(this,pl1.getText().toString()+" win",Toast.LENGTH_LONG).show();
        }
        else if(player1==player2){
            num1 =num1+1;
            num2 =num2+1;
            score.setText("Score : "+num1+" / "+num2);
            pls1.setText("0");
            pls2.setText("0");
            Toast.makeText(this,"Match tie",Toast.LENGTH_LONG).show();
        }
    }
}
