package enigma.mytetris;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Tablero tab= new Tablero();
    Figura fig= new Figura();
    Timer timer = new Timer();
    Timer tiempo = new Timer();
    public static int casa=4;
    boolean est= true;
    boolean rotar= false;
    Long cantTiempo =0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(this, R.raw.gameofthrones2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        RelativeLayout rel= findViewById(R.id.relativeLay);
        switch (casa){
            case 1: rel.setBackgroundResource(R.drawable.starkfondo);
                    break;
            case 2: rel.setBackgroundResource(R.drawable.lannisterfondo);
                    break;
            case 3: rel.setBackgroundResource(R.drawable.baratheonfondo);
                    break;
            case 4: rel.setBackgroundResource(R.drawable.targeryenfondo);
                    break;

        }

        Random rand = new Random();
        int n = rand.nextInt(7);
        fig= new Figura(casa,(n+1),5,-2, 0);
        Log.d("Llega", (n+1)+"");
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Log.d("width ",width+"");
        Log.d("height ",height+"");
        GridLayout gridLayout =  findViewById(R.id.gridView);

        int total = 200;
        int column = 10;
        int row = 20;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);

        for (int i = 0, c = 0, r = 0; i < total; i++, c++) {

            ImageView oImageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/column, height/row);
            oImageView.setLayoutParams(params);
            oImageView.setImageResource(R.drawable.lannisterficha);
            oImageView.setTag(""+i);
            oImageView.setAlpha(0f);
            gridLayout.addView(oImageView);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){

                            refresh();
                    }
                });

            }
        }, 0, 500);
        tiempo.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        cantTiempo++;
                    }
                });

            }
        }, 0, 1000);

    }

    public void refresh(){
        if(!tab.termina){
        if(!tab.choque(fig)){
        if(rotar && !(tab.choqueRotado(fig))){
            fig.rotado++;
            fig.rotar();
            rotar= false;
        }
        fig.mover(8, tab.tabla);}
        else{
            Random rand = new Random();
            int n = rand.nextInt(7);
           fig= new Figura(casa,(n+1),5,-2, 0);
        }
        tab.actualizarTablero(fig);
        tableroAInterfaz();
        tab.limpiaMatriz(tab.revisaMatriz());
        }
        else{
            tab.termina=false;
            timer.cancel();
            tiempo.cancel();
            mediaPlayer.stop();
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Builder of Thrones Terminado\n"+"Duración: "+cantTiempo+" segundos", Toast.LENGTH_SHORT);

            toast1.show();
            this.finish();
        }
    }

    private void tableroAInterfaz() {
        GridLayout grid= findViewById(R.id.gridView);
        for(int i=0; i<20;i++){
            for (int j=0; j<10; j++){

                ImageView img= (ImageView) grid.getChildAt((i*10+j));
                img.setImageResource(tab.tabla[i][j].getImagen());
                if(tab.tabla[i][j].casa!=0)
                    img.setAlpha(1f);
                else
                    img.setAlpha(0f);
            }}
    }

    public void mueveIzq(View view){
        fig.mover(4, tab.tabla);
        tab.actualizarTablero(fig);

    }

    public void mueveDer(View view){
        fig.mover(6, tab.tabla);
        tab.actualizarTablero(fig);
    }

    public void rota(View view){
        rotar=true;
    }

    public void aumentarVelocidad(View view){
        refresh();
        refresh();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            mediaPlayer.stop();
            timer.cancel();
            tiempo.cancel();
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }



}
