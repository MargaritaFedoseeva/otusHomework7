package ru.otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.database.TestDataManager;
import ru.otus.extension.AndroidExtension;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

@ExtendWith(AndroidExtension.class)
public class WishlistEditTest {

  @Inject
  private LoginPage loginPage;
  @Inject
  private MyWishlistsPage myWishlistsPage;
  @Inject
  private TestDataManager testDataManager;

  @Test
  void editWishlist() {
    String login = "auto_margo";
    String wishlistTitle = "Новый год";
    String wishlistDescription = "С Новым Годом!";
    String newWishlistDescription = "Счастья, любви в Новом 2027 году!";

    testDataManager.prepareWishlistDescription(login, wishlistDescription);
    loginPage.login(login, "12345678");
    myWishlistsPage
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(1, wishlistTitle)
        .assertWishlistSubtitle(1, wishlistDescription)
        .tapEditWishlist(1)
        .editWishlistDescription(newWishlistDescription)
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(1, wishlistTitle)
        .assertWishlistSubtitle(1, newWishlistDescription);
  }
}
