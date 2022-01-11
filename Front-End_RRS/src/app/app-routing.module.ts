import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookHistoryComponent } from './components/book-history/book-history.component';
import { AddTrainComponent } from './components/add-train/add-train.component';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { DeleteTrainComponent } from './components/delete-train/delete-train.component';
import { HomeComponent } from './components/home/home.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PnrstatusComponent } from './components/pnrstatus/pnrstatus.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { TrainComponent } from './components/train/train.component';
import { TrainlistComponent } from './components/trainlist/trainlist.component';
import { UpdateTrainComponent } from './components/update-train/update-train.component';
import { WalletComponent } from './components/wallet/wallet.component';
import { PrintTicketComponent } from './print-ticket/print-ticket.component';

const routes: Routes = [
  {path:'login', component:LoginFormComponent},
  {path:'register', component:RegisterFormComponent},
  {path:'trains', component:TrainComponent},
  {path:'home', component:HomeComponent},
  {path:'pnr', component:PnrstatusComponent},
  {path:'pnr/:id', component:PnrstatusComponent},
  {path:'trainlist/:source/:destination/:doj', component:TrainlistComponent},
  {path:'book/:trainNo', component:BookingFormComponent},
  {path:'', component:HomeComponent},
  {path:'admin',component:AdminPanelComponent},
  {path:'addTrain',component:AddTrainComponent},
  {path:'updateTrain/:trainNo',component:UpdateTrainComponent},
  {path:'deleteTrain/:trainNo',component:DeleteTrainComponent},
  {path:'updateTrain',component:UpdateTrainComponent},
  {path:'deleteTrain',component:DeleteTrainComponent},
  {path:'bookHistory/:userId',component:BookHistoryComponent},
  {path:'wallet/:userId',component:WalletComponent},
  {path:'ticket/:pnr',component:PrintTicketComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
