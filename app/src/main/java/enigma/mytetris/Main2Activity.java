package enigma.mytetris;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

public class Main2Activity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onResume(){
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.gameofthrones);
        mediaPlayer.start();
    }

    public void stark(View view){
        mediaPlayer.stop();
        MainActivity.casa=1;
        Intent intent= new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
    public void lannister(View view){
        mediaPlayer.stop();
        MainActivity.casa=2;
        Intent intent= new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
    public void baratheon(View view){
        mediaPlayer.stop();
        MainActivity.casa=3;
        Intent intent= new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
    public void targaryen(View view){
        mediaPlayer.stop();
        MainActivity.casa=4;
        Intent intent= new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }

    public void info(View view){
        mediaPlayer.stop();
        Intent intent= new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
    }
}
