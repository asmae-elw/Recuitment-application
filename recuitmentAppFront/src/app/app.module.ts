import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {ProfileComponent} from './profile/profile.component';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {BoardModeratorComponent} from './board-moderator/board-moderator.component';
import {BoardUserComponent} from './board-user/board-user.component';

// with authentification
import {AppRoutingModule} from './app-routing.module';
import {authInterceptorProviders} from 'src/_helpers/auth.interceptor';
import {OffreComponent} from './offre/offre.component';
import {PostulerComponent} from './postuler/postuler.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    OffreComponent,
    PostulerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, FormsModule
  ],

  // providers: [OffreService],
  // j'ai remplacer offreService par auth... with authentification :)
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
