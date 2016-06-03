// Import necessary wrappers for Jasmine
import { provide } from '@angular/core';
import { beforeEachProviders, describe, expect, inject, it } from '@angular/core/testing';
import { ROUTER_PROVIDERS, LocationStrategy, PathLocationStrategy, APP_BASE_HREF } from '@angular/router';

// Load the implementations that should be tested
import { App } from '../app/components/app';

describe('App', () => {

  // Provide our implementations or mocks to the dependency injector
  beforeEachProviders(() => [
    App,
    // These providers are needed to be injected as dependencies for `Location` object, itself a dependency for the `App` component.
    // Normally, this is performed by `bootstrap.ts` file.
    ROUTER_PROVIDERS,
    provide(APP_BASE_HREF, {useValue: '/'}),
    provide(LocationStrategy, {useClass: PathLocationStrategy})
  ]);

  it('should have a title', inject([App], (app) => {
    expect(app.name).toEqual('DIY');
  }));

});
