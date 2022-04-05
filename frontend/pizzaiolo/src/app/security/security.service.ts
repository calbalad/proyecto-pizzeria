import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild, UrlTree, CanLoad, Route, UrlSegment, Data } from '@angular/router';
import { HttpClient, HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpContextToken } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);

@Injectable({providedIn: 'root'})
export class AuthService {
  private isAuth = false;
  private authToken: string = '';
  private name = '';
  private roles: Array<string> = []

  constructor() {
    if (localStorage && localStorage['access_token']) {
      const rslt = JSON.parse(localStorage['access_token']);
      this.isAuth = rslt.isAuth;
      this.authToken = rslt.authToken;
      this.name = rslt.name;
      this.roles = rslt.roles;
    }
  }

  get AuthorizationHeader() { return this.authToken;  }
  get isAutenticated() { return this.isAuth; }
  get Name() { return this.name; }
  get Roles() { return Object.assign([], this.roles); }

  login(authToken: string, name: string, roles: Array<string>) {
    this.isAuth = true;
    this.authToken = authToken;
    this.name = name;
    this.roles = roles;
    if (localStorage) {
      localStorage['access_token'] = JSON.stringify({ isAuth: this.isAuth, authToken, name, roles });
    }
  }
  isInRoles(...rolesArgs: Array<string>) {
    if(this.isAutenticated && this.roles.length > 0 && rolesArgs.length > 0)
      for(let role of rolesArgs)
        if(this.roles.includes(role)) return true;
    return false;
  }
  logout() {
    this.isAuth = false;
    this.authToken = '';
    this.name = '';
    this.roles = [];
    if (localStorage) {
      localStorage.removeItem('access_token');
    }
  }
}


// { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true, },
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   /*  if (!(req.context.get(AUTH_REQUIRED) || req.withCredentials)) {
      return next.handle(req);
    } */
    const authReq = req.clone(
      { headers: req.headers.set('Authorization', 'Bearer '+ this.auth.AuthorizationHeader) }
    );
    return next.handle(authReq);
  }
}

