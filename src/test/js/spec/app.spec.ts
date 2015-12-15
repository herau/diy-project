// Import necessary wrappers for Jasmine
import {
  beforeEachProviders,
  describe,
  expect,
  inject,
  it,
  injectAsync
} from 'angular2/testing';
import { provide } from 'angular2/core';
import { BaseRequestOptions, Http } from 'angular2/http';
import  { MockBackend } from 'angular2/http/testing';

// Load the implementations that should be tested
import { App } from '../../../main/resources/static/app/components/app';

describe('App', () => {
  // provide our implementations or mocks to the dependency injector
  beforeEachProviders(() => [
    App,
    BaseRequestOptions,
    MockBackend,
    provide(Http, {
      useFactory: function(backend, defaultOptions) {
        return new Http(backend, defaultOptions);
      },
      deps: [MockBackend, BaseRequestOptions]})
  ]);

  it('should have a title', inject([App], (app) => {
    expect(app.name).toEqual('DIY');
  }));

});
