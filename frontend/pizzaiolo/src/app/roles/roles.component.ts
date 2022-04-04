import { Component, OnInit } from '@angular/core';
import { UserListResponse } from '../model/authService/userListResponse';
import { User } from '../model/authService/user';
import { IngredientesEditables } from '../model/pizzaiolo/ingredientesEditables';
import { IngredientesService } from '../services/ingredientes.service';
import { RolesService } from '../services/roles.service';
import { LoginService } from '../services/log.service';
import { UserResponse } from '../model/authService/models';

interface tipoIngrediente {
  name: string,
  code: string
}

interface ITipoRol {
  name: string
}

@Component({
  selector: 'app-ingredientes',
  templateUrl: './roles.component.html',

  styleUrls: ['./roles.component.scss'],
}
)

export class RolesComponent implements OnInit {

  RolDialog: boolean = false;
  usuarios: any = [];
  usuario: UserResponse = {};
  submitted: boolean = false;

  tiposRol:any[] = [];
  role : any;
  name:any;
  tipoRolSelected: any;

  constructor(public restApi: RolesService) {  }

  ngOnInit(): void {

    this.restApi.getUsuarios().subscribe(
        ( data: any ) => {
          this.usuarios = data.data

          console.log(this.usuarios)
        }
      );
      this.tiposRol = [
         'ROLE_USER' ,
         'ROLE_ADMIN' ,
         'ROLE_CHEF' ,
         'ROLE_DELIVERY' ,
        //  'ROLE_SUPER_ADMIN'
      ]
      this.openEdit(this.usuarios);
  }

  openEdit(usuario: UserResponse){
     this.usuario = {...usuario}
    const rol = usuario.data;
  }

  saveRol(id:string, rol:string){

    console.log()
    const RolToBD = {
      ...this.usuario,
      id: this.usuario.data?.id
    }

    this.restApi.updateUsuario(id,{"name":rol}).subscribe(
      ( data: any ) => {
        this.usuarios = data.data
      }
    );

  }

  hideDialog() {
    this.RolDialog = false;
    this.submitted = false;
  }

}
