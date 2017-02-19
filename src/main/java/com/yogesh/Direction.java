package com.yogesh;


public enum Direction {
    DDL("DDL"),
    DUL("DUL"),
    LR("LR"),
    RL("RL"),
    U("U"),
    D("D"),
    DUR("DUR"),
    DDR("DDR");
    

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String direction() {
        return direction;
    }
}