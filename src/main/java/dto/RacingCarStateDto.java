package dto;

public class RacingCarStateDto {

    private final String name;
    private final int position;

    public RacingCarStateDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}