public class CanvasDimensionZeroException extends Exception {
    String message;
    CanvasDimensionZeroException(){
        this.message = "Dimension cannot be zero.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
