import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { FooterComponent } from './components/footer/footer.component';
import { TrainComponent } from './components/train/train.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { PnrstatusComponent } from './components/pnrstatus/pnrstatus.component';
import { TrainlistComponent } from './components/trainlist/trainlist.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';
import { AddTrainComponent } from './components/add-train/add-train.component';
import { UpdateTrainComponent } from './components/update-train/update-train.component';
import { DeleteTrainComponent } from './components/delete-train/delete-train.component';
import { JwtInterceptor } from './util/jwt.interceptor';
import { BookHistoryComponent } from './components/book-history/book-history.component';
import { WalletComponent } from './components/wallet/wallet.component';
import { ErrorInterceptor } from './util/error.interceptor';
@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    NavbarComponent,
    RegisterFormComponent,
    FooterComponent,
    TrainComponent,
    HomeComponent,
    PnrstatusComponent,
    TrainlistComponent,
    BookingFormComponent,
    AdminPanelComponent,
    AddTrainComponent,
    UpdateTrainComponent,
    DeleteTrainComponent,
    BookHistoryComponent,
    WalletComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }, { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },],
  bootstrap: [AppComponent]
})
export class AppModule { }
