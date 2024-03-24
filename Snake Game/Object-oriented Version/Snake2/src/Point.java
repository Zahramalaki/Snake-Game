public class Point {
    private double m_x;
    private double m_y;
    public Point(double m_x, double m_y) {
        this.m_x = m_x;
        this.m_y = m_y;
    }

    public double getX() {
        return m_x;
    }
    public double getY() {
        return m_y;
    }
    public void setX(double x) {
        this.m_x = x;
    }
    public void setY(double y) {
        this.m_y = y;
    }
}


