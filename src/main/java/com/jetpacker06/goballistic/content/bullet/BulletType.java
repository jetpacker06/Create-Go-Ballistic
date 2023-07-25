package com.jetpacker06.goballistic.content.bullet;

public enum BulletType {
    CAL_22("22 Caliber"),
    CAL_44("44 Caliber"),
    SHELL("Shell"),
    MINIE_BALL("Minie Ball");


    final String asString;
    BulletType(String asString) {
        this.asString = asString;
    }
    @Override
    public String toString() {
        return this.asString;
    }
}
