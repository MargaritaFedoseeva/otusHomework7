package ru.otus.components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import lombok.AllArgsConstructor;
import ru.otus.pageobject.AbsPageObject;

@AllArgsConstructor
public class AbsComponent <T extends AbsComponent<T>> extends AbsPageObject {
    protected final SelenideElement root;

    public T shouldBe(WebElementCondition... conditions){
        root.shouldBe(conditions);
        return (T) this;
    }
}
