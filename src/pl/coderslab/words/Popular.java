package src.pl.coderslab.words;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Popular {

    public static void main(String[] args) {

        String[] filterKey = {"dziś", "serie", "coraz", "oraz"};
        List<String> titles = new ArrayList<>();

        String urlSpider = "https://www.spidersweb.pl/";
        String cssQuerySpider = "span.postlink-inner";
        String urlOnet = "http://www.onet.pl/";
        String cssQueryOnet = "span.title";

        try {
            getTitles(urlSpider, cssQuerySpider, titles);
            getTitles(urlOnet, cssQueryOnet, titles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> words = getWords(titles);
        saveWords(words);
        saveFilteredWords(filterKey);


    }

    //Pobieranie tytułów i zapis to listy titles
    static List<String> getTitles(String url, String cssQuery, List<String> titles) throws IOException {
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements links = document.select(cssQuery);
        for (Element elem : links) {
            titles.add(elem.text());
        }
        return titles;
    }

    //Wczytywanie listy tytulów titles i zapis pojedynczych elementów większych niż 3 znaki do listy słów words
    static List<String> getWords(List<String> titles) {
        List<String> words = new ArrayList<>();
        for (String element : titles) {
            String[] line = element.split(" ");
            for (String word : line) {
                if (word.length() > 3) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    //Zapis listy słów words do pliku popular_words.txt
    static void saveWords(List<String> words) {
        try {
            FileWriter writer = new FileWriter("popular_words.txt", true);
            for (String word : words) {
                writer.append(word).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Wczytanie pliku popular_words.txt i stworzenie pliku filtered_popular_words.txt z pominięciem słów filtrowanych z tabeli Stringów filterKey
    static void saveFilteredWords(String[] filterKey) {
        try {
            Path target = Paths.get("popular_words.txt");
            FileWriter writer = new FileWriter("filtered_popular_words.txt", true);
            for (String word : Files.readAllLines(target)) {
                if (!containsString(word, filterKey)) {
                    writer.append(word).append("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sprawdzenie czy String znajduje się w tabeli stringów
    static boolean containsString(String str, String[] tab) {
        int counter = 0;
        for (String filterWord : tab) {
            if (str.equals(filterWord)) {
                counter++;
            }
        }
        return counter > 0;
    }
}
