package me.shopian.shopian3.util;

public class ColumnDirection {
    String column;
    String direction;

    public boolean isDesc() {
        return "desc".equals(direction);
    }

    public boolean isAsc() {
        return !isDesc();
    }

    public ColumnDirection(String column, String direction) {
        this.column = column;
        this.direction = direction;
    }

    public ColumnDirection() {
    }

    @Override
    public String toString() {
        return "ColumnDirection{" +
                "column='" + column + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
