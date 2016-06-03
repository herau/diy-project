describe('App', function() {

  beforeEach(function() {
    browser.get('/');
  });


  it('should have a title', function() {
    let subject = browser.getTitle();
    let result  = 'DIY';
    expect(subject).toEqual(result);
  });

  it('should have <header>', function() {
    let subject = element(by.deepCss('app /deep/ header')).isPresent();
    let result  = true;
    expect(subject).toEqual(result);
  });

  it('should have <main>', function() {
    let subject = element(by.deepCss('app /deep/ main')).isPresent();
    let result  = true;
    expect(subject).toEqual(result);
  });

  it('should have <footer>', function() {
    let subject = element(by.deepCss('app /deep/ footer')).getText();
    let result  = 'footer';
    expect(subject).toEqual(result);
  });

});
