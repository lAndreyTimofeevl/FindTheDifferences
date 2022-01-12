package com.example.findthedifferences;

public class Square {
    int x1;
    int y1;
    int x2;
    int y2;

    Square(int x1_, int y1_, int x2_, int y2_) {
        x1 = x1_;
        y1 = y1_;
        x2 = x2_;
        y2 = y2_;
    }

    boolean isIn(int x, int y) {
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }
}
