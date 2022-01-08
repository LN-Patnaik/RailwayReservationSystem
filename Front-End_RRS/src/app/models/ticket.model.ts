import { PassengerModel } from "./passenger.model";

export class TicketModel{
    constructor(
      public pnrNumber?:string,
      public trainNumber?:string,
      public trainName?:string,
      public source?:string,
      public destination?:string,
      public classType?:string,
      public passengerDetails: PassengerModel[]=[],
      public fare?:string,
      public status?:string,
      public phoneNumber?:string,
      public email?:string,
      public userId?:string,
    ){}
  }