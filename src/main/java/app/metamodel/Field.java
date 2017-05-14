package app.metamodel;

public class Field {
    private String name;
    private String type;
    private String precision;
    private String javaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String toString() {
        return getName() + "\t\t - \t\t" + getType() + "\t\t - \t\t" + getPrecision();
    }
}