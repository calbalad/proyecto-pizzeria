import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { User } from '../model/authService/user';
import { UserResponse } from '../model/authService/userResponse';
import { RestApiService } from '../services/api.service';
import { UserModule } from '../user';
import {MessageService} from 'primeng/api';

interface IMensajesError {
  severity: string;
  summary: string;
  detail: string;
}

@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  constructor(private http: HttpClient) {}
  upload(url: string, file: File): Observable<any> {
    const formData = new FormData();
    var auth_token = localStorage.getItem('access_token') || '';
    let headers = new HttpHeaders({
      Authorization: 'Bearer ' + auth_token,
    });
    let options = { headers: headers };
    formData.append('file', file, file.name);
    return this.http.post(url, formData, options);
  }
}

@Component({
  selector: 'app-user-edit',
  templateUrl: './tmpl-edit.component.html',
  styleUrls: ['./user.component.scss'],
  providers: [MessageService]
})
export class UserEditComponent implements OnInit {
  test: string = '';
  user: any = [];
  file: File | null = null;
  fileURL: string = '';
  httpOptions = {};
  blob: any = null;

  constructor(
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    public restApi: RestApiService,
    private fileUploadService: FileUploadService,
    private messageService: MessageService
  ) {
    this.user = JSON.parse(localStorage.getItem('data') || '{}');
    this.user = this.user.data;
    console.log(this.user);
  }

  ngOnInit(): void {
    this.getBinary();
  }

  onChange(event: any) {
    this.file = event.target.files[0];
    this.sendFormData();
  }

  sendFormData() {
    var user = JSON.parse(localStorage.getItem('data') || '[]');
    if (!this.file) return;
    console.log(this.file);
    this.fileUploadService
      .upload(
        'http://127.0.0.1:8080/api/v1/users/' +
          user.data.id +
          '/picture?action=u',
        this.file
      )
      .subscribe({
        next: (data: any) => {
          this.getBinary();
          this.messageService.add({key: 'c', sticky: true, severity:'success', summary:'Guardado con Éxito', detail:'Los datos se han actualizado'});
        },
      });
  }

  getBinary() {
    this.http
      .get('http://localhost:8080/api/v1/users/profile', {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('access_token'),
        },
        responseType: 'blob',
      })
      .subscribe({
        next: (data: any) => {
          console.log(data);
          this.blob = this.sanitizer.bypassSecurityTrustUrl(
            URL.createObjectURL(data)
          );
        },
      });
  }

  saveData(){
    this.restApi.updateUser(this.user).subscribe(
      ( data: {} ) => {
        localStorage.setItem('data', JSON.stringify(data))
        console.log(data);
        this.messageService.add({key: 'c', sticky: true, severity:'success', summary:'Guardado con Éxito', detail:'Los datos se han actualizado'});
      }
    )
  }

  onConfirm() {
    this.messageService.clear('c');
}

onReject() {
    this.messageService.clear('c');
}
}



@Component({
  selector: 'app-user-reset',
  templateUrl: './tmpl-reset.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserResetComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}


