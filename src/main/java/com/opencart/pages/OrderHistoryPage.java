package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage extends BasePage {

    @FindBy(xpath = "//h1[text()='Order History']")
    WebElement pageHeader;

    // Locator for the first row in the order table (The latest order)
    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr[1]/td[1]")
    WebElement latestOrderId;
    
    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr[1]/td[4]")
    WebElement latestOrderStatus;

    public OrderHistoryPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return pageHeader.getText();
    }

    public String getLatestOrderId() {
        return latestOrderId.getText();
    }
    
    public String getLatestOrderStatus() {
        return latestOrderStatus.getText();
    }
}