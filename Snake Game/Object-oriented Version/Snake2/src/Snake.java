import java.awt.*;
import java.util.ArrayList;
public class Snake {
    private ArrayList<Point> m_body;
    private int m_direction;
    private int counter; //for counting the number of targets the snake has eaten
    public Snake() {
        m_body = new ArrayList<>();
        m_body.add(new Point(0.5, 0.5));
        m_direction = 0;
        counter = 0;
    }

    public void move() {
        for (int i = m_body.size() - 1; i > 0; i--) {
            m_body.get(i).setX(m_body.get(i - 1).getX());
            m_body.get(i).setY(m_body.get(i - 1).getY());
        }

        Point head = m_body.get(0);
        switch (m_direction) {
            case 0: // Up
                head.setY(head.getY() + 0.02);
                if (head.getY() > 1.0)
                    head.setY(0.0);
                break;
            case 1: // Right
                head.setX(head.getX() + 0.02);
                if (head.getX() > 1.0)
                    head.setX(0.0);
                break;
            case 2: // Down
                head.setY(head.getY() - 0.02);
                if (head.getY() < 0.0)
                    head.setY(1.0);
                break;
            case 3: // Left
                head.setX(head.getX() - 0.02);
                if (head.getX() < 0.0)
                    head.setX(1.0);
                break;
        }
    }
    public void setDirection() {
        if (m_direction != 2 && StdDraw.isKeyPressed(38)) {
            m_direction = 0;
        } else if (m_direction != 3 && StdDraw.isKeyPressed(39)) {
            m_direction = 1;
        } else if (m_direction != 0 && StdDraw.isKeyPressed(40)) {
            m_direction = 2;
        } else if (m_direction != 1 && StdDraw.isKeyPressed(37)) {
            m_direction = 3;
        }
    }
    public boolean isEat(Target target) {
        double deltaX = m_body.get(0).getX() - target.getCenter().getX();
        double deltaY = m_body.get(0).getY() - target.getCenter().getY();
        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance < target.getRadius() + 0.02;
    }
    public void normalGrowth() {
        Point tail = m_body.get(m_body.size() - 1);
        m_body.add(new Point(tail.getX(), tail.getY()));
    }
    public void awardGrowth() {
        Point tail = m_body.get(m_body.size() - 1);
        for (int i = 0; i < 3; i++)
            m_body.add(new Point(tail.getX(), tail.getY()));
    }
    public boolean isHitItself() {
        Point head = m_body.get(0);
        for (int i = 1; i < m_body.size(); i++) {
            Point cell = m_body.get(i);
            if (head.getX() == cell.getX() && head.getY() == cell.getY()) {
                return true;
            }
        }
        return false;
    }
    public void draw() {
        StdDraw.setPenRadius(0.04);
        StdDraw.setPenColor(Color.GREEN);
        for (Point point : m_body) {
            StdDraw.point(point.getX(), point.getY());
        }
    }
    public int getLength(){
        return m_body.size();
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
}


