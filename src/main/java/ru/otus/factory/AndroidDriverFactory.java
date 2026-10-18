package ru.otus.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import ru.otus.emulator.Emulator;
import ru.otus.emulator.EmulatorProvider;

import java.net.URL;
import java.time.Duration;

@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class AndroidDriverFactory {

  private final EmulatorProvider emulatorProvider;
  private final Capabilities capabilities;

  @SneakyThrows
  public WebDriver create() {
    Emulator emulator = emulatorProvider.takeAndGet();
    String androidUrl = Optional.ofNullable(System.getenv("ANDROID_URL")).orElse("http://android-1-appium.local:%d/".formatted(emulator.getPort()));
    AndroidDriver driver =
        new AndroidDriver(
            new URL(androidUrl),
            capabilities);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    return driver;
  }

  public void quit(WebDriver driver) {
    emulatorProvider.putBack();
    driver.quit();
  }
}
