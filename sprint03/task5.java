enum ClientType {
    NEW(1),
    SILVER(12),
    GOLD(30),
    PLATINUM(60);
    private final int months;

    ClientType(int months) {
        this.months = months;
    }

    public double discount(){
        return switch (this) {
            case NEW -> 1;
            case SILVER -> (100 - SILVER.months * 0.25) / 100;
            case GOLD -> (100 - GOLD.months * 0.3) / 100;
            case PLATINUM -> (100 - PLATINUM.months * 0.35) / 100;
            default -> -1;
        };

    }
}
