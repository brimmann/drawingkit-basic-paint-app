public enum PencilType {
    P6H(6, 'H'), P5H(5, 'H'), P4H(4, 'H'), P3H(4, 'H'), P2H(2, 'H'), PH(1, 'H'), PHB(0, 'N'), PB(1, 'B'), P2B(2, 'B'), P3B(3, 'B'), P4B(4, 'B'), P5B(5, 'B'), P6B(6, 'B');

    private int value;
    private  char lead;
    PencilType(int value, char lead){
        this.value = value;
        this.lead = lead;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getLead() {
        return lead;
    }

    public void setLead(char lead) {
        this.lead = lead;
    }
}
