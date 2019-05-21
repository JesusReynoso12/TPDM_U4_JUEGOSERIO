package reynosojesus.ittepic.edu.tpdm_u4_juegoserio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {
    Comida manzana,pera,kiwi,naranja,platano,puntero;
    int gx=0,gy=0;

    public Lienzo(Context context) {
        super(context);
        manzana = new Comida(this,R.drawable.manzana,10,50,1);
        pera = new Comida(this,R.drawable.pera,100,50,1);
        naranja = new Comida(this,R.drawable.naranja,200,50,1);
        platano = new Comida(this,R.drawable.platano,300,50,1);
        kiwi = new Comida(this,R.drawable.kiwi,400,50,1);
        puntero=null;
    }

    @Override
    protected void onDraw(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawText("Comida Saludable",0,0,p);
        manzana.dibujar(c,p);
        pera.dibujar(c,p);
        platano.dibujar(c,p);
        naranja.dibujar(c,p);
        kiwi.dibujar(c,p);

    }


    public Comida agarrando(int x,int y){
        if(manzana.estaEnArea(x,y)){
            gx=manzana.x;
            gy=manzana.y;
            return manzana;
        }else if(platano.estaEnArea(x,y)){
            gx=platano.x;
            gy=platano.y;
            return platano;
        }else if(pera.estaEnArea(x,y)){
            gx=pera.x;
            gy=pera.y;
            return pera;
        }else if(naranja.estaEnArea(x,y)){
            gx=naranja.x;
            gy=naranja.y;
            return naranja;
        }else if(kiwi.estaEnArea(x,y)){
            gx=kiwi.x;
            gy=kiwi.y;
            return kiwi;
        }
        return null;
    }

    public boolean onTouchEvent (MotionEvent me){
        int accion = me.getAction();
        int posx = (int) me.getX();
        int posy = (int) me.getY();

        switch (accion){
            case MotionEvent.ACTION_DOWN:
                puntero=agarrando(posx,posy);
                break;
            case MotionEvent.ACTION_MOVE:
                if(puntero != null){
                    puntero.mover(posx,posy);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(puntero!=null){
                    puntero.x=gx;
                    puntero.y=gy;
                }
                puntero=null;
                break;
        }
        invalidate();
        return true;
    }

}
