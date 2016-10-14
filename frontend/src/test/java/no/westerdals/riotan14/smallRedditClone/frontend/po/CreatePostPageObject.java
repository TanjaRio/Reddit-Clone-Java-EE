package no.westerdals.riotan14.smallRedditClone.frontend.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class CreatePostPageObject extends PageObject {

    public CreatePostPageObject(WebDriver driver) {
        super(driver);

        assertEquals("Create New Post", driver.getTitle());
    }

    public boolean isOnPage(){
        return driver.getTitle().equals("Create New Post");
    }

    public HomePageObject createPost(String author, String content){
        setText("createEventForm:author",author);
        setText("createEventForm:content",content);


        driver.findElement(By.id("createPostForm:createButton")).click();
        waitForPageToLoad();

        if(isOnPage()){
            return null;
        } else {
            return new HomePageObject(driver);
        }
    }

}
