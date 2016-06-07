// Import necessary wrappers for Jasmine
import { provide } from '@angular/core';
import { beforeEachProviders, describe, expect, inject, it } from '@angular/core/testing';
import { PathLocationStrategy, LocationStrategy, APP_BASE_HREF } from '@angular/common';
import { ROUTER_PROVIDERS } from '@angular/router';
import { HTTP_PROVIDERS } from '@angular/http';

// Load the implementations that should be tested
import { App } from '../app/components/app';
import { UserService } from "../app/services/user";

describe('App', () => {

  // Provide our implementations or mocks to the dependency injector
  beforeEachProviders(() => [
    App,
    // These providers are needed to be injected as dependencies for `Location` object, itself a dependency for the `App` component.
    // Normally, this is performed by `bootstrap.ts` file.
    ROUTER_PROVIDERS,
    HTTP_PROVIDERS,
    provide(APP_BASE_HREF, {useValue: '/'}),
    provide(LocationStrategy, {useClass: PathLocationStrategy}),
    provide(UserService, {useClass: UserService})
  ]);

  it('should have a title', inject([App], (app) => {
    expect(app.name).toEqual('DIY');
  }));

});
