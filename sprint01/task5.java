enum Color{
    WHITE("White"),
    RED("Red"),
    BLUE("Blue");

    private final String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return colorName;
    }
}

enum Type{
    RARE("Rare"),
    ORDINARY("Ordinary");

    private final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}

class ColorException extends Exception{
    public ColorException(String message) {
        super(message);
    }
}


class TypeException extends Exception{
    public TypeException(String message) {
        super(message);
    }
}

public class Plant {

    private String name;
    private Color color;
    private Type type;

    public Plant(String type, String color, String name) throws ColorException, TypeException {

        if(!checkColor(color)) throw new ColorException("Color " + color + "doesn't exist");
        if(!checkType(type)) throw new TypeException("Type " + type + "doesn't exist");
        this.type = Type.valueOf(type.toUpperCase());
        this.color = Color.valueOf(color.toUpperCase());
        this.name = name;
    }

    private boolean checkColor(String color){
        for (Color c : Color.values()) {
            if (c.name().equalsIgnoreCase(color)) {
                return true;
            }
        }
        return false;
    }


    private boolean checkType(String type){
        for (Type t : Type.values()) {
            if (t.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        return "{" +
               "type: " + type.toString() +
               ", color: " + color.toString() +
               ", name: " + name +
               '}';
    }
}

