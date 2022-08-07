package italy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import static com.codeborne.selenide.Selenide.*;

public class ItalyBot {

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot("5510060298:AAHkpXOLcnEbaw4-92DnE6JluoNwdbEUiyw");
        findWindow(bot);
    }
    static void findWindow(TelegramBot bot) {
        String date;
        Configuration.holdBrowserOpen = true;
        open("https://italy-vms.ru/autoform/?lang=ru");
        while (true) {
            Selenide.refresh();
            $("#center").selectOptionByValue("1");
            sleep(1000);
            date = $("#free_date").getText();
            sleep(1000);
            if (date.contains("08.2022") || date.contains("09.2022")) {
                sleep(500);
                SendMessage request = new SendMessage(-675415151, "Oкошко на " + date);
                SendResponse sendResponse = bot.execute(request);
                break;
            }
        }
    }
}
