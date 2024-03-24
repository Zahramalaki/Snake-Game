public class Target {
    private Point m_center;
    private double m_radius;
    public Target() {
        m_center = new Point(Math.random(),Math.random());
        m_radius = 0.02;
    }

    public void draw(int targetCount) {
        if(m_center.getX() <= 0.06f)         m_center.setX(0.06f);
        if(m_center.getY() <= 0.06f)         m_center.setY(0.06f);
        if(m_center.getX() >= 1 - 0.06f)     m_center.setX(1 - 0.06f);
        if(m_center.getY() >= 1 - 0.06f)     m_center.setY(1 - 0.06f);

        //after the snake eats five targets, the next target will be 3 times larger
        StdDraw.setPenColor(StdDraw.RED);
        if (targetCount == 5) {
            StdDraw.setPenRadius(0.002);
            StdDraw.filledCircle(m_center.getX(), m_center.getY(), 3 * m_radius);
        }
        StdDraw.setPenRadius(0.001);
        StdDraw.filledCircle(m_center.getX(), m_center.getY(), m_radius);

    }
    public Point getCenter() {
        return m_center;
    }
    public double getRadius() {
        return m_radius;
    }
    public void setRadius(double r){
        m_radius = r;
    }
    public void setCenter(Point center) {
        this.m_center = center;
    }
}

