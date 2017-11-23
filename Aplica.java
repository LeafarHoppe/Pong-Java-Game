package aplica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Aplica extends JFrame implements ActionListener{
    
    Font big = new Font("Courier Regular", Font.BOLD, 70);
    Font big2 = new Font("Arial", Font.BOLD, 20);
    
    
    double plscore = 0.0,    iscore = 0.0;
    
    int px = 40;    int ix = 430;
    int py = 190;   int iy = 340;
    
    int x = 210;    int pax = 5;
    int y = 185;    int pay = 470;
    
    int pa2x = 5;   int pa3x = 485;
    int pa2y = 5;   int pa3y = 25;
    
    int pa4x = 5;   int velx = 1;
    int pa4y = 25;  int vely = 1;
    
    int dir = 1,    vidas = 5;
    
    boolean pausarVel = false;
    
    public Aplica(){
        Janela();
        Controles();
        
    }
    
    public void Janela(){
        setSize(530,530);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Janela");
        
        gr = new Graf();
        add(gr);
        
        time = new Timer(2, this);
        time.start();
        
        
    }
    
    public void jogar() throws InterruptedException{
       
        x += velx;
        y += vely;
        
        if(y > pay - 5){
            vely *= -1;
        }
        
        if(y < pa2y + 20){
            vely *= -1;
        }
        
        //colidindo com player
        if(x < 60 && x > 40 && y > py && y < py + 110){
            velx *= -1;
            x = px + 26;
            plscore ++;
        }
        
        //colidindo com inimigo
        if(x > ix && x < ix+20 && y > iy && y < iy + 110){
            velx *= -1;
            x = ix -6;
            iscore ++;
        }
        
        if(x > pa3x){
        x = 210;
        y = 185;     velx *=-1;
        }
        
        if(x < pa4x){
        x = 210;
        y = 185;    velx *=-1;
        sleep(1000);
        vidas--;
        }
        
        gr.repaint();
        
        if(dir == 1 && py < pa2y + 20){
            py = pa2y + 20;
        }else if (dir == 2 && py > pay - 110){
            py = pay-110;
        }
        if(x > 260){
            if(y > iy + 25 && iy < 360){
                iy += 3;
            }
            if(y < iy && iy > 25){
                iy -=3;
            }
        }
        
        
    }
    
    public void Controles(){
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==38){
                    dir = 1;
                    py -= 20;
                }
                if(e.getKeyCode()==40){
                    dir = 2;
                    py += 20;
                }
                if(e.getKeyCode()==32){
                    pausarVel = true;
                    
                }
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
    }
    
    Graf gr;
    Timer time;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jogar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Aplica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class Graf extends JPanel{
            
        public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);
        Graphics2D o1 = (Graphics2D) g;
        Graphics2D pa = (Graphics2D) g;
        Graphics2D pa2 = (Graphics2D) g;
        Graphics2D pa3 = (Graphics2D) g;
        Graphics2D pa4 = (Graphics2D) g;
        Graphics2D ini = (Graphics2D) g;
        Graphics2D pdm = (Graphics2D) g;
        Graphics2D scr = (Graphics2D) g;
        Graphics2D scr2 = (Graphics2D) g;
        Graphics2D scr3 = (Graphics2D) g;
        Graphics2D player = (Graphics2D) g;
       
        if(vidas > 0){
        //player
        player.setColor(Color.white);
        player.fill(new Rectangle2D.Double(px,py,20,110));
        
        //bola
        o1.setColor(Color.white);
        o1.fill(new Rectangle2D.Double(x,y,15,15));
        
        //inimigo
        ini.setColor(Color.white);
        ini.fill(new Rectangle2D.Double(ix, iy, 20, 110));
        
        //pilastra do meio
        pdm.setColor(Color.white);
        pdm.fill(new Rectangle2D.Double(240, 25, 13, 445));
        
        //pilastraBaixo
        pa.setColor(Color.white);
        pa.fill(new Rectangle2D.Double(pax,pay,500,20));
        
        //pilastraCima
        pa2.setColor(Color.white);
        pa2.fill(new Rectangle2D.Double(pa2x,pa2y,500,20));
        
        //pilastraDireita
        pa3.setColor(Color.gray);
        pa3.fill(new Rectangle2D.Double(pa3x,pa3y,20,445));
        
        ////pilastraEsquerda
        pa4.setColor(Color.gray);
        pa4.fill(new Rectangle2D.Double(pa4x,pa4y,20,445));
        
        scr.setColor(Color.white);
        scr.setFont(big);
        scr.drawString(plscore + "    " + iscore, 110, 100);
        
        scr2.setColor(Color.red);
        scr2.setFont(big2);
        scr2.drawString("vida: " + vidas, 90, 450);
        }else{
            py = y -30;
            if(py< 25){
                py = 25;
            
            }
            if(py + 110> pay){
                py = pay - 110;
            }
            
            //player
        player.setColor(Color.gray);
        player.fill(new Rectangle2D.Double(px,py,20,110));
        
        //bola
        o1.setColor(Color.gray);
        o1.fill(new Rectangle2D.Double(x,y,15,15));
        
        //inimigo
        ini.setColor(Color.gray);
        ini.fill(new Rectangle2D.Double(ix, iy, 20, 110));
        
        //pilastra do meio
        pdm.setColor(Color.gray);
        pdm.fill(new Rectangle2D.Double(240, 25, 13, 445));
        
        //pilastraBaixo
        pa.setColor(Color.gray);
        pa.fill(new Rectangle2D.Double(pax,pay,500,20));
        
        //pilastraCima
        pa2.setColor(Color.gray);
        pa2.fill(new Rectangle2D.Double(pa2x,pa2y,500,20));
        
        //pilastraDireita
        pa3.setColor(Color.gray);
        pa3.fill(new Rectangle2D.Double(pa3x,pa3y,20,445));
        
        ////pilastraEsquerda
        pa4.setColor(Color.gray);
        pa4.fill(new Rectangle2D.Double(pa4x,pa4y,20,445));
        
        scr.setColor(Color.gray);
        scr.setFont(big);
        scr.drawString("Pong   Java", 45, 100);
        
        scr2.setColor(Color.gray);
        scr2.setFont(big2);
        scr2.drawString("-----", 90, 450);
        }
        
        
        repaint();
    }
}
    
    
    public static void main(String[] args) {
        new Aplica();
    }
    
}
