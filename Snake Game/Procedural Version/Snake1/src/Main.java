import java.util.ArrayList;
public class Main {

    static void drawSnake(ArrayList<Double> x, ArrayList<Double> y) {
        StdDraw.setPenRadius(0.04);
        StdDraw.setPenColor(StdDraw.GREEN);
        if (x.size() == 1) {
            StdDraw.point(x.get(0), y.get(0));
            return;
        }
        for (int i = 0; i < x.size()-1; i++)
            StdDraw.point(x.get(i), y.get(i));
    }
    static void drawTarget(float x, float y, float r, int counter) {
        if(x<=0.06f)       x=0.06f;
        if(y<=0.06f)       y=0.06f;
        if(x>=1-0.06f)     x=1-0.06f;
        if(y>=1-0.06f)     y=1-0.06f;

        //after the snake eats five targets, the next target will be 3 times larger
        if (counter==5) {
            StdDraw.setPenRadius(0.002);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x, y, 3*r);
        }
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(x, y, r);
    }
    static boolean snakeEat(double snakeHeadX, double snakeHeadY, double targetX, double targetY, double targetRadius) {
        double deltaX = snakeHeadX - targetX;
        double deltaY = snakeHeadY - targetY;
        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance < targetRadius + 0.02;
    }
    static void move(ArrayList<Double> x, ArrayList<Double> y, int direction) {
        for (int i = x.size() - 1; i > 0; i--) {
            x.set(i, x.get(i - 1));
            y.set(i, y.get(i - 1));
        }
        double sx = x.get(0);
        double sy = y.get(0);
        switch (direction) {

            case 0 -> {//up
                y.set(0, y.get(0) + 0.02);
                if (sy >=1.0) {
                    //Warp to bottom
                    for (int j = 0; j < x.size() - 1; j++) {
                        y.set(j, y.get(j) - 1.0);
                    }
                    drawSnake(x, y);
                }
            }

            case 2 -> {//down
                y.set(0, y.get(0) - 0.02);
                if (sy <= 0.0) {
                    //Warp to top
                    for (int j = 0; j < x.size() - 1; j++) {
                        y.set(j, y.get(j) + 1.0);
                    }
                    drawSnake(x, y);
                }
            }

            case 1 -> {//right
                x.set(0, x.get(0) + 0.02);
                if (sx >= 1.0) {
                    //Warp to left
                    for (int j = 0; j < x.size() - 1; j++) {
                        x.set(j, x.get(j) - 1.0);
                    }
                    drawSnake(x, y);
                }
            }

            case 3 -> {//left
                x.set(0, x.get(0) - 0.02);
                if (sx <= 0.0) {
                    //Warp to right
                    for (int j = 0; j < x.size() - 1; j++) {
                        x.set(j, x.get(j) + 1.0);
                    }
                    drawSnake(x, y);
                }
            }

        }
        drawSnake(x, y);
    }
    static boolean hitItself(ArrayList<Double> x, ArrayList<Double> y) {
        double sx = x.get(0);
        double sy = y.get(0);
        for (int i = 1; i < x.size(); i++) {
            if (sx == x.get(i) && sy == y.get(i)) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        //---windows size and title-------------------------------------
        StdDraw.setTitle("Snake");
        StdDraw.setCanvasSize(700, 700);

        //---parameters-------------------------------------------------
        ArrayList<Double> snakeX = new ArrayList<>();
        ArrayList<Double> snakeY = new ArrayList<>();
        int direction = 0; //move up
        float targetX = 0;
        float targetY = 0;
        float targetRadius = 0;
        int counter = 0; //for counting the number of targets the snake has eaten

        //---initialing snake-------------------------------------------
        snakeX.add(0.5);
        snakeY.add(0.5);

        while (true) {

            StdDraw.clear();

            if(targetRadius == 0){
                targetX = (float)Math.random() ;
                targetY = (float)Math.random() ;
                targetRadius = 0.02f ;
            }
            /* x and y coordinates are controlled logically in drawTarget function */
            drawSnake(snakeX , snakeY) ;
            drawTarget(targetX, targetY, targetRadius, counter) ;

            //---handling hit condition-----(hitting itself)-----------------
            if (hitItself(snakeX, snakeY)) break;

            //---handling eat condition-------------------------------------
            if(counter==5) {
                if (targetRadius < 0.001) {
                    targetRadius = 0;
                    counter = 0;
                }
                else targetRadius -= 0.0005;
            }

            if (snakeEat(snakeX.get(0), snakeY.get(0), targetX, targetY, targetRadius)) {
                targetRadius = 0;
                if(counter==5) {
                    snakeX.add (2*(snakeX.get(snakeX.size()-1)));
                    snakeY.add (2*(snakeY.get(snakeY.size()-1)));
                    counter = 0;
                }
                else {
                    snakeX.add(snakeX.get(snakeX.size()-1));
                    snakeY.add(snakeY.get(snakeY.size()-1));
                    counter++;
                }

            }

            move(snakeX, snakeY, direction);

            //---checking key event-----------------------------------------
            if (direction != 2 && StdDraw.isKeyPressed(38)) {
                direction = 0;
            } else if (direction != 3 && StdDraw.isKeyPressed(39)) {
                direction = 1;
            } else if (direction != 0 && StdDraw.isKeyPressed(40)) {
                direction = 2;
            } else if (direction != 1 && StdDraw.isKeyPressed(37)) {
                direction = 3;
            }

            //---refreshing the rate----------------------------------------
            StdDraw.pause(80);

        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, 0.5, "GAME OVER!");
        StdDraw.text(0.5, 0.45, "Score : " + snakeX.size());

    }

}