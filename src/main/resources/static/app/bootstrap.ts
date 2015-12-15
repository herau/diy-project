/*
 * Angular 2 providers
 */

import {bootstrap} from 'angular2/bootstrap';
import {provide} from 'angular2/core';
import {FORM_PROVIDERS} from 'angular2/common';
import {ELEMENT_PROBE_PROVIDERS} from 'angular2/platform/common_dom';
import {ROUTER_PROVIDERS, LocationStrategy, PathLocationStrategy, APP_BASE_HREF} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';

/*
 * App Component
 * our top level component that holds all of our components
 */
import {App} from './components/app';

/*
 * Bootstrap our Angular app with a top level component `App` and inject
 * our Services and Providers into Angular's dependency injection
 */

export function main () {
  return bootstrap(App, [
    // These are dependencies.
    ROUTER_PROVIDERS,
    FORM_PROVIDERS,
    HTTP_PROVIDERS,
    ELEMENT_PROBE_PROVIDERS,
    provide(APP_BASE_HREF, {useValue: '/'}),
    provide(LocationStrategy, {useClass: PathLocationStrategy})
  ]).catch(err => console.error(err));
}

document.addEventListener('DOMContentLoaded', main);
