import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

import { LoginService } from './services/log.service';

@Injectable({
  providedIn: 'root'
})
export class SesionGuardGuard implements CanActivate {
  constructor(private auth: LoginService, private router: Router){};

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      // if(!this.auth.isLoggedIn()){
      //   return this.router.navigate(['/login']).then(() => false);
      // }
    return true;
  }

}
