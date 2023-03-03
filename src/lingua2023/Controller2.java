package lingua2023;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    @FXML
    Label results, res2;

    @FXML
    TextArea textAn;

    @FXML
    BarChart chartSent;

    @FXML
    CategoryAxis cS;

    @FXML
    NumberAxis nS;

    static ArrayList<String> categoriesOfText = new ArrayList<>();
    static ArrayList<Double> valuesOfText = new ArrayList<>();
    static String res;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoriesOfText.clear();
        valuesOfText.clear();
        res = "";
        countRes(Controller.badWords, Controller.goodWords, Controller.cntWords);
        textAn.setText(Controller.text);
        ArrayList<Double> result = countRes2(Controller.gWValues, Controller.bWValues);

        res2.setText("Общий расчёт: Тональность сообщения позитивна на " + result.get(0) + ", негативна - на " + result.get(1) + " по шкале от 0 до 1.");
    }

    public ArrayList<Double> countRes2(ArrayList<Double> gw, ArrayList<Double> bw){

        ArrayList<Double> result = new ArrayList<>(2);
        double scale = Math.pow(10, 4);
        double sumValues = 0;

        int cntG = Controller.goodWords.size();
        int cntB= Controller.badWords.size();

        for(int i = 0; i < gw.size(); ++i){

            sumValues += gw.get(i);

        }

        sumValues = sumValues;

        double sG = gw.size();
        double sB = bw.size();

        double gWandBw1 = sG / sB;
        double gWandBw2 = sB / sG;

        double gV = 0.0;
        double bV = 0.0;

        if(gw.size() != 0) gV = sumValues/gw.size();
        else gV = 0.0;

        sumValues = 0;

        for(int i = 0; i < bw.size(); ++i){

            sumValues += bw.get(i);

        }

        if(bw.size() !=0) bV = sumValues/bw.size();
        else bV = 0.0;

        if(sB > sG && sG != 0){
            gV = gV * gWandBw1;
        }
        else if(sG >= sB && sB != 0){
            bV = bV * gWandBw2;
        }

        result.add(Math.ceil(gV * scale) / scale);
        result.add(Math.ceil(bV * scale) / scale);

        return result;

    }

    public void countRes(HashMap<Integer, Integer> badWords, HashMap<Integer, Integer> goodWords, int cntWords) {

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
