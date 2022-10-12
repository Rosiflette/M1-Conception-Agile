package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * Classe gerant un joueur
 *
 */
public class Player 
{
	  double x;       // position horizontale du joueur
	  final double y; 	  // position verticale du joueur
	  double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	  double step;    // pas d'un joueur
	  String playerColor;
	  
	  // On une image globale du joueur 
	  Image directionArrow;
	  Sprite sprite;
	  ImageView PlayerDirectionArrow;
	  
	  GraphicsContext graphicsContext;

	  Projectile pr;
	  boolean isShooting = false;

	  /**
	   * Constructeur du Joueur
	   *
	   * @param gc ContextGraphic dans lequel on va afficher le joueur
	   * @param color couleur du joueur
	   * @param yInit position verticale
	   */
	  Player(GraphicsContext gc, String color, int xInit, int yInit, String side)
	  {
		// Tous les joueurs commencent au centre du canvas,
	    x = xInit;
	    y = yInit;
	    graphicsContext = gc;
	    playerColor=color;

	    angle = 0;

	    // On charge la representation du joueur
        if(side=="top"){
        	directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}

        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);

        Image tilesheetImage = new Image("assets/orc.png");
        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
        sprite.setX(x);
        sprite.setY(y);
        //directionArrow = sprite.getClip().;

	    // Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
        // Random randomGenerator = new Random();
        // step = randomGenerator.nextFloat();

        // Pour commencer les joueurs ont une vitesse / un pas fixe
        step = Math.random()%100;

	  }

	  /**
	   *  Affichage du joueur
	   */
	  void display()
	  {
//		  System.out.println("Position joueur " + x + " " + y);
		  graphicsContext.save(); // saves the current state on stack, including the current transform


	      rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
		  graphicsContext.drawImage(directionArrow, x, y);
//		  System.out.println(angle);



		  graphicsContext.restore(); // back to original state (before rotation)

		  if(pr != null){
			  pr.display();
		  }

//		  if(isShooting && pr != null){
//
//			  pr.position[0] += pr.direction[0] * pr.speed;
//			  pr.position[1] -= pr.direction[1] * pr.speed;
////			  System.out.println(pr.direction[0] + " " + pr.direction[1]);
//			  graphicsContext.drawImage(pr.imgBall, pr.position[0], pr.position[1]);
//
////			  pr.position[0] += pr.direction[0];
////			  pr.position[1] += pr.direction[1];
////			  System.out.println(pr.direction[0] + " " + pr.direction[1]);
////			  System.out.println(pr.position[0] + " " + pr.position[1]);
//			  //System.out.println("Position " + pr.position[0] + " " + pr.position[1]);
////			  graphicsContext.drawImage(pr.imgBall, pr.position[0], pr.position[1]);
//		  }

	  }

	  private void rotate(GraphicsContext gc, double angle, double px, double py) {
		  Rotate r = new Rotate(angle, px, py);
		  gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	  }
	  
	  /**
	   *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	   */

	  void deplacement(ArrayList<String> input, int i){  }

	  void moveLeft() 
	  {	    
	    if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x -= step;
	    }
	  }

	  /**
	   *  Deplacement du joueur vers la droite
	   */
	  void moveRight() 
	  {
	    if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x += step;
	    }
	  }

	  
	  /**
	   *  Rotation du joueur vers la gauche
	   */
	  void turnLeft() 
	  {
	    if (angle > 0 && angle < 180) 
	    {
	    	angle += 0.2;
	    }
	    else {
	    	angle += 0.2;
	    }

	  }

	  
	  /**
	   *  Rotation du joueur vers la droite
	   */
	  void turnRight() 
	  {
	    if (angle > 0 && angle < 180) 
	    {
	    	angle -=0.2;
	    }
	    else {
	    	angle -= 0.2;
	    }
	  }


	  void shoot(){
		  sprite.playShoot();

		  isShooting = true;

		  pr = new Projectile(angle, this.x, this.y, graphicsContext);
		  System.out.println("Angle joueur : " + angle);
	  }
	  
	  /**
	   *  Deplacement en mode boost
	   */
	  void boost() 
	  {
	    x += step*2;
		  spriteAnimate();
	  }

	  void spriteAnimate(){
	  	  //System.out.println("Animating sprite");
		  if(!sprite.isRunning) {sprite.playContinuously();}
		  sprite.setX(x);
		  sprite.setY(y);
	  }
	  
}
