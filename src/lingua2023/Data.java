package lingua2023;

import java.util.ArrayList;
import java.util.HashMap;

// класс с данными
public class Data {

    //список твердых согласных
    static ArrayList<String> ts = new ArrayList<>();
    static{
        ts.add("б");
        ts.add("г");
        ts.add("д");
        ts.add("з");
        ts.add("к");
        ts.add("л");
        ts.add("м");
        ts.add("н");
        ts.add("п");
        ts.add("р");
        ts.add("с");
        ts.add("т");
        ts.add("ф");
        ts.add("х");
    }
    //список смягчающих гласных
    static ArrayList<String> sg = new ArrayList<>();
    static{
        sg.add("и");
        sg.add("е");
        sg.add("ё");
        sg.add("ю");
        sg.add("я");
        sg.add("ь");
    }

    // частота

    static HashMap<String, Double> p = new HashMap<>();

    static{

        p.put("а", 0.049);
        p.put("б", 0.013);
        p.put("бм", 0.005);
        p.put("в", 0.028);
        p.put("г", 0.012);
        p.put("гм", 0.0103);
        p.put("д", 0.02);
        p.put("дм", 0.017);
        p.put("е", 0.05);
        p.put("ё", 0.039);
        p.put("ж", 0.008);
        p.put("з", 0.013);
        p.put("зм", 0.002);
        p.put("и", 0.041);
        p.put("й", 0.013);
        p.put("к", 0.03);
        p.put("км", 0.003);
        p.put("л", 0.02);
        p.put("лм", 0.017);
        p.put("м", 0.025);
        p.put("мм", 0.007);
        p.put("н", 0.04);
        p.put("нм", 0.024);
        p.put("о", 0.067);
        p.put("п", 0.02);
        p.put("пм", 0.006);
        p.put("р", 0.024);
        p.put("рм", 0.014);
        p.put("с", 0.032);
        p.put("см", 0.017);
        p.put("т", 0.055);
        p.put("тм", 0.02);
        p.put("у", 0.017);
        p.put("ф", 0.002);
        p.put("фм", 0.001);
        p.put("х", 0.008);
        p.put("хм", 0.001);
        p.put("ц", 0.004);
        p.put("ч", 0.02);
        p.put("ш", 0.012);
        p.put("щ", 0.003);
        p.put("ы", 0.01);
        p.put("э", 0.004);
        p.put("ю", 0.004);
        p.put("я", 0.013);

        p.put("ау", 0.046);
        p.put("иу", 0.015);
        p.put("еу", 0.039);
        p.put("оу", 0.037);
        p.put("уу", 0.012);
        p.put("яу", 0.011);
        p.put("юу", 0.002);
        p.put("эу", 0.001);
        p.put("ыу", 0.006);

    }


    // веселый - грустный

    static HashMap<String, Double> hs = new HashMap<>();

    static{
        hs.put("а", 2.0);
        hs.put("б", 2.6);
        hs.put("бм", 3.6);
        hs.put("в", 3.0);
        hs.put("г", 2.8);
        hs.put("гм", 2.9);
        hs.put("д", 2.4);
        hs.put("дм", 2.7);
        hs.put("е", 1.7);
        hs.put("ё", 2.1);
        hs.put("ж", 4.0);
        hs.put("з", 3.2);
        hs.put("зм", 3.2);
        hs.put("и", 2.0);
        hs.put("й", 2.8);
        hs.put("к", 3.4);
        hs.put("км", 3.4);
        hs.put("л", 2.3);
        hs.put("лм", 1.9);
        hs.put("м", 3.5);
        hs.put("мм", 3.3);
        hs.put("н", 2.6);
        hs.put("нм", 2.4);
        hs.put("о", 1.9);
        hs.put("п", 3.5);
        hs.put("пм", 3.6);
        hs.put("р", 3.0);
        hs.put("рм", 3.6);
        hs.put("с", 3.9);
        hs.put("см", 3.2);
        hs.put("т", 3.2);
        hs.put("тм", 2.9);
        hs.put("у", 2.9);
        hs.put("ф", 3.9);
        hs.put("фм", 3.4);
        hs.put("х", 3.9);
        hs.put("хм", 4.1);
        hs.put("ц", 3.1);
        hs.put("ч", 3.0);
        hs.put("ш", 3.5);
        hs.put("щ", 3.7);
        hs.put("ы", 3.7);
        hs.put("э", 2.1);
        hs.put("ю", 1.6);
        hs.put("я", 1.9);
    }

    // хороший-плохой

    static HashMap<String, Double> gb = new HashMap<>();

    static{
        gb.put("а", 1.5);
        gb.put("б", 2.4);
        gb.put("бм", 3.0);
        gb.put("в", 2.9);
        gb.put("г", 3.2);
        gb.put("гм", 3.6);
        gb.put("д", 2.4);
        gb.put("дм", 2.8);
        gb.put("е", 1.9);
        gb.put("ё", 2.3);
        gb.put("ж", 3.7);
        gb.put("з", 3.1);
        gb.put("зм", 3.4);
        gb.put("и", 1.7);
        gb.put("й", 2.9);
        gb.put("к", 3.0);
        gb.put("км", 3.7);
        gb.put("л", 2.1);
        gb.put("лм", 1.8);
        gb.put("м", 2.5);
        gb.put("мм", 3.1);
        gb.put("н", 2.4);
        gb.put("нм", 2.9);
        gb.put("о", 1.6);
        gb.put("п", 3.5);
        gb.put("пм", 3.6);
        gb.put("р", 2.9);
        gb.put("рм", 2.6);
        gb.put("с", 3.6);
        gb.put("см", 3.8);
        gb.put("т", 3.0);
        gb.put("тм", 3.3);
        gb.put("у", 3.0);
        gb.put("ф", 4.0);
        gb.put("фм", 4.2);
        gb.put("х", 4.1);
        gb.put("хм", 4.3);
        gb.put("ц", 4.0);
        gb.put("ч", 3.0);
        gb.put("ш", 4.0);
        gb.put("щ", 3.5);
        gb.put("ы", 3.6);
        gb.put("э", 2.0);
        gb.put("ю", 1.8);
        gb.put("я", 1.8);
    }

    // нежный-грубый

    static HashMap<String, Double> ng = new HashMap<>();

    static{
        ng.put("а", 2.8);
        ng.put("б", 4.2);
        ng.put("бм", 2.6);
        ng.put("в", 3.6);
        ng.put("г", 3.8);
        ng.put("гм", 2.6);
        ng.put("д", 4.4);
        ng.put("дм", 2.6);
        ng.put("е", 2.2);
        ng.put("ё", 2.4);
        ng.put("ж", 4.0);
        ng.put("з", 3.5);
        ng.put("зм", 2.4);
        ng.put("и", 1.8);
        ng.put("й", 3.1);
        ng.put("к", 3.6);
        ng.put("км", 2.4);
        ng.put("л", 3.3);
        ng.put("лм", 2.0);
        ng.put("м", 3.2);
        ng.put("мм", 2.1);
        ng.put("н", 3.6);
        ng.put("нм", 2.0);
        ng.put("о", 3.2);
        ng.put("п", 3.4);
        ng.put("пм", 2.4);
        ng.put("р", 4.6);
        ng.put("рм", 3.2);
        ng.put("с", 3.2);
        ng.put("см", 2.0);
        ng.put("т", 3.6);
        ng.put("тм", 2.2);
        ng.put("у", 3.0);
        ng.put("ф", 3.5);
        ng.put("фм", 2.4);
        ng.put("х", 3.6);
        ng.put("хм", 2.5);
        ng.put("ц", 3.2);
        ng.put("ч", 3.4);
        ng.put("ш", 3.2);
        ng.put("щ", 2.7);
        ng.put("ы", 3.8);
        ng.put("э", 3.4);
        ng.put("ю", 1.9);
        ng.put("я", 2.7);
    }

    // добрый - злой

    static HashMap<String, Double> ke = new HashMap<>();

    static{
        ke.put("а", 1.8);
        ke.put("б", 4.8);
        ke.put("бм", 3.9);
        ke.put("в", 3.8);
        ke.put("г", 3.9);
        ke.put("гм", 3.9);
        ke.put("д", 3.5);
        ke.put("дм", 4.3);
        ke.put("е", 2.5);
        ke.put("ё", 2.2);
        ke.put("ж", 3.0);
        ke.put("з", 2.9);
        ke.put("зм", 3.4);
        ke.put("и", 2.5);
        ke.put("й", 4.1);
        ke.put("к", 4.4);
        ke.put("км", 4.4);
        ke.put("л", 3.4);
        ke.put("лм", 3.3);
        ke.put("м", 3.3);
        ke.put("мм", 4.0);
        ke.put("н", 3.3);
        ke.put("нм", 3.8);
        ke.put("о", 1.7);
        ke.put("п", 4.4);
        ke.put("пм", 4.6);
        ke.put("р", 2.5);
        ke.put("рм", 3.5);
        ke.put("с", 3.7);
        ke.put("см", 3.6);
        ke.put("т", 4.4);
        ke.put("тм", 4.3);
        ke.put("у", 1.9);
        ke.put("ф", 3.8);
        ke.put("фм", 3.9);
        ke.put("х", 3.8);
        ke.put("хм", 4.0);
        ke.put("ц", 4.0);
        ke.put("ч", 4.1);
        ke.put("ш", 3.3);
        ke.put("щ", 2.7);
        ke.put("ы", 2.2);
        ke.put("э", 2.2);
        ke.put("ю", 2.6);
        ke.put("я", 3.0);
    }

    // светлое - темное

    static HashMap<String, Double> ld = new HashMap<>();

    static{
        ld.put("а", 2.2);
        ld.put("б", 3.2);
        ld.put("бм", 2.6);
        ld.put("в", 3.9);
        ld.put("г", 3.3);
        ld.put("гм", 2.9);
        ld.put("д", 3.2);
        ld.put("дм", 2.2);
        ld.put("е", 1.9);
        ld.put("ё", 2.5);
        ld.put("ж", 3.8);
        ld.put("з", 2.5);
        ld.put("зм", 2.4);
        ld.put("и", 2.0);
        ld.put("й", 2.6);
        ld.put("к", 3.6);
        ld.put("км", 3.2);
        ld.put("л", 3.1);
        ld.put("лм", 2.0);
        ld.put("м", 3.3);
        ld.put("мм", 2.6);
        ld.put("н", 3.1);
        ld.put("нм", 2.8);
        ld.put("о", 2.2);
        ld.put("п", 4.0);
        ld.put("пм", 3.3);
        ld.put("р", 3.8);
        ld.put("рм", 2.9);
        ld.put("с", 2.5);
        ld.put("см", 2.4);
        ld.put("т", 4.0);
        ld.put("тм", 3.6);
        ld.put("у", 3.6);
        ld.put("ф", 4.0);
        ld.put("фм", 3.7);
        ld.put("х", 4.4);
        ld.put("хм", 3.5);
        ld.put("ц", 3.5);
        ld.put("ч", 3.3);
        ld.put("ш", 4.3);
        ld.put("щ", 3.8);
        ld.put("ы", 3.8);
        ld.put("э", 2.5);
        ld.put("ю", 2.3);
        ld.put("я", 1.9);
    }

    // красивое - отталкивающее

    static HashMap<String, Double> bu = new HashMap<>();

    static{
        bu.put("а", 1.6);
        bu.put("б", 3.2);
        bu.put("бм", 3.2);
        bu.put("в", 3.5);
        bu.put("г", 3.6);
        bu.put("гм", 3.2);
        bu.put("д", 3.4);
        bu.put("дм", 3.0);
        bu.put("е", 2.4);
        bu.put("ё", 2.5);
        bu.put("ж", 4.5);
        bu.put("з", 4.0);
        bu.put("зм", 3.8);
        bu.put("и", 2.0);
        bu.put("й", 3.4);
        bu.put("к", 4.2);
        bu.put("км", 3.9);
        bu.put("л", 2.6);
        bu.put("лм", 2.2);
        bu.put("м", 2.8);
        bu.put("мм", 2.6);
        bu.put("н", 2.8);
        bu.put("нм", 2.8);
        bu.put("о", 1.5);
        bu.put("п", 4.0);
        bu.put("пм", 3.5);
        bu.put("р", 4.0);
        bu.put("рм", 4.0);
        bu.put("с", 3.6);
        bu.put("см", 3.5);
        bu.put("т", 3.8);
        bu.put("тм", 3.8);
        bu.put("у", 1.8);
        bu.put("ф", 4.4);
        bu.put("фм", 3.8);
        bu.put("х", 4.2);
        bu.put("хм", 3.8);
        bu.put("ц", 3.9);
        bu.put("ч", 4.6);
        bu.put("ш", 4.1);
        bu.put("щ", 4.4);
        bu.put("ы", 2.5);
        bu.put("э", 2.2);
        bu.put("ю", 2.4);
        bu.put("я", 2.2);
    }

    // безопасное - устрашающее

    static HashMap<String, Double> ss = new HashMap<>();

    static{
        ss.put("а", 2.6);
        ss.put("б", 3.2);
        ss.put("бм", 2.3);
        ss.put("в", 3.3);
        ss.put("г", 3.2);
        ss.put("гм", 2.3);
        ss.put("д", 2.8);
        ss.put("дм", 2.8);
        ss.put("е", 2.2);
        ss.put("ё", 2.1);
        ss.put("ж", 4.8);
        ss.put("з", 3.6);
        ss.put("зм", 2.9);
        ss.put("и", 2.1);
        ss.put("й", 2.4);
        ss.put("к", 3.8);
        ss.put("км", 3.0);
        ss.put("л", 2.5);
        ss.put("лм", 1.7);
        ss.put("м", 2.4);
        ss.put("мм", 2.2);
        ss.put("н", 2.8);
        ss.put("нм", 2.2);
        ss.put("о", 2.8);
        ss.put("п", 3.8);
        ss.put("пм", 2.4);
        ss.put("р", 4.6);
        ss.put("рм", 3.4);
        ss.put("с", 3.2);
        ss.put("см", 2.8);
        ss.put("т", 3.4);
        ss.put("тм", 3.2);
        ss.put("у", 3.6);
        ss.put("ф", 4.4);
        ss.put("фм", 3.2);
        ss.put("х", 4.1);
        ss.put("хм", 3.8);
        ss.put("ц", 3.3);
        ss.put("ч", 3.4);
        ss.put("ш", 4.2);
        ss.put("щ", 4.3);
        ss.put("ы", 3.5);
        ss.put("э", 2.2);
        ss.put("ю", 2.0);
        ss.put("я", 2.1);
    }

}
