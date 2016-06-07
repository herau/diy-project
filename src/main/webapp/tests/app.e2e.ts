describe('App', function() {

  beforeEach(function() {
    browser.get('/');
  });


  it('should have a title', function() {
    let subject = browser.getTitle();
    let result  = 'DIY';
    expect(subject).toEqual(result);
  });

  it('should have a toolbar', function() {
    let subject = element(by.css('app md-toolbar')).isPresent();
    let result  = true;
    expect(subject).toEqual(result);
  });

});
