package lingua2023;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    @FXML
    Button sendB, startB;

    @FXML
    TextArea areaU, areaS;

    @FXML
    Label aboutWord;

    @FXML
    ListView resList;

    @FXML
    BarChart chartW;

    @FXML
    CategoryAxis catW;

    @FXML
    NumberAxis numW;

    static String text; // переменная, в которой анализируемый текст
    static Integer cntWords; // кол-во слов

    static int cntK = 6; // кол-во критериев

    ArrayList <String> wordsNot = new ArrayList<>(); // слова, значения которых нужно перевернуть (перед ними не)
    ArrayList <String> wordsVery = new ArrayList<>(); // слова, значения которых усилить (перед ними очень)

    static ArrayList<ArrayList<Double>> vlAllWords = new ArrayList<>(); // значения по всем  словам

    static HashMap<Integer, Integer> badWords = new HashMap<>(5); // плохие
    static HashMap<Integer, Integer> goodWords = new HashMap<>(5); // хорошие

    static ArrayList<Double> gWValues = new ArrayList<>(); // значения по хорошим словам (с весами)
    static ArrayList<Double> bWValues = new ArrayList<>(); // значения по плохим словам (с весами)

    static boolean isAn = false; // проведен ли анализ

    //токены
    ObservableList<String> words = FXCollections.observableArrayList(); // лист со всеми словами для списка

    // ф-ция где инициалиазируется список из слов

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        numW.setLowerBound(1);
        numW.setUpperBound(5);

        resList.getItems().addAll(words); // добавляются все элементы из списка words

        resList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            // метод, срабатывающий при выборе пользователем нового элемента в списке

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                chartW.getData().clear();
                chartW.setTitle(String.valueOf(resList.getSelectionModel().getSelectedItem()));
                int index = resList.getSelectionModel().getSelectedIndex();
                ArrayList<Double> nowV = vlAllWords.get(index);

                catW.setLabel("Признаки");
                numW.setLabel("Значения");

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                //список для графика
                ObservableList <XYChart.Data<String, Number>> dataChart = FXCollections.observableArrayList();

                double scale = Math.pow(10, 3);

                double v1 = Math.ceil(nowV.get(0) * scale) / scale;
                double v2 = Math.ceil(nowV.get(1) * scale) / scale;
                double v3 = Math.ceil(nowV.get(2) * scale) / scale;
                //double v4 = Math.ceil(nowV.get(3) * scale) / scale;
                double v5 = Math.ceil(nowV.get(3) * scale) / scale;
                double v6 = Math.ceil(nowV.get(4) * scale) / scale;
                double v7 = Math.ceil(nowV.get(5) * scale) / scale;

                series.getData().add(new XYChart.Data("В/Г", v1));
                series.getData().add(new XYChart.Data("Х/П", v2));
                series.getData().add(new XYChart.Data("Н/Г", v3));
               // series.getData().add(new XYChart.Data("Д/З", v4));
                series.getData().add(new XYChart.Data("С/Т", v5));
                series.getData().add(new XYChart.Data("К/О", v6));
                series.getData().add(new XYChart.Data("Б/У", v6));

                chartW.getData().addAll(series);//отрисовка графика
                chartW.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

                ArrayList<String> categories = new ArrayList<>();

                categories = define(nowV, String.valueOf(resList.getSelectionModel().getSelectedItem()));

                String res = String.join(",", categories);

                aboutWord.setText((String.valueOf(resList.getSelectionModel().getSelectedItem()).toUpperCase(Locale.ROOT) + ": "+ res + "."));

            }

        });

    }

    static int goodFi = 0;
    static int badFi = 0;

    //определить позитивные и негативные
    public void defineBG(ArrayList<Double> values){

        double scale = Math.pow(10, 4);

        for(int i = 0; i < values.size(); ++i){
            // ключ - идентификатор критерия, значение кол-во слов
            if(values.get(i) <= 2.5){

                goodFi += 1;

                if(values.get(i) == 0) break;

                gWValues.add((values.get(i) - 1)/1.5);

                if(goodWords.containsKey(i))
                    goodWords.put(i, goodWords.get(i) + 1);
                else goodWords.put(i, 1);

            }

            else if(values.get(i) >= 3.5){

                badFi += 1;

                if(values.get(i) == 0) break;

                bWValues.add(abs(values.get(i) - 5) / 1.5);

                if(badWords.containsKey(i))
                    badWords.put(i, badWords.get(i) + 1);
                else badWords.put(i, 1);

            }

        }

        System.out.println("звуки " + goodFi + " " + badFi);

    }

    // работа со значениями (не, очень)

    public ArrayList<Double> workWithValues(ArrayList<Double> values, String word){

        ArrayList<Double> valuesNew = new ArrayList<>();

        for(int i = 0; i < values.size(); ++i){

            if(values.get(i) <= 2.5 || values.get(i) >= 3.5) {

                if (wordsNot.contains(word)) {

                    if (values.get(i) <= 2.5) {

                        values.set(i, values.get(i) + 2);

                    }

                    if (values.get(i) >= 3.5) {

                        values.set(i, values.get(i) - 2);

                    }

                }

                if(wordsVery.contains(word)){

                    if(values.get(i) <= 2.5){

                        values.set(i, values.get(i) - 0.5);

                    }

                    if(values.get(i) >= 3.5){

                        values.set(i, values.get(i) + 0.5);

                    }

                }
            }
        }

        valuesNew = values;
        return valuesNew;

    }

    public double abs(double a){
        if(a >= 0) return a;
        else return -a;
    }

    // определение по хорошим / плохим

    public ArrayList<String> define (ArrayList<Double> values, String word){

        ArrayList<String> categories = new ArrayList<>();

        boolean used = false;

        for(int i = 0; i < values.size(); ++i){

            if(values.get(i) <= 2.5){

                used = true;

                switch (i){

                    case 0:
                        categories.add("веселое");
                        break;
                    case 1:
                        categories.add("хорошее");
                        break;
                    case 2:
                        categories.add("нежное");
                        break;
                    case 3:
                        categories.add("светлое");
                        break;
                    case 4:
                        categories.add("красивое");
                        break;
                    case 5:
                        categories.add("безопасное");
                        break;

                }

            }

            else if(values.get(i) >= 3.5){

                used = true;

                switch (i){

                    case 0:
                        categories.add("грустное");
                        break;
                    case 1:
                        categories.add("плохое");
                        break;
                    case 2:
                        categories.add("грубое");
                        break;
                    case 3:
                        categories.add("темное");
                        break;
                    case 4:
                        categories.add("отталкивающее");
                        break;
                    case 5:
                        categories.add("устрашающее");
                        break;

                }

            }

        }

        if(used == false){

            if(categories.size() >= 1) {

                categories.set(0, "нейтральное");

            }
            else{

                categories.add(0, "нейтральное");

            }

        }

        return categories;

    }

    // получает текст, введенный пользователем

    public void send(ActionEvent actionEvent) {

        text = areaS.getText();
        upload();

    }

    // перезагружает введеный текст в верхее поле

    public void upload(){

        areaU.setText(text);

    }

    // начало анализа текста

    public void start(ActionEvent actionEvent) {

        words.clear();
        vlAllWords.clear();
        badWords.clear();
        goodWords.clear();
        gWValues.clear();
        bWValues.clear();
        cntWords = 0;
        isAn = false;

        List<String> tokens = tokenization();

        for(String w: tokens){
            ArrayList<Double> vl = emotion(w);
            vlAllWords.add(vl);
        }

        for(int i = 0; i < words.size(); ++i){

            workWithValues(vlAllWords.get(i), words.get(i));

        }

    }

    // токенизация

    public List<String> tokenization(){

        Pattern pattern = Pattern.compile("([а-яА-ЯёЁ]+)([-]{1}[А-яа-я]+)?");
        Matcher matcher = pattern.matcher(text);
        List<String> tokens = new ArrayList<>();

        boolean isN = false;
        boolean isV = false;

        while(matcher.find()){

            if(matcher.group().equals("не")){
                System.out.println(1);
                isN = true;
            }

            if(matcher.group().equals("очень")){
                isV = true;
            }

            if(matcher.group().length() <= 3 || matcher.group().equals("очень")){
                continue;
            }

            if(isN){
                wordsNot.add(matcher.group());
                isN = false;
            }

            if(isV){
                wordsVery.add(matcher.group());
                isV = false;
            }

            tokens.add(matcher.group().toLowerCase(Locale.ROOT));
            words.add(matcher.group().toLowerCase(Locale.ROOT));

        }

        resList.setItems(words);
        return tokens;

    }

    // окошко с результатами

    public void showRes(ActionEvent actionEvent) throws IOException {

        cntWords = words.size();
//анализировался ли раньше текст
        if(!isAn) {

            for (int i = 0; i < cntWords; ++i) {

                defineBG(vlAllWords.get(i));

            }
            isAn = true;

        }

        Stage newW = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("win2.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Scene secondScene = new Scene(root1, 600, 400);
        newW.setTitle("Результаты анализа:");
        newW.setScene(secondScene);
        newW.initModality(Modality.WINDOW_MODAL);
        Window mainWindow = sendB.getScene().getWindow();
        newW.initOwner(mainWindow);
        newW.setX(mainWindow.getX() + 200);
        newW.setY(mainWindow.getY() + 100);
        newW.show();

    }

    // ф-ция где происходит анализ слова

    public static ArrayList<Double> emotion (String word){

        ArrayList<ArrayList<Double>> fi = new ArrayList<>(cntK);// массив со всеми характеристиками для всех букв слова
        fi.add(new ArrayList<Double>());
        fi.add(new ArrayList<Double>());
        fi.add(new ArrayList<Double>());
        fi.add(new ArrayList<Double>());
        fi.add(new ArrayList<Double>());
        fi.add(new ArrayList<Double>());
        fi.add(new ArrayList<Double>());

        ArrayList<Double> pi = new ArrayList<>(); // массив с частотами
        ArrayList<Double> ki = new ArrayList<>(); // массив с коэффицентами
        int [] f = new int[cntK];

        for (int i = 0; i < word.length(); ++i) {

            char a1 = word.charAt(i);
            String a = String.valueOf(a1); // буква слова

            // проверка на символы

            if (a1 == '.' || a1 == 'ь' || a1 == 'ъ' || a1 == '-' || a1 == ',' || a1 == '.') {
                continue;
            }

            if (i < word.length() - 1) {

                char b1 = word.charAt(i + 1);
                String b = String.valueOf(b1);

                // проверка нужно ли смягчить звук

                if (Data.ts.contains(a) && Data.sg.contains(b)) {

                    a = a + "м";

                }

                // TODO проверка на доп сочетания по частотe (ау, иу)

            }

            // проверка на йотированные гласные

            if (a1 == 'е' || a1 == 'ё' || a1 == 'ю' || a1 == 'я') {

                a = "й";

                if(a1 == 'е'){

                    word = word + "э";

                }

                if(a1 == 'ё'){

                    word = word + "о";

                }

                if(a1 == 'ю'){

                    word = word + "у";

                }

                if(a1 == 'я'){

                    word = word + "а";

                }

            }

            System.out.println(a1);

            double hsV = Data.hs.get(a); // значение звука по весело-грустно
            double gbV = Data.gb.get(a); // значение звука по хорошо-плохо
            double ngV = Data.ng.get(a); // значение звука по нежно-грубо
            //double keV = Data.ke.get(a); // значение звука по добрый-злой
            double ldV = Data.ld.get(a); // значение звука по светлый-темный
            double buV = Data.bu.get(a); // значение звука по красиво-отталкивающее
            double ssV = Data.ss.get(a); // значение звука по безопасно - устрашающее

            double pV = Data.p.get(a); // частота

            // заполняем массив с характеристиками

            fi.get(0).add(hsV);
            fi.get(1).add(gbV);
            fi.get(2).add(ngV);
            // fi.get(3).add(keV);
            fi.get(3).add(ldV);
            fi.get(4).add(buV);
            fi.get(5).add(ssV);

            pi.add(pV); // добавляем частоту

        }

        double mxP = maxP(pi); // получение максимальной чистоты

        ki = countK(pi, mxP); // считаем коэффиценты

        // проводим итоговые расчеты и присваиваем значение -1,0,1 по критерию

        ArrayList<Double> values = new ArrayList<>();

        for(int i = 0; i < cntK; ++i){

            double sumK = sumKi(ki);
            double sumKF = sumKiwithFi(ki, fi.get(i));

            double fr = sumKF / sumK;

            values.add(fr);

        }

        return values;
    }

    // сумма ки умноженного на фи

    public static double sumKiwithFi(ArrayList<Double> k, ArrayList<Double> f){

        double sum = 0;

        for(int i = 0; i < f.size();++i){

            sum+=k.get(i) * f.get(i);

        }

        System.out.println();

        return sum;

    }

    // сумма кi

    public static double sumKi(ArrayList<Double> k){

        double sum = 0;

        for(int i = 0; i < k.size();++i){

            sum+=k.get(i);

        }

        return sum;

    }

    // считаем коэффиценты каждого звука

    public static ArrayList<Double> countK(ArrayList<Double> p, double mx){

        ArrayList<Double> k = new ArrayList<>();

        for(int i = 0; i < p.size(); ++i){

            k.add(i,mx/p.get(i));

            // первый звук умножаем на 4

            if(i == 0){

                k.set(i, k.get(i) * 4);

            }

        }

        return k;

    }

    // нахождение максимальной чистоты

    public static double maxP(ArrayList<Double> p){

        double mx = 0;

        for(int i = 0; i < p.size(); ++i){

            if(p.get(i) > mx){
                mx = p.get(i);
            }

        }

        return mx;

    }

    //TODO https://www.km.ru/zdorove/encyclopedia/tsvetoterapiya https://world-psychology.ru/cvetoterapiya-i-vliyanie-cveta-na-cheloveka/

    // окошко с информацией

    public void info(ActionEvent actionEvent) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Информация о работе программы:");
        dialog.setHeaderText("Критерии, по которым определяется настроение слов");
        dialog.setContentText("Веселый - грустный (В/Г)" + System.lineSeparator()
                + "Хороший - плохой (Х/П)" + System.lineSeparator()
                + "Нежный - грубый (Н/Г) " + System.lineSeparator()
                + "Светлый - темный (С/Т)" + System.lineSeparator()
                + "Красивый - отталкивающий (К/О)" + System.lineSeparator()
                + "Безопасный - устрашающий (Б/У) " + System.lineSeparator()
                + System.lineSeparator()
                + "Для того, чтобы слово приняло значение веселого / нежного / хорошего / светлого / красивого / безопасного" +
                " нужно, чтобы фонетический ореол был менее 2.5. Чтобы приняло противоположное значение более 3.5. В промежутке слова нейтральны."
        );
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.show();

    }
}
