public static void initialization() {
        String browserName = prop.getProperty("browser");
        logger.info(">> Initializing Browser: " + browserName);

        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            
            // 1. Basic Preferences
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            
            // 2. Performance Options
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            
            // 3. HEADLESS MODE CONFIGURATION
            options.addArguments("--headless=new"); 
            
            // *** CRITICAL FIX: Force Desktop Resolution ***
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized"); // Optional backup
            
            driver = new ChromeDriver(options);
            logger.info(">> Chrome Driver launched in HEADLESS mode (1920x1080).");
        }
        
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(prop.getProperty("url"));
        logger.info(">> Navigated to Application URL: " + prop.getProperty("url"));
    }