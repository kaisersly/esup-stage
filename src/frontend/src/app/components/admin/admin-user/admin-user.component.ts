import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from "../../../services/user.service";
import { MatTabChangeEvent, MatTabGroup } from "@angular/material/tabs";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { RoleService } from "../../../services/role.service";
import { MessageService } from "../../../services/message.service";
import { TableComponent } from "../../table/table.component";
import { AuthService } from "../../../services/auth.service";
import { AppFonction } from "../../../constants/app-fonction";
import { Droit } from "../../../constants/droit";
import { Role } from "../../../constants/role";

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.scss']
})
export class AdminUserComponent implements OnInit {

  columns = ['login', 'nom', 'prenom', 'roles', 'actif', 'action'];
  sortColumn = 'login';
  filters = [
    { id: 'utilisateur', libelle: 'Utilisateur', specific: true },
    { id: 'roles', libelle: 'Rôle', type: 'list', options: [], keyLibelle: 'libelle', keyId: 'id', value: [], specific: true },
    { id: 'actif', libelle: 'Actif', type: 'boolean' },
  ];

  formTabIndex = 1;
  data: any;
  form: FormGroup;
  roles: any;
  roleMultipleSettings = {
    idField: 'code',
    textField: 'code',
  }

  @ViewChild(TableComponent) appTable: TableComponent | undefined;
  @ViewChild('tabs') tabs: MatTabGroup | undefined;

  constructor(
    public userService: UserService,
    private fb: FormBuilder,
    private roleService: RoleService,
    private messageService: MessageService,
    private authService: AuthService,
  ) {
    this.form = this.fb.group({
      login: [null, [Validators.required, Validators.maxLength(255)]],
      nom: [null, [Validators.maxLength(255)]],
      prenom: [null, [Validators.maxLength(255)]],
      roles: [null, [Validators.required]],
      actif: [null],
    });
    this.emptyData();
  }

  ngOnInit(): void {
    this.roleService.findAll().subscribe((response: any) => {
      this.roles = response.data;
      const filterRole = this.filters.find((f: any) => f.id === 'roles');
      if (filterRole) {
        filterRole.options = this.roles;
        filterRole.value = this.roles.filter((r: any) => [Role.ADM, Role.GES, Role.RESP_GES].indexOf(r.code) > -1).map((r: any) => r.id);
      }
    });
  }

  emptyData(): void {
    this.data = {
      login: null,
      nom: null,
      prenom: null,
      roles: null,
      actif: null,
    };
    this.setFormData();
  }

  setFormData(): void {
    const data = {...this.data};
    delete data.id;
    delete data.password;
    delete data.dateCreation;
    this.form.setValue(data);
  }

  tabChanged(event: MatTabChangeEvent): void {
    if (event.index !== this.formTabIndex) {
      this.emptyData();
      this.form.get('login')?.enable();
      this.form.get('actif')?.enable();
    }
  }

  edit(data: any): void {
    this.data = data;
    if (this.tabs) {
      this.tabs.selectedIndex = this.formTabIndex;
    }
    this.setFormData();
    this.form.get('login')?.disable();
    // Interdiction de se désactiver soi-même
    if (this.data.id === this.authService.userConnected.id) {
      this.form.get('actif')?.disable();
    }
  }

  compareRole(option: any, value: any): boolean {
    if (option && value) {
      return option.code === value.code;
    }
    return false;
  }

  save(): void {
    if (this.form.valid) {
      if (this.data.id) {
        this.userService.update(this.data.id, this.form.value).subscribe((response: any) => {
          this.data = response;
          this.setFormData();
          this.appTable?.update();
          this.messageService.setSuccess('Utilisateur modifé');
        })
      } else {
        // TODO create : en attente de la configuration du REST LDAP par établissement
      }
    }
  }

  canEdit(): boolean {
    return this.authService.checkRights({fonction: AppFonction.PARAM_GLOBAL, droits: [Droit.MODIFICATION]});
  }

}
