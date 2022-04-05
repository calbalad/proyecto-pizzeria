import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { RestApiService } from '../services/api.service';


interface IMensajesError {
  severity: string;
  summary: string;
  detail: string;
}

@Component({
  selector: 'app-reset-pass',
  templateUrl: './reset-pass.component.html',
  styleUrls: ['./reset-pass.component.scss'],
  providers: [MessageService]
})
export class ResetPassComponent implements OnInit {
  signinForm: FormGroup;
  email: any;

  constructor(public restApi: RestApiService, public fb: FormBuilder, private messageService: MessageService) {
    this.signinForm = this.fb.group({ email: [''] });
  }

  ngOnInit(): void {

  }
  sendEmail() {
    this.restApi.resetPassword(this.signinForm.value.email).subscribe((data: {}) => {
    }, (error: {}) => {
      console.log(error)
    });
    this.messageService.add({severity:'warn', summary: 'Petición enviada', detail: 'Revisa tu correo'});
  }
  onConfirm() {
    this.messageService.clear('c');
  }

  onReject() {
    this.messageService.clear('c');
  }
}
