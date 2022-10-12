package fr.icom.info.m1.balleauprisonnier_mvn;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * 
 * Classe gerant un joueur
 *
 */
public class PlayerHuman extends Player
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

	  /**
	   * Constructeur du Joueur
	   *
	   * @param gc ContextGraphic dans lequel on va afficher le joueur
	   * @param color couleur du joueur
	   * @param yInit position verticale
	   */
	  PlayerHuman(GraphicsContext gc, String color, int xInit, int yInit, String side)
	  {
		  super(gc, color, xInit, yInit, side);
		  // Tous les joueurs commencent au centre du canvas,
		  x = xInit;
		  y = yInit;
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
	  }

	  void deplacement(ArrayList<String> input, int i){
		  if(i == 0){
			  if (input.contains("LEFT"))
			  {
				  this.moveLeft();
			  }
			  if (input.contains("RIGHT"))
			  {
				  this.moveRight();
			  }
			  if (input.contains("UP"))
			  {
				  this.turnLeft();
			  }
			  if (input.contains("DOWN"))
			  {
				  this.turnRight();
			  }
			  if (input.contains("ENTER")){
				  this.shoot();
			  }
		  }
		  else if (i ==1) {
			  if (input.contains("Q"))
			  {
				  this.moveLeft();
			  }
			  if (input.contains("D"))
			  {
				  this.moveRight();
			  }
			  if (input.contains("Z"))
			  {
				  this.turnLeft();
			  }
			  if (input.contains("S"))
			  {
				  this.turnRight();
			  }
			  if (input.contains("SPACE")){
				  this.shoot();
			  }
		  }



	  }


	  
}
