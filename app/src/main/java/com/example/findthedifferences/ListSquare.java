package com.example.findthedifferences;

import java.util.ArrayList;
import java.util.List;

public class ListSquare {

    List<Square> answers1 = new ArrayList<>();
    List<Square> answers2 = new ArrayList<>();
    List<Square> answers3 = new ArrayList<>();

    ListSquare() {
        answers1.add(new Square(142, 165, 167, 189));
        answers1.add(new Square(150, 192, 166, 242));
        answers1.add(new Square(21, 289, 35, 302));
        answers1.add(new Square(70, 275, 85, 285));
        answers1.add(new Square(122, 301, 169, 354));

        answers2.add(new Square(85, 211, 114, 227));
        answers2.add(new Square(105, 298, 132, 313));
        answers2.add(new Square(83, 281, 105, 300));
        answers2.add(new Square(141, 198, 167, 219));
        answers2.add(new Square(49, 299, 62, 308));

        answers3.add(new Square(98, 234, 119, 248));
        answers3.add(new Square(25, 253, 57, 272));
        answers3.add(new Square(70, 296, 83, 304));
        answers3.add(new Square(45, 318, 157, 332));
        answers3.add(new Square(45, 318, 157, 332));

    }

    int isIn(String level, int x, int y) {
        if (level.equals("1")) {
            for (int i = 0; i < answers1.size(); i++)
                if (answers1.get(i).isIn(x, y)) return i;
        } else if (level.equals("2")) {
            for (int i = 0; i < answers2.size(); i++)
                if (answers2.get(i).isIn(x, y)) return i;
        } else if (level.equals("3")) {
            for (int i = 0; i < answers3.size(); i++)
                if (answers3.get(i).isIn(x, y)) return i;
        }
        return -1;
    }
}
