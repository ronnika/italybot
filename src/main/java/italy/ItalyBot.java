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
        TelegramBot bot = new TelegramBot("token");
        findWindow(bot);
    }

    static void  autoReg() {
        open("https://italy-vms.ru/autoform/?t=thnugte3he-2999325-y8kub0xkgoqngi4nn709gxzvdnv9f2a2hfgqoh3e7u1g3&lang=ru");
        $("button[value='Назначить другую дату").click();
    }
    static void findWindow(TelegramBot bot) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date dateCurr = sdf.parse("22.08.2022");
        Date dateStart = sdf.parse("11.08.2022");
        Date date;
        boolean isDateSent = false;
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
                open("https://italy-vms.ru/autoform/?t=thnugte3he-2999325-y8kub0xkgoqngi4nn709gxzvdnv9f2a2hfgqoh3e7u1g3&lang=ru");
                $("[value='Назначить другую дату'").click();
                $("#appdate").setValue(sdf.format(date));
                sleep(500);
                $("#apptime").selectOptionContainingText("00");
                $("[value='Назначить другую дату ▷'").click();
                break;
            }
        }
    }
}
