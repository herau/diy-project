/// <reference path="../typings/_custom.d.ts" />

// Angular 2 core
import {bootstrap, bind} from 'angular2/angular2';

/*
 * Angular 2 bindings
 */

import {FORM_BINDINGS, ELEMENT_PROBE_BINDINGS} from 'angular2/angular2';
import {routerBindings, LocationStrategy, PathLocationStrategy} from 'angular2/router';
import {HTTP_BINDINGS} from 'angular2/http';

/*
 * App Component
 * our top level component that holds all of our components
 */
import {App} from './components/app';

/*
 * Bootstrap our Angular app with a top level component `App` and inject
 * our services/bindings into Angular's dependency injection
 */
bootstrap(App, [
    // These are dependencies.
    routerBindings(App),
    FORM_BINDINGS,
    HTTP_BINDINGS,
    ELEMENT_PROBE_BINDINGS,
    bind(LocationStrategy).toClass(PathLocationStrategy)
]);
