import java.awt.*;
public class Gameplay {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private Snake snake;
    private Target target;
    public Gameplay() {
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        snake = new Snake();
        target = new Target();
    }

    public void run() {

        while (true) {
            StdDraw.clear();
            if(target.getRadius()== 0){
                Point newTarget = new Point(Math.random(), Math.random());
                target.setCenter(newTarget);
                target.setRadius(0.02f);
            }
            snake.draw();
            target.draw(snake.getCounter());

            if (snake.isHitItself()) {
                gameOver();
                break;
            }
            if (snake.getCounter() == 5) {
                if (target.getRadius() < 0.001) {
                    target.setRadius(0);
                    snake.setCounter(0);
                }
                else target.setRadius(target.getRadius() - 0.0002);
            }

            if (snake.isEat(target)) {
                if(snake.getCounter() == 5) {
                    snake.awardGrowth();
                    snake.setCounter(0);
                }
                else {
                    snake.normalGrowth();
                    snake.setCounter(snake.getCounter()+1);
                }
                target = new Target();
            }

            snake.setDirection();
            snake.move();
            StdDraw.show(80);
        }

    }
    private void gameOver() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.text(0.5, 0.45, "Score: " + snake.getLength());
        StdDraw.show();
    }

    public static void main(String[] args) {
        Gameplay game = new Gameplay();
        game.run();
    }
}

