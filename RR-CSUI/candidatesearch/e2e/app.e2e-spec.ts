import { CandidatesearchPage } from './app.po';

describe('candidatesearch App', function() {
  let page: CandidatesearchPage;

  beforeEach(() => {
    page = new CandidatesearchPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
