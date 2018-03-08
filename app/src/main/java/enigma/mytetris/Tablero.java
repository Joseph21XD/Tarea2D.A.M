package enigma.mytetris;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ramir on 3/2/2018.
 */

public class Tablero {
    Celda [][]tabla= new Celda[20][10];
    Figura old= new Figura();
    static boolean termina= false;
    static boolean esIzq= false;
    boolean colision= false;


    public Tablero() {
        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                tabla[i][j]= new Celda(0,i,j,0);
            }
        }
    }

    public void actualizarTablero(Figura fig){
        if(!colision) {
            for (Celda c : old.figura) {
                if (c.posY >= 0) {
                    Log.d("ERROR:",c.posX+"");
                    tabla[c.posY][c.posX].rotar = 0;
                    tabla[c.posY][c.posX].casa = 0;
                }
            }
            old = new Figura(fig.casa, fig.tipo, fig.posX, fig.posY, fig.rotado);
        }
        else{
            colision=false;
            old= new Figura();
        }
        for (Celda c: fig.figura){
            if(c.posX<0){
                old = new Figura(fig.casa, fig.tipo, fig.posX+1, fig.posY, fig.rotado);
                for (Celda d: fig.figura) {
                    d.setPosX(d.getPosX()+1);
                }
            }
            else if(c.posX>=10){
                old = new Figura(fig.casa, fig.tipo, fig.posX-1, fig.posY, fig.rotado);
                    for (Celda d: fig.figura) {
                        d.setPosX(d.getPosX()-1);
                    }

            }
        }
        for (Celda c: fig.figura) {
            if(c.posY>=0){
                Log.d("ESTE", ""+c.posX);
                tabla[c.posY][c.posX].rotar=c.rotar;
                tabla[c.posY][c.posX].casa=c.casa;}
        }
    }
    public boolean choque(Figura fig){
        boolean estado= false;
        for (Celda c: fig.figura) {
           if(c.posY+1<20){
               if(c.posY+1>=0){
                   if(tabla[c.posY+1][c.posX].casa==-1){
                       estado=true;
                       for (Celda d: fig.figura) {
                       if(d.posY>=0){
                            tabla[d.posY][d.posX].rotar=d.rotar;
                            tabla[d.posY][d.posX].casa=-1;
                           colision= true;
                       }
                       else{
                           termina= true;
                       }

                       }
                   }}
           }
           else{
               estado=true;
               for (Celda d: fig.figura) {
                       tabla[d.posY][d.posX].rotar=d.rotar;
                       tabla[d.posY][d.posX].casa=-1;
                        colision= true;}
           }
        }

        return estado;
    }
    public void quitar(Figura fig){
        for (Celda c : old.figura) {
            if (c.posY >= 0) {
                tabla[c.posY][c.posX].rotar = 0;
                tabla[c.posY][c.posX].casa = 1;
            }
        }
    }
    public void actualizarTableroRote(Figura fig){
        for (Celda c: fig.figura) {
            if(c.posY>=0){
                tabla[c.posY][c.posX].rotar=c.rotar;
                tabla[c.posY][c.posX].casa=c.casa;}
        }
    }


    public boolean choqueRotado(Figura fig) {
        Boolean est= false;
        int i=0,j=0;
        for (Celda c: fig.figura) {
            switch (c.rotar){
                case 1:  i=c.getPosX()+1;
                         j=c.getPosY()-1;
                            break;
                case 2:  i=c.getPosX()+1;
                    j=c.getPosY()-0;
                    break;
                case 3:  i=c.getPosX()+1;
                    j=c.getPosY()+1;
                    break;
                case 4:  i=c.getPosX()+0;
                    j=c.getPosY()-1;
                    break;
                case 5:  i=c.getPosX()+0;
                    j=c.getPosY()-0;
                    break;
                case 6:  i=c.getPosX()+0;
                    j=c.getPosY()+1;
                    break;
                case 7:  i=c.getPosX()-1;
                    j=c.getPosY()-1;
                    break;
                case 8:  i=c.getPosX()-1;
                    j=c.getPosY()-0;
                    break;
                case 9:  i=c.getPosX()-1;
                    j=c.getPosY()+1;
                    break;
                case 10:  i=c.getPosX()+2;
                    j=c.getPosY()-0;
                    break;
                case 11:  i=c.getPosX()+0;
                    j=c.getPosY()-2;
                    break;
            }
            if(i>=0 && i<10 && j>=0 && j<20){
                if(tabla[j][i].casa==-1)
                    est=true;}
            else{

                if(i<0){
                   esIzq=true;
                    est=true;}
                else if(i>=10){
                    esIzq=false;
                    est=true;}

            }
        }
        return est;
    }

    public ArrayList<Integer> revisaMatriz(){
        ArrayList<Integer> est= new ArrayList<Integer>();
        for(int i=19; i>=0; i--){
            int x=0;
            for(int j=0; j<10; j++){
                if(tabla[i][j].casa!=-1)
                    x++;
            }
            if(x==0){
                est.add(i);
            }
        }
        return est;
    }

    public void limpiaMatriz(ArrayList<Integer> est){
        int cant=0;
        for(int i=19; i>=0; i--){
            int k=0;
            for(int j=0; j<10; j++){
                    if(est.size()==0 || i>est.get(0)){
                        int x= tabla[i][j].casa;
                        tabla[i][j].casa=0;
                        tabla[i+cant][j].casa=x;
                    }
                    if(est.size()!=0 && i==est.get(0)){
                        k++;
                        tabla[i][j].casa=0;
                    }
            }
            if(k!=0){
                cant++;
                est.remove(0);
            }
        }
    }
}
