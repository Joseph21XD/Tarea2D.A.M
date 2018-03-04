package enigma.mytetris;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ramir on 3/2/2018.
 */

public class Figura {
    ArrayList<Celda> figura = new ArrayList<Celda>();
    int tipo= 0;
    int posX= 0;
    int posY= 0;
    int casa= 0;
    int rotado= 0;

    public Figura(){

    }
    public Figura(int vcasa, int vtipo, int vposx, int vposy , int vrotado){
        posX= vposx;
        posY= vposy;
        tipo= vtipo;
        casa= vcasa;
        switch (tipo){
            case 1: ele(casa, vposx, vposy);
                    break;
            case 2: eleInvertido(casa, vposx, vposy);
                    break;
            case 3: rayo(casa, vposx, vposy);
                break;
            case 4: rayoInvertido(casa, vposx, vposy);
                break;
            case 5: podio(casa, vposx, vposy);
                break;
            case 6: cuadro(casa, vposx, vposy);
                break;
            case 7: linea(casa, vposx, vposy);
                break;
            default: break;
        }
        int i=0;
        rotado= vrotado;
        while(i!=vrotado){
            rotar();
            i++;
        }
    }

    private void cuadro(int casa, int vposx, int vposy) {
        figura.add(new Celda(casa,(vposx-1),(vposy-1), 1));
        figura.add(new Celda(casa,(vposx-1),(vposy-0), 4));
        figura.add(new Celda(casa,(vposx-0),(vposy-1), 2));
        figura.add(new Celda(casa,(vposx),(vposy), 5));

    }

    private void linea(int casa, int vposx, int vposy) {
        figura.add(new Celda(casa,(vposx-0),(vposy-2), 10));
        figura.add(new Celda(casa,(vposx-0),(vposy+1), 8));
        figura.add(new Celda(casa,(vposx-0),(vposy-1), 2));
        figura.add(new Celda(casa,(vposx),(vposy), 5));
    }

    private void ele(int casa, int vposx, int vposy){
        figura.add(new Celda(casa,(vposx+1),(vposy+1), 9));
        figura.add(new Celda(casa,(vposx-0),(vposy+1), 8));
        figura.add(new Celda(casa,(vposx-0),(vposy-1), 2));
        figura.add(new Celda(casa,(vposx),(vposy), 5));

    }

    private void eleInvertido(int casa, int vposx, int vposy){
        figura.add(new Celda(casa,(vposx-1),(vposy+1), 7));
        figura.add(new Celda(casa,(vposx-0),(vposy+1), 8));
        figura.add(new Celda(casa,(vposx-0),(vposy-1), 2));
        figura.add(new Celda(casa,(vposx),(vposy), 5));

    }

    private void rayo(int casa, int vposx, int vposy){
        figura.add(new Celda(casa,(vposx-1),(vposy-0), 4));
        figura.add(new Celda(casa,(vposx-1),(vposy+1), 7));
        figura.add(new Celda(casa,(vposx-0),(vposy-1), 2));
        figura.add(new Celda(casa,(vposx),(vposy), 5));

    }

    private void rayoInvertido(int casa, int vposx, int vposy){
        figura.add(new Celda(casa,(vposx+1),(vposy+1), 9));
        figura.add(new Celda(casa,(vposx+1),(vposy-0), 6));
        figura.add(new Celda(casa,(vposx-0),(vposy-1), 2));
        figura.add(new Celda(casa,(vposx),(vposy), 5));

    }

    private void podio(int casa, int vposx, int vposy){
        figura.add(new Celda(casa,(vposx+1),(vposy+1), 9));
        figura.add(new Celda(casa,(vposx-1),(vposy+1), 7));
        figura.add(new Celda(casa,(vposx-0),(vposy+1), 8));
        figura.add(new Celda(casa,(vposx),(vposy), 5));

    }

    public void rotar(){
        if(tipo!=6){
        try{
        for (Celda c: figura) {
            switch (c.rotar){
                case 1: c.setRotar(3, posX, posY);
                    break;
                case 2: c.setRotar(6, posX, posY);
                    break;
                case 3: c.setRotar(9, posX, posY);
                    break;
                case 4: c.setRotar(2, posX, posY);
                    break;
                case 5: c.setRotar(5, posX, posY);
                    break;
                case 6: c.setRotar(8, posX, posY);
                    break;
                case 7: c.setRotar(1, posX, posY);
                    break;
                case 8: c.setRotar(4, posX, posY);
                    break;
                case 9: c.setRotar(7, posX, posY);
                    break;
                case 10: c.setRotar(11, posX, posY);
                    break;
                case 11: c.setRotar(10, posX, posY);
                    break;
            }

        }
            for (Celda c: figura){
                if(c.getPosX()<0 || c.getPosX()>=10){
                    throw new NullPointerException();
                }
            }

        }
        catch (NullPointerException e){

        }}
    }
    public void mover(int mov, Celda [][]tab){
            if (mov == 4) {
                boolean est= false;
                for(Celda c: figura){
                    if(c.getPosY()>=0){
                    if((c.getPosX()-1)<0 || tab[c.getPosY()][(c.getPosX()-1)].casa==-1)
                        est=true;}
                }
                if(!est)
                    posX--;

            } else if (mov == 6) {
                boolean est= false;
                for(Celda c: figura){
                    if(c.getPosY()>=0){
                    if((c.getPosX()+1)>=10 || tab[c.getPosY()][c.getPosX()+1].casa==-1)
                        est=true;}
                }
                if(!est)
                posX++;

            } else {
                posY++;
            }
            for (Celda c : figura) {
                switch (c.rotar) {
                    case 1:
                        c.setRotar(1, posX, posY);
                        break;
                    case 2:
                        c.setRotar(2, posX, posY);
                        break;
                    case 3:
                        c.setRotar(3, posX, posY);
                        break;
                    case 4:
                        c.setRotar(4, posX, posY);
                        break;
                    case 5:
                        c.setRotar(5, posX, posY);
                        break;
                    case 6:
                        c.setRotar(6, posX, posY);
                        break;
                    case 7:
                        c.setRotar(7, posX, posY);
                        break;
                    case 8:
                        c.setRotar(8, posX, posY);
                        break;
                    case 9:
                        c.setRotar(9, posX, posY);
                        break;
                    case 10:
                        c.setRotar(10, posX, posY);
                        break;
                    case 11:
                        c.setRotar(11, posX, posY);
                        break;
                }

            }


    }


}
