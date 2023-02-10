package lingua2023;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    @FXML
    Label results;

    @FXML
    TextArea textAn;

    static ArrayList<String> categoriesOfText = new ArrayList<>();
    static ArrayList<Double> valuesOfText = new ArrayList<>();
    static String res;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoriesOfText.clear();
        valuesOfText.clear();
        res = "";
        countRes(Controller.badWords, Controller.goodWords, Controller.cntWords);
        results.setText("Введенный текст обладает такими характеристиками: " + res + ".");
        textAn.setText(Controller.text);

    }

    public void countRes(HashMap<Integer, Integer> badWords, HashMap<Integer, Integer> goodWords, int cntWords) {

        for (Integer j : goodWords.keySet()){

            System.out.println("GW " + j + " " + goodWords.get(j));

        }

        for (Integer j : badWords.keySet()){

            System.out.println("BW " + j + " " + badWords.get(j));

        }

        for (int i = 0; i < 7; ++i) {

            Boolean is = false;

            if (badWords.containsKey(i) && goodWords.containsKey(i)) {

                double pVB = badWords.get(i) / cntWords;
                double pVG = goodWords.get(i) / cntWords;

                is = true;

                if (pVB > pVG) {

                    double curV = pVB - pVG;

                    switch (i) {

                        case 0:
                            categoriesOfText.add("грустный");
                            break;
                        case 1:
                            categoriesOfText.add("плохой");
                            break;
                        case 2:
                            categoriesOfText.add("грубый");
                            break;
                        case 3:
                            categoriesOfText.add("злой");
                            break;
                        case 4:
                            categoriesOfText.add("темный");
                            break;
                        case 5:
                            categoriesOfText.add("отталкивающий");
                            break;
                        case 6:
                            categoriesOfText.add("устрашающий");
                            break;

                    }

                    valuesOfText.add(curV);

                } else {

                    double curV = pVG - pVB;

                    switch (i) {

                        case 0:
                            categoriesOfText.add("веселый");
                            break;
                        case 1:
                            categoriesOfText.add("хороший");
                            break;
                        case 2:
                            categoriesOfText.add("нежный");
                            break;
                        case 3:
                            categoriesOfText.add("добрый");
                            break;
                        case 4:
                            categoriesOfText.add("светлый");
                            break;
                        case 5:
                            categoriesOfText.add("красивый");
                            break;
                        case 6:
                            categoriesOfText.add("безопасный");
                            break;

                    }

                    valuesOfText.add(curV);

                }

            }

            if (badWords.size() > 0 && badWords.containsKey(i) && !is) {

                switch (i) {

                    case 0:
                        categoriesOfText.add("грустный");
                        break;
                    case 1:
                        categoriesOfText.add("плохой");
                        break;
                    case 2:
                        categoriesOfText.add("грубый");
                        break;
                    case 3:
                        categoriesOfText.add("злой");
                        break;
                    case 4:
                        categoriesOfText.add("темный");
                        break;
                    case 5:
                        categoriesOfText.add("отталкивающий");
                        break;
                    case 6:
                        categoriesOfText.add("устрашающий");
                        break;

                }

                double curV = badWords.get(i) / cntWords;

                valuesOfText.add(curV);

            }

            for (Integer e : badWords.keySet()){

                System.out.println(e + " " + badWords.get(e));

            }

            if (goodWords.size() > 0 && !is && goodWords.containsKey(i)) {

                switch (i) {

                    case 0:
                        categoriesOfText.add("веселый");
                        break;
                    case 1:
                        categoriesOfText.add("хороший");
                        break;
                    case 2:
                        categoriesOfText.add("нежный");
                        break;
                    case 3:
                        categoriesOfText.add("добрый");
                        break;
                    case 4:
                        categoriesOfText.add("светлый");
                        break;
                    case 5:
                        categoriesOfText.add("красивый");
                        break;
                    case 6:
                        categoriesOfText.add("безопасный");
                        break;

                }

                double curV = goodWords.get(i) / cntWords;

                valuesOfText.add(curV);

            }

            res = String.join(",", categoriesOfText);

        }
    }
}
