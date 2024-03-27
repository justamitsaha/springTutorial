package com.saha.amit.entity.enumerated;

public enum Semester {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private int semester;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    Semester(int semester) {
        this.semester = semester;
    }
    public int getTimeToDelivery() {
        return semester;
    }
}
