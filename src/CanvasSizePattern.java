public enum CanvasSizePattern {
    P1("1000 pixel grid", 1000, 1000), P2("2000 pixel grid", 2000, 2000), P3("Postcard", 1200, 1800), P4("1080p", 1920, 1080), P5("720p", 1280, 720), P6("Custom", 0, 0), P7("800x600", 800, 600), P8("Minimum", 400, 200);
    private String name;
    private int width;
    private int height;
    CanvasSizePattern(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
