package italy;

import com.codeborne.selenide.Configuration;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.openqa.selenium.WindowType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class ItalyBot {

    public static void main(String[] args) throws ParseException {
        TelegramBot bot = new TelegramBot(TOKEN);
        findWindow(bot);
    }

    static void findWindow(TelegramBot bot) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date dateCurr = sdf.parse("15.08.2022");
        Date dateStart = sdf.parse("11.08.2022");
        Date date;
        Configuration.holdBrowserOpen = true;
        while (true) {
            open("https://italy-vms.ru/autoform/?lang=ru");
            $("#center").selectOptionByValue("1");
            sleep(1000);
            date = sdf.parse($("#free_date").getText());
            sleep(1000);
            if (date.compareTo(dateCurr) < 0 && date.compareTo(dateStart) > 0) {
                sleep(500);
                SendMessage request = new SendMessage(-675415151, "Oкошко на " + sdf.format(date));
                bot.execute(request);
            }
        }
    }
}
