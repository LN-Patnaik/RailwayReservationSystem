import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { PassengerModel } from '../models/passenger.model';
import { TicketModel } from '../models/ticket.model';
import jspdf from 'jspdf';
import html2canvas from 'html2canvas'

@Component({
  selector: 'app-print-ticket',
  templateUrl: './print-ticket.component.html',
  styleUrls: ['./print-ticket.component.css']
})
export class PrintTicketComponent implements OnInit {

  

pnrNum:any
ticketModel:TicketModel=new TicketModel()
passengers:PassengerModel[]=[]



  ngOnInit(): void {
    this.pnrNum = this.route.snapshot.paramMap.get('pnr');
    this.http.get(environment.apiUrl+"/reservation/pnr/ticket/"+this.pnrNum).subscribe((res:any)=>{
      this.ticketModel = res;
      this.passengers=res.passengerDetails
    })
  }
  constructor(private route:ActivatedRoute, private http:HttpClient) { }



  downloadTicket()
  {
    let element = <HTMLElement>document.getElementById('content');
    html2canvas(element).then((canvas)=>{
      let imgData = canvas.toDataURL('image/png')
      let doc = new jspdf()
      let imgHeight = canvas.height * 208 / canvas.width;
      doc.addImage(imgData,0,0,208,imgHeight)
      doc.save("RRS_Ticket.pdf")
    })
  }


}
