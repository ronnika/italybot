package italy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class ItalyBot {

    public static void main(String[] args) throws ParseException {
        TelegramBot bot = new TelegramBot("5510060298:AAHkpXOLcnEbaw4-92DnE6JluoNwdbEUiyw");
        findWindow(bot);
    }
    static void findWindow(TelegramBot bot) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date dateCurr = sdf.parse("19.09.2022");
        Date date;
        Configuration.holdBrowserOpen = true;
        open("https://italy-vms.ru/autoform/?lang=ru");
        while (true) {
            Selenide.refresh();
            $("#center").selectOptionByValue("1");
            sleep(1000);
            date = sdf.parse($("#free_date").getText());
            sleep(1000);
            if (date.compareTo(dateCurr) < 0) {
                sleep(500);
                SendMessage request = new SendMessage(-675415151, "Oкошко на " + sdf.format(date));
                bot.execute(request);
            }
        }
    }
}
