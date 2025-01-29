import { browser, by, element } from 'protractor';

describe('AppComponent', () => {
  beforeEach(() => {
    browser.get('/');
  });

  it('debe mostrar el título correcto', async () => {
    const title = await element(by.css('app-root h1')).getText();
    expect(title).toEqual('Bienvenido a mi aplicación Angular');
  });

  it('debe navegar a la página de "Acerca de"', async () => {
    const aboutLink = element(by.css('a[routerLink="/about"]'));
    aboutLink.click();
    const aboutTitle = await element(by.css('app-about h2')).getText();
    expect(aboutTitle).toEqual('Acerca de');
  });
});