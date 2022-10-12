package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */
	Player [] joueursHuman = new PlayerHuman[1];
	Player [] joueursAI = new PlayerAI[2];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();

	Projectile theBall;
    

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
		theBall = new Projectile(0, w/2, h/2, this.gc);
        
        /** On initialise le terrain de jeu */
		for (int i = 0; i < joueursHuman.length; i++){
			joueursHuman[i] = new PlayerHuman(gc, colorMap[0], w/2, h-50, "bottom");
			joueursHuman[i].display();
		}

		for (int i = 0; i < joueursAI.length; i++){
			joueursAI[i] = new PlayerAI(gc, colorMap[1], w/2, 20, "top");
			joueursAI[i].display();
		}





	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {

	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);

				if(theBall != null){
					// Deplacement et affichage des joueurs
					for (int i = 0; i < joueursHuman.length; i++)
					{

						joueursHuman[i].deplacement(input, i);
						joueursHuman[i].spriteAnimate();
						joueursHuman[i].display();
					}
					for (int i = 0; i < joueursAI.length; i++){
						if(!theBall.isTouchingPlayer(joueursAI[i])){
							joueursAI[i].spriteAnimate();
							joueursAI[i].display();
						}
						else{
							System.out.println("TUERRRR");
							joueursAI[i].isAlive = false;
						}



//							System.out.println("Joueur " + joueursAI[i].x + " " +joueursAI[i].y);
//							System.out.println("Balle " + theBall.position[0] + " " + theBall.position[1]);



					}
						theBall.display();
				}

	    	}
	     }.start(); // On lance la boucle de rafraichissement 
	     
	}

	public Player[] getJoueursAI() {
		return joueursAI;
	}

	public int getNbJoueursAI() {
		return joueursAI.length;
	}

	public Player[] getJoueursHuman() {
		return joueursHuman;
	}

	public int getNbJoueursHuman() {
		return joueursHuman.length;
	}
}
