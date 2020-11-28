package com.example.digitalrush;


import java.net.CookieHandler;
import java.util.HashMap;

public class Text_gifs {
    static HashMap<Integer, String> texts = new HashMap<Integer, String>() {{
        put(R.raw.content, "3 подхода по 10 раз в комфортном темпе");
        put(R.raw.content1, "2 подхода по 15 раз в быстром темпе");
        put(R.raw.content2, "5 подходов по 5 раз на каждую ногу.");
        put(R.raw.content3, "6 подходов по 10 раз в комфортном темпе");
        put(R.raw.content4, "2 подхода по 10 раз в нормальном темпе");
        put(R.raw.content5, "2 подхода по 10 раз в комфортном темпе");
        put(R.raw.content6, "5 подходов по 5 раз на каждую ногу");
        put(R.raw.content7, "2 подход по 10 раз, по одному подходу на каждую ногу");
        put(R.raw.content8, "3 подхода по 10 раз в комфортном темпе");
    }};

    static HashMap<Integer, String> texts_rec = new HashMap<Integer, String>() {{
        put(1, "Может поспим сегодня по дольше?");
        put(2, "Остановись! Сегодня ты сделал все что мог. Отдохни");
        put(3, "Пошли в парк? Полгуляем =)");
    }};

    public static String getText(int id) {
        return texts.get(id);
    }
    public static String getTextRec(int id) {
        return texts_rec.get(id);
    }

}
