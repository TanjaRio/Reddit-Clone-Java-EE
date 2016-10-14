package no.westerdals.riotan14.smallRedditClone.frontend.po;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */
public class CreateCommentPageObject extends PageObject {
    public CreateCommentPageObject(WebDriver driver) {
        super(driver);

        assertEquals("Create New Comment", driver.getTitle());
    }

    public boolean isOnPage(){
        return driver.getTitle().equals("Create New Post");
    }


}
