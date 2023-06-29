package jom.com.softserve.s1.task6;


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
        if(!checkType(type)) throw new TypeException("Invalid value " + type + " for field type");
        if(!checkColor(color)) throw new ColorException("Invalid value " + color + " for field color");
        this.type = Type.valueOf(type.toUpperCase());
        this.color = Color.valueOf(color.toUpperCase());
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public static Plant tryCreatePlant(String type, String color, String name){
        try {
            return new Plant(type, color, name);
        }catch (TypeException ex){
            try {
                return new Plant("Ordinary", color, name);
            }catch (ColorException colorException){
                try {
                    return new Plant("Ordinary", "Red", name);
                } catch (Exception e) {
                    return null;
                }
            }catch (Exception e){
                return null;
            }
        }catch (ColorException colorException || Excepy){
            try {
                return new Plant(type, "red", name);
            }catch (Exception e){
                return null;
            }
        }
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

