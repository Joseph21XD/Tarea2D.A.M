package enigma.mytetris;

import android.util.Log;

/**
 * Created by ramir on 3/2/2018.
 */

public class Celda {
    static int imagen= R.drawable.lannisterficha;
    int casa= 0;
    int posX= 0;
    int posY= 0;
    int rotar= 0;

    public Celda(  int vcasa, int vposx, int vposy, int vrotar){
        casa= vcasa;
        posX= vposx;
        posY= vposy;
        rotar= vrotar;
        switch (vcasa){
            case 1: imagen= R.drawable.starkficha;
                    break;
            case 2: imagen= R.drawable.lannisterficha;
                break;
            case 3: imagen= R.drawable.baratheonficha;
                break;
            case 4: imagen= R.drawable.targeryenficha;
                break;
        }

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getCasa() {
        return casa;
    }

    public void setCasa(int casa) {
        this.casa = casa;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getRotar() {
        return rotar;
    }

    public void setRotar(int vrotar, int vposX, int vposY) {
        switch (vrotar){
            case 1: posX=vposX-1; posY=vposY-1;
                break;
            case 2: posX=vposX-0; posY=vposY-1;
                break;
            case 3: posX=vposX+1; posY=vposY-1;
                break;
            case 4: posX=vposX-1; posY=vposY-0;
                break;
            case 5: posX=vposX-0; posY=vposY-0;
                break;
            case 6: posX=vposX+1; posY=vposY-0;
                break;
            case 7: posX=vposX-1; posY=vposY+1;
                break;
            case 8: posX=vposX-0; posY=vposY+1;
                break;
            case 9: posX=vposX+1; posY=vposY+1;
                break;
            case 10: posX=vposX-0; posY=vposY-2;
                break;
            case 11: posX=vposX+2; posY=vposY-0;
                break;
        }

        this.rotar = vrotar;
    }
}
