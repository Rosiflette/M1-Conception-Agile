package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Projectile {
    GraphicsContext graphicsContext;
    double speed;
    double[] direction;
    double[] position;

    Image imgBall;
    Sprite ball;

    double angle;

    Projectile(double angle, double xPosition, double yPosition, GraphicsContext gr){
        this.graphicsContext = gr;
        direction = new double[2];
        position = new double[2];
        double angleRad = Math.toRadians(angle);
        this.angle = angle;



//       System.out.println(Math.cos(angleRad));
//       System.out.println(Math.sin(angleRad));
        position[0] = xPosition;
        position[1] = yPosition;
        speed = 1.05;
        direction[1] = -Math.cos(angleRad);
        direction[0] = Math.sin(angleRad);

//        position[0] = xPosition;
//        position[1] = yPosition;
//
//        double newangle = Math.atan2(xPosition, yPosition);
//
//        direction[0] =( Math.cos(newangle)*1 )/ 50;
//        direction[1] = (-Math.sin(newangle)*1) /50;

        System.out.println("Angle balle : " + angle);
        System.out.println("Direction : " + direction[0] + ";" + direction[1]);
        imgBall = new Image("assets/ball.png");

    }

    void deplacement(){

    }

    void display()
    {
        position[0] += direction[0] * speed;
        position[1] += direction[1] * speed;

        graphicsContext.save(); // saves the current state on stack, including the current transform
//        position[0] += direction[0] * speed;
//        position[1] -= direction[1] * speed;
        rotate(graphicsContext, angle, position[0] + imgBall.getWidth() / 2, position[1] + imgBall.getHeight() / 2);
        graphicsContext.drawImage(imgBall, position[0] , position[1]);




        graphicsContext.restore(); // back to original state (before rotation)


    }

    public void setAngle(double angle) {
        this.angle = angle;
    }



    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    boolean isTouchingPlayer(Player pl){
        if(Math.round(pl.x) == Math.round(this.position[0]) && Math.round(pl.y) == Math.round(this.position[1])){
            return true;
        }
        return false;
    }
}
