package com.opencart.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.opencart.base.BasePage;

public class ComparisonPage extends BasePage {

    @FindBy(xpath = "//h1[text()='Product Comparison']")
    WebElement pageHeader;

    @FindBy(xpath = "//table[@class='table table-bordered']//tbody/tr[1]/td")
    List<WebElement> productNames; // This gets all product names in the table row

    public ComparisonPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return pageHeader.getText();
    }

    public boolean isProductInComparison(String productName) {
        // Iterate through all cells in the first row to find the product name
        for (WebElement col : productNames) {
            if (col.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }
}