import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from './notification/notification.component';
import { NotificationModalComponent } from './notification-modal/notification-modal.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MenuModule } from 'primeng/menu';
import {MenubarModule} from 'primeng/menubar';
import { RouterModule } from '@angular/router';
import {BadgeModule} from 'primeng/badge';



@NgModule({
  declarations: [
    NotificationComponent,
    NotificationModalComponent,
    HeaderComponent,
    FooterComponent,
    PageNotFoundComponent,
  ],
  exports: [
    NotificationComponent,
    NotificationModalComponent,
    HeaderComponent,
    FooterComponent,
    PageNotFoundComponent
  ],
  imports: [
    CommonModule, MenuModule,MenubarModule, BadgeModule, RouterModule.forChild([])
  ]
})
export class MainModule {
  constructor( @Optional() @SkipSelf() parentModule: MainModule) {
    if (parentModule) {
      const msg = `MainModule has already been loaded.
        Import MainModule once, only, in the root AppModule.`;
      throw new Error(msg);
    }
  }
}
