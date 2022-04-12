package helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class CookieHelper {

    public static void saveCookie(WebDriver driver, String cookieName) throws IOException {
        File file = new File("Cookies.data");
        file.delete();
        file.createNewFile();
        try(FileWriter fileWrite = new FileWriter(file)){
            fileWrite.write(driver.manage().getCookieNamed(cookieName).getValue());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void readCookie(WebDriver driver, String cookieName) {
        String key = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("Cookies.data"))) {
            key = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(cookieName, key);
        driver.manage().addCookie(cookie);
    }
}

