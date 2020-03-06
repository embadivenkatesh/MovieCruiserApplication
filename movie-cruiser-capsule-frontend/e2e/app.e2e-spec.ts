import { AppPage } from './app.po';
import { browser, by,element } from 'protractor';
import {protractor} from 'protractor/built/ptor';

describe('MovieCruiserCapsuleFrontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display corerct title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiserCapsuleFrontend');
  });

  it('should redirect to login page after open the chrome', () => {    
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should redirect to register page after click on register', () => { 
    browser.element(by.css(".register-button")).click(); 
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should successfully register user', () => { 
    browser.element(by.id('firstName')).sendKeys('super user firstname');
    browser.element(by.id('lastName')).sendKeys('super lastName');
    browser.element(by.id('userId')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.register-user')).click() 
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  
  it('should successfully login and navigate to popular movies', () => { 
    browser.element(by.id('userId')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    
    browser.element(by.css(".login-user")).click();
    expect(browser.getCurrentUrl()).toContain('popular');
  });

  it('should successfully search movies', () => { 

    browser.element(by.css(".search-button")).click();
    expect(browser.getCurrentUrl()).toContain('/search');

    browser.element(by.id("search-input-button")).sendKeys("Super");
    browser.element(by.id("search-input-button")).sendKeys(protractor.Key.ENTER);
    
    const searchedItems = element.all(by.css(".movie-title")) 
    expect(searchedItems.count()).toBe(20);
    for(let i=0;i<1;i++){
    expect(searchedItems.get(i).getText()).toContain('Super');
    }
  });

  it('should successfully added movies to watch list', () => { 

    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);

    const searchedItems = element.all(by.css(".movie-thumbnail")) 
    expect(searchedItems.count()).toBe(20);
    searchedItems.get(0).click();
    browser.element(by.css('.addButton')).click();

    
  });
});
